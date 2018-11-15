package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

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
    Date date;

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

        long millis=System.currentTimeMillis();
        date=new Date(millis);
        tvDate.setText(date.toString());

        Intent intent = getIntent();
        String src   = intent.getStringExtra("SOURCE");
        String price = intent.getStringExtra("PRICE");
        String des   = intent.getStringExtra("DEST");
        String dep   = intent.getStringExtra("DEPARTURE");
        String arr   = intent.getStringExtra("ARRIVAL");
        String cpn   = intent.getStringExtra("COMPANY");
        tvSrc.setText(src);
        tvPrice.setText(price);
        tvDest.setText(des);
        tvDept.setText(dep);
        tvArr.setText(arr);
        tvCpn.setText(cpn);


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
    }
}
