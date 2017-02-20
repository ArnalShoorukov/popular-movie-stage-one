package com.example.arnal.popularmoviesapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movies>>{

    private static final String LOG_TAG = MainActivity.class.getName();

    /*ListView global variable*/
    private ListView movieListView;
    //Adapter for list of books
    private MovieAdapter mAdapter;
    /**
     * +     * Constant value for the news loader ID. We can choose any integer.
     * +     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIE_LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;
    /**
     * URL for News data from the theguardion.com
     */
    private String NEWS_URL = "http://api.themoviedb.org/3/movie/popular?api_key=******";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = (ListView) findViewById(R.id.list);
        // Create a new {@link ArrayAdapter} of news
        mAdapter = new MovieAdapter(this, new ArrayList<Movies>());
        movieListView.setAdapter(mAdapter);
        mEmptyStateTextView = (TextView) findViewById(R.id.text);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            android.app.LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
            //check if there's already a loader
            if (getLoaderManager().getLoader(MOVIE_LOADER_ID).isStarted()) {
                //restart it if there's one
                getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
            }
        } else {
            //Otherwise,display error
            //First hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading);
            loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter.clear();
        movieListView.setAdapter(mAdapter);
        movieListView.setEmptyView(mEmptyStateTextView);
    }
    @Override
    public Loader<List<Movies>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new MovieLoader(this, NEWS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movies>> loader, List<Movies> data) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No books found."
        mEmptyStateTextView.setText(R.string.no_news);

        // Clear the adapter of previous book data
        mAdapter.clear();
        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
        Log.v("Loader State","on Load Finished");
    }

    @Override
    public void onLoaderReset(Loader<List<Movies>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
