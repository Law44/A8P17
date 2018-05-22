package com.company.manager;

import com.company.model.Equip;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class ManagerEquips {
    static int MAXNOM = 12;
    static int MAXID = 4;

    public static Equip inscriureEquip(String nom) throws IOException {
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));
        long posFinal = fc.size();

        fc.position(posFinal);
        fc.write(ByteBuffer.wrap(nom.getBytes()));

        int id = (int) (posFinal/(MAXNOM+MAXID))+1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(MAXID);
        byteBuffer.putInt(0, id);

        fc.position(posFinal+MAXNOM);
        fc.write(byteBuffer);
        Equip equip = new Equip(nom);
        equip.id = id;
        return equip;
    }


    public static Equip obtenirEquip(int id) throws IOException {
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));

        fc.position((id-1) * (MAXNOM+MAXID));
        ByteBuffer byteBufferNom = ByteBuffer.allocate(MAXNOM);

        fc.read(byteBufferNom);
        String nom = new String(byteBufferNom.array(), Charset.forName("UTF-8"));
        Equip equip = new Equip(nom);
        equip.id = id;

        return equip;
    }

    public static Equip obtenirEquip(String nom) throws IOException {
            long pos = 0;
            FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
            long fin=fc.size();
            while(pos<fin) {
                fc.position(pos);
                ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
                fc.read(byteBuffer);
                String nomC = new String(byteBuffer.array(), Charset.forName("UTF-8"));
                int larg=nom.length();
                String prueba=nomC.substring(0,larg);

                if (prueba.toLowerCase().equals(nom.toLowerCase())) {

                    ByteBuffer byteBuffer1 = ByteBuffer.allocate(MAXID);
                    fc.position(pos+MAXNOM);
                    fc.read(byteBuffer1);
                    int id =byteBuffer1.getInt(0);
                    Equip equip = new Equip(nom);
                    equip.id = id;
                    return equip;
                }
                pos+=MAXNOM+MAXID;
            }



        return null;
    }

    public static String obtenirNomEquip(int id) throws IOException {
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));
        fc.position((id-1) * (MAXNOM+MAXID));
        ByteBuffer byteBufferNom = ByteBuffer.allocate(MAXNOM);

        fc.read(byteBufferNom);
        String nom = new String(byteBufferNom.array(), Charset.forName("UTF-8"));

        return nom;
    }

    public static Equip[] obtenirLlistaEquips() throws IOException {
        Equip[] llistaEquips = new Equip[obtenirNumeroEquips()];
        int pos = 0;
        int cont = 0;
        FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
        long fin=fc.size();
        while(pos<fin) {
            fc.position(pos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBuffer);

            ByteBuffer byteBuffer1 = ByteBuffer.allocate(MAXID);
            fc.position(pos+MAXNOM);
            fc.read(byteBuffer1);
            int id =byteBuffer1.getInt(0);
            Equip equip = new Equip(obtenirNomEquip(id));
            equip.id = id;
            llistaEquips[cont] = equip;
            cont++;

            pos += MAXNOM + MAXID;
        }

        return llistaEquips;
    }

    public static Equip[] buscarEquipsPerNom(String nom) throws IOException {
        Equip[] llistaEquips = new Equip[obtenirNumeroEquipsPerNom(nom)];
        int pos = 0;
        int cont = 0;
        FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
        long fin=fc.size();
        while(pos<fin) {
            fc.position(pos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBuffer);
            String nomC = new String(byteBuffer.array(), Charset.forName("UTF-8"));
            String prueba = nomC.substring(0, nomC.length());

            if (prueba.toLowerCase().contains(nom.toLowerCase())) {
                ByteBuffer byteBuffer1 = ByteBuffer.allocate(MAXID);
                fc.position(pos+MAXNOM);
                fc.read(byteBuffer1);
                int id =byteBuffer1.getInt(0);
                Equip equip = new Equip(obtenirNomEquip(id));
                equip.id = id;
                llistaEquips[cont] = equip;
                cont++;
            }
            pos += MAXNOM + MAXID;
        }

        return llistaEquips;
    }

    public static boolean existeixEquip(String nom) throws IOException {
        long pos = 0;
        FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
        long fin=fc.size();
        while(pos<fin) {
            fc.position(pos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBuffer);
            String nomC = new String(byteBuffer.array(), Charset.forName("UTF-8"));
            int larg=nom.length();
            String prueba=nomC.substring(0,larg);

            if (prueba.toLowerCase().equals(nom.toLowerCase())) {

                return true;
            }
            pos+=MAXNOM+MAXID;
        }


        return false;
    }

    public static void modificarNomEquip(int id, String nouNom) throws IOException {
        String nombre = nouNom;
        if (nouNom.length() > MAXNOM){
            nombre = nouNom.substring(0, MAXNOM);
        }
        else {
            for (int i = 0; i < MAXNOM-nouNom.length(); i++) {
                nombre+= " ";
            }
        }
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));
        fc.position((id-1) * (MAXNOM+MAXID));
        fc.write(ByteBuffer.wrap(nombre.getBytes()));
    }

    public static void esborrarEquip(int id) throws IOException {
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));
        fc.position((id-1) * (MAXNOM+MAXID));
        String nom = "";
        for (int i = 0; i < MAXID+MAXNOM; i++) {
            nom+= " ";
        }
        fc.write(ByteBuffer.wrap(nom.getBytes()));
    }

    private static int obtenirUltimIdEquip() throws IOException {
        int maxId = 0;
        int pos = 0;
        FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
        long fin=fc.size();
        while(pos<fin) {
            fc.position(pos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBuffer);

            ByteBuffer byteBuffer1 = ByteBuffer.allocate(MAXID);
            fc.position(pos+MAXNOM);
            fc.read(byteBuffer1);
            int id =byteBuffer1.getInt(0);
            if (id > maxId){
                maxId = id;
            }

            pos += MAXNOM + MAXID;
        }
        return maxId;
    }

    private static int obtenirNumeroEquips() throws IOException {
        int count = 0;
        long inicio = 0;
        FileChannel fc = (FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, WRITE, CREATE));
        long posFinal = fc.size();

        while (inicio<posFinal) {
            fc.position(inicio);
            ByteBuffer byteBufferNom = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBufferNom);
            String nom = new String(byteBufferNom.array(), Charset.forName("UTF-8"));
            if (nom.charAt(0) != ' ') {
                count++;
            }
            inicio+=MAXNOM+MAXID;

        }


        return count;
    }

    private static int obtenirNumeroEquipsPerNom(String nom) throws IOException {
        int count = 0;
        long pos = 0;
        FileChannel fc = FileChannel.open(FileSystems.getDefault().getPath("equips.txt"), READ, CREATE);
        long fin=fc.size();
        while(pos<fin) {
            fc.position(pos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(MAXNOM);
            fc.read(byteBuffer);
            String nomC = new String(byteBuffer.array(), Charset.forName("UTF-8"));
            String prueba = nomC.substring(0, nomC.length());

            if (prueba.toLowerCase().contains(nom.toLowerCase())) {

                count++;
            }
            pos += MAXNOM + MAXID;
        }
        return count;
    }
}
