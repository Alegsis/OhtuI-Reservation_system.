package com.example.demo6;

import java.util.ArrayList;

public class Toimipiste {

    /** Kenttien määrittelyä */

    public int toimipisteID;
    public String toimipisteNimi;
    public String kaupunki;
    public String tpKatuosoite;
    public String tpPostinumero;
    public String tpPostitoimipaikka;
    public String tpAktiivinen;
    public String tpMuutosPvm;
    public ArrayList<String> tpLista;

    /** Konstruktori */

    public Toimipiste(int toimipisteID, String toimipisteNimi ,String kaupunki, String tpKatuosoite, String tpPostinumero,
                      String tpPostitoimipaikka, String tpAktiivinen, String tpMuutosPvm ){
        this.toimipisteID = toimipisteID;
        this.toimipisteNimi = toimipisteNimi;
        this.kaupunki = kaupunki;
        this.tpKatuosoite = tpKatuosoite;
        this.tpPostinumero = tpPostinumero;
        this.tpPostitoimipaikka = tpPostitoimipaikka;
        this.tpAktiivinen = tpAktiivinen;
        this.tpMuutosPvm = tpMuutosPvm;
    }

    public Toimipiste() {
    }

    /** Getterit ja setterit */

    public int getToimipisteID() {
        return toimipisteID;
    }
    public void setToimipisteID(int toimipisteID) {
        this.toimipisteID = toimipisteID;
    }
    public String getToimipisteNimi() {
        return toimipisteNimi;
    }
    public void setToimipisteNimi(String toimipisteNimi) {
        this.toimipisteNimi = toimipisteNimi;
    }
    public String getKaupunki() {
        return kaupunki;
    }
    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }
    public String getTpKatuosoite() {
        return tpKatuosoite;
    }
    public void setTpKatuosoite(String tpKatuosoite) {
        this.tpKatuosoite = tpKatuosoite;
    }
    public String getTpPostinumero() {
        return tpPostinumero;
    }
    public void setTpPostinumero(String tpPostinumero) {
        this.tpPostinumero = tpPostinumero;
    }
    public String getTpPostitoimipaikka() {
        return tpPostitoimipaikka;
    }
    public void setTpPostitoimipaikka(String tpPostitoimipaikka) {
        this.tpPostitoimipaikka = tpPostitoimipaikka;
    }
    public String getTpAktiivinen() {
        return tpAktiivinen;
    }
    public void setTpAktiivinen(String tpAktiivinen) {
        this.tpAktiivinen = tpAktiivinen;
    }
    public String getTpMuutosPvm() {
        return tpMuutosPvm;
    }
    public void setTpMuutosPvm(String tpMuutosPvm) {
        this.tpMuutosPvm = tpMuutosPvm;
    }

}
