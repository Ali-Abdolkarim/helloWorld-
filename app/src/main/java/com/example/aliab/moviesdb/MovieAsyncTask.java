package com.example.aliab.moviesdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.aliab.moviesdb.Json.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieAsyncTask extends AsyncTaskLoader<List<Movie>> {

    public MovieAsyncTask(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<Movie> loadInBackground() {
        Log.e("async", "loadInBackground: " );
        HttpHandler handler = new HttpHandler();
        ArrayList<Movie> result = new ArrayList<>();
        try {
            return result = handler.makeHttpRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
