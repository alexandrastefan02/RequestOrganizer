package org.example;

import java.util.Date;

public class Elev extends Utilizator{
    String nume;
    String scoala;

    public Elev(String nume, String scoala) {
        this.nume = nume;
        this.scoala = scoala;
    }

    public String getName() {
        return nume;
    }

    @Override
    public String scriereText(Cerere.Type tipCerere) throws IllegalRequestException{
        if (tipCerere== Cerere.Type.inlocBul || tipCerere== Cerere.Type.inlocCarnetElev) {
            return "Subsemnatul " + this.nume + ", elev la scoala " +this.scoala+", va rog sa-mi aprobati urmatoarea solicitare: "+tipCerere.getTxt();
        } else {
            throw new IllegalRequestException("Utilizatorul de tip elev nu poate inainta o cerere de tip "+tipCerere.getTxt());
        }
    }
    public Cerere createCerere(Cerere.Type tipCerere, int priority, Date date) {
        Cerere cerere =null;
        try{
            cerere= new Cerere(scriereText(tipCerere), date, priority);
        }catch(IllegalRequestException e){
            //System.out.println("Utilizatorul de tip elev nu poate inainta o cerere de tip"+tipCerere.getTxt());
            cerere =null;
        }
        return cerere;
    }
    @Override
    public String toString() {
        return "Elev{" +
                "nume='" + nume + '\'' +
                ", scoala='" + scoala + '\'' +
                '}';
    }
}
