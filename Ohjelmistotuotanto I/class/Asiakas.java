package com.example.demo6;
public class Asiakas {

    /** Pakolliset määrittelyt asiakasluokan kentille  */

    private int asiakasID;
    private String aNimi;
    private String tunnus;
    private String yhteyshenkilo;
    private String katuosoite;
    private String postiNro;
    private String toimipaikka;
    private String puhelinnumero;
    private String sahkoposti;


    /** konstruktori */

    public Asiakas(int asiakasID, String aNimi ,String tunnus, String yhteyshenkilo, String katuosoite,
                   String postiNro,String toimipaikka,String puhelinnumero, String sahkoposti ){
        this.asiakasID = asiakasID;
        this.aNimi = aNimi;
        this.tunnus = tunnus;
        this.yhteyshenkilo = yhteyshenkilo;
        this.katuosoite = katuosoite;
        this.postiNro = postiNro;
        this.toimipaikka = toimipaikka;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
    }

public Asiakas(){

}
    /**Getterit ja setterit*/

    public int getAsiakasID() {
        return asiakasID;
    }
    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
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
    public String getPostiNro() {
        return postiNro;
    }
    public void setPostiNro(String postiNroToimipaikka) {
        this.postiNro = postiNroToimipaikka;
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
    public String getToimipaikka() {
        return toimipaikka;
    }
    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }

    @Override
    public String toString() {
        return "Asiakkaan tiedot \n" +
                "Nimi: '" + aNimi + "'\n"  +
                "Tunnus: '" + tunnus + "'\n"  +
                "Yhteyshenkilö: '" + yhteyshenkilo + "'\n"  +
                "Katuosoite: '" + katuosoite + "'\n"  +
                "Postinumero: '" + postiNro + "'\n"  +
                "Postitoimipaikka: '" + toimipaikka + "'\n"  +
                "Puhelinnumero: '" + puhelinnumero + "'\n"  +
                "Sähköposti: '" + sahkoposti;
    }
}

