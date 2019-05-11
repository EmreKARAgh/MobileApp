package com.example.mobileapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Anasayfa extends AppCompatActivity
        implements AnasayfaFragment.OnFragmentInteractionListener,
        ProfilFragment.OnFragmentInteractionListener,BagislarFragment.OnFragmentInteractionListener,
        IstatistikFragment.OnFragmentInteractionListener,AyarlarFragment.OnFragmentInteractionListener,
        ProfiliDuzenleFragment.OnFragmentInteractionListener,HatirlaticiFragment.OnFragmentInteractionListener,
        DestekOlFragment.OnFragmentInteractionListener,BildirimlerFragment.OnFragmentInteractionListener,HakkindaFragment.OnFragmentInteractionListener,
        KurumlarFragment.OnFragmentInteractionListener,KurumDetayFragment.OnFragmentInteractionListener,BagisOlusturFragment.OnFragmentInteractionListener,
        BagislarimFragment.OnFragmentInteractionListener,BagisIcerikFragment.OnFragmentInteractionListener, BagisimIcerikFragment.OnFragmentInteractionListener,
        BagiscilariGosterFragment.OnFragmentInteractionListener,KurumBagislarFragment.OnFragmentInteractionListener,ProfiliDuzenleKurumsalFragment.OnFragmentInteractionListener

{

    private TextView mTextMessage;
    public static Birey currentBirey = new Birey();
    public static Kurum currentKurum = new Kurum();
    public static String kullaniciTipi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        mTextMessage = (TextView) findViewById(R.id.message);

        final AnasayfaFragment anasayfaFragment=new AnasayfaFragment();
        final BagislarFragment bagislarFragment=new BagislarFragment();
        final ProfilFragment profilFragment=new ProfilFragment();
        final KurumlarFragment kurumlarFragment=new KurumlarFragment();
        setFragment(anasayfaFragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.navigation_anasayfa){
                    setFragment(anasayfaFragment);
                    return true;
                }else if(id==R.id.navigation_bagislar){
                    setFragment(bagislarFragment);
                    return true;
                }else if(id==R.id.navigation_kurumlar){
                    setFragment(kurumlarFragment);
                    return true;
                }else if(id==R.id.navigation_profil){
                    setFragment(profilFragment);
                    return true;
                }
                return false;
            }
        });

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
