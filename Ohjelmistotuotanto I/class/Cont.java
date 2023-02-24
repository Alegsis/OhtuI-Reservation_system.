package com.example.demo6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import static javafx.application.Platform.exit;

public class Cont {

    private Stage stage;
    private Scene scene;
    private Parent root;

    String toimiTilanNimi;
    String alkupvm;
    String loppupvm;

    //Asiakastiedot.fxml tiedot
    @FXML private DatePicker datepickalkupvmat;
    @FXML private DatePicker datepickloppupvmat;
    @FXML private TextField textfieldtunnusat;
    @FXML private TextField textfieldpuhelinnumeroat;
    @FXML private TextField textfieldsahkopostiat;
    @FXML private TextField textfieldnimiat;
    @FXML private TextField textfieldosoiteat;
    @FXML private TextField textfieldyhteyshenkiloat;
    @FXML private TextField textfieldvaraustunnusat;
    @FXML private TextField textfieldtoimipaikkaat;
    @FXML private TextField textfieldpostinumeroat;
    @FXML private ListView<String> toimiPisteAsiakas = new ListView<>();
    @FXML private ListView<String> toimitilaAsiakas = new ListView<>();
    @FXML private ListView<String> laitepalveluAsiakas = new ListView<>();
    String muuttuja;
    String muuttujaToimitilaID;
    String AsiakasID;
    String VarausID;
    String LPlistausMuuttuja;
    String laitteetpalvelutID;
    String toimipisteenNimi;

    //Toimipistetiedot.fxml tiedot
    @FXML private TextField textfieldtoimipisteennimitp;
    @FXML private TextField textfieldkaupunkitp;
    @FXML private TextField textfieldosoitetp;
    @FXML private TextField textfieldpostinumerotp;
    @FXML private TextField textfieldpostitoimipaikkatp;
    @FXML private TextField textfieldtoimitilatp;
    @FXML private TextField textfieldhintatp;
    @FXML private TextField textfieldhenkilomaaratp;
    @FXML private TextField textfieldtoimipisteAktiv;
    @FXML public ListView<String> lvtoimipiste = new ListView<>();
    @FXML public ListView<String> lvtoimitilat = new ListView<>();

    //Toimitilojen fxml-tiedot
    @FXML private TextField textfieldtoimitilapiste;
    @FXML private TextField textfieldtoimitilanimi;
    @FXML private TextField textfieldhinta;
    @FXML private TextField textfieldhenkilomaara;
    @FXML private TextField textfieldtoimitilaAktiv;

    //laitteet ja palvelut tiedot
    @FXML private TextField textfieldlaitepalvelulp;
    @FXML private TextField textfieldhintalp;
    @FXML private TextArea textareakuvauslp;
    @FXML private TextField textfieldlpToimipiste;
    @FXML private ListView<String> lvlaitteetjapalvelut = new ListView<>();
    @FXML private TextField textfieldAktiivisuusLP;

    //Raportointi tiedot
    @FXML private ListView<String> LVraportointi = new ListView<>();
    @FXML private DatePicker datepickalkupvmrp;
    @FXML private DatePicker datepickloppupvmrp;

    //Varausten tiedot
    String varaustunnus;
    String aloituspvm;
    String lopetuspvm;
    private ObservableList<String> valitutLaitteetJaPalvelut;
    private String asiakkaanVaraustunnus;

    //Laskutuksen tiedot
    @FXML private TextField textfieldnimila;
    @FXML private TextField textfieldtunnusla;
    @FXML private TextField textfieldyhteyshenkilola;
    @FXML private TextField textfieldvarausla;
    @FXML private TextField textfieldpuhelinnrola;
    @FXML private TextField textfieldsahkopostila;
    @FXML private TextField textfieldosoitela;
    @FXML private TextField textfieldpostinrola;
    @FXML private TextField textfieldpostitoimipaikkala;
    @FXML private TextField textfieldalkupvmla;
    @FXML private TextField textfieldloppupvm;
    @FXML private TextField textfieldvarattutoimitilala;
    @FXML private TextField textfieldvaraushintala;
    @FXML private ListView<String> lvlaskutuslp = new ListView<>();
    private ArrayList<String> laitepalvelulista = new ArrayList<>();
    @FXML private CheckBox checkboxsplasku;

    //Getterit ja setterit joillekin kentille

