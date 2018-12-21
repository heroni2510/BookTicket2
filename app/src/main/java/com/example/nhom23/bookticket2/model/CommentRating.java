package com.example.nhom23.bookticket2.model;

public class CommentRating {


    private String cmt_id ;
    private String user_id;
    private String company_id;
    private String content;
    private String ratingScore;



    private String date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String busCompany_id) {
        this.company_id = busCompany_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(String ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getCmt_id() {
        return cmt_id;
    }

    public void setCmt_id(String cmt_id) {
        this.cmt_id = cmt_id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public CommentRating(){}
    public CommentRating(String cmt_id, String user_id, String busCompany_id, String content, String ratingScore, String date){
        this.cmt_id = cmt_id;
        this.user_id = user_id;
        this.company_id = busCompany_id;
        this.content = content;
        this.ratingScore = ratingScore;
        this.date = date;
    }

}
