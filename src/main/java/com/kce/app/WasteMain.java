package com.kce.app;

import java.util.List;
import java.util.Scanner;
import com.kce.bean.Citizen;
import com.kce.bean.WasteServiceRow;
import com.kce.service.WasteService;
import com.kce.util.HibernateUtil;
import com.kce.util.ValidationException;
import com.kce.util.ActiveComplaintsExistException;
import com.kce.util.ComplaintStatusException;

public class WasteMain {

    public static void main(String[] args) {
        // Initialize Hibernate Session Factory
        HibernateUtil.getSessionFactory();

        Scanner sc = new Scanner(System.in);
        WasteService service = new WasteService();
        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("   Urban Waste Management System ");
            System.out.println("======================================");
            System.out.println("1. Register New Citizen");
            System.out.println("2. Log Scheduled Waste Visit");
            System.out.println("3. Register a Complaint");
            System.out.println("4. Update Complaint Status");
            System.out.println("5. View All Citizens");
            System.out.println("6. Deactivate Citizen");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number (1-7).");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        Citizen c = new Citizen();
                        System.out.print("Enter Citizen ID: ");
                        c.setCitizenID(sc.nextLine());
                        System.out.print("Enter Full Name: ");
                        c.setFullName(sc.nextLine());
                        System.out.print("Enter House No: ");
                        c.setHouseOrBuildingNo(sc.nextLine());
                        System.out.print("Enter Street: ");
                        c.setStreetName(sc.nextLine());
                        System.out.print("Enter Ward Code: ");
                        c.setWardCode(sc.nextLine());
                        System.out.print("Enter Route Code: ");
                        c.setRouteCode(sc.nextLine());
                        System.out.print("Enter Mobile: ");
                        c.setMobile(sc.nextLine());
                        System.out.print("Enter Email: ");
                        c.setEmail(sc.nextLine());

                        if (service.registerNewCitizen(c))
                            System.out.println("✅ Citizen Registered Successfully!");
                        else
                            System.out.println("❌ Registration Failed!");
                        break;

                    case 2:
                        System.out.print("Enter Ward Code: ");
                        String ward = sc.nextLine();
                        System.out.print("Enter Route Code: ");
                        String route = sc.nextLine();
                        System.out.print("Enter Vehicle No: ");
                        String vNo = sc.nextLine();
                        System.out.print("Enter Shift (MORNING/EVENING): ");
                        String shift = sc.nextLine();
                        
                        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                        
                        if (service.logScheduledVisit(ward, route, today, vNo, shift))
                            System.out.println("✅ Visit Logged Successfully!");
                        else
                            System.out.println("❌ Failed to Log Visit.");
                        break;

                    case 3:
                        System.out.print("Enter Citizen ID: ");
                        String cid = sc.nextLine();
                        System.out.print("Enter Complaint Type: ");
                        String type = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Priority (LOW/HIGH): ");
                        String priority = sc.nextLine();

                        java.sql.Date cDate = new java.sql.Date(System.currentTimeMillis());

                        if (service.registerComplaint(cid, type, desc, priority, cDate, null))
                            System.out.println("✅ Complaint Registered!");
                        else
                            System.out.println("❌ Registration Failed (Check Citizen ID/Status).");
                        break;

                    case 4:
                        System.out.print("Enter Service Row ID: ");
                        int rid = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter New Status (RESOLVED/CLOSED): ");
                        String status = sc.nextLine();
                        System.out.print("Enter Closure Remarks: ");
                        String remarks = sc.nextLine();

                        if (service.updateComplaintStatus(rid, status, remarks))
                            System.out.println("✅ Status Updated!");
                        else
                            System.out.println("❌ Update Failed.");
                        break;

                    case 5:
                        List<Citizen> list = service.viewAllCitizens();
                        System.out.println("\n--- CITIZEN DIRECTORY ---");
                        for (Citizen item : list) {
                            System.out.println(item.getCitizenID() + " | " + item.getFullName() + " | " + item.getWardCode() + " | " + item.getStatus());
                        }
                        break;

                    case 6:
                        System.out.print("Enter Citizen ID to deactivate: ");
                        String dcid = sc.nextLine();
                        if (service.deactivateOrRemoveCitizen(dcid))
                            System.out.println("✅ Citizen deactivated.");
                        else
                            System.out.println("❌ Deactivation failed.");
                        break;

                    case 7:
                        System.out.println("Shutting down system... Goodbye!");
                        HibernateUtil.shutdown();
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (ValidationException e) {
                System.out.println("⚠ Input Error: Please provide valid details.");
            } catch (ActiveComplaintsExistException e) {
                System.out.println("⚠ Cannot deactivate: Citizen has open complaints.");
            } catch (ComplaintStatusException e) {
                System.out.println("⚠ Error: Invalid complaint ID or already closed.");
            } catch (Exception e) {
                System.out.println("⚠ System Error: " + e.getMessage());
            }

        } while (choice != 7);

        sc.close();
    }
}