    /**
     * Muuttaa datepickerin päivämäärän oikeaan muotoon sql:lle
     */
    private void pvmmuutin() {
        int alkuvuosi = datepickalkupvmat.getValue().getYear();
        int alkukuukausi = datepickalkupvmat.getValue().getMonthValue();
        int alkupaiva = datepickalkupvmat.getValue().getDayOfMonth();
        int loppuvuosi = datepickloppupvmat.getValue().getYear();
        int loppukuukausi = datepickloppupvmat.getValue().getMonthValue();
        int loppupaiva = datepickloppupvmat.getValue().getDayOfMonth();
        String skuukausi = Integer.toString(alkukuukausi);
        String spaiva = Integer.toString(alkupaiva);
        String lkuukausi = Integer.toString(loppukuukausi);
        String lpaiva = Integer.toString(loppupaiva);
        if (skuukausi.length() == 1) {
            skuukausi = "0" + skuukausi;
        }
        if (lkuukausi.length() == 1) {
            lkuukausi = "0" + lkuukausi;
        }
        if (spaiva.length() == 1) {
            spaiva = "0" + spaiva;
        }
        if (lpaiva.length() == 1) {
            lpaiva = "0" + lpaiva;
        }
        alkupvm = this.alkupvm = alkuvuosi + "-" + skuukausi + "-" + spaiva;
        loppupvm = this.loppupvm = loppuvuosi + "-" + lkuukausi + "-" + lpaiva;
        System.out.println(alkupvm);
        System.out.println(loppupvm);
    }
    //Käyttöliittymän metodeja. Näiden avulla voidaan navigoida näkymästä toiseen.
    public void alkuvalikko(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Alkunäkymä.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public void asiakastiedot(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Asiakastiedot.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public void toimipisteet(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Toimipisteet.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public void raportointi(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Raportointi.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
        naytaHalytys(Alert.AlertType.INFORMATION,"Työnalla oleva scene");
    }

    public void laskutus(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Laskutus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public void laitteetjapalvelut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Laitteetjapalvelut.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public void sulje(ActionEvent event){
        exit();
    }

    /**
     * Hae asiakas tiedot tunnuksen perusteella, asiakkaan tunnus jää näkyville ja palautetaan virhe
     * mikäli asiakasta ei löydy.
     * Jos asiakas löytyy asiakkaan tiedot haetaan kenttiin.
     *
     */
    public void haeTunnus(ActionEvent event) throws Exception {

        if (textfieldtunnusat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Ei haettavaa tietoa kentässä");
        } else {

            try {
                Tietokanta.TietokannanAvaus();
                Asiakas tilanVuokraaja = new Asiakas();
                String tunnustesti = textfieldtunnusat.getText();
                String asiakasTietue = "SELECT asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Sahkoposti, asiakas.Puhelinnumero, " +
                        "asiakas.Postinumero, asiakas.Katuosoite, asiakas.Postitoimipaikka, asiakas.Tunnus FROM asiakas WHERE Tunnus = " + "'" + tunnustesti + "'";

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(asiakasTietue);
                    System.out.println(asiakasTietue);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println(SQLPalaute);
                    if (!SQLPalaute.next()) {
                        textfieldtunnusat.setText(tunnustesti);
                        naytaHalytys(Alert.AlertType.ERROR, "Asiakasta ei löydy, täytä asiakkaan tiedot ja lisää asiakas. Tämän jälkeen tee varaus normaalisti.");
                        System.out.println("DEBUG: Asiakasta ei löydy tietokannasta");
                        textfieldtunnusat.setText(tunnustesti);
                    } else {
                        //textfieldtunnusat.setText(tilanVuokraaja.getTunnus());
                        if (SQLPalaute.getString(1) != null) {
                            System.out.println(SQLPalaute);
                            tilanVuokraaja.setaNimi(SQLPalaute.getString("Asiakas.Nimi"));
                            tilanVuokraaja.setYhteyshenkilo(SQLPalaute.getString("Asiakas.Yhteyshenkilo"));
                            tilanVuokraaja.setKatuosoite(SQLPalaute.getString("Asiakas.Katuosoite"));
                            tilanVuokraaja.setPostiNro(SQLPalaute.getString("Asiakas.Postinumero"));
                            tilanVuokraaja.setToimipaikka(SQLPalaute.getString("Asiakas.Postitoimipaikka"));
                            tilanVuokraaja.setPuhelinnumero(SQLPalaute.getString("Asiakas.Puhelinnumero"));
                            tilanVuokraaja.setSahkoposti(SQLPalaute.getString("Asiakas.sahkoposti"));
                        }
                    }
                } catch (Exception se) {
                    throw se;
                }

                textfieldnimiat.setText(tilanVuokraaja.getaNimi());
                textfieldtunnusat.setText(tunnustesti); //palautetaan tekstikenttään syötetty arvo.
                textfieldpuhelinnumeroat.setText(tilanVuokraaja.getPuhelinnumero());
                textfieldsahkopostiat.setText(tilanVuokraaja.getSahkoposti());
                textfieldosoiteat.setText(tilanVuokraaja.getKatuosoite());
                textfieldyhteyshenkiloat.setText(tilanVuokraaja.getYhteyshenkilo());
                textfieldtoimipaikkaat.setText(tilanVuokraaja.getToimipaikka());
                textfieldpostinumeroat.setText(tilanVuokraaja.getPostiNro());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Haetaan tietokannasta
     *
     * @param event
     * @throws Exception
     */
    public void haeVaraustunnus(ActionEvent event) throws Exception {

        if (textfieldvaraustunnusat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Ei haettavaa tietoa kentässä");
        } else {

            try {
                Tietokanta.TietokannanAvaus();
                /**
                 * Tällä saa haettua tietokannasta jotain kivaa. Käy vaikka kokeilee.
                 */
                String varaustunnustesti = textfieldvaraustunnusat.getText();
                String asiakas2Tietue = "SELECT varaus.Varaustunnus, varaus.aloitusPVM, varaus.lopetusPVM, toimitila.Nimi, toimipiste.Nimi, toimitila.Henkilomaara, varaus.toimitilaID, " +
                        "laitteetpalvelut.Nimi, asiakas.Tunnus, asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Sahkoposti, " +
                        "asiakas.Puhelinnumero, asiakas.Postinumero, asiakas.Katuosoite, asiakas.Postitoimipaikka " +
                        "FROM varaus, toimitila, toimipiste, varauslaitteetpalvelut, laitteetpalvelut, asiakas " +
                        "WHERE varaus.ToimitilaID = toimitila.ToimitilaID AND " +
                        "toimitila.ToimipisteID = toimipiste.ToimipisteID AND " +
                        "varaus.VarausID = varauslaitteetpalvelut.VarausID AND " +
                        "varauslaitteetpalvelut.LaitteetPalvelutID = laitteetpalvelut.LaitteetPalvelutID AND " +
                        "varaus.AsiakasID = asiakas.AsiakasID AND " +
                        "varaus.Varaustunnus = " + "'" + varaustunnustesti + "'";


                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(asiakas2Tietue);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println("DEBUG " + asiakas2Tietue);


                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }

                Asiakas tilanVuokraaja = new Asiakas();
                Varaus varauksentiedot = new Varaus();
                try {

                    if (SQLPalaute.next()) {
                        tilanVuokraaja.setaNimi(SQLPalaute.getString("asiakas.Nimi"));
                        tilanVuokraaja.setTunnus(SQLPalaute.getString("Tunnus"));
                        tilanVuokraaja.setYhteyshenkilo(SQLPalaute.getString("Yhteyshenkilo"));
                        tilanVuokraaja.setKatuosoite(SQLPalaute.getString("Katuosoite"));
                        tilanVuokraaja.setPostiNro(SQLPalaute.getString("Postinumero"));
                        tilanVuokraaja.setToimipaikka(SQLPalaute.getString("Postitoimipaikka"));
                        tilanVuokraaja.setPuhelinnumero(SQLPalaute.getString("Puhelinnumero"));
                        tilanVuokraaja.setSahkoposti(SQLPalaute.getString("sahkoposti"));
                        varauksentiedot.setVaraustunnus(SQLPalaute.getString("Varaustunnus"));
                        varauksentiedot.setAloituspvm(SQLPalaute.getString("aloitusPVM"));
                        varauksentiedot.setLopetuspvm(SQLPalaute.getString("lopetusPVM"));
                        varauksentiedot.setVarattuTila(SQLPalaute.getString("ToimitilaID"));
                    }
                } catch (Exception se) {
                    throw se;
                }
                textfieldnimiat.setText(tilanVuokraaja.getaNimi());
                textfieldtunnusat.setText(tilanVuokraaja.getTunnus());
                textfieldpuhelinnumeroat.setText(tilanVuokraaja.getPuhelinnumero());
                textfieldsahkopostiat.setText(tilanVuokraaja.getSahkoposti());
                textfieldosoiteat.setText(tilanVuokraaja.getKatuosoite());
                textfieldyhteyshenkiloat.setText(tilanVuokraaja.getYhteyshenkilo());
                textfieldtoimipaikkaat.setText(tilanVuokraaja.getToimipaikka());
                textfieldpostinumeroat.setText(tilanVuokraaja.getPostiNro());
                textfieldvaraustunnusat.setText(varauksentiedot.getVaraustunnus());
                datepickalkupvmat.setValue(LocalDate.parse(varauksentiedot.getAloituspvm()));
                datepickloppupvmat.setValue(LocalDate.parse(varauksentiedot.getLopetuspvm()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Tässä on toimipisteelle kuuluvien nappuloiden toiminnallisuuksia
     */
    //Tässä on jokin pohja tuolle toimipistenäkymässä olevalle "toimipistehaku"-painikkeelle.
    //Version 1.3, hakunappula näyttäisi toimivan ihan ok. Vähän vaatii jonkinlaista siistimistä, mutta hyvä tulee!
    public void toimipisteKysely(ActionEvent event) throws Exception {
        if (textfieldtoimipisteennimitp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Ei haettavaa tietoa kentässä");
        } else {

            try {
                Tietokanta.TietokannanAvaus();

                String nimiTeksti = textfieldtoimipisteennimitp.getText();
                String toimipisteTietue = "SELECT toimipiste.Nimi, toimipiste.Kaupunki, toimipiste.Katuosoite, " +
                        "toimipiste.Postinumero, toimipiste.Postitoimipaikka, toimipiste.Aktiivisuus " +
                        "FROM toimipiste WHERE Toimipiste.Nimi = " + "'" + nimiTeksti + "'";

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipisteTietue);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println("DEBUG " + toimipisteTietue);

                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }

                Toimipiste toimipistetiedot = new Toimipiste();
                try {
                    if (SQLPalaute.next()) {
                        toimipistetiedot.setToimipisteNimi(SQLPalaute.getString("Nimi"));
                        toimipistetiedot.setKaupunki(SQLPalaute.getString("Kaupunki"));
                        toimipistetiedot.setTpKatuosoite(SQLPalaute.getString("Katuosoite"));
                        toimipistetiedot.setTpPostinumero(SQLPalaute.getString("Postinumero"));
                        toimipistetiedot.setTpPostitoimipaikka(SQLPalaute.getString("Postitoimipaikka"));
                        toimipistetiedot.setTpAktiivinen(SQLPalaute.getString("Aktiivisuus"));

                    }
                } catch (Exception se) {
                    throw se;
                }
                textfieldtoimipisteennimitp.setText(toimipistetiedot.getToimipisteNimi());
                textfieldkaupunkitp.setText(toimipistetiedot.getKaupunki());
                textfieldosoitetp.setText(toimipistetiedot.getTpKatuosoite());
                textfieldpostinumerotp.setText(toimipistetiedot.getTpPostinumero());
                textfieldpostitoimipaikkatp.setText(toimipistetiedot.getTpPostitoimipaikka());
                textfieldtoimipisteAktiv.setText(toimipistetiedot.getTpAktiivinen());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Voidaan lisätä uusi toimipiste
     */
    //Toistaikseksi antaa lisätä "tyhjiä" toimipisteitä
    public void lisaaToimipiste(ActionEvent event) throws Exception {

        if (textfieldtoimipisteennimitp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldkaupunkitp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldosoitetp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldpostinumerotp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldpostitoimipaikkatp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldtoimipisteAktiv.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else {

            Tietokanta.TietokannanAvaus();

            // Asetetaan SQL-lausekkeelle pohja tietojen tallennusta varten.
            String sql = "INSERT INTO toimipiste "
                    + "(Nimi, Kaupunki, Katuosoite, Postinumero, Postitoimipaikka, Aktiivisuus, AktiivisuusMuutosPVM) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            ResultSet tulosjoukko = null;
            PreparedStatement lause = null;

            Toimipiste valimuuttuja = new Toimipiste();

            valimuuttuja.setToimipisteNimi(String.valueOf(textfieldtoimipisteennimitp.getText()));
            valimuuttuja.setKaupunki(String.valueOf(textfieldkaupunkitp.getText()));
            valimuuttuja.setTpKatuosoite(String.valueOf(textfieldosoitetp.getText()));
            valimuuttuja.setTpPostinumero(String.valueOf(textfieldpostinumerotp.getText()));
            valimuuttuja.setTpPostitoimipaikka(String.valueOf(textfieldpostitoimipaikkatp.getText()));
            valimuuttuja.setTpAktiivinen(String.valueOf(textfieldtoimipisteAktiv.getText()));
            valimuuttuja.setTpMuutosPvm(String.valueOf(LocalDate.now()));

            try {
                //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                lause = Tietokanta.tietoKanta.prepareStatement(sql);
                //lisätään tiedot haluttuihin parametreihin
                lause.setString(1, valimuuttuja.getToimipisteNimi());
                lause.setString(2, valimuuttuja.getKaupunki());
                lause.setString(3, valimuuttuja.getTpKatuosoite());
                lause.setString(4, valimuuttuja.getTpPostinumero());
                lause.setString(5, valimuuttuja.getTpPostitoimipaikka());
                lause.setString(6, String.valueOf(valimuuttuja.getTpAktiivinen()));
                lause.setString(7, valimuuttuja.getTpMuutosPvm());

                // suorita sql-lause
                int lkm = lause.executeUpdate();

                //	System.out.println("lkm " + lkm);
                if (lkm == 0) {
                    throw new Exception("Asiakaan lisaaminen ei onnistu");
                }
            } catch (Exception e) {
                throw e;
            }
            System.out.println("importtaus onnistui");
        }
    }

    public void muutaToimipiste(ActionEvent event) throws Exception {

        if (textfieldtoimipisteennimitp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldkaupunkitp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldosoitetp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldpostinumerotp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldpostitoimipaikkatp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldtoimipisteAktiv.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else {

            String nimiTeksti = textfieldtoimipisteennimitp.getText();
            String toimipisteTietue = "SELECT toimipiste.Nimi, toimipiste.Kaupunki, toimipiste.Katuosoite, toimipiste.Postinumero, toimipiste.Postitoimipaikka " +
                    "FROM toimipiste WHERE toimipiste.Nimi = " + "'" + nimiTeksti + "'";
            System.out.println(toimipisteTietue);

            ResultSet SQLPalaute = null;
            PreparedStatement lause = null;
            try {
                lause = Tietokanta.tietoKanta.prepareStatement(toimipisteTietue);
                SQLPalaute = lause.executeQuery();
                System.out.println("rivi 618 " + toimipisteTietue);

                //Virhekoodin syöttämisessä on jotain hämminkiä.
                if (SQLPalaute == null) {
                    System.out.println("rivi 623, tähän vois heittää kanssa sellaisen pop-upin ");
                    throw new Exception("Asiakasta ei loydy tietokannasta");

                }
            } catch (Exception se) {
                throw se;
            }
            // ylikirjoitetaan aikaisempi SQL-lauseke
            toimipisteTietue = "UPDATE  toimipiste "
                    + "SET toimipiste.Kaupunki = ?, toimipiste.Katuosoite = ?, toimipiste.Postinumero = ?, toimipiste.Postitoimipaikka = ?, toimipiste.aktiivisuus = ? "
                    + " WHERE toimipiste.Nimi = ?";

            lause = null;
            try {
                // luo PreparedStatement-olio sql-lauseelle
                lause = Tietokanta.tietoKanta.prepareStatement(toimipisteTietue);

                // laitetaan olion attribuuttien arvot UPDATEen

                lause.setString(1, String.valueOf(textfieldkaupunkitp.getText()));
                lause.setString(2, String.valueOf(textfieldosoitetp.getText()));
                lause.setString(3, String.valueOf(textfieldpostinumerotp.getText()));
                lause.setString(4, String.valueOf(textfieldpostitoimipaikkatp.getText()));
                lause.setString(5, String.valueOf(textfieldtoimipisteAktiv.getText()));
                lause.setString(6, String.valueOf(textfieldtoimipisteennimitp.getText()));


                lause.executeUpdate();

                System.out.println("tiedot päivitetty! JIHUU!!!");

            } catch (Exception e) {
                // JDBC ym. virheet
                throw e;
            }
        }
    }

    public void toimitilaHaku(ActionEvent event) throws Exception {

        if (textfieldtoimitilanimi.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Ei haettavaa tietoa kentässä");
        } else {

            try {
                Tietokanta.TietokannanAvaus();

                String nimiTeksti = textfieldtoimitilanimi.getText();
                String toimipisteTietue = "SELECT toimitila.ToimipisteID, toimitila.Nimi, toimitila.Hinta, toimitila.Henkilomaara, toimitila.Aktiivisuus " +
                        "FROM toimitila WHERE toimitila.Nimi = " + "'" + nimiTeksti + "'";
                System.out.println(toimipisteTietue);

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipisteTietue);
                    SQLPalaute = SQLKysely.executeQuery();

                    if (SQLPalaute == null) {
                        throw new Exception("Toimitilaa ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }
                Toimitila toimitila = new Toimitila();
                try {
                    if (SQLPalaute.next()) {
                        toimitila.setToimitilaID(Integer.parseInt(SQLPalaute.getString("toimitila.ToimipisteID")));
                        textfieldtoimitilanimi.setText(SQLPalaute.getString("toimitila.Nimi"));
                        textfieldhinta.setText(SQLPalaute.getString("Hinta"));
                        textfieldhenkilomaara.setText(SQLPalaute.getString("Henkilomaara"));
                        textfieldtoimitilaAktiv.setText(SQLPalaute.getString("Aktiivisuus"));
                    }
                } catch (Exception se) {
                    throw se;
                }
                int ID = toimitila.getToimitilaID();
                String toimitilapisteTietue = "SELECT Nimi FROM toimipiste WHERE toimipisteID = " + "'" + ID + "'";
                System.out.println(toimitilapisteTietue);
                ResultSet SQLPalaute1 = null;
                PreparedStatement SQLKysely1 = null;
                try {
                    SQLKysely1 = Tietokanta.tietoKanta.prepareStatement(toimitilapisteTietue);
                    SQLPalaute1 = SQLKysely1.executeQuery();

                    if (SQLPalaute1 == null) {
                        throw new Exception("Toimitilaa ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }
                try{
                    if(SQLPalaute1.next()) {
                        textfieldtoimitilapiste.setText(SQLPalaute1.getString("Nimi"));
                    }
                }catch(Exception e){
                    throw e;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Voidaan lisätä uusi toimitila
     * <p>
     * <p>
     * Tietojen sisäänviemisessä on tällä hetkellä jotain ongelmaa. Kokeillaan korjata niitä sit jossakin välissä.
     */
    public void lisaaToimitila(ActionEvent event) throws Exception {

        if (textfieldtoimitilapiste.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldtoimitilanimi.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldhinta.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldhenkilomaara.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else if (textfieldtoimitilaAktiv.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        } else {

            try {
                Tietokanta.TietokannanAvaus();

                String toimipisteNimi = textfieldtoimitilapiste.getText();
                String kokeiluSQL = "SELECT toimipiste.ToimipisteID FROM toimipiste WHERE toimipiste.Nimi = " + "'" + toimipisteNimi + "'";

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(kokeiluSQL);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println("DEBUG " + kokeiluSQL);

                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }
                try {
                    while (SQLPalaute.next()) {
                        muuttuja = SQLPalaute.getString("ToimipisteID");

                    }
                } catch (Exception se) {
                    throw se;
                }
                // Asetetaan SQL-lausekkeelle pohja tietojen tallennusta varten.
                String sql = "INSERT INTO toimitila "
                        + "(Nimi, Hinta, Henkilomaara, Aktiivisuus, AktiivisuusMuutosPVM, ToimipisteID) "
                        + " VALUES (?, ?, ?, ?, ?, ?)";
                System.out.println(sql);

                ResultSet tulosjoukko = null;
                PreparedStatement lause = null;

                Toimitila valimuuttujaToimitila = new Toimitila();

                valimuuttujaToimitila.setToimipisteID(Integer.parseInt(muuttuja));
                valimuuttujaToimitila.setTilaNimi(String.valueOf(textfieldtoimitilanimi.getText()));
                valimuuttujaToimitila.setHinta(Double.parseDouble(textfieldhinta.getText()));
                valimuuttujaToimitila.setHenkilomaara(Integer.parseInt(textfieldhenkilomaara.getText()));
                valimuuttujaToimitila.setTtMuutosPvm(LocalDate.now());
                valimuuttujaToimitila.setTtAktiivinen(textfieldtoimitilaAktiv.getText());

                try {
                    //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                    lause = Tietokanta.tietoKanta.prepareStatement(sql);
                    //lisätään tiedot haluttuihin parametreihin
                    lause.setString(1, valimuuttujaToimitila.getTilaNimi());
                    lause.setDouble(2, valimuuttujaToimitila.getHinta());
                    lause.setString(3, String.valueOf(valimuuttujaToimitila.getHenkilomaara()));
                    lause.setString(4, valimuuttujaToimitila.getTtAktiivinen());
                    lause.setDate(5, Date.valueOf(valimuuttujaToimitila.getTtMuutosPvm()));
                    lause.setInt(6, valimuuttujaToimitila.getToimipisteID());

                    System.out.println("706" + lause);
                    System.out.println(sql);

                    // suorita sql-lause
                    int lkm = lause.executeUpdate();

                    //	System.out.println("lkm " + lkm);
                    if (lkm == 0) {
                        throw new Exception("Asiakaan lisaaminen ei onnistu");
                    }
                } catch (Exception e) {
                    throw e;
                }
                System.out.println("importtaus onnistui");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void muutaToimitila(ActionEvent event) throws Exception {

        if (textfieldtoimitilapiste.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldtoimitilanimi.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldhinta.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldhenkilomaara.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldtoimitilaAktiv.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else {

            String nimiTeksti = textfieldtoimitilanimi.getText();
            String toimitilatietue = "SELECT toimitila.Nimi, toimitila.Hinta, toimitila.Henkilomaara, " +
                    " toimitila.Aktiivisuus, toimitila.AktiivisuusMuutosPVM, toimitila.ToimipisteID" +
                    " FROM toimitila WHERE toimitila.Nimi = " + "'" + nimiTeksti + "'";

            ResultSet SQLPalaute = null;
            PreparedStatement lause = null;
            try {
                lause = Tietokanta.tietoKanta.prepareStatement(toimitilatietue);
                SQLPalaute = lause.executeQuery();

                if (SQLPalaute == null) {
                    throw new Exception("Toimitilaa ei loydy tietokannasta");

                }
            } catch (Exception se) {
                throw se;
            }
            // ylikirjoitetaan aikaisempi SQL-lauseke
            toimitilatietue = "UPDATE  toimitila "
                    + "SET toimitila.Hinta = ?, toimitila.Henkilomaara = ?, toimitila.Aktiivisuus = ?"
                    + " WHERE toimitila.Nimi = ?";


            lause = null;
            try {
                // luo PreparedStatement-olio sql-lauseelle
                lause = Tietokanta.tietoKanta.prepareStatement(toimitilatietue);

                // laitetaan olion attribuuttien arvot UPDATEen

                lause.setString(1, String.valueOf(textfieldhinta.getText()));
                lause.setString(2, String.valueOf(textfieldhenkilomaara.getText()));
                lause.setString(3, String.valueOf(textfieldtoimitilaAktiv.getText()));
                lause.setString(4, String.valueOf(textfieldtoimitilanimi.getText()));

                System.out.println("tiedot päivitetty! JIHUU!!!");

                // suorita sql-lause
                int lkm = lause.executeUpdate();
                if (lkm == 0) {
                    throw new Exception("Toimipisteen tietojen muuttaminen ei onnistu");
                }
            } catch (Exception e) {
                // JDBC ym. virheet
                throw e;
            }
        }
    }
    /** Tässä pyritään hakemaan listausta/luetteloa olemassa olevista laitteista ja palveluista sekä siirtää ne varaus
     * näkymän sisällä olevaan tyhjään listview kenttään.*/

    /**
     * Edit. 2.0. tietojen siirtäminen onnistui helposti. listviewiä pääsee käyttämään, kun asettaa toimipistekenttään sille kuuluvan arvon.
     * Esimerkiksi ullakkohuoneen palveluita päästään tarkastelemaan laittamalla siihen "1" ja painamalla haku-näppäintä.
     * Painamalla jotain listalla näkyvää palvelua, se täyttää ne tyhjiin kenttiin.
     */
    //Haetaan tällä luettelo laitteista ja palveluista
    public void haeLaitteetJaPalvelut() throws Exception {

        if (textfieldlpToimipiste.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Ei haettavaa tietoa kentässä");
        } else {

            ArrayList<String> laitteetPalvelutListaus = new ArrayList<>();

            try {
                Tietokanta.TietokannanAvaus();

                String nimiTeksti = textfieldlpToimipiste.getText();
                String kokeiluSQL = "SELECT toimitila.ToimitilaID FROM toimitila WHERE toimitila.Nimi = " + "'" + nimiTeksti + "'";

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(kokeiluSQL);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println("DEBUG " + kokeiluSQL);

                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }
                try {
                    while (SQLPalaute.next()) {
                        muuttuja = SQLPalaute.getString("ToimitilaID");

                    }
                } catch (Exception se) {
                    throw se;
                }
                String laitteetPalvelutSQL = "SELECT laitteetpalvelut.Nimi FROM laitteetpalvelut WHERE laitteetpalvelut.ToimitilaID = " + "'" + muuttuja + "'";
                System.out.println("Testi1");

                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(laitteetPalvelutSQL);
                    System.out.println("kokeilu " + laitteetPalvelutSQL);
                    SQLPalaute = SQLKysely.executeQuery();
                    try {
                        while (SQLPalaute.next()) {
                            laitteetPalvelutListaus.add(SQLPalaute.getString("Nimi"));
                            System.out.println(laitteetPalvelutListaus);
                        }
                    } catch (Exception se) {
                        throw se;
                    }
                } catch (Exception se) {
                    throw se;
                }

                ObservableList<String> LaitteetPalvelut = FXCollections.observableArrayList(
                        laitteetPalvelutListaus);

                lvlaitteetjapalvelut.setItems(LaitteetPalvelut);
                lvlaitteetjapalvelut.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                lvlaitteetjapalvelut.setOnMouseClicked(e -> {
                    String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                    ObservableList<String> valittuLaitePalvelu; //lista
                    valittuLaitePalvelu = lvlaitteetjapalvelut.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                    String nimi = valittuLaitePalvelu.toString().replace("[", "")
                            .replace("]", "");

                    String toimipisteTietue = "SELECT laitteetpalvelut.Nimi, laitteetpalvelut.Hinta, " +
                            "laitteetpalvelut.Kuvaus, laitteetpalvelut.Aktiivisuus " +
                            "FROM laitteetpalvelut WHERE laitteetpalvelut.Nimi = " + "'" + nimi + "'";

                    ResultSet uusPalaute = null;
                    PreparedStatement uusSQL = null;
                    try {
                        uusSQL = Tietokanta.tietoKanta.prepareStatement(toimipisteTietue);
                        uusPalaute = uusSQL.executeQuery();

                        if (uusPalaute == null) {
                            throw new Exception("Toimitilaa ei loydy");
                        }
                    } catch (Exception he) {
                        try {
                            throw he;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    try {
                        if (uusPalaute.next()) {
                            textfieldlaitepalvelulp.setText(uusPalaute.getString("Nimi"));
                            textfieldhintalp.setText(uusPalaute.getString("Hinta"));
                            textareakuvauslp.setText(uusPalaute.getString("Kuvaus"));
                            textfieldAktiivisuusLP.setText(uusPalaute.getString("Aktiivisuus"));
                        }
                    } catch (Exception se) {
                        try {
                            throw se;
                        } catch (Exception throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tässä on laitteiden ja palveluiden nappulat
     */

    public void lisaaLaiteJaPalvelu(ActionEvent event) throws Exception {

        if (textfieldlpToimipiste.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldAktiivisuusLP.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldlaitepalvelulp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldhintalp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textareakuvauslp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else {
            Tietokanta.TietokannanAvaus();

            String toimitilaNimi = textfieldlpToimipiste.getText();
            String kokeiluSQL = "SELECT toimitila.ToimitilaID FROM toimitila WHERE toimitila.Nimi = " + "'" + toimitilaNimi + "'";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(kokeiluSQL);
                SQLPalaute = SQLKysely.executeQuery();
                System.out.println("DEBUG " + kokeiluSQL);

                if (SQLPalaute == null) {
                    throw new Exception("Asiakasta ei loydy");
                }
            } catch (Exception se) {
                throw se;
            }
            try {
                while (SQLPalaute.next()) {
                    muuttuja = SQLPalaute.getString("ToimitilaID");

                }
            } catch (Exception se) {
                throw se;
            }
            // Asetetaan SQL-lausekkeelle pohja tietojen tallennusta varten.
            String SQLkysely = "INSERT INTO laitteetpalvelut "
                    + "(Nimi, Hinta, Kuvaus, Aktiivisuus, ToimitilaID, AktiivisuusMuutosPVM)"
                    + " VALUES (?, ?, ?, ?, ?,?)";

            ResultSet tulosjoukko = null;
            PreparedStatement lause = null;

            //Nämä tuottanee jotain ongelmia. Aktiivisuudelle mahdollisesti jokin kenttä?

            LaitteetJaPalvelut valimuuttuja = new LaitteetJaPalvelut();

            valimuuttuja.setLpNimi(textfieldlaitepalvelulp.getText());
            valimuuttuja.setLpHinta(Integer.parseInt((textfieldhintalp.getText())));
            valimuuttuja.setLpKuvaus(String.valueOf(textareakuvauslp.getText()));
            valimuuttuja.setLpAktiivinen(textfieldAktiivisuusLP.getText());
            valimuuttuja.setLpMuutosPvm(String.valueOf(LocalDate.now()));
            valimuuttuja.setLpID(Integer.parseInt(muuttuja));
            System.out.println(valimuuttuja);

            try {
                //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                lause = Tietokanta.tietoKanta.prepareStatement(SQLkysely);
                //lisätään tiedot haluttuihin parametreihin

                //Tähän pitäis keksiä joku fiksu tapa yhdistää toimitila laitteisiin ja palveluihin
                lause.setString(1, valimuuttuja.getLpNimi());
                lause.setString(2, String.valueOf(valimuuttuja.getLpHinta()));
                lause.setString(3, valimuuttuja.getLpKuvaus());
                lause.setString(4, valimuuttuja.getLpAktiivinen());
                lause.setString(6, valimuuttuja.getLpMuutosPvm());
                lause.setString(5, String.valueOf(valimuuttuja.getLpID()));

                System.out.println("Tuleeko tästä mitään ulos");
                System.out.println("edit. toimii hyvästi!!!");

                // suorita sql-lause
                int lkm = lause.executeUpdate();

                //	System.out.println("lkm " + lkm);
                if (lkm == 0) {
                    throw new Exception("laitteen/palvelun lisääminen ei onnistu");
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void muutaLaitePalvelu(ActionEvent event) throws Exception {

        if (textfieldlpToimipiste.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldAktiivisuusLP.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldlaitepalvelulp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldhintalp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textareakuvauslp.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else {

            Tietokanta.TietokannanAvaus();

            String nimiTeksti = textfieldlaitepalvelulp.getText();
            String laitePalvelutietue = "SELECT Nimi, Hinta, Kuvaus " +
                    "Aktiivisuus FROM laitteetpalvelut WHERE Nimi = " + "'" + nimiTeksti + "'";
            System.out.println(laitePalvelutietue);

            ResultSet SQLPalaute = null;
            PreparedStatement lause = null;
            try {
                lause = Tietokanta.tietoKanta.prepareStatement(laitePalvelutietue);
                SQLPalaute = lause.executeQuery();
                System.out.println("rivi 935 " + laitePalvelutietue);

                //Virhekoodin syöttämisessä on jotain hämminkiä.
                if (SQLPalaute == null) {
                    throw new Exception("Laitetta/palvelua ei loydy tietokannasta");

                }
            } catch (Exception se) {
                throw se;
            }
            // ylikirjoitetaan aikaisempi SQL-lauseke
            laitePalvelutietue = "UPDATE  laitteetpalvelut "
                    + "SET Nimi = ?, Hinta = ?, Kuvaus = ?, Aktiivisuus = ?"
                    + " WHERE laitteetpalvelut.Nimi = ?";

            lause = null;
            try {
                // luo PreparedStatement-olio sql-lauseelle
                lause = Tietokanta.tietoKanta.prepareStatement(laitePalvelutietue);

                // laitetaan olion attribuuttien arvot UPDATEen
                //Palvelun nimeä ei näköjään voi vaihtaa enää sen luomisen jälkeen

                lause.setString(1, String.valueOf(textfieldlaitepalvelulp.getText()));
                lause.setString(2, String.valueOf(textfieldhintalp.getText()));
                lause.setString(3, String.valueOf(textareakuvauslp.getText()));
                lause.setString(4, String.valueOf(textfieldAktiivisuusLP.getText()));
                lause.setString(5, String.valueOf(textfieldlaitepalvelulp.getText()));

                System.out.println(laitePalvelutietue);

                lause.executeUpdate();

                System.out.println("tiedot päivitetty");
            } catch (Exception e) {
                // JDBC ym. virheet
                throw e;
            }
        }
    }

    private void naytaHalytys(Alert.AlertType tyyppi, String syy) {
        Alert alert = new Alert(tyyppi);
        alert.setTitle("Alert");
        alert.setContentText(syy);
        alert.show();
    }
    /**
     * Hakee toimipisteeet "Hae"-painikkeella Raportointi käyttöliittymään ja mahdollistaa useamman valinnan.
     */
    public void haeToimipisteet(ActionEvent event) throws Exception {
        ArrayList<String> lista = new ArrayList<>();

        try {
            Tietokanta.TietokannanAvaus();

            String toimipistehaku = "SELECT toimipiste.Nimi FROM toimipiste";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipistehaku);
                System.out.println("DEBUG " + toimipistehaku);
                SQLPalaute = SQLKysely.executeQuery();


                Toimipiste toimipistetiedot = new Toimipiste();
                try {
                    while (SQLPalaute.next()) {
                        lista.add(SQLPalaute.getString("Nimi"));
                    }
                } catch (Exception se) {
                    throw se;
                }
            } catch (Exception se) {
                throw se;
            }

            ObservableList<String> raportontilista = FXCollections.observableArrayList(
                    lista);
            LVraportointi.setItems(raportontilista);
            LVraportointi.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            LVraportointi.setOnMouseClicked(e -> {
                String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = LVraportointi.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                System.out.println(valitut);
                //Käydään läpi kaikki valitut ja lisätään ne valittu-muuttujaan
                for (String n : valitut) {
                    valittu += " " + n;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void haeToimipisteetJaToimitilat(ActionEvent event) throws Exception {
        ArrayList<String> toimipisteLista = new ArrayList<>();
        ArrayList<String> toimitilaLista = new ArrayList<>();

        try {
            Tietokanta.TietokannanAvaus();

            String toimipistehaku = "SELECT toimipiste.Nimi FROM toimipiste";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            ResultSet SQLPalaute1 = null;
            PreparedStatement SQLKysely1 = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipistehaku);
                System.out.println("DEBUG " + toimipistehaku);
                SQLPalaute = SQLKysely.executeQuery();

                try {
                    while (SQLPalaute.next()) {
                        System.out.println("toimipistelista soutti");
                        toimipisteLista.add(SQLPalaute.getString("Nimi"));
                        System.out.println(toimipisteLista);
                    }
                } catch (Exception se) {
                    throw se;
                }

                String toimitilahaku = "SELECT toimitila.Nimi FROM toimitila";
                SQLKysely1 = Tietokanta.tietoKanta.prepareStatement(toimitilahaku);
                System.out.println("DEBUG " + toimitilahaku);

                SQLPalaute1 = SQLKysely1.executeQuery();
                try {
                    while (SQLPalaute1.next()) {
                        System.out.println("toimitilalista soutti");
                        toimitilaLista.add(SQLPalaute1.getString("Nimi"));
                        System.out.println(toimitilaLista);
                    }
                } catch (Exception se) {
                    throw se;
                }
            } catch (Exception se) {
                throw se;
            }

            ObservableList<String> toimipisteet = FXCollections.observableArrayList(
                    toimipisteLista);
            ObservableList<String> toimitilat = FXCollections.observableArrayList(
                    toimitilaLista);
            lvtoimipiste.setItems(toimipisteet);
            lvtoimitilat.setItems(toimitilat);
            lvtoimipiste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lvtoimipiste.setOnMouseClicked(e -> {
                String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = lvtoimipiste.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                System.out.println(valitut);
                //Käydään läpi kaikki valitut ja lisätään ne valittu-muuttujaan
                for (String n : valitut) {
                    valittu += " " + n;
                }

            });
            lvtoimitilat.setOnMouseClicked(e -> {
                String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = lvtoimitilat.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                System.out.println(valitut);
                //Käydään läpi kaikki valitut ja lisätään ne valittu-muuttujaan
                for (String n : valitut) {
                    valittu += " " + n;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void haePainikeAsiakasNakyma(ActionEvent event) throws Exception {
        ArrayList<String> lista = new ArrayList<>();

        // Päivämäärä kenttä tarkistus, tarkistus tehdään hae-painikkeella.
        if (datepickalkupvmat.getValue().getYear() > datepickloppupvmat.getValue().getYear()) {
            naytaHalytys(Alert.AlertType.ERROR, "Alkupäivämäärä ei voi olla myöhäisempi kuin loppupäivämäärä!");
        } else if (datepickalkupvmat.getValue().getMonthValue() > datepickloppupvmat.getValue().getMonthValue()) {
            naytaHalytys(Alert.AlertType.ERROR, "Alkupäivämäärä ei voi olla myöhäisempi kuin loppupäivämäärä!");
        } else if (datepickalkupvmat.getValue().getMonthValue() == datepickloppupvmat.getValue().getMonthValue()) {
            if (datepickalkupvmat.getValue().getDayOfMonth() > datepickloppupvmat.getValue().getDayOfMonth()) {
                naytaHalytys(Alert.AlertType.ERROR, "Alkupäivämäärä ei voi olla myöhäisempi kuin loppupäivämäärä!");
            } else {
                System.out.println("DEBUG: Päivämäärätarkistus ok!");
            }
        }

        try {
            Tietokanta.TietokannanAvaus();

            String toimipisteHakuSQL = "SELECT toimipiste.Nimi FROM toimipiste";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipisteHakuSQL);
                System.out.println("DEBUG " + toimipisteHakuSQL);
                SQLPalaute = SQLKysely.executeQuery();

                try {
                    while (SQLPalaute.next()) {
                        lista.add(SQLPalaute.getString("Nimi"));

                    }
                } catch (Exception se) {
                    throw se;
                }
            } catch (Exception se) {
                throw se;
            }

            ObservableList<String> asiakkaat = FXCollections.observableArrayList(
                    lista);
            toimiPisteAsiakas.setItems(asiakkaat);
            toimiPisteAsiakas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            toimiPisteAsiakas.setOnMouseClicked(e -> {
                String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = toimiPisteAsiakas.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                toimipisteenNimi = valitut.toString().replace("[", "")
                        .replace("]", "");
                toimitilaHaku();

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toimitilaHaku() {
        ArrayList<String> toimitilaLista = new ArrayList<>();

        try {
            Tietokanta.TietokannanAvaus();

            String toimipisteNimi = toimipisteenNimi;
            String toimipisteIDhakuSQL = "SELECT toimipiste.ToimipisteID FROM toimipiste WHERE toimipiste.Nimi = " + "'" + toimipisteNimi + "'";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(toimipisteIDhakuSQL);
                SQLPalaute = SQLKysely.executeQuery();
                System.out.println("DEBUG " + toimipisteIDhakuSQL);

                if (SQLPalaute == null) {
                    throw new Exception("Asiakasta ei loydy");
                }
            } catch (Exception se) {
                throw se;
            }
            try {
                while (SQLPalaute.next()) {
                    muuttuja = SQLPalaute.getString("ToimipisteID");

                }
            } catch (Exception se) {
                throw se;
            }
            String toimitilaHakuSQL = "SELECT toimitila.Nimi, toimitila.toimitilaID FROM toimitila WHERE toimitila.ToimipisteID = " + muuttuja;
            System.out.println(toimitilaHakuSQL);
            ResultSet SQLtoimitila = null;
            PreparedStatement SQLKyselytoimitila = null;

            try {
                SQLKyselytoimitila = Tietokanta.tietoKanta.prepareStatement(toimitilaHakuSQL);
                System.out.println("DEBUG " + toimitilaHakuSQL);
                SQLtoimitila = SQLKyselytoimitila.executeQuery(); // Toimipiste ID:n haku, joka palauttaa Toimipisteen tilat


                while (SQLtoimitila.next()) {
                    muuttujaToimitilaID = SQLtoimitila.getString("ToimitilaID");
                    String muuttujaToimitilaNimi = SQLtoimitila.getString("Nimi");
                    pvmmuutin(); // Kutsutaan pvmmuutin, jotta alkupvm ja loppupvm saa tietokantaan verrattavat arvot
                    String vapaaToimitilaSQL = "SELECT * FROM varaus WHERE '" + alkupvm + "' < LopetusPVM AND '" + loppupvm + "' > AloitusPVM AND toimitilaID = " + muuttujaToimitilaID;
                    System.out.println(vapaaToimitilaSQL);
                    PreparedStatement SQLKysely1 = Tietokanta.tietoKanta.prepareStatement(vapaaToimitilaSQL);
                    ResultSet SQLtoimitilavapaa = null;

                    // Luodaan kysely joka palauttee jokaiselle
                    try {
                        SQLtoimitilavapaa = SQLKysely1.executeQuery();
                        System.out.println("DEBUG SQLTOIMITILAVAPAA: ");

                        if (!SQLtoimitilavapaa.next() ) {
                            toimitilaLista.add(muuttujaToimitilaNimi);
                        }
                    } catch (Exception se) {
                        throw se;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ObservableList<String> asiakkaat = FXCollections.observableArrayList(
                    toimitilaLista);
            toimitilaAsiakas.setItems(asiakkaat);
            toimitilaAsiakas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            toimitilaAsiakas.setOnMouseClicked(e -> { // Tähän voidaan lisätä henkilömäärä hakukyely
                String valittu = "Valittuina:"; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = toimitilaAsiakas.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
                toimiTilanNimi = valitut.toString().replace("[", "")
                        .replace("]", "");
                laiteJapalveluHaku();

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void laiteJapalveluHaku() {
        ArrayList<String> laitePalveluLista = new ArrayList<>();

        try {
            Tietokanta.TietokannanAvaus();

            String toimilistaNimi = toimiTilanNimi;
            String kokeiluSQL = "SELECT toimitila.ToimitilaID FROM toimitila WHERE toimitila.Nimi = " + "'" + toimilistaNimi + "'";

            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;
            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(kokeiluSQL);
                SQLPalaute = SQLKysely.executeQuery();

                if (SQLPalaute == null) {
                    throw new Exception("Asiakasta ei loydy");
                }
            } catch (Exception se) {
                throw se;
            }
            try {
                while (SQLPalaute.next()) {
                    muuttuja = SQLPalaute.getString("ToimitilaID");

                }
            } catch (Exception se) {
                throw se;
            }
            String asiakasSQL = "SELECT laitteetpalvelut.Nimi FROM laitteetpalvelut WHERE laitteetpalvelut.ToimitilaID = " + muuttuja;


            ResultSet SQLtoimitila = null;
            PreparedStatement SQLKyselytoimitila = null;
            try {
                SQLKyselytoimitila = Tietokanta.tietoKanta.prepareStatement(asiakasSQL);
                SQLtoimitila = SQLKyselytoimitila.executeQuery();

                try {
                    while (SQLtoimitila.next()) {
                        laitePalveluLista.add(SQLtoimitila.getString("Nimi"));
                        System.out.println(laitePalveluLista);
                    }
                } catch (Exception se) {
                    throw se;
                }
            } catch (Exception se) {
                throw se;
            }

            ObservableList<String> lpasiakaslista = FXCollections.observableArrayList(
                    laitePalveluLista);
            laitepalveluAsiakas.setItems(lpasiakaslista);
            laitepalveluAsiakas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            laitepalveluAsiakas.setOnMouseClicked(e -> {
                String valittu = ""; // muuttuja, joka alustetaan aina hiiren klikkauksella
                ObservableList<String> valitut; //lista
                valitut = laitepalveluAsiakas.getSelectionModel().getSelectedItems(); // lista, johon lisätään valitut
//                System.out.println(valitut);
                //Käydään läpi kaikki valitut ja lisätään ne valittu-muuttujaan
                for (String n : valitut) {
                    valittu += "" +  n;
                }
                LPlistausMuuttuja = valittu;
                System.out.println("DEBUG: " + LPlistausMuuttuja);
                this.valitutLaitteetJaPalvelut = valitut;

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lisaaAsiakas(ActionEvent event) throws Exception {

        if (textfieldtunnusat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldpuhelinnumeroat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldsahkopostiat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldnimiat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldosoiteat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldyhteyshenkiloat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldtoimipaikkaat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else if (textfieldpostinumeroat.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR, "Täytä kaikki kentät");
        } else {
            Tietokanta.TietokannanAvaus();

            // Asetetaan SQL-lausekkeelle pohja tietojen tallennusta varten.

            String sql = "INSERT INTO asiakas(asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Puhelinnumero, asiakas.Sahkoposti, asiakas.Katuosoite, "
                    + "asiakas.Postinumero, asiakas.Postitoimipaikka, asiakas.Tunnus)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            ResultSet tulosjoukko = null;
            PreparedStatement lause = null;

            Asiakas valimuuttuja = new Asiakas();

            valimuuttuja.setaNimi(String.valueOf(textfieldnimiat.getText()));
            valimuuttuja.setYhteyshenkilo(String.valueOf(textfieldyhteyshenkiloat.getText()));
            valimuuttuja.setPuhelinnumero(String.valueOf(textfieldpuhelinnumeroat.getText()));
            valimuuttuja.setSahkoposti(String.valueOf(textfieldsahkopostiat.getText()));
            valimuuttuja.setKatuosoite(String.valueOf(textfieldosoiteat.getText()));
            valimuuttuja.setPostiNro(String.valueOf(textfieldpostinumeroat.getText()));
            valimuuttuja.setToimipaikka(String.valueOf(textfieldtoimipaikkaat.getText()));
            valimuuttuja.setTunnus(String.valueOf(textfieldtunnusat.getText()));

            try {
                //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                lause = Tietokanta.tietoKanta.prepareStatement(sql);
                //lisätään tiedot haluttuihin parametreihin
                lause.setString(1, valimuuttuja.getaNimi());
                lause.setString(2, valimuuttuja.getYhteyshenkilo());
                lause.setString(3, valimuuttuja.getPuhelinnumero());
                lause.setString(4, valimuuttuja.getSahkoposti());
                lause.setString(5, valimuuttuja.getKatuosoite());
                lause.setString(6, valimuuttuja.getPostiNro());
                lause.setString(7, valimuuttuja.getToimipaikka());
                lause.setString(8, valimuuttuja.getTunnus());

                // suorita sql-lause
                int lkm = lause.executeUpdate();

                //	System.out.println("lkm " + lkm);
                if (lkm == 0) {
                    throw new Exception("Asiakkaan lisaaminen ei onnistu");
                }
            } catch (Exception e) {
                throw e;
            }
            System.out.println("importtaus onnistui");
            textfieldnimiat.setText("");
            textfieldyhteyshenkiloat.setText("");
            textfieldpuhelinnumeroat.setText("");
            textfieldsahkopostiat.setText("");
            textfieldosoiteat.setText("");
            textfieldpostinumeroat.setText("");
            textfieldtoimipaikkaat.setText("");
            textfieldtunnusat.setText("");
            naytaHalytys(Alert.AlertType.INFORMATION, "Asiakkaan lisäys onnistui!");
        }
    }

    public void luoRaportti(ActionEvent event) throws Exception {
        pvmmuutinRaportointiin();
        ArrayList<String> lista = new ArrayList<>();

        Tietokanta.TietokannanAvaus();

        String sql = "SELECT varaus.aloitusPVM, varaus.lopetusPVM, varaus.Varaustunnus, toimitila.Nimi, toimipiste.Nimi, toimitila.Henkilomaara, "
                + "laitteetpalvelut.Nimi, asiakas.Tunnus, asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Sahkoposti ,"
                + "asiakas.Puhelinnumero, asiakas.Postinumero, asiakas.Katuosoite, asiakas.Postitoimipaikka "
                + "FROM varaus, toimitila, toimipiste, varauslaitteetpalvelut, laitteetpalvelut, asiakas "
                + "WHERE (varaus.aloitusPVM < '" + loppupvm + "' AND varaus.lopetusPVM > '" + alkupvm + "') AND "
                + "varaus.ToimitilaID = toimitila.ToimitilaID AND "
                + "toimitila.ToimipisteID = toimipiste.ToimipisteID AND "
                + "varaus.VarausID = varauslaitteetpalvelut.VarausID AND "
                + "varauslaitteetpalvelut.LaitteetPalvelutID = laitteetpalvelut.LaitteetPalvelutID AND "
                + "varaus.AsiakasID = asiakas.AsiakasID "
                + "ORDER BY toimipiste.nimi;";

        ResultSet SQLAsiakas = null;
        PreparedStatement SQLKyselyAsiakas = null;
        Varaus varausOlio = new Varaus();

        try {
            SQLKyselyAsiakas = Tietokanta.tietoKanta.prepareStatement(sql);
            System.out.println("DEBUG " + sql);
            SQLAsiakas = SQLKyselyAsiakas.executeQuery();

            FileWriter raportointi = new FileWriter(LocalDate.now() + " Raportti.txt");
            System.out.println("Tiedosto avattu");
            try {
                if(SQLAsiakas.next()) {
                    raportointi.write("Raportti aikavälillä: " + SQLAsiakas.getString("varaus.aloitusPVM") + " - " + SQLAsiakas.getString("varaus.lopetusPVM") + "\r\n");
                    raportointi.write("\r\n");
                }
                    while (SQLAsiakas.next()) {
                    System.out.println("Aikaväli: " + SQLAsiakas.getString("varaus.aloitusPVM") + " ja " + SQLAsiakas.getString("varaus.lopetusPVM"));
                    System.out.println("Toimipiste: " + SQLAsiakas.getString("toimipiste.Nimi"));
                    System.out.println("Toimitila: " + SQLAsiakas.getString("toimitila.Nimi"));
                    raportointi.write("AikavÃ¤li: " + SQLAsiakas.getString("varaus.aloitusPVM") + " ja " + SQLAsiakas.getString("varaus.lopetusPVM") + "\r\n");
                    raportointi.write("Toimipiste: " + SQLAsiakas.getString("toimipiste.Nimi") + "\r\n");
                    raportointi.write("Toimitila: " + SQLAsiakas.getString("toimitila.Nimi") + "\r\n");
                    raportointi.write("\r\n");
                    System.out.println(" ");
                    System.out.println(" ");
                }
                raportointi.close();
                System.out.println("Tiedosto suljettu");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void pvmmuutinRaportointiin() {
        int alkuvuosi = datepickalkupvmrp.getValue().getYear();
        int alkukuukausi = datepickalkupvmrp.getValue().getMonthValue();
        int alkupaiva = datepickalkupvmrp.getValue().getDayOfMonth();
        int loppuvuosi = datepickloppupvmrp.getValue().getYear();
        int loppukuukausi = datepickloppupvmrp.getValue().getMonthValue();
        int loppupaiva = datepickloppupvmrp.getValue().getDayOfMonth();
        String skuukausi = Integer.toString(alkukuukausi);
        String spaiva = Integer.toString(alkupaiva);
        String lkuukausi = Integer.toString(loppukuukausi);
        String lpaiva = Integer.toString(loppupaiva);
        if (skuukausi.length() == 1) {
            skuukausi = "0" + skuukausi;
        }
        if (lkuukausi.length() == 1) {
            lkuukausi = "0" + lkuukausi;
        }
        if (spaiva.length() == 1) {
            spaiva = "0" + spaiva;
        }
        if (lpaiva.length() == 1) {
            lpaiva = "0" + lpaiva;
        }
        this.alkupvm = alkuvuosi + "-" + skuukausi + "-" + spaiva;
        this.loppupvm = loppuvuosi + "-" + lkuukausi + "-" + lpaiva;
        System.out.println(alkupvm);
        System.out.println(loppupvm);
    }

    // Tallenna painike
    public void tallennaPainikeAsiakas(ActionEvent event) throws Exception {

        if(textfieldtunnusat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldpuhelinnumeroat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldsahkopostiat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldnimiat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldosoiteat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldyhteyshenkiloat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldtoimipaikkaat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else if(textfieldpostinumeroat.getText().isEmpty()){
            naytaHalytys(Alert.AlertType.ERROR,"Täytä kaikki kentät");
        }else{
        Tietokanta.TietokannanAvaus(); // Avataan tietokanta

        // Haetaan käyttöliittymästä varauksen tiedot muuttujiin
        int alkuvuosi = datepickalkupvmat.getValue().getYear();
        int alkukuukausi = datepickalkupvmat.getValue().getMonthValue();
        int alkupaiva = datepickalkupvmat.getValue().getDayOfMonth();
        int loppuvuosi = datepickloppupvmat.getValue().getYear();
        int loppukuukausi = datepickloppupvmat.getValue().getMonthValue();
        int loppupaiva = datepickloppupvmat.getValue().getDayOfMonth();
        // muutetaan int muuttujat Stringeiksi
        String skuukausi = Integer.toString(alkukuukausi);
        String spaiva = Integer.toString(alkupaiva);
        String lkuukausi = Integer.toString(loppukuukausi);
        String lpaiva = Integer.toString(loppupaiva);
        // Tehdään päivämäärä muutos tietokantaan sopivaksi
        if (skuukausi.length() == 1) {
            skuukausi = "0" + skuukausi;
        }
        if (lkuukausi.length() == 1) {
            lkuukausi = "0" + lkuukausi;
        }
        if (spaiva.length() == 1) {
            spaiva = "0" + spaiva;
        }
        if (lpaiva.length() == 1) {
            lpaiva = "0" + lpaiva;
        }
        this.alkupvm = alkuvuosi + "-" + skuukausi + "-" + spaiva;
        this.loppupvm = loppuvuosi + "-" + lkuukausi + "-" + lpaiva;

        String tunnusteksti = textfieldtunnusat.getText();

        String AsiakkaanValintaTunnuksella = "SELECT AsiakasID FROM asiakas WHERE Tunnus = " + "'" + tunnusteksti + "'"; // Asiakkaan hakeminen tietokannasta tunnuksella
        ResultSet SQLPalaute = null;
        PreparedStatement SQLKysely = null;

        try {
            SQLKysely = Tietokanta.tietoKanta.prepareStatement(AsiakkaanValintaTunnuksella);
            SQLPalaute = SQLKysely.executeQuery();

            /**
             * Tässä tutkitaan, vastaako joku tietueista kyselyä ja sen ehtoja. jos kyllä, niin asiakkaan tiedot päivitetään.
             * */
            if (SQLPalaute.next()) {
                System.out.println("DEBUG: Asiakkaan tiedot löytyvät, päivitetään tiedot");

                ResultSet Palaute = null;
                PreparedStatement lause = null;
                ResultSet varausPalaute = null;
                PreparedStatement varausLause = null; // Luodaan null

                // Asetetaan muuttujaksi asiakkaan tiedot päivitys lauseke
                String asiakasTiedonPaivitysSQL = "UPDATE  asiakas "
                        + "SET Nimi = ?, Yhteyshenkilo = ?, Puhelinnumero = ?, Sahkoposti = ?, Katuosoite = ?, Postinumero = ?, Postitoimipaikka = ? "
                        + " WHERE Tunnus = ?";

                try {
                    // luo PreparedStatement-olio sql-lauseelle
                    lause = Tietokanta.tietoKanta.prepareStatement(asiakasTiedonPaivitysSQL);
                    lause.setString(1, String.valueOf(textfieldnimiat.getText())); // ensimmäinen ? saa arvon textfieldnimiat kentästä getText-metodilla
                    lause.setString(2, String.valueOf(textfieldyhteyshenkiloat.getText()));
                    lause.setString(3, String.valueOf(textfieldpuhelinnumeroat.getText()));
                    lause.setString(4, String.valueOf(textfieldsahkopostiat.getText()));
                    lause.setString(5, String.valueOf(textfieldosoiteat.getText()));
                    lause.setString(6, String.valueOf(textfieldpostinumeroat.getText()));
                    lause.setString(7, String.valueOf(textfieldtoimipaikkaat.getText()));
                    lause.setString(8, String.valueOf(textfieldtunnusat.getText()));
                    lause.executeUpdate();

                    //Liittyy ylläolevaan kommentointiin EI PÄIVITETÄ VARAUKSEN TIETOJA
                    //varausLause = Tietokanta.tietoKanta.prepareStatement(varausPaivitysSQL);
                    //varausLause.setString(1, (loppupvm));
                    //varausLause.setString(2, textfieldvaraustunnusat.getText());
                    //varausLause.executeUpdate();
                    System.out.println("päivitetty tietoja!");

                } catch (Exception e) {
                    System.out.println(e);
                    throw e;
                }
            }else{
                /** tarkistaa/tekee välissä uuden asiakasID:n*/

                String asiakkaanLisaaminenSQL = "INSERT INTO asiakas(asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Puhelinnumero, asiakas.Sahkoposti, asiakas.Katuosoite, "
                        + "asiakas.Postinumero, asiakas.Postitoimipaikka, asiakas.Tunnus)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement lause = null;

                Asiakas valimuuttuja = new Asiakas();

                valimuuttuja.setaNimi(String.valueOf(textfieldnimiat.getText()));
                valimuuttuja.setYhteyshenkilo(String.valueOf(textfieldyhteyshenkiloat.getText()));
                valimuuttuja.setPuhelinnumero(String.valueOf(textfieldpuhelinnumeroat.getText()));
                valimuuttuja.setSahkoposti(String.valueOf(textfieldsahkopostiat.getText()));
                valimuuttuja.setKatuosoite(String.valueOf(textfieldosoiteat.getText()));
                valimuuttuja.setPostiNro(String.valueOf(textfieldpostinumeroat.getText()));
                valimuuttuja.setToimipaikka(String.valueOf(textfieldtoimipaikkaat.getText()));
                valimuuttuja.setTunnus(String.valueOf(textfieldtunnusat.getText()));

                try {
                    //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                    lause = Tietokanta.tietoKanta.prepareStatement(asiakkaanLisaaminenSQL);
                    //lisätään tiedot haluttuihin parametreihin
                    lause.setString(1, valimuuttuja.getaNimi());
                    lause.setString(2, valimuuttuja.getYhteyshenkilo());
                    lause.setString(3, valimuuttuja.getPuhelinnumero());
                    lause.setString(4, valimuuttuja.getSahkoposti());
                    lause.setString(5, valimuuttuja.getKatuosoite());
                    lause.setString(6, valimuuttuja.getPostiNro());
                    lause.setString(7, valimuuttuja.getToimipaikka());
                    lause.setString(8, valimuuttuja.getTunnus());

                    // suorita sql-lause
                    lause.executeUpdate();
                    naytaHalytys(Alert.AlertType.INFORMATION, "Luotu uusi asiakas. \n \n" + valimuuttuja.toString());
                    System.out.println("DEBUG: uusi asiakas luotu!");
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (Exception se) {
            System.out.println(se);
            throw se;
        }
            // Tehdään varaus tässä try rakenteessa ONGELMA -> Luo uuden varauksen. :) (myös päivittää asiakkaan tiedot ja tekee uuden asiakkaan.)
            try {
                ResultSet palauteVaraus = null;
                PreparedStatement varaus = null;
                ResultSet palauteLpVaraus = null;
                PreparedStatement lpVaraus = null;

                /** Tämä mahdollisesti vaatii tietokannan päivittämisen jotta saadaan "uuden asiakkaan" AsiakasID
                 * poimittua seuraavan kohdan parametriksi. */

                varaustunnus = com.example.demo6.varaustunnus.luoVaraustunnus();

                String varausLisaysSQL = "INSERT INTO varaus"
                        + "(Varaustunnus, aloitusPVM, lopetusPVM, AsiakasID, ToimitilaID) VALUES (?"
                        + ", ? ,? ,? ,? )";
                System.out.println("DEBUG: [" + varausLisaysSQL + "   " + "] varaustiedot");

                String asiakasNimi = textfieldnimiat.getText();
                String asiakasnimiSQL = "SELECT AsiakasID FROM asiakas WHERE Nimi = " + "'" + asiakasNimi + "'";

                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(asiakasnimiSQL);
                    SQLPalaute = SQLKysely.executeQuery();
//                System.out.println("DEBUG " + AsiakkaanValintaTunnuksella);

                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }
                try {
                    while (SQLPalaute.next()) {
                        /** Tää tulee varauskenttään parametriksi!!*/
                        AsiakasID = SQLPalaute.getString("AsiakasID");
                    }
                } catch (Exception se) {
                    throw se;
                }
                System.out.println(VarausID);
                String lisaaVarausLaitteetPalvelutSQL = "INSERT INTO varauslaitteetpalvelut"
                        + "(VarausID, laitteetpalvelutID) VALUES (?,?)";
                System.out.println(lisaaVarausLaitteetPalvelutSQL);

                try {
                    while (SQLPalaute.next()) { //"SELECT AsiakasID FROM asiakas WHERE Nimi = " + "'" + asiakasNimi + "'";
                        /** Tää tulee varauskenttään parametriksi!!*/
                        VarausID = SQLPalaute.getString("VarausID");
                        System.out.println(VarausID + "Kokeilu");
                    }
                } catch (Exception se) {
                    throw se;
                }

                Asiakas valimuuttuja = new Asiakas();
                Varaus apumuuttuja = new Varaus();
                Toimitila kolmasmuuttuja = new Toimitila();
                LaitteetJaPalvelut laitteetPalvelutOlio = new LaitteetJaPalvelut();

                apumuuttuja.setAloituspvm(alkupvm);
                apumuuttuja.setLopetuspvm(loppupvm);
                valimuuttuja.setAsiakasID(Integer.parseInt(AsiakasID));
                kolmasmuuttuja.setToimitilaID(Integer.parseInt(muuttuja));

                // Listätään varausLisaysSQL-lausekkeeseen muuttujat
                try {
                    //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                    varaus = Tietokanta.tietoKanta.prepareStatement(varausLisaysSQL);

                    //lisätään tiedot haluttuihin parametreihin
                    varaus.setString(1, this.varaustunnus);
                    varaus.setString(2, apumuuttuja.getAloituspvm());
                    varaus.setString(3, apumuuttuja.getLopetuspvm());
                    varaus.setString(4, String.valueOf(valimuuttuja.getAsiakasID()));
                    varaus.setString(5, String.valueOf(kolmasmuuttuja.getToimitilaID()));
                    varaus.executeUpdate(); // TÄmän kyselyn jälkeen haetaan varausID
                    System.out.println("DEBUG: " + varaus + "RIVI 1975");
                } catch (Exception e) {
                    throw e;
                }

                try {
                    String varausTunnusSQL = "SELECT VarausID FROM varaus WHERE Varaustunnus = " + "'" + varaustunnus + "'";
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(varausTunnusSQL);
                    SQLPalaute = SQLKysely.executeQuery();
                    this.asiakkaanVaraustunnus = varaustunnus;
                    this.varaustunnus="";
                    if (SQLPalaute.next()) {
                        System.out.println("DEBUG: " + SQLPalaute + "= VarausID");
                        apumuuttuja.setVarausID(Integer.parseInt(SQLPalaute.getString("varaus.VarausID")));
                    }

                    System.out.println(apumuuttuja.getVarausID());
                    System.out.println(varausTunnusSQL);
                    System.out.println(SQLPalaute);
                    // Luo tähän välimuuttuja

                } catch (Exception se) {
                    System.out.println(se);
                    throw se;
                }
                /**
                 * Tämä Try lauseka lisää varausLaitteetPalvelut tauluun tiedon JOKAISESTA valitusta laitteesta ja palvelusta erillisellä lausekkeella ja toimii oikein. EI SAA MUUTTAA.
                 */
                try {
                    //Sijoitetaan välimuuttujan avulla tiedot SQL-lauseeseen
                    lpVaraus = Tietokanta.tietoKanta.prepareStatement(lisaaVarausLaitteetPalvelutSQL); //  "INSERT INTO varauslaitteetpalvelut(VarausID, laitteetpalvelutID) VALUES (?,?)";
                    //lisätään tiedot haluttuihin parametreihin
                    //String laitepalveluNimi = LPlistausMuuttuja;
                    String laitepalveluSQL; // luodaan muuttujat
                    String laitepalveluID; // luodaan muuttujat

                    // valitutLaitteetJaPalvelut sisältää nimi-listan laitteista ja palveluista -> muutetaan ne LaitePalveluID:ksi SQL kyselyllä, käydään tässä kaikki valitut läpi for loopissa
                    for (int i = 0; i < valitutLaitteetJaPalvelut.size(); i++ ) {
                        SQLPalaute = null; // tyhjennetään SQLPalaute
                        SQLKysely = null; // Tyhjennetään SQLKysely
                        laitepalveluID = valitutLaitteetJaPalvelut.get(i);
                        System.out.println(laitepalveluID);

                        // Luodaan kysely joka palauttee jokaiselle laitteelle ja palvelulle ID:n
                        try {
                            laitepalveluSQL = "SELECT LaitteetPalvelutID FROM laitteetpalvelut WHERE Nimi = " + "'" + laitepalveluID + "'"; //SQL kysely, joka palauttaa nimellä laitteetpalveluID:n
                            SQLKysely = Tietokanta.tietoKanta.prepareStatement(laitepalveluSQL);
                            SQLPalaute = SQLKysely.executeQuery();

                            if (SQLPalaute == null) {
                                throw new Exception("laitetta/palvelua ei loydy");
                            }
                        } catch (Exception se) {
                            throw se;
                        }

                        // Jos kysely palauttaa laitteelle ja palvelulle ID:n niin se asetetaan laitteetpalveluID muuttujaan
                        try {
                            while (SQLPalaute.next()) {

                                /** Tää tulee varauskenttään parametriksi!!*/
                                laitteetpalvelutID = SQLPalaute.getString("LaitteetPalvelutID"); //tämä palauttaa laitteetPalvelutID:n tietokannasta
                            }
                        } catch (Exception se) {
                            throw se;
                        }
                        laitteetPalvelutOlio.setLpID(Integer.parseInt(laitteetpalvelutID)); // laitteetPalvelutOlio saa muuttujan joka kierroksella uudesta ID:stä
                        lpVaraus.setString(1, String.valueOf(apumuuttuja.getVarausID())); // tätä ei tarvitse muuttaa, pitää pysyä samana
                        lpVaraus.setString(2, String.valueOf(laitteetPalvelutOlio.getLpID())); //Asetetaan tähän joka kierroksella seuraava laitePalveluID
                        lpVaraus.executeUpdate();  // suorita sql-lause
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    throw e;
                }
            } catch (Exception e){
                System.out.println(e + ":)");
            }
        System.out.println("DEBUG: Varauksen lisäys onnistui");
        naytaHalytys(Alert.AlertType.INFORMATION, "Varauksen lisäys onnistui, lähetä asiakkaalle sähköpostiin varaustunnus: " + this.asiakkaanVaraustunnus);
        this.asiakkaanVaraustunnus=""; // Asetetaan tyhjä varaustunnus
        }
    }
    /**EDIT 2.414 Riviltä 1550 löytyy laitteetJaPalvelut observerlist, josta saadaan valitut muuttuja otettua. Pitää
     * keksiä keino, miten me saadaan se "globaaliksi", koska muuten sitä ei voi käyttää.
     *
     * Suurimmat haasteet on juoksevien tunnisteiden kanssa(Eli kaikki ID-hömpötykset). Ongelma korjautunee
     * SQL-kyselyiden avulla
     *
     * Varaukset tulee läpi, laitteiden yhdistäminen vielä hiemnan sakkaa. Jeesuskaan ei tiedä, mistä on kysymys.*/
    public void haeVaraustunnusla(ActionEvent event) throws Exception {
        int hinta = 0;

        if (textfieldvarausla.getText().isEmpty()) {
            naytaHalytys(Alert.AlertType.ERROR,"Ei haettavaa tietoa kentässä");
        } else {

            try {
                Tietokanta.TietokannanAvaus();
                /**
                 * Tällä saa haettua tietokannasta jotain kivaa. Käy vaikka kokeilee.
                 */
                String varaustunnustesti = textfieldvarausla.getText();
                String asiakas2Tietue = "SELECT varaus.Varaustunnus, varaus.aloitusPVM, varaus.lopetusPVM, toimitila.Nimi, toimipiste.Nimi, toimitila.Henkilomaara, varaus.toimitilaID, " +
                        "laitteetpalvelut.Nimi, laitteetpalvelut.Hinta, asiakas.Tunnus, asiakas.Nimi, asiakas.Yhteyshenkilo, asiakas.Sahkoposti, " +
                        "asiakas.Puhelinnumero, asiakas.Postinumero, asiakas.Katuosoite, asiakas.Postitoimipaikka, toimitila.Hinta " +
                        "FROM varaus, toimitila, toimipiste, varauslaitteetpalvelut, laitteetpalvelut, asiakas " +
                        "WHERE varaus.ToimitilaID = toimitila.ToimitilaID AND " +
                        "toimitila.ToimipisteID = toimipiste.ToimipisteID AND " +
                        "varaus.VarausID = varauslaitteetpalvelut.VarausID AND " +
                        "varauslaitteetpalvelut.LaitteetPalvelutID = laitteetpalvelut.LaitteetPalvelutID AND " +
                        "varaus.AsiakasID = asiakas.AsiakasID AND " +
                        "varaus.Varaustunnus = " + "'" + varaustunnustesti + "'";

                ResultSet SQLPalaute = null;
                PreparedStatement SQLKysely = null;
                try {
                    SQLKysely = Tietokanta.tietoKanta.prepareStatement(asiakas2Tietue);
                    SQLPalaute = SQLKysely.executeQuery();
                    System.out.println("DEBUG " + asiakas2Tietue);

                    if (SQLPalaute == null) {
                        throw new Exception("Asiakasta ei loydy");
                    }
                } catch (Exception se) {
                    throw se;
                }

                Laskutus laskutus = new Laskutus();
                try {

                    if (SQLPalaute.next()) {
                        laskutus.setaNimi(SQLPalaute.getString("asiakas.Nimi"));
                        laskutus.setTunnus(SQLPalaute.getString("Tunnus"));
                        laskutus.setYhteyshenkilo(SQLPalaute.getString("Yhteyshenkilo"));
                        laskutus.setKatuosoite(SQLPalaute.getString("Katuosoite"));
                        laskutus.setPostiNro(SQLPalaute.getString("Postinumero"));
                        laskutus.setToimipaikka(SQLPalaute.getString("Postitoimipaikka"));
                        laskutus.setPuhelinnumero(SQLPalaute.getString("Puhelinnumero"));
                        laskutus.setSahkoposti(SQLPalaute.getString("sahkoposti"));
                        laskutus.setVaraustunnus(SQLPalaute.getString("Varaustunnus"));
                        laskutus.setAloituspvm(SQLPalaute.getString("aloitusPVM"));
                        laskutus.setLopetuspvm(SQLPalaute.getString("lopetusPVM"));
                        laskutus.setVarattuTila(SQLPalaute.getString("toimitila.Nimi"));
                        laskutus.setVarausHinta(Double.parseDouble(SQLPalaute.getString("toimitila.Hinta")));
                    }
                } catch (Exception se) {
                    throw se;
                }
                try {
                    Tietokanta.TietokannanAvaus();
                    /**
                     * Tällä saa haettua tietokannasta jotain kivaa. Käy vaikka kokeilee.
                     */
                    varaustunnustesti = textfieldvarausla.getText();
                    String asiakas2Tietue2 = "SELECT laitteetpalvelut.Hinta, laitteetpalvelut.Nimi FROM varaus, varauslaitteetpalvelut, " +
                            "laitteetpalvelut WHERE varaus.VarausID = varauslaitteetpalvelut.VarausID " +
                            "AND varauslaitteetpalvelut.LaitteetPalvelutID = laitteetpalvelut.LaitteetPalvelutID " +
                            "AND varaus.Varaustunnus = " + "'" + varaustunnustesti + "'";

                    ResultSet SQLPalaute2 = null;
                    PreparedStatement SQLKysely2 = null;
                    try {
                        SQLKysely2 = Tietokanta.tietoKanta.prepareStatement(asiakas2Tietue2);
                        SQLPalaute2 = SQLKysely2.executeQuery();
                        System.out.println("DEBUG " + asiakas2Tietue2);
                    } catch (Exception se) {
                        throw se;
                    }

                    LaitteetJaPalvelut laitteetJaPalvelut = new LaitteetJaPalvelut();
                    try {
                        while (SQLPalaute2.next()) {
                            laitteetJaPalvelut.setLpHinta(Integer.parseInt(SQLPalaute2.getString("laitteetpalvelut.Hinta")));
                            laitepalvelulista.add(SQLPalaute2.getString("laitteetpalvelut.Nimi"));
                            hinta = hinta + laitteetJaPalvelut.getLpHinta();
                        }
                    } catch (Exception se) {
                        throw se;
                    }

                    LocalDate parsed = LocalDate.parse(laskutus.getAloituspvm());
                    LocalDate current = LocalDate.parse(laskutus.getLopetuspvm());
                    Period p = Period.between(parsed, current);
                    double varauksenhinta = laskutus.getVarausHinta() * p.getDays() + hinta;

                    textfieldnimila.setText(laskutus.getaNimi());
                    textfieldtunnusla.setText(laskutus.getTunnus());
                    textfieldpuhelinnrola.setText(laskutus.getPuhelinnumero());
                    textfieldsahkopostila.setText(laskutus.getSahkoposti());
                    textfieldosoitela.setText(laskutus.getKatuosoite());
                    textfieldyhteyshenkilola.setText(laskutus.getYhteyshenkilo());
                    textfieldpostitoimipaikkala.setText(laskutus.getToimipaikka());
                    textfieldpostinrola.setText(laskutus.getPostiNro());
                    textfieldvarausla.setText(laskutus.getVaraustunnus());
                    textfieldvarattutoimitilala.setText(laskutus.getVarattuTila());
                    textfieldalkupvmla.setText(laskutus.getAloituspvm());
                    textfieldloppupvm.setText(laskutus.getLopetuspvm());
                    textfieldvaraushintala.setText(Double.toString(varauksenhinta));

                    ObservableList<String> laskutuslista = FXCollections.observableArrayList(
                            laitepalvelulista);
                    lvlaskutuslp.setItems(laskutuslista);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                throw e;
            }
        }
    }

    public void tulostaLasku(ActionEvent event) throws Exception {
        String filename = LocalDate.now() +" Varauksen "+ "'" + textfieldvarausla.getText() + "'" + " Lasku.txt";
        try {

            FileWriter fileWriter = new FileWriter(filename);
                System.out.println("Tiedosto avattu");
                fileWriter.write("Nimi: " + textfieldnimila.getText() + "\r\n");
                fileWriter.write("Tunnus: " + textfieldtunnusla.getText() + "\r\n");
                fileWriter.write("Yhteyshenkilö: " + textfieldyhteyshenkilola.getText() + "\r\n");
                fileWriter.write("Puhelinnumero: " + textfieldpuhelinnrola.getText() + "\r\n");
                fileWriter.write("Sähköposti: " + textfieldsahkopostila.getText() + "\r\n");
                fileWriter.write("Osoite: " + textfieldosoitela.getText() + "\r\n");
                fileWriter.write("Postinumero: " + textfieldpostinrola.getText() + "\r\n");
                fileWriter.write("Postitoimipaikka: " + textfieldpostitoimipaikkala.getText() + "\r\n");
                fileWriter.write("Varausnumero: " + textfieldvarausla.getText() + "\r\n");
                fileWriter.write("Varattutoimitila: " + textfieldvarattutoimitilala.getText() + "\r\n");
                fileWriter.write("Varauksen Laitteet ja palvelut: " + laitepalvelulista + "\r\n");
                fileWriter.write("Varauksen alkupäivämäärä: " + textfieldalkupvmla.getText() + "\r\n");
                fileWriter.write("Varauksen loppupäivämäärä: " + textfieldloppupvm.getText() + "\r\n");
                fileWriter.write("Varauksen loppuhinta: " + textfieldvaraushintala.getText() + "\r\n");

                fileWriter.close();
                System.out.println("Tiedosto suljettu");
            } catch (Exception e) {
                System.err.println(e);
            }
        if(checkboxsplasku.isSelected()){
            Tietokanta.TietokannanAvaus();

            String tunnusteksti = textfieldvarausla.getText();

            String AsiakkaanValintaTunnuksella = "SELECT VarausID FROM varaus WHERE Varaustunnus = " + "'" + tunnusteksti + "'";
            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;

            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(AsiakkaanValintaTunnuksella);
                SQLPalaute = SQLKysely.executeQuery();

                /**
                 * Tässä tutkitaan, vastaako joku tietueista kyselyä ja sen ehtoja. jos kyllä, niin asiakkaan tiedot päivitetään.
                 * */
                if (SQLPalaute.next()) {
                    System.out.println("Tosiaan1");

                    ResultSet Palaute = null;
                    PreparedStatement lause = null;

                    String asiakasTiedonPaivitysSQL = "UPDATE  varaus "
                            + "SET aloitusPVM = ?, lopetusPVM = ?, Sahkopostilasku = ? "
                            + " WHERE Varaustunnus = ?";

                    try {
                        // luo PreparedStatement-olio sql-lauseelle
                        lause = Tietokanta.tietoKanta.prepareStatement(asiakasTiedonPaivitysSQL);
                        lause.setString(1, String.valueOf(textfieldalkupvmla.getText()));
                        lause.setString(2, String.valueOf(textfieldloppupvm.getText()));
                        lause.setString(3, String.valueOf(1));
                        lause.setString(4, String.valueOf(textfieldvarausla.getText()));
                        lause.executeUpdate();

                        System.out.println("Tehty päivityksiä asiakastietoihin!");

                    } catch (Exception e) {
                        System.out.println(e);
                        throw e;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            naytaHalytys(Alert.AlertType.INFORMATION,"Lähetetään lasku sähköpostiin");
        }else{
            Tietokanta.TietokannanAvaus();

            String tunnusteksti = textfieldvarausla.getText();

            String AsiakkaanValintaTunnuksella = "SELECT VarausID FROM varaus WHERE Varaustunnus = " + "'" + tunnusteksti + "'";
            ResultSet SQLPalaute = null;
            PreparedStatement SQLKysely = null;

            try {
                SQLKysely = Tietokanta.tietoKanta.prepareStatement(AsiakkaanValintaTunnuksella);
                SQLPalaute = SQLKysely.executeQuery();

                /**
                 * Tässä tutkitaan, vastaako joku tietueista kyselyä ja sen ehtoja. jos kyllä, niin asiakkaan tiedot päivitetään.
                 * */
                if (SQLPalaute.next()) {
                    System.out.println("DEBUG: Asiakkaan tiedot löytyvät, päivitetään tiedot");

                    ResultSet Palaute = null;
                    PreparedStatement lause = null;

                    String asiakasTiedonPaivitysSQL = "UPDATE  varaus "
                            + "SET aloitusPVM = ?, lopetusPVM = ?, Paperilasku = ? "
                            + " WHERE Varaustunnus = ?";

                    try {
                        // luo PreparedStatement-olio sql-lauseelle
                        lause = Tietokanta.tietoKanta.prepareStatement(asiakasTiedonPaivitysSQL);
                        lause.setString(1, String.valueOf(textfieldalkupvmla.getText()));
                        lause.setString(2, String.valueOf(textfieldloppupvm.getText()));
                        lause.setString(3, String.valueOf(1));
                        lause.setString(4, String.valueOf(textfieldvarausla.getText()));
                        lause.executeUpdate();

                        System.out.println("Tehty päivityksiä asiakastietoihin!");

                    } catch (Exception e) {
                        System.out.println(e);
                        throw e;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            naytaHalytys(Alert.AlertType.INFORMATION,"Lähetetään lasku paperisena");
        }
    }

    /** Tässä olis asiakastiedoille uusi painike - tarvii vaan buttonin käyttöliittymään */

    public void muokkaaAsiakasTietoja(ActionEvent event) throws Exception {

        Tietokanta.TietokannanAvaus();

        String tunnusteksti = textfieldtunnusat.getText();

        String AsiakkaanValintaTunnuksella = "SELECT AsiakasID FROM asiakas WHERE Tunnus = " + "'" + tunnusteksti + "'";
        ResultSet SQLPalaute = null;
        PreparedStatement SQLKysely = null;

        try {
            SQLKysely = Tietokanta.tietoKanta.prepareStatement(AsiakkaanValintaTunnuksella);
            SQLPalaute = SQLKysely.executeQuery();

            /**
             * Tässä tutkitaan, vastaako joku tietueista kyselyä ja sen ehtoja. jos kyllä, niin asiakkaan tiedot päivitetään.
             * */
            if (SQLPalaute.next()) {
                System.out.println("DEBUG: Asiakkaan tiedot löytyvät, päivitetään tiedot");

                ResultSet Palaute = null;
                PreparedStatement lause = null;

                String asiakasTiedonPaivitysSQL = "UPDATE  asiakas "
                        + "SET Nimi = ?, Yhteyshenkilo = ?, Puhelinnumero = ?, Sahkoposti = ?, Katuosoite = ?, Postinumero = ?, Postitoimipaikka = ? "
                        + " WHERE Tunnus = ?";

                try {
                    // luo PreparedStatement-olio sql-lauseelle
                    lause = Tietokanta.tietoKanta.prepareStatement(asiakasTiedonPaivitysSQL);
                    lause.setString(1, String.valueOf(textfieldnimiat.getText()));
                    lause.setString(2, String.valueOf(textfieldyhteyshenkiloat.getText()));
                    lause.setString(3, String.valueOf(textfieldpuhelinnumeroat.getText()));
                    lause.setString(4, String.valueOf(textfieldsahkopostiat.getText()));
                    lause.setString(5, String.valueOf(textfieldosoiteat.getText()));
                    lause.setString(6, String.valueOf(textfieldpostinumeroat.getText()));
                    lause.setString(7, String.valueOf(textfieldtoimipaikkaat.getText()));
                    lause.setString(8, String.valueOf(textfieldtunnusat.getText()));
                    lause.executeUpdate();

                    System.out.println("Tehty päivityksiä asiakastietoihin!");

                } catch (Exception e) {
                    System.out.println(e);
                    throw e;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}