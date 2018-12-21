package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom23.bookticket2.adapter.CmtAdapter;
import com.example.nhom23.bookticket2.adapter.CmtListener;
import com.example.nhom23.bookticket2.adapter.CmtService;
import com.example.nhom23.bookticket2.adapter.RouteAdapter;
import com.example.nhom23.bookticket2.model.Bus;
import com.example.nhom23.bookticket2.model.BusCompany;
import com.example.nhom23.bookticket2.model.CommentRating;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryDetailActivity extends AppCompatActivity {
    TextView tvSrc;
    TextView tvDest;
    TextView tvArr;
    TextView tvDept;
    TextView tvCpn;
    TextView  tvTotal;
    TextView tvPrice;
    TextView tvDate;
    TextView tvSeat;
    Button btnSend;
    Button btnCancel;
    Date date1;
    RatingBar ratingBar;
    EditText editText;
    RecyclerView recyclerView;
    CmtListener cmtListener;
    CmtService cmtService;
    CmtAdapter cmtAdapter;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    String company_id;
    String bus_id;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        btnSend = (Button) findViewById(R.id.cmtBtn);

        recyclerView = (RecyclerView) findViewById(R.id.listCmt);

        tvTotal = (TextView) findViewById(R.id.total_history);
        tvPrice = (TextView) findViewById(R.id.price_history);
        tvDate  = (TextView) findViewById(R.id.date_history);
        tvSrc   = (TextView) findViewById(R.id.source_history);
        tvDest  = (TextView) findViewById(R.id.dest_history);
        tvDept  = (TextView) findViewById(R.id.departure_history);
        tvArr   = (TextView) findViewById(R.id.arrival_history);
        tvCpn   = (TextView) findViewById(R.id.company_history);
        tvSeat = (TextView) findViewById(R.id.seat_history2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        editText = (EditText) findViewById(R.id.input_cmt);

        final Intent intent = getIntent();
        final String route_id = intent.getStringExtra("ROUTE_ID");
        String date = intent.getStringExtra("DATE");
        String total = intent.getStringExtra("TOTAL");
        String seats = intent.getStringExtra("SEATS");
        String order_id = intent.getStringExtra("ORDER_ID");
        tvTotal.setText(total);
        tvSeat.setText(seats);
        tvDate.setText(date);


        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("id_R").equalTo(route_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Route route = ds.getValue(Route.class);
                            tvArr.setText(route.getArrival());
                            tvDest.setText(route.getDest());
                            tvSrc.setText(route.getSour());
                            tvPrice.setText(route.getPrice());
                            tvCpn.setText(route.getCompanyName());
//                            FirebaseDatabase.getInstance().getReference().child("Bus").orderByChild("id_B").equalTo(route.getBus())
//                                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            //Parse dataSnapshot
//                                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
//                                                Bus bus = ds1.getValue(Bus.class);
//                                                    company_id = bus.getBusCompany();
//                                                    ConfigRecyclerView(recyclerView);
//                                                    cmtService = new CmtService();
//                                                    getAllComment(company_id);
//                                            }
//                                        }
//                                        @Override
//                                        public void onCancelled(DatabaseError databaseError) {
//                                        }
//                                    });

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HistoryDetailActivity.this,"Comment or rating is empty",Toast.LENGTH_SHORT).show();
                if(ratingBar.getRating() == 0 || editText.getText().equals("")){
                    Toast.makeText(HistoryDetailActivity.this,"Comment or rating is empty",Toast.LENGTH_SHORT).show();
                } else{
                    final String KeyID = FirebaseDatabase.getInstance().getReference().push().getKey();
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    final String user_id = user.getUid();
                    final String content = editText.getText().toString();
                    int rate = (int) ratingBar.getRating();
                    final String ratting = String.valueOf(rate);
                    final String rid = intent.getStringExtra("ROUTE_ID");
                    FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("id_R").equalTo(rid)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        final Route route4 = ds.getValue(Route.class);
                                        FirebaseDatabase.getInstance().getReference().child("BusCompany").orderByChild("name").equalTo(route4.getCompanyName())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        //Parse dataSnapshot
                                                        for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                                            final BusCompany bus = ds1.getValue(BusCompany.class);
                                                            company_id = bus.getID_BC();
                                                            date1=new Date();
                                                            String strDateFormat = "dd/MM/yyyy";
                                                            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                                                            CommentRating commentRating = new CommentRating(KeyID, user_id, company_id, content, ratting,sdf.format(date1));
                                                            FirebaseDatabase.getInstance().getReference().child("CommentRating").push().setValue(commentRating);
                                                            ConfigRecyclerView(recyclerView);
                                                            cmtService = new CmtService();
                                                            getAllComment(company_id);
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                    }
                                                });

                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                    Toast.makeText(HistoryDetailActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
            }
        });
        //set list comment
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("id_R").equalTo(route_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            final Route route5 = ds.getValue(Route.class);
                            FirebaseDatabase.getInstance().getReference().child("BusCompany").orderByChild("name").equalTo(route5.getCompanyName())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                                BusCompany bus = ds1.getValue(BusCompany.class);
                                                //Toast.makeText(HistoryDetailActivity.this,bus.getName(),Toast.LENGTH_SHORT).show();
                                                company_id = bus.getID_BC();
                                                ConfigRecyclerView(recyclerView);
                                                cmtService = new CmtService();
                                                getAllComment(company_id);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                        }
                                    });

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
    public void ConfigRecyclerView(RecyclerView view) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        view.setHasFixedSize(true);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setLayoutManager(manager);
    }
    private void getAllComment(String company_id) {

        cmtService.getAllCommentInFirebase(company_id,new CmtListener()   {
            @Override
            public void getAllCommentSuccess(ArrayList<CommentRating> cmtList) {
                cmtAdapter = new CmtAdapter(cmtList,HistoryDetailActivity.this);
                recyclerView.setAdapter(cmtAdapter);
                cmtAdapter.notifyDataSetChanged();
            }

            @Override
            public void getCommentFailure(Exception ex) {

            }
        });
    }

}
