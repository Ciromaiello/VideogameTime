package com.example.cirom.videogametime.utilizzo;

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
    private int numeroDiTab = 2;

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
                return new GestioneProfiloFragment();

            case 1:
                return new RecensioneFragment();

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
                return R.string.tab_seguiti;
            case 1:
                return R.string.tab_rece;
            default:
                return R.string.tab_unknown;
        }
    }
}