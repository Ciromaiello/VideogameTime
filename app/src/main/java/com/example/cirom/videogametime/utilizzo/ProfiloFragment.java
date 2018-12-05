package com.example.cirom.videogametime.utilizzo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cirom.videogametime.R;
import com.squareup.picasso.Picasso;

public class ProfiloFragment extends Fragment {

    private ImageView imgProfilo;

    public ProfiloFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profilo, container, false);
        imgProfilo = view.findViewById(R.id.profiloimg);



        Picasso.with(getContext()).load(Account.personPhoto).into(imgProfilo);

      TabLayout  tabLayout = view.findViewById(R.id.tab_layout);
      final ViewPager viewPager = view.findViewById(R.id.pager);
      final ProfiloPagerAdapter vpagerAdapter = new ProfiloPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(vpagerAdapter);

        // Creazione dei tab ed aggiunta alla toolbar
        for (int i = 0; i < vpagerAdapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(vpagerAdapter.getItemTabNameResourceId(i)));
        }
   //      tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
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



     return view;
    }

}
