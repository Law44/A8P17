package com.company;

import com.company.manager.ManagerCorredors;
import com.company.model.Corredor;
import com.company.model.Equip;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        Corredor[] corredors = ManagerCorredors.obtenirLlistaCorredors();
        for (int i=0; i < corredors.length; i++){
            System.out.println(corredors[i].nom);
        }
    }
}
