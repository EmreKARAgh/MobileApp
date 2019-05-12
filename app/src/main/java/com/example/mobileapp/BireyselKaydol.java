package com.example.mobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class BireyselKaydol extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST=71;
    private StorageReference storageReferance;


    EditText edittextBireyselKaydolAd;
    String edittextBireyselKaydolAdtut;

    EditText edittextBireyselKaydolSoyad;
    String edittextBireyselKaydolSoyadtut;

    EditText edittextBireyselKaydolEmail;
    String edittextBireyselKaydolEmailtut;

    EditText edittextBireyselKaydolAdres;
    String edittextBireyselKaydolAdrestut;

    EditText edittextBireyselKaydolTelefon;
    String edittextBireyselKaydolTelefontut;

    EditText edittextBireyselKaydolKullaniciadi;
    String edittextBireyselKaydolKullaniciaditut;

    EditText edittextBireyselKaydolSifre;
    String edittextBireyselKaydolSifretut;

    EditText editTextBireyselKaydolSifreDogrulama;
    String edittextBireyselKaydolSifreDogrulamatut;

    EditText edittextBireyselKaydolSosyalmedya;
    String edittextBireyselKaydolSosyalmedyatut;

    Button buttonBireyselKaydolKayitol;
    Button buttonBireyselKaydolFotograf;

    ImageView imageviewBireyselKaydolFotograf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bireysel_kaydol);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        storageReferance = FirebaseStorage.getInstance().getReference();

        //mAuth.signOut();

        edittextBireyselKaydolAd = (EditText) findViewById(R.id.edittextBireyselKaydolAd);


        edittextBireyselKaydolSoyad = (EditText) findViewById(R.id.edittextBireyselKaydolSoyad);


        edittextBireyselKaydolEmail = (EditText) findViewById(R.id.edittextBireyselKaydolEmail);


        edittextBireyselKaydolAdres = (EditText) findViewById(R.id.edittextBireyselKaydolAdres);


        edittextBireyselKaydolTelefon = (EditText) findViewById(R.id.edittextBireyselKaydolTelefon);
        //edittextBireyselKaydolTelefon.addTextChangedListener(new PhoneNumberFormattingTextWatcher()); //Bunu bir dene


        edittextBireyselKaydolKullaniciadi = (EditText) findViewById(R.id.edittextBireyselKaydolKullaniciadi);


        edittextBireyselKaydolSifre = (EditText) findViewById(R.id.edittextBireyselKaydolSifre);

        editTextBireyselKaydolSifreDogrulama = (EditText) findViewById(R.id.edittextBireyselKaydolSifreDogrula);


        edittextBireyselKaydolSosyalmedya = (EditText) findViewById(R.id.edittextBireyselKaydolSosyalmedya);

        imageviewBireyselKaydolFotograf = (ImageView) findViewById(R.id.imageviewBireyselKaydolFotograf);

        buttonBireyselKaydolKayitol = (Button) findViewById(R.id.buttonBireyselKaydolKayitol);
        buttonBireyselKaydolKayitol.setOnClickListener(this);

        buttonBireyselKaydolFotograf = (Button) findViewById(R.id.buttonBireyselKaydolFotograf);
        buttonBireyselKaydolFotograf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println(" şifre=  "+edittextBireyselKaydolSifre.getText());
        System.out.println(" şifre dogrula=  "+editTextBireyselKaydolSifreDogrulama.getText());

        edittextBireyselKaydolSifretut = edittextBireyselKaydolSifre.getText().toString();
        edittextBireyselKaydolSifreDogrulamatut = editTextBireyselKaydolSifreDogrulama.getText().toString();

        if(v.getId() == buttonBireyselKaydolKayitol.getId()){

            if(!(edittextBireyselKaydolSifreDogrulamatut.equals(edittextBireyselKaydolSifretut))){



                Toast.makeText(getApplicationContext(),"Girilen Şifreler Birbirini Tutmuyo Hayırdır.",Toast.LENGTH_LONG).show();


            }else{


                edittextBireyselKaydolEmailtut = edittextBireyselKaydolEmail.getText().toString(); //Email tut
                edittextBireyselKaydolSifretut = edittextBireyselKaydolSifre.getText().toString();//Şifreyi tut
                if(edittextBireyselKaydolEmailtut.equals("") && edittextBireyselKaydolSifretut.equals("")){
                    Toast.makeText(getApplicationContext(),"Email ve Şifre Giriniz.",Toast.LENGTH_LONG).show();
                }else{
                    createAccount(edittextBireyselKaydolEmailtut,edittextBireyselKaydolSifretut);
                }
            }






        }

        else if(v.getId() == buttonBireyselKaydolFotograf.getId()){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction((Intent.ACTION_GET_CONTENT));
            startActivityForResult(Intent.createChooser(intent,"Select Pivture"),PICK_IMAGE_REQUEST);
        }
    }

    public void pushUser2DB(String Uid,String Email){
        edittextBireyselKaydolAdtut = edittextBireyselKaydolAd.getText().toString(); //Adını Tut
        edittextBireyselKaydolSoyadtut = edittextBireyselKaydolSoyad.getText().toString();//Soyadını tut
        edittextBireyselKaydolAdrestut = edittextBireyselKaydolAdres.getText().toString(); // Adresi tut
        edittextBireyselKaydolTelefontut = edittextBireyselKaydolTelefon.getText().toString(); //Telefonu Tut
        edittextBireyselKaydolKullaniciaditut = edittextBireyselKaydolKullaniciadi.getText().toString();//Kullanıcı adını tut
        edittextBireyselKaydolSosyalmedyatut = edittextBireyselKaydolSosyalmedya.getText().toString();//Sosyal medya tut



        Birey birey;
        boolean returned = uploadFile(Uid);
        if(returned == true){
            birey = new Birey(edittextBireyselKaydolAdtut, edittextBireyselKaydolSoyadtut, edittextBireyselKaydolAdrestut, edittextBireyselKaydolTelefontut,  edittextBireyselKaydolKullaniciaditut, edittextBireyselKaydolSosyalmedyatut, Email, (Uid+".jpg"), Uid);
        }
        else {
            birey = new Birey(edittextBireyselKaydolAdtut, edittextBireyselKaydolSoyadtut, edittextBireyselKaydolAdrestut, edittextBireyselKaydolTelefontut,  edittextBireyselKaydolKullaniciaditut, edittextBireyselKaydolSosyalmedyatut, Email, (" "), Uid);
        }
        DatabaseReference kullaniciEkle =db.getReference().child("Kullanicilar").child("Bireysel");
        kullaniciEkle.child(Uid).setValue(birey);

        Intent Anasayfa = new Intent(BireyselKaydol.this,Anasayfa.class);
        com.example.mobileapp.Anasayfa.currentBirey = birey;
        com.example.mobileapp.Anasayfa.kullaniciTipi = "Bireysel";
        startActivity(Anasayfa);
    }

    private void createAccount(String email, String password) {

        Log.d(TAG, "createAccount:" + email);

        //  showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            pushUser2DB(user.getUid(),user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            String Hata = ""+task.getException();

                            String[] kelime = null;
                            kelime = Hata.split(":");

                            if(kelime[1].equals(" The email address is badly formatted.")){
                                Toast.makeText(getApplicationContext(),"Email Formatı Hatalı.",Toast.LENGTH_LONG).show();
                            }

                            if(kelime[1].equals(" The given password is invalid. [ Password should be at least 6 characters ]")){
                                Toast.makeText(getApplicationContext(),"Şifre En Az 6 Haneli Olmalı.",Toast.LENGTH_LONG).show();
                            }
                        }

                        // ...
                    }
                });
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageviewBireyselKaydolFotograf.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private boolean uploadFile(String Uid){
        boolean returned = false;
        if(filePath != null){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference riversRef = storageReferance.child("kullanicilar/"+Uid+".jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"File Uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage() , Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int)progress)+"% Uploaded...");
                        }
                    });

            returned = true;
        }else{
            //filepath bos hata kısmı
        }
        return returned;
    }

}