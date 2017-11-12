package com.mal.udacity.ahmed.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.adapter.ReviewsAdapter;
import com.mal.udacity.ahmed.adapter.TrailersAdapter;
import com.mal.udacity.ahmed.listeners.ReviewsListener;
import com.mal.udacity.ahmed.listeners.TrailersListener;
import com.mal.udacity.ahmed.model.MovieModel;
import com.mal.udacity.ahmed.model.ReviewModel;
import com.mal.udacity.ahmed.model.TrailerModel;
import com.mal.udacity.ahmed.task.ReviewsAsyncTask;
import com.mal.udacity.ahmed.task.TrailerAsyncTask;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class DetailsFragment extends Fragment implements TrailersListener, ReviewsListener {
    private TextView tv_mn;
    private TextView tv_release;
    private TextView tv_ov;
    private ImageView ivPoster;
    RecyclerView recyclerViewTrailers, recyclerViewReviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details, container, false);

        ivPoster = (ImageView) v.findViewById(R.id.image_showposter);
        tv_ov = (TextView) v.findViewById(R.id.tv_overview);
        tv_mn = (TextView) v.findViewById(R.id.tv_moviename);
        tv_release = (TextView) v.findViewById(R.id.tv_release);

        recyclerViewTrailers = (RecyclerView) v.findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = (RecyclerView) v.findViewById(R.id.recyclerViewReviews);
        recyclerViewTrailers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    public  void showUserSelectedMovie(MovieModel movieModel){
        getView().setVisibility(View.VISIBLE);
        tv_ov.setText(movieModel.getOverview());
        tv_mn.setText(movieModel.getTitle());
        tv_release.setText(movieModel.getReleaseDate());
        String basePoster = "http://image.tmdb.org/t/p/w185/";
        String linkPoster = basePoster + movieModel.getPosterPath();

        Picasso.with(getActivity())
                .load(linkPoster)
                .into(ivPoster);

        String trailerLink = "http://api.themoviedb.org/3/movie/" +
                movieModel.getId() +
                "/videos?api_key=6aa860fc8c6328a719c4a59f2fc6f032";


        String reviewsLink = "http://api.themoviedb.org/3/movie/" +
                movieModel.getId() +
                "/reviews?api_key=6aa860fc8c6328a719c4a59f2fc6f032";

        new ReviewsAsyncTask(this).execute(reviewsLink);
        new TrailerAsyncTask(this).execute(trailerLink);
    }

    @Override
    public void onLoadTrailers(ArrayList<TrailerModel> movieDataArrayList) {
        recyclerViewTrailers.setAdapter(new TrailersAdapter(getActivity(), movieDataArrayList));
    }

    @Override
    public void onLoadMovies(ArrayList<ReviewModel> reviewsDataList) {
        if (reviewsDataList != null && reviewsDataList.size() > 0)
            recyclerViewReviews.setAdapter(new ReviewsAdapter(reviewsDataList));
    }
}
