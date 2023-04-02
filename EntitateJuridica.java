package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntitateJuridica extends Utilizator{
    String numeCompanie;
    String reprezentant;

    public EntitateJuridica(String numeCompanie, String reprezentant) {
        this.numeCompanie = numeCompanie;
        this.reprezentant = reprezentant;
    }


    public String getName() {
        return reprezentant;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    @Override
    public String scriereText(Cerere.Type tipCerere) throws IllegalRequestException{
        if (tipCerere== Cerere.Type.creareActConstitutiv || tipCerere== Cerere.Type.reinnoreAutorizatie) {
            return "Subsemnatul " + this.reprezentant + ", reprezentant legal al companiei " + this.numeCompanie+", va rog sa-mi aprobati urmatoarea solicitare: "+tipCerere.getTxt();
        } else {
            throw new IllegalRequestException("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip "+tipCerere.getTxt());
        }
    }

    public Cerere createCerere(Cerere.Type tipCerere, int priority, Date date) {
        Cerere cerere = null;
        try{
            cerere= new Cerere(scriereText(tipCerere), date, priority);
        }catch(IllegalRequestException e){
            //System.out.println("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip"+tipCerere.getTxt());
            cerere=null;
        }
        return cerere;
    }



}
