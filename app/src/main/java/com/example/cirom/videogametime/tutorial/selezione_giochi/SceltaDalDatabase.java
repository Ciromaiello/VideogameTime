package com.example.cirom.videogametime.tutorial.selezione_giochi;

import java.util.ArrayList;
import java.util.List;

public class SceltaDalDatabase {


    private ArrayList<String> Giochi;

    public ArrayList<String> getGiochi() {
        return Giochi;
    }

    public void setGiochi(ArrayList<String> giochi) {
        Giochi = giochi;
    }

    public SceltaDalDatabase(ArrayList<String> giochi) {
        Giochi = giochi;
    }

    public SceltaDalDatabase() {
    }
}
