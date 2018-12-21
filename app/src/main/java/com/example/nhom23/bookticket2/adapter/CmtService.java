package com.example.nhom23.bookticket2.adapter;

import com.example.nhom23.bookticket2.model.CommentRating;
import com.example.nhom23.bookticket2.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class CmtService {
    public CmtService(){}
    public void getAllCommentInFirebase (final String company_id, final CmtListener listener ) {
        final ArrayList<CommentRating> list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("CommentRating")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            CommentRating commentRating = ds.getValue(CommentRating.class);
                            if(commentRating.getCompany_id().equals(company_id)) {
                                list.add(commentRating);
                            }
                        }
                        listener.getAllCommentSuccess(list);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.getCommentFailure(databaseError.toException());
                    }
                });

    }
}
