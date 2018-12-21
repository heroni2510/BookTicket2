package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom23.bookticket2.model.BusCompany;
import com.example.nhom23.bookticket2.model.Route;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SelectRouteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Button seclectButton;
    private EditText editTextSource;
    private EditText editTextDest;
    private Spinner spinnerSource;
    private Spinner spinnerDest;

    private String Source;
    private String Dest;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public SelectRouteFragment() {

    }


    public static SelectRouteFragment newInstance() {
        return  new SelectRouteFragment();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_route, container, false);
/*        editTextDest = rootView.findViewById(R.id.editTextDest);
        editTextSource = rootView.findViewById(R.id.editTextSource);*/
        ///set spinner
        spinnerDest = rootView.findViewById(R.id.spinnerDes);
        spinnerSource = rootView.findViewById(R.id.spinnerSrc);
        final List<String> listDest = new ArrayList<>();
        final List<String> listSrc = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Route").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Route route = ds.getValue(Route.class);
                    listSrc.add(route.getSour());
                    listDest.add(route.getDest());
                }
                Set<String> set = new HashSet<String>(listSrc);
                Set<String> set1 = new HashSet<String>(listDest);
                List<String> listSrc1 = new ArrayList<String>(set);
                List<String> listDest1 = new ArrayList<String>(set1);
                ArrayAdapter<String> adapter = new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_item, listSrc1);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSource.setAdapter(adapter);
                ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_item,listDest1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDest.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /*Dest = spinnerDest.getSelectedItem().toString();
        Source = spinnerSource.getSelectedItem().toString();*/
        seclectButton = rootView.findViewById(R.id.buttonSelect);
        seclectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (editTextDest.getText().toString().equals(editTextSource.getText().toString())) {
                    Toast.makeText(getContext(), "Choose a different Destination", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getContext(), BookActivity.class);
                    intent.putExtra("SOURCE",editTextSource.getText().toString());
                    intent.putExtra("DESTINATION",editTextDest.getText().toString());
                    getActivity().startActivity(intent);

                }*/
                if (spinnerDest.getSelectedItem().toString().equals(spinnerSource.getSelectedItem().toString())) {
                    Toast.makeText(getContext(), "Choose a different Destination", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getContext(), BookActivity.class);
                    final String src = spinnerSource.getSelectedItem().toString();
                    final String des = spinnerDest.getSelectedItem().toString();
                    intent.putExtra("SOURCE",src);
                    intent.putExtra("DESTINATION",des);
                    getActivity().startActivity(intent);

                }
            }
        });


        return rootView;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
