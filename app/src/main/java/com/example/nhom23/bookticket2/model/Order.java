package com.example.nhom23.bookticket2.model;

public class Order {
    private String id_O;
    private String user;
    private String route;
    private String numberOrder;
    private int numofseat;
    private String date;
    private String Total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumofseat() {
        return numofseat;
    }

    public void setNumofseat(int numofseat) {
        this.numofseat = numofseat;
    }

    public String getId_O() {
        return id_O;
    }

    public void setId_O(String id_O) {
        this.id_O = id_O;
    }

    public String getRoute() {
        return route;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public String getUser() {
        return user;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
