package com.example.demo6;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Toimitila extends Toimipiste {

    /** kenttien määrittely */

    public int toimitilaID;
    public String tilaNimi;
    public double Hinta;
    public int henkilomaara;
    public String ttAktiivinen;
    public LocalDate ttMuutosPvm;

    public int getToimitilaID() {
        return toimitilaID;
    }

    public void setToimitilaID(int toimitilaID) {
        this.toimitilaID = toimitilaID;
    }

    public String getTilaNimi() {
        return tilaNimi;
    }

    public void setTilaNimi(String tilaNimi) {
        this.tilaNimi = tilaNimi;
    }

    public double getHinta() {
        return Hinta;
    }

    public void setHinta(double hinta) {
        Hinta = hinta;
    }

    public int getHenkilomaara() {
        return henkilomaara;
    }

    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    public String getTtAktiivinen() {
        return ttAktiivinen;
    }

    public void setTtAktiivinen(String ttAktiivinen) {
        this.ttAktiivinen = ttAktiivinen;
    }

    public LocalDate getTtMuutosPvm() {
        return ttMuutosPvm;
    }

    public void setTtMuutosPvm(LocalDate ttMuutosPvm) {
        this.ttMuutosPvm = ttMuutosPvm;
    }

    public ArrayList<String> ttLista;

    /**
     * Konstruktori
     *
     * @param toimipisteID
     * @param toimipisteNimi
     * @param kaupunki
     * @param tpKatuosoite
     * @param tpPostinumero
     * @param tpPostitoimipaikka
     * @param tpAktiivinen
     * @param tpMuutosPvm
     */
/**
    public Toimitila(int toimipisteID, String toimipisteNimi, String kaupunki, String tpKatuosoite, String tpPostinumero, String tpPostitoimipaikka, String tpAktiivinen, String tpMuutosPvm,
                     int toimitilaID, String tilaNimi, int Hinta, int henkilomaara, String ttAktiivinen, String ttMuutosPvm) {
        super(toimipisteID, toimipisteNimi, kaupunki, tpKatuosoite, tpPostinumero, tpPostitoimipaikka, tpAktiivinen, tpMuutosPvm);
        this.toimitilaID= toimitilaID;
        this.tilaNimi= tilaNimi;
        this.Hinta= Hinta;
        this.henkilomaara= henkilomaara;
        this.ttAktiivinen= ttAktiivinen;
        this.ttMuutosPvm= ttMuutosPvm;
    }
 */
    public Toimitila(){
    }
}
