package com.kce.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.sql.Date;

@Entity
@Table(name = "WASTE_SERVICE_TBL")
public class WasteServiceRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database automatically handles ID
    @Column(name = "service_row_id")
    private int serviceRowID;

    @Column(name = "citizen_id")
    private String citizenID;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "route_code")
    private String routeCode;

    @Column(name = "scheduled_date")
    private Date scheduledDate;

    @Column(name = "vehicle_no")
    private String vehicleNo;

    @Column(name = "crew_shift")
    private String crewShift;

    @Column(name = "complaint_date")
    private Date complaintDate;

    @Column(name = "complaint_type")
    private String complaintType;

    @Column(name = "complaint_description")
    private String complaintDescription;

    @Column(name = "priority_level")
    private String priorityLevel;

    @Column(name = "complaint_status")
    private String complaintStatus = "PENDING";

    @Column(name = "closure_remarks")
    private String closureRemarks;

	public int getServiceRowID() {
		return serviceRowID;
	}

	public void setServiceRowID(int serviceRowID) {
		this.serviceRowID = serviceRowID;
	}

	public String getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getCrewShift() {
		return crewShift;
	}

	public void setCrewShift(String crewShift) {
		this.crewShift = crewShift;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public String getClosureRemarks() {
		return closureRemarks;
	}

	public void setClosureRemarks(String closureRemarks) {
		this.closureRemarks = closureRemarks;
	}

    
}