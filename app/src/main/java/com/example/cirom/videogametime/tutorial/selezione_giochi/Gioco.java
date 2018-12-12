package com.example.cirom.videogametime.tutorial.selezione_giochi;

import java.util.ArrayList;

public class Gioco
{
    private String nome;
    private ArrayList<String> generi;
    private ArrayList<String> piattaforme;
    private String id_gioco;
    private String descrizione;
    private String immagine;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getGeneri() {
        return generi;
    }

    public void setGeneri(ArrayList<String> generi) {
        this.generi = generi;
    }

    public ArrayList<String> getPiattaforme() {
        return piattaforme;
    }

    public void setPiattaforme(ArrayList<String> piattaforme) {
        this.piattaforme = piattaforme;
    }


    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public Gioco(String nome, ArrayList<String> generi, ArrayList<String> piattaforme, String descrizione, String immagine) {
        this.nome = nome;
        this.generi = generi;
        this.piattaforme = piattaforme;
        this.id_gioco = id_gioco;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }

    public Gioco(String nome, ArrayList<String> generi, ArrayList<String> piattaforme) {
        this.nome = nome;
        this.generi = generi;
        this.piattaforme = piattaforme;
    }

    public Gioco(){};
}
