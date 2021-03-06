package com.example.cirom.videogametime.tutorial.selezione_giochi;

import java.util.Date;

public class Recensione {
    private float stars;
    private String recensione, titolo, id, personImm, personName, title, image, idgioco;
    private Date data;

    public Recensione(float stars, String recensione, String titolo, String id, String personImm, String personName, Date data, String title, String image, String idgioco) {
        this.stars = stars;
        this.recensione = recensione;
        this.titolo = titolo;
        this.id = id;
        this.personImm = personImm;
        this.personName = personName;
        this.data = data;
        this.title = title;
        this.image = image;
        this.idgioco = idgioco;
    }

    public Recensione() {
    }

    public String getIdgioco() {
        return idgioco;
    }

    public void setIdgioco(String idgioco) {
        this.idgioco = idgioco;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
