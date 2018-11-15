package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nhom23.bookticket2.adapter.RouteAdapter;
import com.example.nhom23.bookticket2.adapter.RouteListener;
import com.example.nhom23.bookticket2.adapter.RouteService;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //@BindView(R.id.recyclerView)
    private RouteService routeService;
    private RouteAdapter routeAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Intent intent = getIntent();
        String src = intent.getStringExtra("SOURCE");
        String des = intent.getStringExtra("DESTINATION");
        //ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ConfigRecyclerView(recyclerView);
        routeService = new RouteService();
        getAllProduct(src,des);





    }
    private void getAllProduct(String src, String des) {

        routeService.getAllRouteInFirebase(src,des,new RouteListener()   {
            @Override
            public void getAllProductSuccess(ArrayList<Route> routeList) {
                routeAdapter = new RouteAdapter(routeList,BookActivity.this);
                recyclerView.setAdapter(routeAdapter);
                routeAdapter.notifyDataSetChanged();
            }

            @Override
            public void getProductFailure(Exception ex) {

            }
        });
    }

    public void ConfigRecyclerView(RecyclerView view) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        view.setHasFixedSize(true);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setLayoutManager(manager);
    }

}
