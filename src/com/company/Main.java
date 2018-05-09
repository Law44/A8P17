package com.company;

import com.company.manager.ManagerCorredors;
import com.company.manager.ManagerCorredors2;
import com.company.manager.ManagerEquips;
import com.company.view.PantallaMenuPrincipal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ManagerEquips.inscriureEquip("LLiure");
        ManagerEquips.inscriureEquip("Equip A");
        ManagerEquips.inscriureEquip("Equip B");
        ManagerEquips.inscriureEquip("Equip C");
        ManagerEquips.inscriureEquip("Equip D");
        ManagerCorredors2.inscriureCorredor("Corredor Lliure", ManagerEquips.obtenirEquip("LLiure"));
        ManagerCorredors2.inscriureCorredor("Corredor A", ManagerEquips.obtenirEquip("Equip A"));
        ManagerCorredors2.inscriureCorredor("Corredor AA", ManagerEquips.obtenirEquip("Equip A"));
        ManagerCorredors2.inscriureCorredor("Corredor AAA", ManagerEquips.obtenirEquip("Equip A"));
        ManagerCorredors2.inscriureCorredor("Corredor B", ManagerEquips.obtenirEquip("Equip B"));
        ManagerCorredors2.inscriureCorredor("Corredor BB", ManagerEquips.obtenirEquip("Equip B"));
        ManagerCorredors2.inscriureCorredor("Corredor C", ManagerEquips.obtenirEquip("Equip C"));

        PantallaMenuPrincipal.mostrar();
    }
}
