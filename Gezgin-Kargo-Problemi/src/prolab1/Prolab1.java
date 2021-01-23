package prolab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.*;

public class Prolab1 {

    static void sirala(int kms[], int ids[], int uzakliklar[][]) {
        int c = 0;
        for (int a = 0; a < ids.length; a++) {
            for (int b = ids.length - 1; b > a; b--) {
                if (kms[b - 1] > kms[b]) {
                    int temp = kms[b-1];
                    kms[b-1] = kms[b];
                    kms[b] = temp;

                    temp = ids[b-1];
                    ids[b-1] = ids[b];
                    ids[b] = temp;
                }
            }
            for (c = a + 1; c < kms.length; c++) {
                kms[c] = uzakliklar[ids[a]][ids[c]];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        File sehir = new File("sehir.txt");
        File kgm = new File("kgm.txt");
        File kordinat = new File("kordinat.txt");

        int[][] uzakliklar = new int[81][81];
        int[] kordinatY = new int[81];
        int[] kordinatX = new int[81];
        list sehirler = new list(81);

        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(sehir));
        int i = 0;
        String satir = reader.readLine();
        while (satir!=null) {
            String[] line = satir.split(",");
            for (int j = 1; j < line.length-1; j++) {
                int k = Integer.parseInt(line[j].toString());
                sehirler.addKomsu(i, j, k,line[82]);
            }
            i++;
            satir = reader.readLine();
        }

        i = 0;
        reader = new BufferedReader(new FileReader(kgm));
        satir = reader.readLine();
        while (satir!=null) {
            String[] line = satir.split(",");
            for (int j = 0; j < line.length; j++) {
                uzakliklar[i][j] = Integer.parseInt(line[j]);
            }
            i++;
            satir = reader.readLine();
        }

        i = 0;
        reader = new BufferedReader(new FileReader(kordinat));
        satir = reader.readLine();
        while (satir!=null) {
            String[] line = satir.split(",");
            kordinatX[i] = Integer.parseInt(line[0]);
            kordinatY[i] = Integer.parseInt(line[1]);
            i++;
            satir = reader.readLine();
        }

        System.out.println("Gidilecek şehirlerin plakalarını aralarında boşluk bırakarak giriniz");
        Scanner km = new Scanner(System.in);
        String n = km.nextLine();
        String[] cities = n.split(" ");
        int[] ids = new int[cities.length];
        int[] kms = new int[cities.length];

        for (i = 0; i < cities.length; i++) {
            ids[i] = Integer.parseInt(cities[i]) - 1;
            kms[i] = uzakliklar[40][ids[i]];
        }
        
        sirala(kms, ids, uzakliklar);
        
        System.out.println("Gidiş Güzergahı");
        System.out.print("KOCAELİ(41)");
        
        i = 40;
        int k = 1;
        int[] gidis = new int[50];
        gidis[0] = 40;
        int uzaklik;
        int plaka = ids[0];
        String name = "";
        for (int a = 0; a < ids.length; a++) {
            while (uzakliklar[i][ids[a]] != 0) {
                LinkedList<komsu> city = sehirler.getCity(i);

                uzaklik = uzakliklar[i][ids[a]];
                for (int j = 0; j < city.size(); j++) {
                    if (city.get(j).maliyet != 0) {
                        if (uzaklik > uzakliklar[j][ids[a]]) {
                            uzaklik = uzakliklar[j][ids[a]];
                            plaka = j;
                            name = sehirler.getCity(j).get(j).name;
                            i = plaka;
                        }
                    }
                }

                gidis[k] = plaka;
                k++;
                System.out.print("->"+name+"("+(plaka+1)+")");
            }
            i = ids[a];
        }
        gidis[k] = 99;

        int[] donus = new int[20];
        donus[0] = i;
        k = 1;

        System.out.println("\nDönüş Güzergahı");
        System.out.print(name+"("+(plaka+1)+")");
        
        while (uzakliklar[i][40] != 0) {
            LinkedList<komsu> city = sehirler.getCity(i);
            uzaklik = uzakliklar[i][40];
            for (int j = 0; j < city.size(); j++) {
                if (city.get(j).maliyet != 0) {
                    if (uzaklik > uzakliklar[j][40]) {
                        uzaklik = uzakliklar[j][40];
                        plaka = j;
                        i = plaka;
                    }
                }
            }
            donus[k] = plaka;
            System.out.print("->"+sehirler.getCity(plaka).get(0).name+"("+(plaka+1)+")");
            k++;
        }

        donus[k] = 99;
        new harita(kordinatX, kordinatY, gidis, donus, ids);
    }

}
