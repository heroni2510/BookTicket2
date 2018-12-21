package com.example.nhom23.bookticket2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom23.bookticket2.BookActivity;
import com.example.nhom23.bookticket2.BookConfirm;
import com.example.nhom23.bookticket2.R;
import com.example.nhom23.bookticket2.model.Bus;
import com.example.nhom23.bookticket2.model.BusCompany;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteHolder> {
    private Activity activity;
    private ArrayList<Route> listRoute;
    String CompanyName;
    public RouteAdapter(ArrayList<Route> listRoute, Activity activity) {
        this.listRoute = listRoute;
        this.activity = activity;
    }
    @NonNull
    @Override
    public RouteAdapter.RouteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_row_item,viewGroup,false);
        return new RouteHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RouteAdapter.RouteHolder routeHolder, int position) {
        final Route route = listRoute.get(position);

        routeHolder.tvPrice.setText(route.getPrice());
        routeHolder.tvDest.setText("Destination: " + route.getDest());
        routeHolder.tvSource.setText("Source: " + route.getSour());
        routeHolder.tvDeparture.setText("Departure: "+ route.getDeparture());
        routeHolder.tvCompany.setText("Company:" + route.getCompanyName());
        routeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,BookConfirm.class);
                intent.putExtra("ROUTE_ID",route.getId_R());
                intent.putExtra("COMPANY_NAME",route.getCompanyName());
                intent.putExtra("SOURCE",route.getSour());
                intent.putExtra("DEST",route.getDest());
                intent.putExtra("ARRIVAL",route.getArrival());
                intent.putExtra("DEPARTURE",route.getDeparture());
                intent.putExtra("PRICE",route.getPrice());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoute.size();
    }
    class RouteHolder extends RecyclerView.ViewHolder {
        TextView tvSource;
        TextView tvDest;
        TextView tvDeparture;
        TextView tvCompany;
        TextView tvPrice;
        ImageView img;

       public RouteHolder(View itemView) {
            super(itemView);
            tvSource = (TextView) itemView.findViewById(R.id.source_view);
            tvDest = (TextView) itemView.findViewById(R.id.dest_view);
            tvDeparture = (TextView) itemView.findViewById(R.id.departure_view);
            tvCompany = (TextView) itemView.findViewById(R.id.company_view);
            //img = (ImageView) itemView.findViewById(R.id.numberImageView);
            tvPrice = (TextView) itemView.findViewById(R.id.price_view);
        }
    }

}
