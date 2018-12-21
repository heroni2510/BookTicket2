package com.example.nhom23.bookticket2.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Order {
    private String id_O;
    private String user;
    private String route;
    private int numofseat;
    private String date;
    private String total;
    private String CompanyName;

    public Order(){};
    public Order(String id_O, String user, String route, int numofseat, String date, String total){
        this.id_O = id_O;
        this.user = user;
        this.route= route;

        this.numofseat = numofseat;
        this.date = date;
        this.total = total;
    }
    public String getCompanyName(){

//        FirebaseDatabase.getInstance().getReference().child("Route").child(route)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Route route1 = dataSnapshot.getValue(Route.class);
//                        FirebaseDatabase.getInstance().getReference().child("Bus").child(route1.getBus()).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                Bus bus = dataSnapshot.getValue(Bus.class);
//                                FirebaseDatabase.getInstance().getReference().child("BusCompany").child(bus.getBusCompany()).addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        BusCompany busCompany = dataSnapshot.getValue(BusCompany.class);
//                                        //routeHolder.tvCompany.setText("Company: " + busCompany.getName());
//                                        CompanyName = busCompany.getName();
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
        return CompanyName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

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


    public String getUser() {
        return user;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
