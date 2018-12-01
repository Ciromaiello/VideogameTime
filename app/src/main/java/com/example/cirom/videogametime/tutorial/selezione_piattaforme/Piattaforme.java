package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

/**
* Classe dei get e dei set delle piattaforme
 */
public class Piattaforme {
    private String textpiattaforme;
    private boolean isSelected1;
    private int image;

    public String getTextpiattaforme() {

        return textpiattaforme;
    }

    public void setTextpiattaforme(String textpiattaforme) {

        this.textpiattaforme = textpiattaforme;
    }

    public boolean isSelected1() {

        return isSelected1;
    }

    public void setSelected1(boolean selected1) {

        isSelected1 = selected1;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
