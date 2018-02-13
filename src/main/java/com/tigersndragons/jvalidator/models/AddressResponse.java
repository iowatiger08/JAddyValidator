package com.tigersndragons.jvalidator.models;

public class AddressResponse {
    private Long id;
    private String P_Firm;
    private String P_Address;
    private String P_City;
    private String P_ErrorCode;
    private String P_ErrorMsg;
    private double P_Latitude;
    private double P_Longitude;
    private String P_Timezone;
    private String P_State;
    private String P_Country;
    private String P_Zip;
    private String P_Zip4;
    
    public AddressResponse(){
        
    }

    public AddressResponse(Long id, String p_Address, String p_City, String p_ErrorCode,
            String p_ErrorMsg, double p_Latitude, double p_Longitude, String p_Timezone, String p_State,
            String p_Country, String p_Zip, String p_Zip4) {
        this.id = id;
        P_Address = p_Address;
        P_City = p_City;
        P_ErrorCode = p_ErrorCode;
        P_ErrorMsg = p_ErrorMsg;
        P_Latitude = p_Latitude;
        P_Longitude = p_Longitude;
        P_Timezone = p_Timezone;
        P_State = p_State;
        P_Country = p_Country;
        P_Zip = p_Zip;
        P_Zip4 = p_Zip4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getP_Firm() {
        return P_Firm;
    }

    public void setP_Firm(String p_Firm) {
        P_Firm = p_Firm;
    }

    public String getP_Address() {
        return P_Address;
    }

    public void setP_Address(String p_Address) {
        P_Address = p_Address;
    }

    public String getP_City() {
        return P_City;
    }

    public void setP_City(String p_City) {
        P_City = p_City;
    }

    public String getP_ErrorCode() {
        return P_ErrorCode;
    }

    public void setP_ErrorCode(String p_ErrorCode) {
        P_ErrorCode = p_ErrorCode;
    }

    public String getP_ErrorMsg() {
        return P_ErrorMsg;
    }

    public void setP_ErrorMsg(String p_ErrorMsg) {
        P_ErrorMsg = p_ErrorMsg;
    }

    public double getP_Latitude() {
        return P_Latitude;
    }

    public void setP_Latitude(double p_Latitude) {
        P_Latitude = p_Latitude;
    }

    public double getP_Longitude() {
        return P_Longitude;
    }

    public void setP_Longitude(double p_Longitude) {
        P_Longitude = p_Longitude;
    }

    public String getP_Timezone() {
        return P_Timezone;
    }

    public void setP_Timezone(String p_Timezone) {
        P_Timezone = p_Timezone;
    }

    public String getP_State() {
        return P_State;
    }

    public void setP_State(String p_State) {
        P_State = p_State;
    }

    public String getP_Country() {
        return P_Country;
    }

    public void setP_Country(String p_Country) {
        P_Country = p_Country;
    }

    public String getP_Zip() {
        return P_Zip;
    }

    public void setP_Zip(String p_Zip) {
        P_Zip = p_Zip;
    }

    public String getP_Zip4() {
        return P_Zip4;
    }

    public void setP_Zip4(String p_Zip4) {
        P_Zip4 = p_Zip4;
    }

}
