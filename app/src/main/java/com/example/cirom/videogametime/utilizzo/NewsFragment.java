package com.example.cirom.videogametime.utilizzo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;

public class NewsFragment extends Fragment {
    private final String TAG = "DEMO_MISC";
    public NewsFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_high1, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch per individuare la voce di menu scelta
        switch (item.getItemId()) {
            case R.id.action_search:
                // L'utente ha scelto "Cerca"
                Log.v(TAG, "Menu-> Cerca");
                return true;

            default:
                // Scelta non riconosciuta, passo il controllo al metodo della classe base
                return super.onOptionsItemSelected(item);
        }
    }
}
