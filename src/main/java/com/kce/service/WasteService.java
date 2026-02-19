package com.kce.service;

import com.kce.util.ActiveComplaintsExistException;
import com.kce.util.ComplaintStatusException;
import com.kce.util.ValidationException;
import java.util.List;
import java.sql.Date;
import com.kce.bean.Citizen;
import com.kce.bean.WasteServiceRow;
import com.kce.dao.*;

public class WasteService {
    private CitizenDAO citizenDAO = new CitizenDAO();
    private WasteServiceDAO wasteserviceDAO = new WasteServiceDAO();

    public Citizen viewCitizenDetails(String citizenID) throws ValidationException {
        if (citizenID == null || citizenID.trim().isEmpty()) {
            throw new ValidationException();
        }
        return citizenDAO.findCitizen(citizenID);
    }

    public List<Citizen> viewAllCitizens() {
        return citizenDAO.viewAllCitizens();
    }

    public boolean registerNewCitizen(Citizen citizen) throws ValidationException {
        if (citizen.getCitizenID() == null || citizen.getCitizenID().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getFullName() == null || citizen.getFullName().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getHouseOrBuildingNo() == null || citizen.getHouseOrBuildingNo().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getStreetName() == null || citizen.getStreetName().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getWardCode() == null || citizen.getWardCode().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getRouteCode() == null || citizen.getRouteCode().trim().isEmpty())
            throw new ValidationException();
        if (citizen.getMobile() == null || !citizen.getMobile().matches("\\d{10,15}"))
            throw new ValidationException();

        // Check if citizen already exists
        if (citizenDAO.findCitizen(citizen.getCitizenID()) != null)
            return false;

        if (citizen.getStatus() == null || citizen.getStatus().trim().isEmpty())
            citizen.setStatus("ACTIVE");

        return citizenDAO.insertCitizen(citizen);
    }

    public boolean logScheduledVisit(String wardCode, String routeCode, Date scheduledDate, String vehicleNo, String crewShift) throws ValidationException {
        if (wardCode == null || wardCode.trim().isEmpty() || routeCode == null || routeCode.trim().isEmpty() ||
            vehicleNo == null || vehicleNo.trim().isEmpty() || crewShift == null || crewShift.trim().isEmpty() || scheduledDate == null) {
            throw new ValidationException();
        }

        WasteServiceRow row = new WasteServiceRow();
        row.setServiceType("SCHEDULED_VISIT");
        row.setCitizenID(null);
        row.setWardCode(wardCode);
        row.setRouteCode(routeCode);
        row.setScheduledDate(scheduledDate);
        row.setVehicleNo(vehicleNo);
        row.setCrewShift(crewShift);

        // generateServiceRowID thevai illai, Bean-la @GeneratedValue automatic-aa id tharum
        return wasteserviceDAO.insertServiceRow(row);
    }

    public boolean registerComplaint(String citizenID, String complaintType, String complaintDescription, 
                                     String priorityLevel, Date complaintDate, Date relatedScheduledDate) throws ValidationException {
        
        if (citizenID == null || complaintType == null || complaintDescription == null || 
            priorityLevel == null || complaintDate == null) {
            throw new ValidationException();
        }

        Citizen citizen = citizenDAO.findCitizen(citizenID);
        if (citizen == null || !"ACTIVE".equalsIgnoreCase(citizen.getStatus())) {
            return false;
        }

        if (relatedScheduledDate != null && complaintDate.before(relatedScheduledDate)) {
            throw new ValidationException();
        }

        WasteServiceRow row = new WasteServiceRow();
        row.setServiceType("COMPLAINT");
        row.setCitizenID(citizenID);
        row.setWardCode(citizen.getWardCode());
        row.setRouteCode(citizen.getRouteCode());
        row.setComplaintDate(complaintDate);
        row.setComplaintType(complaintType);
        row.setComplaintDescription(complaintDescription);
        row.setPriorityLevel(priorityLevel);
        row.setComplaintStatus("OPEN");
        row.setClosureRemarks(null);

        return wasteserviceDAO.insertServiceRow(row);
    }

    public boolean updateComplaintStatus(int serviceRowID, String newStatus, String closureRemarks) 
                                         throws ValidationException, ComplaintStatusException {
        
        if (serviceRowID < 0) throw new ValidationException();
        if (!newStatus.matches("OPEN|IN_PROGRESS|RESOLVED|CLOSED")) throw new ValidationException();

        WasteServiceRow wasteServiceRow = wasteserviceDAO.findServiceRow(serviceRowID);
        if (wasteServiceRow == null || !"COMPLAINT".equals(wasteServiceRow.getServiceType())) {
            throw new ComplaintStatusException();
        }

        if ("CLOSED".equalsIgnoreCase(wasteServiceRow.getComplaintStatus())) {
            throw new ComplaintStatusException();
        }

        return wasteserviceDAO.updateComplaintStatusAndClosure(serviceRowID, newStatus, closureRemarks);
    }

    public List<WasteServiceRow> listComplaintsByCitizen(String citizenID) {
        return wasteserviceDAO.findComplaintsByCitizen(citizenID);
    }

    public boolean deactivateOrRemoveCitizen(String citizenID) throws ValidationException, ActiveComplaintsExistException {
        if (citizenID == null || citizenID.trim().isEmpty()) {
            throw new ValidationException();
        }

        List<WasteServiceRow> openComplaints = wasteserviceDAO.findOpenComplaintsByCitizen(citizenID);
        if (openComplaints != null && !openComplaints.isEmpty()) {
            throw new ActiveComplaintsExistException();
        }

        return citizenDAO.updateCitizenStatus(citizenID, "INACTIVE");
    }
}