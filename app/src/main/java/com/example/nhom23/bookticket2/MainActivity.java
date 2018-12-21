package com.example.nhom23.bookticket2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nhom23.bookticket2.model.Route;
import com.example.nhom23.bookticket2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
DatabaseReference mData, mBusCom;
FirebaseUser user1;
Menu menu;
    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        user1 = FirebaseAuth.getInstance().getCurrentUser();
        setSupportActionBar(toolbar);
        //User user = new User();


        /*long when = System.currentTimeMillis() + 10000L;
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("myAction", "mDoNotify");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, when, pendingIntent);*/
       //        String KeyID = FirebaseDatabase.getInstance().getReference().push().getKey();
       // BusCompany busCompany = new BusCompany(KeyID,"Phuong Trang","19006067","Ho Chi Minh");
       // FirebaseDatabase.getInstance().getReference().child("BusCompany").child(KeyID).setValue(busCompany);
       // Route route = new Route("Ho Chi Minh","Dong Thap","-LQJrj80dcM_G1SjDE_p","95000","18:30",KeyID,"21:30","Phuong Trang");
       //FirebaseDatabase.getInstance().getReference().child("Route").push().setValue(route);
//        FirebaseDatabase.getInstance().getReference().child("BusCompany").child("-LQJolpDQmQl81Nvu4nB").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                BusCompany a = dataSnapshot.getValue(BusCompany.class);
//               // Toast.makeText(MainActivity.this,a.getName(),Toast.LENGTH_SHORT).show();
//                Bus bus = new Bus("001","56H1511","-LQJolpDQmQl81Nvu4nB");
//                FirebaseDatabase.getInstance().getReference().child("Bus").push().setValue(bus);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        Fragment fragment = SelectRouteFragment.newInstance();
        //getSupportActionBar().setTitle(0);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setTitle("Home");
        fragmentTransaction.replace(R.id.frame , fragment);
        fragmentTransaction.commit();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(user1 != null) {
            navigationView.getMenu().findItem(R.id.nav_share).setEnabled(false);
        } else {
            navigationView.getMenu().findItem(R.id.nav_send).setEnabled(false);
        }
        invalidateOptionsMenu();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu)
//    {
//        super.onPrepareOptionsMenu(menu);
//        user1 = FirebaseAuth.getInstance().getCurrentUser();
//        MenuItem login = (MenuItem) menu.findItem(R.id.nav_share);
//        if(user1 != null)
//        {
//            login.setEnabled(false);
//        }
//        else
//        {
//            login.setEnabled(true);
//        }
//        return true;
//    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        Fragment fragment1;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (id == R.id.nav_camera) {
            fragment = SelectRouteFragment.newInstance();
            getSupportActionBar().setTitle("Home");
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            // Handle the camera action


        } else if (id == R.id.nav_slideshow) {
            if(user != null){
            fragment = HistoryFragment.newInstance();
            getSupportActionBar().setTitle("History");
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();}else{Toast.makeText(this, "You haven't logged in", Toast.LENGTH_SHORT).show();}
        } else if (id == R.id.nav_manage) {
            if(user != null){
            fragment1 = ProfileFragment.newInstance();
            getSupportActionBar().setTitle("Profile");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();}else{Toast.makeText(this, "You haven't logged in", Toast.LENGTH_SHORT).show();}
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this,LoginActivity.class);
            NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
            if(user != null){
                item.setEnabled(false);
            }
            this.startActivity(intent);
            navigationView1.getMenu().findItem(R.id.nav_send).setEnabled(true);
        } else if (id == R.id.nav_send) {
            mFirebaseAuth.signOut();
            Toast.makeText(MainActivity.this,"Log out success",Toast.LENGTH_SHORT).show();
            item.setEnabled(false);
            NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
            navigationView1.getMenu().findItem(R.id.nav_share).setEnabled(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
