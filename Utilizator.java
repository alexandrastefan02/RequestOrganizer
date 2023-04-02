package org.example;

import java.util.Date;

public abstract class Utilizator {
    public abstract String scriereText(Cerere.Type tipCerere) throws IllegalRequestException;
    public abstract Cerere createCerere(Cerere.Type tipCerere, int priority, Date date);

    /*public Cerere creareCerere(Cerere.Type tipCerere, int priority, String date){
        Cerere cerere = new Cerere(tipCerere.getTxt(), date, priority);
        return cerere;
    }

    public abstract void retragerereCerere(String date);*/
    //mentinere colectii cereri solutionte/in asteptare
    //afisare cereri solutionate, in ordinea datei de creare
    //afisare cereri in asteptare, in ordinea datei de creare
}
