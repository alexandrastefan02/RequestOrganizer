package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pensionar extends Utilizator{
    String nume;

    public Pensionar(String nume) {
        this.nume = nume;
    }

    public String getName() {
        return nume;
    }

    @Override
    public String scriereText(Cerere.Type tipCerere) throws IllegalRequestException{
        if (tipCerere== Cerere.Type.inlocBul || tipCerere== Cerere.Type.inlocCarnetSofer || tipCerere== Cerere.Type.inrCupoanePensie) {
            return "Subsemnatul " + this.nume + ", va rog sa-mi aprobati urmatoarea solicitare: "+tipCerere.getTxt();
        } else {
            throw new IllegalRequestException("Utilizatorul de tip pensionar nu poate inainta o cerere de tip "+tipCerere.getTxt());
        }
    }
    public Cerere createCerere(Cerere.Type tipCerere, int priority, Date date) {
       Cerere cerere=null;
        try{
           cerere= new Cerere(scriereText(tipCerere), date, priority);
        }catch(IllegalRequestException e){
           // System.out.println("Utilizatorul de tip pensionar nu poate inainta o cerere de tip"+tipCerere.getTxt());
            cerere=null;
        }
        return cerere;
    }

}
