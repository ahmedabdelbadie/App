package com.mal.udacity.ahmed.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.fragment.DetailsFragment;
import com.mal.udacity.ahmed.model.MovieModel;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MovieModel movieModel = getIntent().getParcelableExtra("item");
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.showUserSelectedMovie(movieModel);
    }
}
