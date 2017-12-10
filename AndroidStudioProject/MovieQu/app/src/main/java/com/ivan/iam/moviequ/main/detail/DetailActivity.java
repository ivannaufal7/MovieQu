package com.ivan.iam.moviequ.main.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivan.iam.moviequ.R;
import com.ivan.iam.moviequ.main.MainDao;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private MainDao mData;

    private TextView txtTitle, txtRelease, txtDesc;
    private ImageView imgBack, imgPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mData = getIntent().getParcelableExtra("dataMovie");
        txtTitle = findViewById(R.id.txtTitle);
        txtDesc = findViewById(R.id.txtDesc);
        txtRelease = findViewById(R.id.txtRelease);

        imgBack = findViewById(R.id.imageToolbar);
        imgPoster = findViewById(R.id.imgPoster);

        txtTitle.setText(mData.getTitle());
        txtDesc.setText(mData.getDescription());
        txtRelease.setText(mData.getReleaseDate());

        Picasso.with(this)
                .load(mData.getImageUrl())
                .into(imgPoster);

        Picasso.with(this)
                .load(mData.getImageBackground())
                .into(imgBack);
    }
}
