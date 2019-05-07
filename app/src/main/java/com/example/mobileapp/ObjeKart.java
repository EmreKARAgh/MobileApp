package com.example.mobileapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

public class ObjeKart extends android.support.v7.widget.CardView {
    Bagis bagis;
    Kurum kurum;
    public ObjeKart(@NonNull Context context,Bagis gelenBagis) {
        super(context);
        bagis=gelenBagis;
        this.setCardBackgroundColor(Color.parseColor("#ffffff"));
        this.setRadius((float)50F);
        this.addView(new ObjeLayout(getContext(), gelenBagis));


    }
    public ObjeKart(@NonNull Context context,Kurum gelenKurum) {
        super(context);
        kurum=gelenKurum;
        this.setCardBackgroundColor(Color.parseColor("#ffffff"));
        this.setRadius((float)50F);
        this.addView(new ObjeLayout(getContext(), gelenKurum));
    }
}
