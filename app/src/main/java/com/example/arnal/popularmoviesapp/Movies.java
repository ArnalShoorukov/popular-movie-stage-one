package com.example.arnal.popularmoviesapp;

/**
 * Created by arnal on 2/16/17.
 */
public class Movies {
    private String mTitle;
    private String mPosterPath;
    private String mOverview;
    private String mRating;
    private String mReleaseDate;

    public Movies(String title, String posterPath, String overview, String rating, String releaseDate){
        mTitle = title;
        mPosterPath = posterPath;
        mOverview = overview;
        mRating = rating;
        mReleaseDate = releaseDate;
    }

    public String getTitle(){return mTitle;}

    public String getPosterPath(){return mPosterPath;}

    public String getOverview(){return mOverview;}

    public String getRating (){return mRating;}

    public String getReleaseDate(){return mReleaseDate; }
}
