package com.ivan.iam.moviequ.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivan.iam.moviequ.R;

import java.util.ArrayList;
import java.util.List;

import data.ApiClient;
import data.dao.MovieResponseDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView rView;
    private SwipeRefreshLayout mRefreshLayout;
    private List<MainDao> mData  = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        adapter = new MainAdapter(mData);

        rView = (RecyclerView) findViewById(R.id.rView);
        rView.setAdapter(adapter);
        rView.setLayoutManager(gridLayoutManager);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeMain);
        mRefreshLayout.setOnRefreshListener(this);

        getDataMovie();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mData.add(new MainDao("Satu", "https://pbs.twimg.com/profile_images/456092050975248384/Bv29Rp4D_400x400.jpeg"));
//                mData.add(new MainDao("Dua","https://avatars0.githubusercontent.com/u/19439866?v=4&s=460"));
//                mData.add(new MainDao("Tiga","https://www.wowkeren.com/images/news/00133806.jpg"));
//                mData.add(new MainDao("Empat","https://cdn.koreaboo.com/wp-content/uploads/2017/09/Tzuyu-Weight-00.jpg"));
//
//                adapter.notifyDataSetChanged();
//            }
//        }, 5000);

        Toast.makeText(this, "Loading 5 detik....", Toast.LENGTH_SHORT).show();
    }

    private void getDataMovie(){
        mRefreshLayout.setRefreshing(true);
        ApiClient.service().getMovieList("92a6ad79021577c42954d9838ff0bdc1")
                .enqueue(new Callback<MovieResponseDao>() {
                    @Override
                    public void onResponse(Call<MovieResponseDao> call, Response<MovieResponseDao> response) {
                        if (response.isSuccessful()) {
                            mRefreshLayout.setRefreshing(false);
                            for (MovieResponseDao.MovieData data : response.body().getResults()) {
                                mData.add(new MainDao(data.getTitle(), data.getOverview(), "http://image.tmdb.org/t/p/w185"+data.getBackdrop_path(), "http://image.tmdb.org/t/p/w185"+data.getPoster_path(), data.getRelease_date()));
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            mRefreshLayout.setRefreshing(false);
                            Toast.makeText(MainActivity.this, "Internal Server error...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponseDao> call, Throwable t) {
                        mRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onRefresh() {
        getDataMovie();
    }
}
