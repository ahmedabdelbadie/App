package com.mal.udacity.ahmed.listeners;

import com.mal.udacity.ahmed.model.ReviewModel;
import java.util.ArrayList;

public interface ReviewsListener {


     void onLoadMovies(ArrayList<ReviewModel> reviewModelArrayList);
}
