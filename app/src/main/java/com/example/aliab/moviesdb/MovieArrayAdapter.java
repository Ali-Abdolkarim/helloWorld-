package com.example.aliab.moviesdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliab.moviesdb.Json.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    private int resources;

    public MovieArrayAdapter(@NonNull Context context, int resource, List<Movie> movies) {
        super(context, resource, movies);
        resources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String imageBase = "https://image.tmdb.org/t/p/w500/";
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(resources, null);

        Movie currentMovie = getItem(position);

        ImageView movieImageView = convertView.findViewById(R.id.movie_img);
        ImageView backgroundImageView = convertView.findViewById(R.id.background_img);
        TextView movieTitle = convertView.findViewById(R.id.movie_title);

        movieTitle.setText(currentMovie.getTitle());
        Picasso.get().load(imageBase + currentMovie.getPosterPath()).into(movieImageView);
        Picasso.get().load(imageBase + currentMovie.getBackdropPath()).into(backgroundImageView);
        return convertView;
    }
}
