package com.example.cirom.videogametime.utilizzo;

public class News {
    private String titolo;
    private String desc;
    private String URL;
    private String img;

    public News(String titolo, String desc, String URL, String img) {
        this.titolo = titolo;
        this.desc = desc;
        this.URL = URL;
        this.img = img;
    }

    public News() {
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
