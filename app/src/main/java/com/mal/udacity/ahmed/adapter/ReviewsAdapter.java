package com.mal.udacity.ahmed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.model.ReviewModel;
import java.util.ArrayList;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private ArrayList<ReviewModel> ReviewsDataArrayList;

    public ReviewsAdapter(ArrayList<ReviewModel> ReviewsDataArrayList){
        this.ReviewsDataArrayList = ReviewsDataArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_reviews, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReviewModel ReviewModel = ReviewsDataArrayList.get(position);
        holder.tv_author.setText(ReviewModel.getAuthor());
        holder.tv_content.setText(ReviewModel.getContent());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_author, tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    @Override
    public int getItemCount() {
        return ReviewsDataArrayList.size();
    }

}
