package com.example.cirom.videogametime.tutorial.selezione_giochi;

public class Recensione {
    private float stars;
    private String recensione, titolo, id;

    public Recensione(float stars, String recensione, String titolo, String id) {
        this.stars = stars;
        this.recensione = recensione;
        this.titolo = titolo;
        this.id = id;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(String recensione) {
        this.recensione = recensione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
