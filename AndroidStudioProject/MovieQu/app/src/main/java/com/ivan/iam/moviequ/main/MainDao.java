package com.ivan.iam.moviequ.main;

/**
 * Created by IAM on 01/12/2017.
 */

public class MainDao {
    private String title;
    private String imageUrl;

    public MainDao(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {

        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
