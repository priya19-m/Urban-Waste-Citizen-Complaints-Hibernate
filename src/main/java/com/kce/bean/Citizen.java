package com.kce.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Citizen_TBL")
public class Citizen {

    @Id
    @Column(name = "citizen_id")
    private String citizenID;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "house_building_no")
    private String houseOrBuildingNo;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "area_locality")
    private String areaOrLocality;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "route_code")
    private String routeCode;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status = "ACTIVE"; // Unga friend code maari default set panniruken

    // Getters and Setters (Same as before)
    public String getCitizenID() { return citizenID; }
    public void setCitizenID(String citizenID) { this.citizenID = citizenID; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getHouseOrBuildingNo() { return houseOrBuildingNo; }
    public void setHouseOrBuildingNo(String houseOrBuildingNo) { this.houseOrBuildingNo = houseOrBuildingNo; }
    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public String getAreaOrLocality() { return areaOrLocality; }
    public void setAreaOrLocality(String areaOrLocality) { this.areaOrLocality = areaOrLocality; }
    public String getWardCode() { return wardCode; }
    public void setWardCode(String wardCode) { this.wardCode = wardCode; }
    public String getRouteCode() { return routeCode; }
    public void setRouteCode(String routeCode) { this.routeCode = routeCode; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}