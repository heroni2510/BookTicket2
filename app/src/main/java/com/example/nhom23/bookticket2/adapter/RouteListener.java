package com.example.nhom23.bookticket2.adapter;

import com.example.nhom23.bookticket2.model.Route;

import java.util.ArrayList;

public interface RouteListener {
    void getAllProductSuccess(ArrayList<Route> routeList);
    void getProductFailure(Exception ex);
}
