package com.example.nhom23.bookticket2.model;

public class Route {
    private String id_R;
    private String sour;
    private String dest;
    private String bus;
    private String price;
    private String departure;

    public Route(){}
    public Route(String sour, String dest, String Bus, String price, String departure, String id_R ){
        this.bus=Bus;
        this.sour=sour;
        this.dest=dest;
        this.price=price;
        this.departure=departure;
        this.id_R=id_R;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getDest() {
        return dest;
    }

    public String getSour() {
        return sour;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSour(String sour) {
        this.sour = sour;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
