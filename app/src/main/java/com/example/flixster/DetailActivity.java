package com.example.flixster;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    final String APIKey = "AIzaSyBuSxEoNpnbBC2GobFD8K5fjjxX2Du2-5o";
    final String videosURL1 = "https://api.themoviedb.org/3/movie/";
    final String videosURL2 = "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView ytPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvDeTitle);
        tvOverview = findViewById(R.id.tvDeOverview);
        ratingBar = findViewById(R.id.ratingBar);
        ytPlayer = findViewById(R.id.ytPlayer);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getSummary());
        ratingBar.setRating((float) movie.getRating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(videosURL1 + movie.getMovieID() + videosURL2, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray videosArray = json.jsonObject.getJSONArray("results");
                    if(videosArray.length() == 0) {
                        return;
                    }

                    String youTubeKey = videosArray.getJSONObject(0).getString("key");
                    Log.d("DetailActivity", youTubeKey);

                    ytPlayer.initialize(APIKey, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.cueVideo(youTubeKey);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }
                catch (JSONException e) {
                    Log.e("DetailActivity", "Error parsing JSON");
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }
}