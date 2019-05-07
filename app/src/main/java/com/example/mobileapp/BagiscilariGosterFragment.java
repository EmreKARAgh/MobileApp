package com.example.mobileapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BagiscilariGosterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BagiscilariGosterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BagiscilariGosterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Bagis bagis;
    String Ad,Soyad,Telefon,Email;
    String kurumAdi, kurumEmail, kurumTelefon;
    TableLayout tableLayoutBagiscilariGoster;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    private OnFragmentInteractionListener mListener;

    public BagiscilariGosterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BagiscilariGosterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BagiscilariGosterFragment newInstance(String param1, String param2) {
        BagiscilariGosterFragment fragment = new BagiscilariGosterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_bagiscilari_goster, container, false);
        veriCek(RootView);

        //init(RootView);


        return RootView;
    }

    public void initBaslangic(){

        TableRow tbrow0 = new TableRow(getContext());
        TextView tv1 = new TextView(this.getContext());
        tv1.setText("Ad Soyad  ");
        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(Gravity.LEFT);
        tv1.setTextSize(20);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this.getContext());
        tv2.setText(" Telefon ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextSize(20);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this.getContext());
        tv3.setText("E-mail        ");
        tv3.setTextColor(Color.BLACK);
        tv3.setGravity(Gravity.RIGHT);
        tv3.setTextSize(20);
        tbrow0.addView(tv3);
        tableLayoutBagiscilariGoster.addView(tbrow0);
    }

    public void init() {


        TableRow tbrow = new TableRow(getContext());
        TextView t1v = new TextView(getContext());
        t1v.setText(Ad+" "+Soyad);
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.LEFT);
        t1v.setTextSize(15);
        tbrow.addView(t1v);
        TextView t2v = new TextView(getContext());
        t2v.setText(Telefon);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        t2v.setTextSize(15);
        tbrow.addView(t2v);
        TextView t3v = new TextView(getContext());
        t3v.setText("  "+Email);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.RIGHT);
        t3v.setTextSize(15);
        tbrow.addView(t3v);
        tableLayoutBagiscilariGoster.addView(tbrow);


    }
    public void initKurum() {


        TableRow tbrow = new TableRow(getContext());
        TextView t1v = new TextView(getContext());
        t1v.setText(kurumAdi);
        t1v.setTextColor(Color.RED);
        t1v.setGravity(Gravity.LEFT);
        t1v.setTextSize(15);
        tbrow.addView(t1v);
        TextView t2v = new TextView(getContext());
        t2v.setText(kurumTelefon);
        t2v.setTextColor(Color.RED);
        t2v.setGravity(Gravity.CENTER);
        t2v.setTextSize(15);
        tbrow.addView(t2v);
        TextView t3v = new TextView(getContext());
        t3v.setText("  "+kurumEmail);
        t3v.setTextColor(Color.RED);
        t3v.setGravity(Gravity.RIGHT);
        t3v.setTextSize(15);
        tbrow.addView(t3v);
        tableLayoutBagiscilariGoster.addView(tbrow);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void veriCek(final View RootView){
        DatabaseReference okuBagisYapanlar;
        tableLayoutBagiscilariGoster = (TableLayout) RootView.findViewById(R.id.tableLayoutBagiscilariGoster);
        initBaslangic();
        final boolean check = true;
        okuBagisYapanlar = db.getReference().child("Bagislar").child(bagis.getBagisid()).child("bagiscilar");
        okuBagisYapanlar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap myMap = (HashMap) dataSnapshot.getValue();
                if(myMap == null){
                    return;
                }
                String[] strings = (String[]) myMap.keySet().toArray(new String[myMap.size()]);



                for(int i=0;i<strings.length;i++){
                    DatabaseReference okuBagislar = db.getReference().child("Kullanicilar").child("Bireysel").child(strings[i]);
                    okuBagislar.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try{
                                Ad=dataSnapshot.child("ad").getValue().toString();
                                Soyad=dataSnapshot.child("soyad").getValue().toString();
                                Telefon=dataSnapshot.child("telefon").getValue().toString();
                                Email=dataSnapshot.child("email").getValue().toString();
                                init();

                            }catch(Exception e){
                                e.printStackTrace();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        okuBagisYapanlar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap myMap = (HashMap) dataSnapshot.getValue();
                if(myMap == null){
                    return;
                }
                String[] strings = (String[]) myMap.keySet().toArray(new String[myMap.size()]);



                for(int i=0;i<strings.length;i++){
                    DatabaseReference okuBagislar = db.getReference().child("Kullanicilar").child("Kurumsal").child(strings[i]);
                    okuBagislar.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try{
                                kurumAdi=dataSnapshot.child("ad").getValue().toString();
                                kurumTelefon=dataSnapshot.child("telefon").getValue().toString();
                                kurumEmail=dataSnapshot.child("email").getValue().toString();
                                initKurum();

                            }catch(Exception e){
                                e.printStackTrace();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
