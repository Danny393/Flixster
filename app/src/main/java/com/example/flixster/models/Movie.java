package com.example.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Movie {

    String title;
    String posterIMG;
    String summary;
    String backdropIMG;
    int movieID;
    double rating;

    //this is for parceler library
    public Movie() {}

    public Movie(JSONObject movieObject) throws JSONException
    {
        title = movieObject.getString("title");
        posterIMG = movieObject.getString("poster_path");
        summary = movieObject.getString("overview");
        backdropIMG = movieObject.getString("backdrop_path");
        movieID = movieObject.getInt("id");
        rating = movieObject.getDouble("vote_average") / 2.0;
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

    public int getMovieID() { return movieID; }

    public double getRating() { return rating; }

}
