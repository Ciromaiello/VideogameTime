package com.example.cirom.videogametime;

/**
 * Classe dei get e set dei generi
 */
public class Generi {
    private String textgeneri;
    private boolean isSelected;

    public String getTextgeneri() {
        return textgeneri;
    }

    public void setTextgeneri(String textparole) {
        this.textgeneri = textparole;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
