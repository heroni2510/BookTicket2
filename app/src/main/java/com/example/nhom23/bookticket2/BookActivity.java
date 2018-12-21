package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom23.bookticket2.adapter.RouteAdapter;
import com.example.nhom23.bookticket2.adapter.RouteListener;
import com.example.nhom23.bookticket2.adapter.RouteService;
import com.example.nhom23.bookticket2.model.BusCompany;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;
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
    private Spinner spinnerPrice;
    private Spinner spinnerCompany;
    String src;
    String des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Intent intent = getIntent();
         src = intent.getStringExtra("SOURCE");
         des = intent.getStringExtra("DESTINATION");

        // set Spinner
        spinnerCompany = (Spinner) findViewById(R.id.spinnerCompany);
        spinnerPrice = (Spinner) findViewById(R.id.spinnerPrice);
        List<String> listPrice = new ArrayList<>();
        listPrice.add("Price");
        listPrice.add("Ascending");
        listPrice.add("Descending");
        final List<String> listCompany = new ArrayList<>();
        listCompany.add("Company(ALL)");
        FirebaseDatabase.getInstance().getReference().child("BusCompany").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BusCompany busCompany = ds.getValue(BusCompany.class);
                    listCompany.add(busCompany.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, listPrice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrice.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,listCompany);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompany.setAdapter(adapter1);


        spinnerCompany.setOnItemSelectedListener(new SpinnerEvent());
        spinnerPrice.setOnItemSelectedListener(new SpinnerEvent2());


        //set Recycler
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ConfigRecyclerView(recyclerView);
        routeService = new RouteService();
        getAllProduct(src,des);





    }
    private void getAllProduct(String src, String des) {

        routeService.getAllRouteInFirebase(src,des,new RouteListener()   {
            @Override
            public void getAllProductSuccess(ArrayList<Route> routeList) {
                if(routeList.isEmpty()){Toast.makeText(BookActivity.this,"This route is unavailable",Toast.LENGTH_SHORT).show();}
                routeAdapter = new RouteAdapter(routeList,BookActivity.this);
                recyclerView.setAdapter(routeAdapter);
                routeAdapter.notifyDataSetChanged();
            }

            @Override
            public void getProductFailure(Exception ex) {

            }
        });
    }
    private void getAllProductByCompany(String src, String des, String Company) {

        routeService.getAllRouteInFirebaseByCompany(src,des,Company,new RouteListener()   {
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
    private void getAllProductByPriceDescending(String src, String des) {

        routeService.getAllRouteInFirebaseByPriceDescending(src,des, new RouteListener()   {
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
    private void getAllProductByPriceAscending(String src, String des) {

        routeService.getAllRouteInFirebaseByPriceAscending(src,des, new RouteListener()   {
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
    public void SetRecyclerDefault(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ConfigRecyclerView(recyclerView);
        routeService = new RouteService();
        getAllProduct(src,des);
    }
    public class SpinnerEvent implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String cmp = spinnerCompany.getSelectedItem().toString();
            Intent intent1 = getIntent();
            src = intent1.getStringExtra("SOURCE");
            des = intent1.getStringExtra("DESTINATION");
            if(cmp.equals("Company(ALL)")==false){
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                ConfigRecyclerView(recyclerView);
                routeService = new RouteService();
                getAllProductByCompany(src,des,cmp);
            }else {SetRecyclerDefault();}
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    public class SpinnerEvent2 implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String cmp = spinnerPrice.getSelectedItem().toString();
            Intent intent1 = getIntent();
            src = intent1.getStringExtra("SOURCE");
            des = intent1.getStringExtra("DESTINATION");
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            ConfigRecyclerView(recyclerView);
            routeService = new RouteService();
            if(cmp.equals("Descending")==true){
                getAllProductByPriceDescending(src,des);
            }else if(cmp.equals("Ascending")==true){getAllProductByPriceAscending(src,des);}
            else if(cmp.equals("Price")){getAllProduct(src,des);}
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
