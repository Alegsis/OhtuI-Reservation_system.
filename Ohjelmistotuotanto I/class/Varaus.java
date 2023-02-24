package com.example.demo6;

public class Varaus {

    /** Kenttien määrittely */

    public int varausID;
    public String varaustunnus;
    public String aloituspvm;
    public String lopetuspvm;
    public String varattuTila;
    public double varausHinta;
    public boolean paperilasku;

    /** konstruktori */

    public Varaus(int varausID, String varaustunnus, String aloituspvm, String lopetuspvm, String varattuTila,
                  double varausHinta, boolean paperilasku) {
        this.varausID = varausID;
        this.varaustunnus = varaustunnus;
        this.aloituspvm = aloituspvm;
        this.lopetuspvm = lopetuspvm;
        this.varattuTila = varattuTila;
        this.varausHinta = varausHinta;
        this.paperilasku = paperilasku;
    }

    public Varaus() {
    }

    /** Getterit ja setterit*/

    public int getVarausID() {
        return varausID;
    }
    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }
    public String getVaraustunnus() {
        return varaustunnus;
    }
    public void setVaraustunnus(String varaustunnus) {
        this.varaustunnus = varaustunnus;
    }
    public String getAloituspvm() {
        return aloituspvm;
    }
    public void setAloituspvm(String aloituspvm) {
        this.aloituspvm = aloituspvm;
    }
    public String getLopetuspvm() {
        return lopetuspvm;
    }
    public void setLopetuspvm(String lopetuspvm) {
        this.lopetuspvm = lopetuspvm;
    }
    public String getVarattuTila() {
        return varattuTila;
    }
    public void setVarattuTila(String varattuTila) {
        this.varattuTila = varattuTila;
    }
    public double getVarausHinta() {
        return varausHinta;
    }
    public void setVarausHinta(double varausHinta) {
        this.varausHinta = varausHinta;
    }
    public boolean isPaperilasku() {
        return paperilasku;
    }
    public void setPaperilasku(boolean paperilasku) {
        this.paperilasku = paperilasku;
    }

    @Override
    public String toString() {
        return "varausID=" + varausID +
                ", varaustunnus='" + varaustunnus + '\'' +
                ", aloituspvm='" + aloituspvm + '\'' +
                ", lopetuspvm='" + lopetuspvm + '\'' +
                ", varattuTila='" + varattuTila + '\'' +
                ", varausHinta=" + varausHinta +
                ", paperilasku=" + paperilasku;
    }

}
