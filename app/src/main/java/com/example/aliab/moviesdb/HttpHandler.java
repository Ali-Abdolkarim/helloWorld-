package com.example.aliab.moviesdb;

import com.example.aliab.moviesdb.Json.Movie;
import com.example.aliab.moviesdb.Json.Root;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpHandler {
    private String urlString = "https://api.themoviedb.org/3/movie/popular?api_key=c2152db12e30e59e29ea7f62b3e47afe&language=en-US&page=1";

    public HttpHandler() {
    }

    public ArrayList<Movie> makeHttpRequest() throws IOException {
        StringBuilder jsonRespond = new StringBuilder();
        URL url = new URL(urlString);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.connect();

        InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (null != line) {
            jsonRespond.append(line);
            line = reader.readLine();
        }
        return parsing(jsonRespond.toString());
    }

    public ArrayList<Movie> parsing(String s){
        ArrayList<Movie> movies;
        Gson gson= new Gson();
        Root root =gson.fromJson(s,Root.class);

        movies = (ArrayList<Movie>) root.getResults();

        return movies;
    }
}
