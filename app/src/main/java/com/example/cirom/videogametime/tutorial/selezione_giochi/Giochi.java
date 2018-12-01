package com.example.cirom.videogametime.tutorial.selezione_giochi;


public class Giochi {


    //ci serve solo per avere lo stesso nome nel fragment e nel database
    //generi = {"Action", "Avventura", "Casual", "Free-to-play", "Hack and slash","Sportivo", "Erotico", "Gestionale", "MMORPG", "Picchiaduro", "RPG", "Sparatutto"};
    //piattaforme = {"PlayStation 4", "XBOX ONE", "Nintendo SWITCH", "PS VITA", "Nintendo 3DS", "PlayStation 3", "XBOX 360", "Nintendo DS", "PSP"};

    private String nome;
    private String genere;
    private String piattaforma;
    private String id_gioco;
    //per ora salvo ciò che ci serve per salvare i giochi, successivamente si salveranno i commenti etc


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public String getId_gioco() {
        return id_gioco;
    }

    public void setId_gioco(String id_gioco) {
        this.id_gioco = id_gioco;
    }

    public Giochi(String nome, String genere, String piattaforma,String id_gioco) {
        this.nome = nome;
        this.genere = genere;
        this.piattaforma = piattaforma;
        this.id_gioco=id_gioco;
    }





}
