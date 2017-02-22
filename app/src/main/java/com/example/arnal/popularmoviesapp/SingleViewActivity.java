package com.example.arnal.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleViewActivity extends AppCompatActivity {

    final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_WIDTH = "w500";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        Intent intent = getIntent();
        Movies currentMovie = intent.getParcelableExtra("movie");

        //Find the image view with view ID tv_movie_poster
        ImageView moviePosterView = (ImageView) findViewById(R.id.poster_path);
        String moviePosterPath = new String (currentMovie.getPosterPath());

        // Using picasso, get the poster from the current poster object and set this image on the imageView
        final String FULL_POSTER_URL = POSTER_BASE_URL + POSTER_WIDTH + moviePosterPath;
        Picasso.with(getBaseContext()).load(FULL_POSTER_URL).into(moviePosterView);






/*
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {

            String textEntered = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);

            mDisplayText.setText(textEntered);
        }*/

       /* for(int i=0;i<imageArray.size();i++){
           mDisplayText.setText("Movie Title : "+imageArray.get(3));
           *//* ((TextView) rootView.findViewById(R.id.overview)).setText(imageArray.get(1));
            ((TextView) rootView.findViewById(R.id.releasedate)).setText("Release Date : "+imageArray.get(2));
            ((TextView) rootView.findViewById(R.id.vote)).setText("Rating : "+imageArray.get(3));
            imagePath = imageArray.get(4);*//*
        }*/




    }
}

/*
        LayoutInflater inflater;

        View rootView = inflater.inflate(R.layout.grid_detail,container,false);
        Intent intent = getIntent();
       if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
           String imagePath;
           String baseurl = "http://image.tmdb.org/t/p/w500";

           imageArray = intent.getStringArrayListExtra(Intent.EXTRA_TEXT);

           for(int i=0;i<imageArray.size();i++){

               ((TextView) rootView.findViewById(R.id.movietitle)).setText("Movie Title : "+imageArray.get(0));
               ((TextView) rootView.findViewById(R.id.overview)).setText(imageArray.get(1));
               ((TextView) rootView.findViewById(R.id.releasedate)).setText("Release Date : "+imageArray.get(2));
               ((TextView) rootView.findViewById(R.id.vote)).setText("Rating : "+imageArray.get(3));
    /*           imagePath = imageArray.get(4);*//*
           }
       }


*/


/*
        MovieAdapter image = new MovieAdapter(this, new ArrayList<Movies>());
        movieListView.setAdapter(image);

    }*/

