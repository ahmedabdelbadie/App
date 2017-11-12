package com.mal.udacity.ahmed.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.activity.DetailActivity;
import com.mal.udacity.ahmed.activity.MainActivity;
import com.mal.udacity.ahmed.fragment.MoviesFragment;
import com.mal.udacity.ahmed.model.MovieModel;
import com.mal.udacity.ahmed.sqlite.SqlLiteHelper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {
    private MoviesFragment moviesFragment;
    private ArrayList<MovieModel> MovieModelArrayList;

    public PostersAdapter(MoviesFragment moviesFragment, ArrayList<MovieModel> MovieModelArrayList){
        this.moviesFragment = moviesFragment;
        this.MovieModelArrayList = MovieModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_posters, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel movieModel = MovieModelArrayList.get(position);
        String posterPath = movieModel.getPosterPath();
        String baseUrl = "http://image.tmdb.org/t/p/";
        String recommendedSize = "w185";
        String completeUrl=
                new StringBuilder(baseUrl)
                .append(recommendedSize)
                .append(posterPath)
                .toString();

        Picasso.with(moviesFragment.getActivity()).load(completeUrl).into(holder.iv_poster);
        SqlLiteHelper sqlLiteHelper = SqlLiteHelper.getInstance(moviesFragment.getActivity());
        if (sqlLiteHelper.isFav(movieModel.getId())){
            holder.btnFav.setText("UNFAVORITE");
        }
        else {
            holder.btnFav.setText("FAVORITE");
        }
    }

    @Override
    public int getItemCount() {
        return MovieModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView
                iv_poster;
        Button btnFav;
        ViewHolder(View itemView) {
            super(itemView);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
            btnFav = (Button) itemView.findViewById(R.id.btnFav);

            iv_poster.setOnClickListener(this);
            btnFav.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == btnFav){
                MovieModel movieModel = MovieModelArrayList.get(getLayoutPosition());
                SqlLiteHelper sqlLiteHelper = SqlLiteHelper.getInstance(moviesFragment.getActivity());

                if (sqlLiteHelper.isFav(movieModel.getId())){
                    sqlLiteHelper.deleteFav(movieModel.getId());
                    btnFav.setText("FAVORITE");
                }
                else {
                    sqlLiteHelper.setFav(movieModel);
                    btnFav.setText("UNFAVORITE");
                }
            }

            else if (view == iv_poster){
                MovieModel movieModel = MovieModelArrayList.get(getLayoutPosition());
                MainActivity mainActivity = (MainActivity) moviesFragment.getActivity();

                if (mainActivity.isMyPhoneTablet()){
                    mainActivity.detailsFragment.showUserSelectedMovie(movieModel);
                    mainActivity.detailsFragment.getView().setVisibility(View.VISIBLE);
                }
                else{
                    Intent intent = new Intent(mainActivity, DetailActivity.class);
                    intent.putExtra("item", movieModel);
                    mainActivity.startActivity(intent);
                }
            }
        }
    }
}
