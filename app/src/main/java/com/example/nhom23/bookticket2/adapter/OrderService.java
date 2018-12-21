package com.example.nhom23.bookticket2.adapter;

import android.widget.Toast;

import com.example.nhom23.bookticket2.BookConfirm;
import com.example.nhom23.bookticket2.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderService {
    public OrderService() {
    }
    public void getAllOrderInFirebase (final String user_id, final OrderListener listener ) {
        final ArrayList<Order> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Order").orderByChild("user").equalTo(user_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Order order = ds.getValue(Order.class);
                            list.add(order);
                        }
                        listener.getAllOrderSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getOrderFailure(databaseError.toException());
                    }
                });

    }
}
