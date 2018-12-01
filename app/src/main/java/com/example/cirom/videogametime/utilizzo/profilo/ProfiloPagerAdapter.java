package com.example.cirom.videogametime.utilizzo.profilo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cirom.videogametime.R;

/**
 * Adapter per la navigazione tra i fragment dell'activity principale
 */
public class ProfiloPagerAdapter extends FragmentPagerAdapter {

    /**
     * Numero di tab e quindi di fragment da visualizzare
     */
    private int numeroDiTab = 3;

    /**
     * Costruttore
     * @param fm
     */
    public ProfiloPagerAdapter(FragmentManager fm) {
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
                return new NewsFragment();

            case 1:
                return new HomeFragment();

            case 2:
                return new CommentFragment();

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
                return R.string.tab_news;
            case 1:
                return R.string.tab_home;
            case 2:
                return R.string.tab_comment;
            default:
                return R.string.tab_unknown;
        }
    }
}