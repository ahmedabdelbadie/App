package com.mal.udacity.ahmed.task;

import android.os.AsyncTask;

import com.mal.udacity.ahmed.fragment.MoviesFragment;
import com.mal.udacity.ahmed.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Abo Abdel-Badie on 10/25/2016.
 */

public class MoviesAsyncTask extends AsyncTask<String, Integer, ArrayList<MovieModel>> {
   private MoviesFragment moviesFragment;

    public MoviesAsyncTask(MoviesFragment moviesFragment){
        this.moviesFragment = moviesFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<MovieModel> doInBackground(String... link) {

        try {
            URL url;
            url = new URL(link[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder sb = new StringBuilder();
            if (connection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line + "\n");
                }
                in.close();
            }
            String response = sb.toString();
            //json
            JSONObject obj = new JSONObject(response);
            JSONArray JA = obj.getJSONArray("results");

            MovieModel data;
            ArrayList<MovieModel> datalist = new ArrayList<>();

            for (int i = 0; i < JA.length(); i++) {
                JSONObject jsonObject = JA.getJSONObject(i);

                String posterPath = jsonObject.getString("poster_path");
                String adult = jsonObject.getString("adult");
                String overview = jsonObject.getString("overview");
                String releaseDate = jsonObject.getString("release_date");
                String id = jsonObject.getString("id");
                String originalTitle = jsonObject.getString("original_title");
                String originalLanguage = jsonObject.getString("original_language");
                String title = jsonObject.getString("title");
                String backdrop = jsonObject.getString("backdrop_path");
                String popularity = jsonObject.getString("popularity");
                String voteCount = jsonObject.getString("vote_count");
                String video = jsonObject.getString("video");
                String voteRange = jsonObject.getString("vote_average");


                data = new MovieModel(posterPath, adult, overview, releaseDate, id, originalTitle, originalLanguage,
                        title, backdrop, popularity, voteCount, video, voteRange);
                datalist.add(data);
            }

            return datalist;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onPostExecute(ArrayList<MovieModel> movieModel) {
        super.onPostExecute(movieModel);
        moviesFragment.onMoviesParseEnd(movieModel);
    }
}
