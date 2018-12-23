package com.example.cirom.videogametime.utilizzo;

import android.widget.ImageView;

import com.example.cirom.videogametime.R;

public class News {
   private int img;
   private String url;

    public News(int img, String url) {
        this.img=img;
        this.url = url;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
