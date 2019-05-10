package com.example.mobileapp;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UygulamaGiris extends AppCompatActivity implements View.OnClickListener {

    Button buttonUygulamaGirisGiris;
    Button buttonUygulamaGirisKayitol;
    Button buttonAdmin;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama_giris);

        DatabaseReference okuBagislar = db.getReference().child("version");

        okuBagislar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String app_version = dataSnapshot.getValue().toString();
                String versionName = BuildConfig.VERSION_NAME;
                if(!(app_version.equals(versionName))){
                    alertDialog();
                }
                else{
                    buttonUygulamaGirisGiris = (Button) findViewById(R.id.buttonUygulamaGirisGiris);
                    buttonUygulamaGirisGiris.setOnClickListener(UygulamaGiris.this);
                    buttonUygulamaGirisKayitol = (Button) findViewById(R.id.buttonUygulamaGirisKayitol);
                    buttonUygulamaGirisKayitol.setOnClickListener(UygulamaGiris.this);
                    buttonAdmin=(Button)findViewById(R.id.adminGiris);
                    buttonAdmin.setOnClickListener(UygulamaGiris.this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonAdmin.getId()){
       //       Intent Anasayfa=new Intent(UygulamaGiris.this, com.example.mobileapp.Anasayfa.class);
         //     startActivity(Anasayfa);
            Toast.makeText(getApplicationContext(),"Bu Özellik Şimdilik Kullanım Dışı",Toast.LENGTH_SHORT).show();

        }
        else if(v.getId() == buttonUygulamaGirisGiris.getId()){
            Intent Giris = new Intent(UygulamaGiris.this,Giris.class);
            startActivity(Giris);
        }
        else if(v.getId() == buttonUygulamaGirisKayitol.getId()){
            Intent KayitOl = new Intent(UygulamaGiris.this,KayitOl.class);
            startActivity(KayitOl);
        }
    }
    private void alertDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Üzgünüz Bu Sürüm Artık Desteklenmiyor");
        builder1.setCancelable(false);

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
