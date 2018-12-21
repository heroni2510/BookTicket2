package com.example.nhom23.bookticket2.adapter;

import com.example.nhom23.bookticket2.model.Order;

import java.util.ArrayList;

public interface OrderListener {
    void getAllOrderSuccess(ArrayList<Order> orderList);
    void getOrderFailure(Exception ex);
}
