package com.example.mobileapp;

public class Bagis {
    String baslik;
    String kurum;
    String bilgi;
    String ozet;
    String kullaniciKey;
    String resimKey;
    String bagisid;



    public Bagis(String baslik, String bilgi, String kurum, String ozet, String bagisid){
        this.baslik = baslik;
        this.kurum = kurum;
        this.bilgi = bilgi;
        this.ozet = ozet;
        this.bagisid = bagisid;
    }
    public Bagis(String baslik, String bilgi, String kurum, String ozet, String bagisid, String resimKey){
        this.baslik = baslik;
        this.kurum = kurum;
        this.bilgi = bilgi;
        this.ozet = ozet;
        this.bagisid = bagisid;
        this.resimKey = resimKey;
    }
    public Bagis(String baslik, String bilgi, String kurum, String ozet){
        this.baslik = baslik;
        this.kurum = kurum;
        this.bilgi = bilgi;
        this.ozet = ozet;
    }

    public String getBagisid() {
        return bagisid;
    }

    public void setBagisid(String bagisid) {
        this.bagisid = bagisid;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getKurum() {
        return kurum;
    }

    public void setKurum(String kurum) {
        this.kurum = kurum;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String getOzet() {
        return ozet;
    }

    public void setOzet(String ozet) {
        this.ozet = ozet;
    }
    public String getKullaniciKey() {
        return kullaniciKey;
    }

    public void setKullaniciKey(String kullaniciKey) {
        this.kullaniciKey = kullaniciKey;
    }

    public String getResimKey() {
        return resimKey;
    }

    public void setResimKey(String resimKey) {
        this.resimKey = resimKey;
    }
}
