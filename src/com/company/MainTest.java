package com.company;

import com.company.manager.ManagerCorredors;
import com.company.model.Equip;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        ManagerCorredors.inscriureCorredor("pepe viyuela", new Equip("Los pepes"));

    }
}
