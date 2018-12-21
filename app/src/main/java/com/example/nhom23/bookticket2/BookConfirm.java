package com.example.nhom23.bookticket2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom23.bookticket2.adapter.CmtAdapter;
import com.example.nhom23.bookticket2.adapter.CmtListener;
import com.example.nhom23.bookticket2.adapter.CmtService;
import com.example.nhom23.bookticket2.model.BusCompany;
import com.example.nhom23.bookticket2.model.CommentRating;
import com.example.nhom23.bookticket2.model.Order;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookConfirm extends AppCompatActivity {
    EditText etSeat;
    TextView tvSrc;
    TextView tvDest;
    TextView tvArr;
    TextView tvDept;
    TextView tvCpn;
    TextView  tvTotal;
    TextView tvPrice;
    TextView tvDate;
    Button btnOrder;
    Button btnCancel;
    Date date;
    RecyclerView recyclerView;
    RatingBar ratingBar;
    CmtService cmtService;
    CmtListener cmtListener;
    CmtAdapter cmtAdapter;
    Calendar now;
    SimpleDateFormat sdf;
    private FirebaseUser user;
    private String idR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_confirm);

        tvTotal = (TextView) findViewById(R.id.total);
        etSeat  = (EditText) findViewById(R.id.editTextSeats);
        tvPrice = (TextView) findViewById(R.id.price_view);
        tvDate  = (TextView) findViewById(R.id.date);
        tvSrc   = (TextView) findViewById(R.id.source_view);
        tvDest  = (TextView) findViewById(R.id.dest_view);
        tvDept  = (TextView) findViewById(R.id.departure_view);
        tvArr   = (TextView) findViewById(R.id.arrival_view);
        tvCpn   = (TextView) findViewById(R.id.company_view);
        //btnCancel = (Button) findViewById(R.id.cancel_button);
        btnOrder = (Button) findViewById(R.id.order_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_cmt);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        long millis=System.currentTimeMillis();
        date=new Date();
        String strDateFormat = "dd/MM/yyyy hh:mm:ss a";
        now = Calendar.getInstance();
        sdf = new SimpleDateFormat(strDateFormat);
        sdf.format(now.getTime());
        tvDate.setText(sdf.format(now.getTime()));

        final Intent intent = getIntent();
        String src   = intent.getStringExtra("SOURCE");
        String price = intent.getStringExtra("PRICE");
        String des   = intent.getStringExtra("DEST");
        String dep   = intent.getStringExtra("DEPARTURE");
        String arr   = intent.getStringExtra("ARRIVAL");
        String cpn   = intent.getStringExtra("COMPANY_NAME");
        idR   = intent.getStringExtra("ROUTE_ID");
        tvSrc.setText(src);
        tvPrice.setText(price);
        tvDest.setText(des);
        tvDept.setText(dep);
        tvArr.setText(arr);
        tvCpn.setText(cpn);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(BookConfirm.this,BookActivity.class);
//                startActivity(intent1);
//            }
//        });
        //set Rating bar
        FirebaseDatabase.getInstance().getReference().child("BusCompany").orderByChild("name").equalTo(cpn)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                            BusCompany bus = ds1.getValue(BusCompany.class);
                           //oast.makeText(,bus.getName(),Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference().child("CommentRating").orderByChild("company_id").equalTo(bus.getID_BC())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            float rt = 0;
                                            float i = 0;
                                            for(DataSnapshot ds2 : dataSnapshot.getChildren()){
                                                CommentRating commentRating = ds2.getValue(CommentRating.class);
                                                rt+=Float.parseFloat(commentRating.getRatingScore());
                                                i++;
                                            }
                                            ratingBar.setRating(rt/i);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    if(etSeat.getText().equals("")){Toast.makeText(BookConfirm.this,"Please input the number",Toast.LENGTH_SHORT).show();}else{
                    Integer seats = Integer.parseInt(etSeat.getText().toString());
                    String KeyID = FirebaseDatabase.getInstance().getReference().push().getKey();
                    Order order = new Order(KeyID,user.getUid(),idR,seats,sdf.format(now.getTime()),tvTotal.getText().toString());
                    FirebaseDatabase.getInstance().getReference().child("Order").push().setValue(order);
                    Toast.makeText(BookConfirm.this,"Book Success",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(BookConfirm.this,MainActivity.class);
                    startActivity(intent3);


                    ///Bus Reminder
                        String input = tvDest.getText().toString();
                        Calendar cal = Calendar.getInstance();
                        Date date = new Date();
                        // Changed the format to represent time of day
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
                        try {
                            date = sdf.parse(input);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        cal.setTime(date);
                        //We haven't parsed the seconds from the original date so this will result
                        //in 18:02:00 - 10seconds.
                        //For a correct calculation, you could parse the seconds as well
                        //See SimpleDateFormat above, but you would have to provide the original date
                        //with seconds as well
                        cal.add(Calendar.MINUTE, -30);
                        long when = System.currentTimeMillis() + 10000L;


                        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        Intent intent5 = new Intent(BookConfirm.this, MyReceiver.class);
                        intent.putExtra("myAction", "mDoNotify");
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(BookConfirm.this, 0, intent5, 0);
                        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                    }
                }else {
                    Toast.makeText(BookConfirm.this,"You must be logged in before press button PAY",Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(BookConfirm.this,LoginActivity.class);
                    startActivity(intent2);
                }
            }
        });

        etSeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double seats;
                if(etSeat.getText().toString().equals("")==true){
                    tvTotal.setText("TOTAL: 0");
                }else {
                    seats = Double.parseDouble(s.toString());
                    String tvprice = tvPrice.getText().toString();
                    double price = Double.parseDouble(tvprice);
                    double kq = seats * price;
                    tvTotal.setText("TOTAL: " + (int) kq);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("Route").orderByChild("id_R").equalTo(idR)
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
                                                String company_id = bus.getID_BC();
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
                cmtAdapter = new CmtAdapter(cmtList,BookConfirm.this);
                recyclerView.setAdapter(cmtAdapter);
                cmtAdapter.notifyDataSetChanged();
            }

            @Override
            public void getCommentFailure(Exception ex) {

            }
        });
    }
}
