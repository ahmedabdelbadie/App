package com.mal.udacity.ahmed.task;

import android.os.AsyncTask;

import com.mal.udacity.ahmed.listeners.ReviewsListener;
import com.mal.udacity.ahmed.model.ReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ReviewsAsyncTask extends AsyncTask<String, Void, ArrayList<ReviewModel>>{
   private ReviewsListener listener;

    public ReviewsAsyncTask(ReviewsListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<ReviewModel> doInBackground(String... strings) {

        try {
            String urlString = strings[0];
            URL urlObj = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlObj.openConnection();
            httpURLConnection.setRequestMethod("GET");
            StringBuilder sb = null;
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();
            }

            if (sb == null) return null;

            String responseJson = sb.toString();

            JSONObject jsonObject = new JSONObject(responseJson);
            JSONArray jsonArrayResult = jsonObject.getJSONArray("results");

            ArrayList<ReviewModel> reviewsDataArrayList = new ArrayList<>();
            ReviewModel info ;
            for (int i = 0 ; i < jsonArrayResult.length() ; i++){

                JSONObject jsonObj = jsonArrayResult.getJSONObject(i);
                String content = jsonObj.getString("content");
                String author = jsonObj.getString("author");

                info = new ReviewModel(content, author);
                reviewsDataArrayList.add(info);
            }
            return reviewsDataArrayList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ReviewModel> ReviewsDataArrayList) {
        super.onPostExecute(ReviewsDataArrayList);

        listener.onLoadMovies(ReviewsDataArrayList);


    }
}
