package com.example.demo6;

import java.util.ArrayList;

public class LaitteetJaPalvelut {

    /** Kenttien määrittelyä */

    public int lpID;
    public String lpNimi;
    public int lpHinta;
    public String lpKuvaus;
    public String lpAktiivinen;
    public String lpMuutosPvm;
    public ArrayList<String> lpLista;
//    public String toimiTila;
    /** konstruktori */

    public LaitteetJaPalvelut(int lpID, String lpNimi, int lpHinta, String lpKuvaus, String lpAktiivinen, String lpMuutosPvm) {
        this.lpID = lpID;
        this.lpNimi = lpNimi;
        this.lpHinta = lpHinta;
        this.lpKuvaus = lpKuvaus;
        this.lpAktiivinen = lpAktiivinen;
        this.lpMuutosPvm = lpMuutosPvm;
    }
    public LaitteetJaPalvelut(){
    }

    /** getterit ja setterit */
    public int getLpID() {
        return lpID;
    }
    public void setLpID(int lpID) {
        this.lpID = lpID;
    }
    public String getLpNimi() {
        return lpNimi;
    }
    public void setLpNimi(String lpNimi) {
        this.lpNimi = lpNimi;
    }
    public int getLpHinta() {
        return lpHinta;
    }
    public void setLpHinta(int lpHinta) {
        this.lpHinta = lpHinta;
    }
    public String getLpKuvaus() {
        return lpKuvaus;
    }
    public void setLpKuvaus(String lpKuvaus) {
        this.lpKuvaus = lpKuvaus;
    }
    public String getLpAktiivinen() {
        return lpAktiivinen;
    }
    public void setLpAktiivinen(String lpAktiivinen) {
        this.lpAktiivinen = lpAktiivinen;
    }
    public String getLpMuutosPvm() {
        return lpMuutosPvm;
    }
    public void setLpMuutosPvm(String lpMuutosPvm) {
        this.lpMuutosPvm = lpMuutosPvm;
    }

}
