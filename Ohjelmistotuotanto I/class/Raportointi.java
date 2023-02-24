package com.example.demo6;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Raportointi{

    /** Kenttien määrittely */
    Text teksti = new Text();

    private String alkupvm;
    private String loppupvm;
    private ArrayList<String> toimipistelistaus;
    private ArrayList<String> valitutToimipisteet;
    private ArrayList<String> laitteetjapalvelut;
    private ArrayList<String> valitutLaitteetJaPalvelut;

    /** Konstruktori */

    public Raportointi(String alkupvm, String loppupvm, ArrayList<String> toimipistelistaus,
                       ArrayList<String> valitutToimipisteet, ArrayList<String> laitteetjapalvelut,
                       ArrayList<String> valitutLaitteetJaPalvelut) {
        this.alkupvm = alkupvm;
        this.loppupvm = loppupvm;
        this.toimipistelistaus = toimipistelistaus;
        this.valitutToimipisteet = valitutToimipisteet;
        this.laitteetjapalvelut = laitteetjapalvelut;
        this.valitutLaitteetJaPalvelut = valitutLaitteetJaPalvelut;
    }
    public Raportointi(){
    }




    /** Getterit ja setterit */

    public String getAlkupvm() {
        return alkupvm;
    }
    public void setAlkupvm(String alkupvm) {
        this.alkupvm = alkupvm;
    }
    public String getLoppupvm() {
        return loppupvm;
    }
    public void setLoppupvm(String loppupvm) {
        this.loppupvm = loppupvm;
    }

    /*
    public static ArrayList<String> lueToimipisteet(){
        ArrayList <String> nimilista =  new ArrayList<String>(
                Arrays.asList("Kalle", "Ville", "Sakari", "Heikki", "Armas", "Toivo", "Kalevi"));
        return nimilista;
    }


 */


}
