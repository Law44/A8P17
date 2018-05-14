package com.company.manager;

import com.company.model.Corredor;
import com.company.model.Equip;

import java.io.*;

public class ManagerCorredors {
    static Corredor[] corredors = new Corredor[100];

    public static Corredor inscriureCorredor(String nom, Equip equip) throws IOException {
        if(equip == null){
            return null;
        }
        FileWriter out = new FileWriter("Corredors.txt", true);

        out.write(nom + ":");
        out.write(equip.id + ":");
        out.write(String.valueOf(1000+1) + "\n");
        out.close();

        return null;
    }

    public static Corredor obtenirCorredor(int id) throws IOException {
       BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
       String c;
       while ((c = reader.readLine()) != null) {
           String[] partes = c.split(":");
            if (id == Integer.parseInt(partes[2])){
                Corredor corredor = new Corredor(partes[0], Integer.parseInt(partes[1]));
                corredor.id = id;
                return corredor;
            }

       }

        return null;
    }

    public static Corredor[] obtenirLlistaCorredors() throws IOException {
        Corredor[] llistaCorredors = new Corredor[obtenirNumeroCorredors()];
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        int cont = 0;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            Corredor corredor = new Corredor(partes[0], Integer.parseInt(partes[1]));
            corredor.id = Integer.parseInt(partes[2]);
            llistaCorredors[cont] = corredor;
            cont++;


        }

        return llistaCorredors;
    }

    public static Corredor[] buscarCorredorsPerNom(String nom){
        Corredor[] llistaCorredors = new Corredor[obtenirNumeroCorredorsPerNom(nom)];

        int j = 0;
        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].nom.toLowerCase().contains(nom.toLowerCase())){
                llistaCorredors[j] = corredors[i];
                j++;
            }
        }

        return llistaCorredors;
    }

    public static boolean existeixCorredor(String nom) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if (nom.equals(partes[0])){
                return true;
            }

        }

        return false;
    }

    public static void modificarNomCorredor(int id, String nouNom){
        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].id == id){
                corredors[i].nom = nouNom;
            }
        }
    }

    public static void modificarEquipCorredor(int id, Equip nouEquip){
        if(nouEquip == null){
            return;
        }

        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].id == id){
                corredors[i].idEquip = nouEquip.id;
            }
        }
    }

    public static void esborrarCorredor(int id){
        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].id == id){
                corredors[i] = null;
            }
        }
    }

    private static int obtenirUltimIdCorredor(){
        int maxId = 0;
        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].id > maxId){
                maxId = corredors[i].id;
            }
        }

        return maxId;
    }

    private static int obtenirNumeroCorredors() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        int count = 0;
        while ((c = reader.readLine()) != null) {
            count++;

        }

        return count;
    }

    private static int obtenirNumeroCorredorsPerNom(String nom){
        int count = 0;
        for (int i = 0; i < corredors.length; i++) {
            if(corredors[i] != null && corredors[i].nom.toLowerCase().contains(nom.toLowerCase())){
                count++;
            }
        }

        return count;
    }
}
