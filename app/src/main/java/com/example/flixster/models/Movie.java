package com.example.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    String title;
    String posterIMG;
    String summary;

    public Movie(JSONObject movieObject) throws JSONException
    {
        title = movieObject.getString("title");
        posterIMG = movieObject.getString("poster_path");
        summary = movieObject.getString("overview");
    }

    public String getTitle() {
        return title;
    }

    public String getPosterIMG() {
        return "https://image.tmdb.org/t/p/w342" + posterIMG;
    }

    public String getSummary() {
        return summary;
    }

    //this felt out of place here, moved to main activity for now unless problems arise
    /*public static List<Movie> fromJsonArray(JSONArray movieArray) throws JSONException
    {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieArray.length(); i++)
        {
            movies.add(new Movie(movieArray.getJSONObject(i)));
        }

        return movies;
    }*/
}
