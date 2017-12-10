package com.ivan.iam.moviequ.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan.iam.moviequ.R;
import com.ivan.iam.moviequ.main.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by IAM on 01/12/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private  List<MainDao> mData;

    public MainAdapter(List<MainDao> mData) {
        this.mData = mData;
    }

    @Override
    public MainAdapter.MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main,parent,false);
        MainHolder holder = new MainHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainHolder holder, final int position) {
        holder.titleRow.setText(mData.get(position).getTitle());
        Picasso.with(holder.imgRow.getContext())
                .load(mData.get(position).getImageUrl())
                .into(holder.imgRow);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("dataMovie",mData.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        private ImageView imgRow;
        private TextView titleRow;
        public MainHolder(View itemView) {
            super(itemView);

            imgRow = (ImageView) itemView.findViewById(R.id.imgRow);
            titleRow = (TextView) itemView.findViewById(R.id.titleRow);


        }
    }
}
