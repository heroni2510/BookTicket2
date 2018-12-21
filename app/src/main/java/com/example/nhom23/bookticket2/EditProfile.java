package com.example.nhom23.bookticket2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom23.bookticket2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etAddress;
    private Button btnUpdate;
    private FirebaseUser user;
    User user1;
    private OnFragmentInteractionListener mListener;

    public EditProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment EditProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfile newInstance() {
        EditProfile fragment = new EditProfile();

        return fragment;
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        etAddress =  rootView.findViewById(R.id.etAddress);
        etEmail =  rootView.findViewById(R.id.etEmail);
        etName =  rootView.findViewById(R.id.etPersonName);
        etPhone =  rootView.findViewById(R.id.etPhone);
        btnUpdate = rootView.findViewById(R.id.btnUpdate);

        user = FirebaseAuth.getInstance().getCurrentUser();
        final String user_id = user.getUid();
        FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user1 = dataSnapshot.getValue(User.class);
                //Toast.makeText(getContext(),user1.getAddress(),Toast.LENGTH_SHORT).show();
                etAddress.setText(user1.getAddress());
                etEmail.setText(user1.getEmail());
                etName.setText(user1.getName());
                etPhone.setText(user1.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().equals("")||etPhone.getText().equals("")||etAddress.getText().equals("")||etEmail.getText().equals("")){
                    Toast.makeText(getContext(),"You cannot be empty any field",Toast.LENGTH_SHORT).show();
                }else if (etName.getText().equals(user1.getName())==false) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("name").setValue(etName.getText().toString());
                }else if (etPhone.getText().equals(user1.getPhone())==false) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("phone").setValue(etPhone.getText().toString());
                }else if (etAddress.getText().equals(user1.getAddress())==false) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("address").setValue(etAddress.getText().toString());
                }else if (etEmail.getText().equals(user1.getEmail())==false) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("email").setValue(etEmail.getText().toString());
                }
                Toast.makeText(getContext(),"Update successful",Toast.LENGTH_SHORT).show();
                Fragment fragment = ProfileFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame , fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
