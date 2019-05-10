package com.example.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfiliDuzenleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfiliDuzenleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfiliDuzenleFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfiliDuzenleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfiliDuzenleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfiliDuzenleFragment newInstance(String param1, String param2) {
        ProfiliDuzenleFragment fragment = new ProfiliDuzenleFragment();
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

    EditText editTextProfiliDuzenleAd,editTextProfiliDuzenleSoyad,editTextProfiliDuzenleEmail,
            editTextProfiliDuzenleTelefon,editTextProfiliDuzenleSifre,editTextProfiliDuzenleKullaniciadi,
            editTextProfiliDuzenleAdres,editTextProfiliDuzenleSosyalmedya;

    String editTextProfiliDuzenleAdtut,editTextProfiliDuzenleSoyadtut,editTextProfiliDuzenleEmailtut,
            editTextProfiliDuzenleTelefontut,editTextProfiliDuzenleSifretut,editTextProfiliDuzenleKullaniciaditut,
            editTextProfiliDuzenleAdrestut,editTextProfiliDuzenleSosyalmedyatut;

    Button buttonProfiliDuzenleGuncelle;

    String ad,soyad,adres,telefon,kullaniciAdi,sosyalMedya,email,resimKey;
    String kurumAdi,kurumNo;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_profili_duzenle, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();

        editTextProfiliDuzenleAd=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleAd);
        editTextProfiliDuzenleEmail=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleEmail);
        editTextProfiliDuzenleSoyad=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleSoyad);
        editTextProfiliDuzenleTelefon=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleTelefon);
        editTextProfiliDuzenleKullaniciadi=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleKullaniciadi);
        editTextProfiliDuzenleAdres=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleAdres);
        editTextProfiliDuzenleSosyalmedya=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleSosyalmedya);
        editTextProfiliDuzenleSifre=(EditText)RootView.findViewById(R.id.editTextProfiliDuzenleSifre);
        buttonProfiliDuzenleGuncelle=(Button)RootView.findViewById(R.id.buttonProfiliDuzenleGuncelle);
        buttonProfiliDuzenleGuncelle.setOnClickListener(this);

        verileriCekBireysel(user.getUid());

        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();

        return RootView;
    }
    Birey birey;

    @Override
    public void onClick(View view) {
        if(view.getId()==buttonProfiliDuzenleGuncelle.getId()){
            editTextProfiliDuzenleAdtut = editTextProfiliDuzenleAd.getText().toString();
            editTextProfiliDuzenleSoyadtut = editTextProfiliDuzenleSoyad.getText().toString();
            editTextProfiliDuzenleAdrestut = editTextProfiliDuzenleAdres.getText().toString();
            editTextProfiliDuzenleTelefontut = editTextProfiliDuzenleTelefon.getText().toString();
            editTextProfiliDuzenleKullaniciaditut = editTextProfiliDuzenleKullaniciadi.getText().toString();
            editTextProfiliDuzenleSosyalmedyatut = editTextProfiliDuzenleSosyalmedya.getText().toString();
            editTextProfiliDuzenleEmailtut = editTextProfiliDuzenleEmail.getText().toString();
            editTextProfiliDuzenleSifretut = editTextProfiliDuzenleSifre.getText().toString();


            birey = new Birey(editTextProfiliDuzenleAdtut, editTextProfiliDuzenleSoyadtut, editTextProfiliDuzenleAdrestut, editTextProfiliDuzenleTelefontut,  editTextProfiliDuzenleKullaniciaditut, editTextProfiliDuzenleSosyalmedyatut, editTextProfiliDuzenleEmailtut, (resimKey+".jpg"), user.getUid());

            DatabaseReference kullaniciEkle =db.getReference().child("Kullanicilar").child("Bireysel");
            kullaniciEkle.child(user.getUid()).setValue(birey);

            user.updateEmail(editTextProfiliDuzenleEmailtut)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User email address updated.");
                            }
                        }
                    });


            String newPassword = editTextProfiliDuzenleSifretut;

            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated.");
                            }
                        }
                    });

            Toast.makeText(getContext(),"Verileriniz Güncellenmiştir.",Toast.LENGTH_LONG).show();
        }
    }

    private void verileriCekBireysel(String Uid){
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        DatabaseReference okuBagislar =db.getReference().child("Kullanicilar").child("Bireysel").child(Uid);
        okuBagislar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ad = dataSnapshot.child("ad").getValue().toString();
                editTextProfiliDuzenleAd.setText(ad);

                soyad = dataSnapshot.child("soyad").getValue().toString();
                editTextProfiliDuzenleSoyad.setText(soyad);

                adres = dataSnapshot.child("adres").getValue().toString();
                editTextProfiliDuzenleAdres.setText(adres);

                telefon = dataSnapshot.child("telefon").getValue().toString();
                editTextProfiliDuzenleTelefon.setText(telefon);

                kullaniciAdi = dataSnapshot.child("kullaniciAdi").getValue().toString();
                editTextProfiliDuzenleKullaniciadi.setText(kullaniciAdi);

                sosyalMedya = dataSnapshot.child("sosyalMedya").getValue().toString();
                editTextProfiliDuzenleSosyalmedya.setText(sosyalMedya);

                email = dataSnapshot.child("email").getValue().toString();
                editTextProfiliDuzenleEmail.setText(email);



                resimKey = " ";
                try {
                    resimKey = dataSnapshot.child("resimKey").getValue().toString();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
