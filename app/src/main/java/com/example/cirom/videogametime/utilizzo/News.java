package com.example.cirom.videogametime.utilizzo;

import android.media.Image;
import android.widget.ImageView;

import com.example.cirom.videogametime.R;

public class News {
   private String img;
   private String url;

    public News(String img, String url) {
        this.img=img;
        this.url = url;
    }

    public News(){};

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
