package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cirom.videogametime.R;

import java.util.ArrayList;

public class GiochiPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Giochi> giochi;
    /**
     * Numero di tab e quindi di fragment da visualizzare
     */
    private int numeroDiTab = 2;

    /**
     * Costruttore
     * @param fm
     */
    public GiochiPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Restituisce l'elemento corrispondente alla posizione passata
     * @param position posizione
     * @return elemento
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RecensioniGiocoFragment();

            case 1:
                return new InfoFragment();

            default:
                return null;
        }
    }

    /**
     * Restituisce il numero di fragment totali
     * @return numero di fragment
     */
    @Override
    public  int getCount() {
        return numeroDiTab;
    }

    /**
     * Restituisce l'id della risorsa con il nome del tab da mettere nel menu
     * @param position indice della posizione
     * @return id della stringa da mostrare nel tab
     */
    public int getItemTabNameResourceId(int position) {
        switch (position) {
            case 0:
                return R.string.tab_rece;
            case 1:
                return R.string.tab_info;
            default:
                return R.string.tab_unknown;
        }
    }
}
