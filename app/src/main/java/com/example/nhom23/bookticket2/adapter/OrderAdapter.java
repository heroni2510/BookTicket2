package com.example.nhom23.bookticket2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom23.bookticket2.BookConfirm;
import com.example.nhom23.bookticket2.HistoryDetailActivity;
import com.example.nhom23.bookticket2.R;
import com.example.nhom23.bookticket2.model.Order;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    private Fragment activity;
    private ArrayList<Order> listOrder;
    String CompanyName;
    public OrderAdapter(ArrayList<Order> listOrder, Fragment activity) {
        this.listOrder = listOrder;
        this.activity = activity;
    }
    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item,viewGroup,false);
        return new OrderHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final OrderAdapter.OrderHolder orderHolder, int position) {
        final Order order = listOrder.get(position);
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("id_R").equalTo(order.getRoute())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            orderHolder.tvTo.setText("To: " + route.getDest());
                            orderHolder.tvFrom.setText("From: " + route.getSour());
                            orderHolder.tvCompany.setText("Company: " + route.getCompanyName());
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



        orderHolder.tvPrice.setText(order.getTotal());
        orderHolder.tvDate.setText("Date: "+order.getDate());
        orderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getContext(),HistoryDetailActivity.class);
                intent.putExtra("ROUTE_ID",order.getRoute());
                intent.putExtra("ORDER_ID",order.getId_O());
                intent.putExtra("DATE",order.getDate());
                intent.putExtra("TOTAL",order.getTotal());
                intent.putExtra("SEATS", String.valueOf(order.getNumofseat()));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }
    class OrderHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvFrom;
        TextView tvTo;
        TextView tvCompany;
        TextView tvPrice;
        ImageView img;

        public OrderHolder(View itemView) {
            super(itemView);
            tvFrom = (TextView) itemView.findViewById(R.id.from);
            tvTo = (TextView) itemView.findViewById(R.id.to);
            tvDate = (TextView) itemView.findViewById(R.id.date_order);
            tvCompany = (TextView) itemView.findViewById(R.id.company_order);
            //img = (ImageView) itemView.findViewById(R.id.numberImageView);
            tvPrice = (TextView) itemView.findViewById(R.id.total_order);
        }
    }

}
