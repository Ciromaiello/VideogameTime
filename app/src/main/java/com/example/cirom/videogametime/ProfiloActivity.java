package com.example.cirom.videogametime;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfiloActivity extends AppCompatActivity {

    private final String TAG = "DEMO_MISC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        // Abilita l'action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Imposta il Pager
        impostaPager();


    }

    /**
     * Evento generato per la creazione del menu
     * Consente l'associazione del menu alla toolbar
     * @param menu Riferimento al menu
     * @return esito della creazione
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_high, menu);
        return super.onCreateOptionsMenu(menu);

    }


    /**
     * L'utente ha effettuato una scelta nel menu
     * @param item rappresenta l'elemento scelto
     * @return true in caso di elaborazione evento
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch per individuare la voce di menu scelta
        switch (item.getItemId()) {
            case R.id.action_search:
                // L'utente ha scelto "Cerca"
                Log.v(TAG, "Menu-> Cerca");
                return true;

            case R.id.action_settings:
                // L'utente ha scelto "impostazioni"
                Log.v(TAG, "Menu-> Impostazioni");
                Intent i = new Intent(this,ImpostazioniActivity.class);
                startActivity(i);
                return true;

            case R.id.action_logout:
                // L'utente ha scelto "logout"
                Log.v(TAG, "Menu-> Logout");
                return true;

            default:
                // Scelta non riconosciuta, passo il controllo al metodo della classe base
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Imposta la gestione della paginazione
     */
    private void impostaPager() {

        // Riferimento al pager
        final ViewPager viewPager = findViewById(R.id.pager);

        // Creo l'adapter
        final ProfiloPagerAdapter pagerAdapter1 = new ProfiloPagerAdapter(getSupportFragmentManager());

        // Imposto l'adapter per il pager
        viewPager.setAdapter(pagerAdapter1);

        // Creazione dei tab ed aggiunta alla toolbar
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < pagerAdapter1.getCount(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter1.getItemTabNameResourceId(i)));

        // Listner delle selezioni dei tab
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
