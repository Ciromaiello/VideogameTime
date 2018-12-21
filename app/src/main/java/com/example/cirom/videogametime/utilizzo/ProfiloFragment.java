package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cirom.videogametime.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.squareup.picasso.Picasso;

import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class ProfiloFragment extends Fragment {

    private ImageView imgProfilo;
    private Account account;

    public ProfiloFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        acct = GoogleSignIn.getLastSignedInAccount(getContext());
        account = new Account(acct.getDisplayName(), acct.getId(), acct.getPhotoUrl().toString());
        View view = inflater.inflate(R.layout.fragment_profilo, container, false);
        imgProfilo = view.findViewById(R.id.profiloimg);
        Picasso.with(getContext()).load(account.getPersonPhoto()).transform(new CircleTransform(35,10)).into(imgProfilo);
        TabLayout  tabLayout = view.findViewById(R.id.tab_layout);
        final ViewPager viewPager = view.findViewById(R.id.pager);
        final ProfiloPagerAdapter vpagerAdapter = new ProfiloPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpagerAdapter);
        // Creazione dei tab ed aggiunta alla toolbar
        for (int i = 0; i < vpagerAdapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(vpagerAdapter.getItemTabNameResourceId(i)));
        }
        // tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
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
