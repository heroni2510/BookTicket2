package com.example.nhom23.bookticket2.adapter;

import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RouteService {
    private DatabaseReference mDatabase;

    public RouteService() {

    }

    public void getAllRouteInFirebase (final String src, String dest, final RouteListener listener ) {
        final ArrayList<Route> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("dest").equalTo(dest)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            if(route.getSour().equals(src))
                                list.add(route);
                        }
                        listener.getAllProductSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getProductFailure(databaseError.toException());
                    }
                });

    }
    public void getAllRouteInFirebaseByCompany (final String src, final String dest, String company, final RouteListener listener ) {
        final ArrayList<Route> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("companyName").equalTo(company)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            if(route.getSour().equals(src)&&route.getDest().equals(dest))
                                list.add(route);
                        }
                        listener.getAllProductSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getProductFailure(databaseError.toException());
                    }
                });

    }
    public void getAllRouteInFirebaseByPriceAscending (final String src, final String dest,  final RouteListener listener ) {
        final ArrayList<Route> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("Price")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            if(route.getSour().equals(src)&&route.getDest().equals(dest))
                                list.add(route);
                        }
                        listener.getAllProductSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getProductFailure(databaseError.toException());
                    }
                });

    }
    public void getAllRouteInFirebaseByPriceDescending (final String src, final String dest,  final RouteListener listener ) {
        final ArrayList<Route> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("Price")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            if(route.getSour().equals(src)&&route.getDest().equals(dest))
                                list.add(route);

                        }
                        Collections.reverse(list);
                        listener.getAllProductSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getProductFailure(databaseError.toException());
                    }
                });

    }
}
