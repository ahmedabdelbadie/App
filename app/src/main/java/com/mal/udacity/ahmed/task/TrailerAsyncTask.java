package com.mal.udacity.ahmed.task;

import android.os.AsyncTask;

import com.mal.udacity.ahmed.listeners.TrailersListener;
import com.mal.udacity.ahmed.model.TrailerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TrailerAsyncTask extends AsyncTask<String, Void, ArrayList<TrailerModel>>{
   private TrailersListener listener;

    public TrailerAsyncTask(TrailersListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TrailerModel> doInBackground(String... strings) {

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

            ArrayList<TrailerModel> trailerDataList = new ArrayList<>();
            TrailerModel info ;
            for (int i = 0 ; i < jsonArrayResult.length() ; i++){
                JSONObject jsonObj = jsonArrayResult.getJSONObject(i);
                String key = jsonObj.getString("key");
                String name = jsonObj.getString("name");
                String site = jsonObj.getString("site");
                String size = jsonObj.getString("size");
                String type = jsonObj.getString("type");


                info = new TrailerModel( key, name,  site,
                        size, type);
                trailerDataList.add(info);
            }
            return trailerDataList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<TrailerModel> TrailerDataArrayList) {
        super.onPostExecute(TrailerDataArrayList);

        listener.onLoadTrailers(TrailerDataArrayList);


    }
}
