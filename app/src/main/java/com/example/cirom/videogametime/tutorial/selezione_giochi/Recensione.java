package com.example.cirom.videogametime.tutorial.selezione_giochi;

public class Recensione {
    private float stars;
    private String recensione, titolo, id, personImm, personName;

    public Recensione(float stars, String recensione, String titolo, String id, String personImm, String personName) {
        this.stars = stars;
        this.recensione = recensione;
        this.titolo = titolo;
        this.id = id;
        this.personImm = personImm;
        this.personName = personName;
    }

    public Recensione() {
    }

    public String getPersonImm() {
        return personImm;
    }

    public void setPersonImm(String personImm) {
        this.personImm = personImm;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
