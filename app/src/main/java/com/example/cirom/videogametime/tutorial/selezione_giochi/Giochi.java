package com.example.cirom.videogametime.tutorial.selezione_giochi;


public class Giochi {


    //ci serve solo per avere lo stesso nome nel fragment e nel database
    //generi = {"Action", "Avventura", "Casual", "Free-to-play", "Hack and slash","Sportivo", "Erotico", "Gestionale", "MMORPG", "Picchiaduro", "RPG", "Sparatutto"};
    //piattaforme = {"PlayStation 4", "XBOX ONE", "Nintendo SWITCH", "PS VITA", "Nintendo 3DS", "PlayStation 3", "XBOX 360", "Nintendo DS", "PSP"};

    private String nome;
    private String generi;
    private String piattaforme;
    private String id_gioco;
    private String descrizione;
    private int immagine;
    //per ora salvo ciò che ci serve per salvare i giochi, successivamente si salveranno i commenti etc


    public int getImmagine() {
        return immagine;
    }

    public void setImmagine(int immagine) {
        this.immagine = immagine;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getGeneri() {
        return generi;
    }

    public void setGeneri(String generi) {
        this.generi = generi;
    }

    public String getPiattaforme() {
        return piattaforme;
    }

    public void setPiattaforme(String piattaforme) {
        this.piattaforme = piattaforme;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getId_gioco() {

        return id_gioco;
    }

    public void setId_gioco(String id_gioco) {

        this.id_gioco = id_gioco;
    }

    public Giochi(String nome, String generi, String piattaforme, String descrizione, int immagine) {
        this.nome = nome;
        this.generi = generi;
        this.piattaforme = piattaforme;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }
}
