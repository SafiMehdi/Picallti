package com.example.picallti;

public class LikedPosts {
    String Title, Status,Price;
    int ImageLiked;


    public LikedPosts(String title, String price,int imageLiked) {
        this.Title = title;
        this.ImageLiked = imageLiked;
        this.Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImageLiked() {
        return ImageLiked;
    }

    public void setImageLiked(int imageLiked) {
        ImageLiked = imageLiked;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
