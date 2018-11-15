package com.example.nhom23.bookticket2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SelectRouteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Button seclectButton;
    private EditText editTextSource;
    private EditText editTextDest;
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
        editTextDest = rootView.findViewById(R.id.editTextDest);
        editTextSource = rootView.findViewById(R.id.editTextSource);

        Dest = editTextDest.getText().toString();
        Source =editTextSource.getText().toString() ;
        seclectButton = rootView.findViewById(R.id.buttonSelect);
        seclectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextDest.getText().toString().equals(editTextSource.getText().toString())) {
                    Toast.makeText(getContext(), "Choose a different Destination", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(getContext(), BookActivity.class);
                    intent.putExtra("SOURCE",editTextSource.getText().toString());
                    intent.putExtra("DESTINATION",editTextDest.getText().toString());
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
