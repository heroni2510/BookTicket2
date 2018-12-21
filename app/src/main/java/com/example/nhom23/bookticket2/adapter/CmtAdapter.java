package com.example.nhom23.bookticket2.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nhom23.bookticket2.R;
import com.example.nhom23.bookticket2.model.CommentRating;
import com.example.nhom23.bookticket2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CommentHolder> {
    private Activity activity;
    private ArrayList<CommentRating> listComment;
    private FirebaseUser user;
    public CmtAdapter(ArrayList<CommentRating> listComment, Activity activity) {
        this.listComment = listComment;
        this.activity = activity;
    }
    @NonNull
    @Override
    public CmtAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item,viewGroup,false);
        return new CommentHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final CmtAdapter.CommentHolder commentHolder, int position) {
        final CommentRating commentRating = listComment.get(position);

        commentHolder.tvDate.setText(commentRating.getDate());
        commentHolder.tvContent.setText(commentRating.getContent());
        commentHolder.rating.setRating(Float.parseFloat(commentRating.getRatingScore()));
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("u_id").equalTo(commentRating.getUser_id())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Parse dataSnapshot
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            commentHolder.tvName.setText(user.getName());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        commentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }
    class CommentHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvName;
        TextView tvContent;
        RatingBar rating;
        ImageView img;

        public CommentHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.date_cmt);
            tvName = (TextView) itemView.findViewById(R.id.name_cmt);
            tvContent = (TextView) itemView.findViewById(R.id.content_cmt);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar2);
        }
    }
}
