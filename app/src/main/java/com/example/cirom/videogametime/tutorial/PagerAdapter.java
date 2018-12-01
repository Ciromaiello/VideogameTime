package com.example.cirom.videogametime.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cirom.videogametime.tutorial.selezione_generi.GeneriFragment;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.CustomViewPager;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeFragment;

/**
 * Adapter per la navigazione tra i fragment dell'activity principale
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private CustomViewPager customViewPager;

    /**
     * Numero di tab e quindi di fragment da visualizzare
     */
    private int numeroDiTab = 2;
    /**
     * Costruttore
     * @param fm
     */
    public PagerAdapter(FragmentManager fm, CustomViewPager viewPager) {
        super(fm);
        customViewPager = viewPager;
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
                PiattaformeFragment piattaformeFragment = new PiattaformeFragment();
                customViewPager.setFragment(piattaformeFragment);
                return piattaformeFragment;

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
}
