package com.mal.udacity.ahmed.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.model.TrailerModel;

import java.util.ArrayList;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TrailerModel> TrailerModelArrayList;

    public TrailersAdapter(Context context, ArrayList<TrailerModel> TrailerModelArrayList){
        this.context = context;
        this.TrailerModelArrayList = TrailerModelArrayList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_trailer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrailerModel TrailerModel = TrailerModelArrayList.get(position);
        holder.tvTrailerTitle.setText(TrailerModel.getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTrailerTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTrailerTitle = (TextView) itemView.findViewById(R.id.tvTrailerTitle);
            tvTrailerTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view == tvTrailerTitle){
                String url = "http://www.youtube.com/watch?v=" +
                        TrailerModelArrayList.get((getLayoutPosition())).getKey();
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        }
    }
    @Override
    public int getItemCount() {
        return TrailerModelArrayList.size();
    }


}
