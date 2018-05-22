package com.company.manager;

import com.company.model.Corredor;
import com.company.model.Equip;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

public class ManagerCorredors {

    public static Corredor inscriureCorredor(String nom, Equip equip) throws IOException {
        if(equip == null){
            return null;
        }
        FileWriter out = new FileWriter("Corredors.txt", true);

        out.write(nom + ":");
        out.write(equip.id + ":");
        out.write((obtenirUltimIdCorredor()+1) + "\n");
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

    public static Corredor[] buscarCorredorsPerNom(String nom) throws IOException {
        Corredor[] llistaCorredors = new Corredor[obtenirNumeroCorredorsPerNom(nom)];
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        int cont = 0;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if(partes[0].toLowerCase().contains(nom.toLowerCase())) {
                Corredor corredor = new Corredor(partes[0], Integer.parseInt(partes[1]));
                corredor.id = Integer.parseInt(partes[2]);
                llistaCorredors[cont] = corredor;
                cont++;
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

    public static void modificarNomCorredor(int id, String nouNom) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        FileWriter out = new FileWriter("Corredors2.txt", true);
        String c;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if (Integer.parseInt(partes[2]) != id){
                out.write(partes[0] + ":");
                out.write(partes[1] + ":");
                out.write(partes[2] + "\n");

            }
            else {
                out.write(nouNom + ":");
                out.write(partes[1] + ":");
                out.write(partes[2] + "\n");

            }

        }
        out.close();
        reader.close();

        Files.move(FileSystems.getDefault().getPath("Corredors2.txt"), FileSystems.getDefault().getPath("Corredors.txt"), REPLACE_EXISTING);

    }

    public static void modificarEquipCorredor(int id, Equip nouEquip) throws IOException {
        if(nouEquip == null){
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        FileWriter out = new FileWriter("Corredors2.txt", true);
        String c;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if (Integer.parseInt(partes[2]) != id){
                out.write(partes[0] + ":");
                out.write(partes[1] + ":");
                out.write(partes[2] + "\n");

            }
            else {
                out.write(partes[0] + ":");
                out.write(nouEquip.id + ":");
                out.write(partes[2] + "\n");

            }

        }
        out.close();
        reader.close();

        Files.move(FileSystems.getDefault().getPath("Corredors2.txt"), FileSystems.getDefault().getPath("Corredors.txt"), REPLACE_EXISTING);

    }

    public static void esborrarCorredor(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        FileWriter out = new FileWriter("Corredors2.txt", true);
        String c;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if (Integer.parseInt(partes[2]) != id){
                out.write(partes[0] + ":");
                out.write(partes[1] + ":");
                out.write(partes[2] + "\n");

            }
        }
        out.close();
        reader.close();

        Files.move(FileSystems.getDefault().getPath("Corredors2.txt"), FileSystems.getDefault().getPath("Corredors.txt"), REPLACE_EXISTING);

    }

    private static int obtenirUltimIdCorredor() throws IOException {
        int maxId = 0;
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if (Integer.parseInt(partes[2]) > maxId) {
                maxId = Integer.parseInt(partes[2]);
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

    public static int obtenirNumeroCorredorsPerNom(String nom) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Corredors.txt"));
        String c;
        int count = 0;
        while ((c = reader.readLine()) != null) {
            String[] partes = c.split(":");
            if(partes[0].toLowerCase().contains(nom.toLowerCase())){
                count++;
            }

        }

        return count;
    }
}
