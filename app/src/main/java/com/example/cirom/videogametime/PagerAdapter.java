package com.example.cirom.videogametime;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter per la navigazione tra i fragment dell'activity principale
 */
public class PagerAdapter extends FragmentPagerAdapter {

    /**
     * Numero di tab e quindi di fragment da visualizzare
     */
    private int numeroDiTab = 2;

    /**
     * Costruttore
     * @param fm
     */
    public PagerAdapter(FragmentManager fm) {
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
                return new PiattaformeFragment();

            case 1:
                return new GeneriFragment();

            default:
                return null;
        }
    }

    /**
     * Restituisce il numero di fragment totali
     * @return numero di fragment
     */
    @Override
    public int getCount() {
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
                return R.string.tab_piattaforme;

            case 1:
                return R.string.tab_generi;

            default:
                return R.string.tab_unknown;
        }
    }
}
