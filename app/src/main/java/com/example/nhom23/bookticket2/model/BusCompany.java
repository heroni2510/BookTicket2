package com.example.nhom23.bookticket2.model;

public class BusCompany {
    private String ID_BC;
    private String name;
    private String phone;
    private String address;

    public BusCompany(){}
    public BusCompany(String ID_BC, String name, String phone, String address){
        this.ID_BC=ID_BC;
        this.name=name;
        this.phone=phone;
        this.address=address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_BC() {
        return ID_BC;
    }

    public void setID_BC(String ID_BC) {
        this.ID_BC = ID_BC;
    }
}
