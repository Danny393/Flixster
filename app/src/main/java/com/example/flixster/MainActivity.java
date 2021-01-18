package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    final String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    final String TAG = "MainActivity";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_DB_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;

                try {
                    JSONArray resultsArray = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + resultsArray.toString());
                    movies = fromJsonArray(resultsArray);
                    Log.i(TAG, "Movies: " + movies.size());
                }
                catch (JSONException e) {
                    Log.e(TAG,"JSON exception", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    public List<Movie> fromJsonArray(JSONArray movieArray) throws JSONException
    {
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieArray.length(); i++)
        {
            movies.add(new Movie(movieArray.getJSONObject(i)));
        }

        return movies;
    }
}