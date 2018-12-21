package com.example.nhom23.bookticket2.model;

public class User {
    private String name;
    private String u_id;
    private String phone;
    private String address;
    private String email;

    User(){}
    public User(String name, String U_id, String phone, String address, String email){
        this.address=address;
        this.email=email;
        this.phone=phone;
        this.name=name;
        this.u_id=U_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }



    public String getPhone() {
        return phone;
    }

    public String getU_id() {
        return u_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setU_id(String u_id) {
        u_id = u_id;
    }
}
