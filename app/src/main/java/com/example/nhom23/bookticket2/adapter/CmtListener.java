package com.example.nhom23.bookticket2.adapter;

import com.example.nhom23.bookticket2.model.CommentRating;
import com.example.nhom23.bookticket2.model.Route;

import java.util.ArrayList;

public interface CmtListener {
    void getAllCommentSuccess(ArrayList<CommentRating> commentRatingArrayList);
    void getCommentFailure(Exception ex);
}
