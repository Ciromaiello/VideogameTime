package com.example.cirom.videogametime.utilizzo;

import java.util.ArrayList;

public class GiochiScelti {
    private ArrayList<String> scelte;

    public GiochiScelti(ArrayList<String> scelte) {
        this.scelte = scelte;
    }

    public GiochiScelti() {
    }

    public ArrayList<String> getScelte() {
        return scelte;
    }

    public void setScelte(ArrayList<String> scelte) {
        this.scelte = scelte;
    }
}
