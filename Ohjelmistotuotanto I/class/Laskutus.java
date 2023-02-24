package com.example.demo6;


public class Laskutus {

    /** Kenttien määrittely*/

    private String laskuID;
    private String aNimi;
    private String tunnus;
    private String yhteyshenkilo;
    private String katuosoite;
    private String postiNro;
    private String toimipaikka;
    private String puhelinnumero;
    private String sahkoposti;
    private String varaustunnus;
    private String aloituspvm;
    private String lopetuspvm;
    private String varattuTila;
    private double varausHinta;
    private boolean paperilasku;

    /** Konstruktori*/

    public Laskutus(String laskuID, String aNimi, String tunnus, String yhteyshenkilo, String katuosoite,
                    String postiNro, String toimipaikka, String puhelinnumero, String sahkoposti, String varaustunnus,
                    String aloituspvm, String lopetuspvm, String varattuTila, double varausHinta,
                    boolean paperilasku) {
        this.laskuID = laskuID;
        this.aNimi = aNimi;
        this.tunnus = tunnus;
        this.yhteyshenkilo = yhteyshenkilo;
        this.katuosoite = katuosoite;
        this.postiNro = postiNro;
        this.toimipaikka = toimipaikka;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
        this.varaustunnus = varaustunnus;
        this.aloituspvm = aloituspvm;
        this.lopetuspvm = lopetuspvm;
        this.varattuTila = varattuTila;
        this.varausHinta = varausHinta;
        this.paperilasku = paperilasku;
    }
    public Laskutus(){
    }

    /** Getterit ja setterit */

    public String getLaskuID() {
        return laskuID;
    }
    public void setLaskuID(String laskuID) {
        this.laskuID = laskuID;
    }
    public String getaNimi() {
        return aNimi;
    }
    public void setaNimi(String aNimi) {
        this.aNimi = aNimi;
    }
    public String getTunnus() {
        return tunnus;
    }
    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
    public String getYhteyshenkilo() {
        return yhteyshenkilo;
    }
    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
    }
    public String getKatuosoite() {
        return katuosoite;
    }
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    public String getPuhelinnumero() {
        return puhelinnumero;
    }
    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }
    public String getSahkoposti() {
        return sahkoposti;
    }
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
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
    public String getPostiNro() {
        return postiNro;
    }
    public void setPostiNro(String postiNro) {
        this.postiNro = postiNro;
    }
    public String getToimipaikka() {
        return toimipaikka;
    }
    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }
}
