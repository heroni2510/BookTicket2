package com.example.nhom23.bookticket2.model;

public class Bus {
    private  String name;
    private  String numberPlate;
    private  String id_B;
    private  String busCompany;
    public Bus(){}
    public Bus(String name, String numberPlate, String busCompany){
        this.name=name;
        this.numberPlate=numberPlate;
        //this.seats=seats;
        this.busCompany=busCompany;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

//    public Integer getSeats() {
//        return seats;
//    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getId_B() {
        return id_B;
    }

    public void setId_B(String id_B) {
        this.id_B = id_B;
    }
//    public void setSeats(Integer seats) {
//        this.seats = seats;
//    }
}
