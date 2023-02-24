package com.example.demo6;
import java.util.Random;

public class varaustunnus {
    static String merkit = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    static String rs = "";
    static int pituus = 15;

    static Random rand = new Random();

    public static String luoVaraustunnus() {
        rs = "";
        char[] teksti = new char[pituus]; // Luodaan uusi char-array, jonka pituus on pituus muuttuja
        for (int i = 0; i < pituus; i++) {
            teksti[i] = merkit.charAt(rand.nextInt(merkit.length()));
        }
        for (int i = 0; i < teksti.length; i++) {
            rs += teksti[i];
        }
        return rs;
    }
}