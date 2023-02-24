package com.example.demo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Alkunäkymä.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("~uber varausjärjestelmä!");
        stage.setScene(scene);
        stage.setResizable(false); // Estetään ikkunan koon muuttaminen.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        //Testattiin tietokannan avaamista ja sulkemista. Hyvin toimi!
        try {
            Tietokanta.TietokannanAvaus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tietokanta.suljeTietokanta();


    }
}