package com.example.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    String title;
    String posterIMG;
    String summary;
    String backdropIMG;

    public Movie(JSONObject movieObject) throws JSONException
    {
        title = movieObject.getString("title");
        posterIMG = movieObject.getString("poster_path");
        summary = movieObject.getString("overview");
        backdropIMG = movieObject.getString("backdrop_path");
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

    public String getBackdropIMG() { return "https://image.tmdb.org/t/p/w342" + backdropIMG; }
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
