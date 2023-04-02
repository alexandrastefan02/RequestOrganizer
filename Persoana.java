package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Persoana extends Utilizator {
    String nume;

    public Persoana() {
    }

    public Persoana(String nume) {
        this.nume = nume;
    }

    public String getName() {
        return nume;
    }

    @Override
    public String scriereText(Cerere.Type tipCerere) throws IllegalRequestException {
            if (tipCerere== Cerere.Type.inlocBul || tipCerere== Cerere.Type.inlocCarnetSofer) {
                return "Subsemnatul " + this.nume + ", va rog sa-mi aprobati urmatoarea solicitare: "+tipCerere.getTxt();
            } else {
                throw new IllegalRequestException("Utilizatorul de tip persoana nu poate inainta o cerere de tip "+tipCerere.getTxt());
            }
    }

    @Override
    public Cerere createCerere(Cerere.Type tipCerere, int priority, Date date) {
        Cerere cerere = null;
            try{
                cerere = new Cerere(scriereText(tipCerere), date, priority);
            }catch(IllegalRequestException e){
                //System.out.println("Utilizatorul de tip persoana nu poate inainta o cerere de tip"+tipCerere.getTxt());
                cerere=null;
            }
          return cerere;
    }

}