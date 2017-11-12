package com.mal.udacity.ahmed.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.adapter.PostersAdapter;
import com.mal.udacity.ahmed.model.MovieModel;
import com.mal.udacity.ahmed.sqlite.SqlLiteHelper;
import com.mal.udacity.ahmed.task.MoviesAsyncTask;
import com.mal.udacity.ahmed.prefs.PrefsController;
import java.util.ArrayList;

public class MoviesFragment extends Fragment{
    private RecyclerView gridView ;
    private String baseLink;
    private String apiKey;
    private String link;
    private String popular;
    private String rated;
    private ArrayList<MovieModel> movieModelArrayList;
    TextView tv_check;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        gridView = (RecyclerView)  view.findViewById(R.id.gv);
        gridView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        tv_check = (TextView) view.findViewById(R.id.tv_check);

        popular="/movie/popular";
        rated="/movie/top_rated";
        baseLink = "http://api.themoviedb.org/3" ;
        apiKey =  "?api_key=ddcab0907164e022b72ad3818937cf00";

        PrefsController prefsController = new PrefsController(getActivity());
        String sortBy = prefsController.getSortBy();
        if (sortBy.equals("favorite")) sortBy = "popular";
        start(sortBy);
        return view;
    }

    public void start(String sortBy){
        if(isNwConnected(getActivity())){

            switch (sortBy) {
                case "popular":
                    link = baseLink + popular + apiKey;
                    new MoviesAsyncTask(this).execute(link);
                    break;
                case "rated":
                    link = baseLink + rated + apiKey;
                    new MoviesAsyncTask(this).execute(link);
                    break;

                case "favorite":
                    SqlLiteHelper sqlLiteHelper = SqlLiteHelper.getInstance(getActivity());
                    ArrayList<MovieModel> favMovies = sqlLiteHelper.getFav();
                    onMoviesParseEnd(favMovies);
                    break;

                default:
                    link = baseLink + popular + apiKey;
                    new MoviesAsyncTask(this).execute(link);
                    break;
            }
        }
        else{
            tv_check.setVisibility(View.VISIBLE);
        }

    }

    public void onMoviesParseEnd(ArrayList<MovieModel> movieModel) {
        if (movieModel == null || movieModel.size() == 0) return;
        movieModelArrayList = movieModel;
        gridView.setAdapter(new PostersAdapter(this, movieModel));
    }

    public static boolean isNwConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
        return nwInfo != null && nwInfo.isConnectedOrConnecting();
    }

}
