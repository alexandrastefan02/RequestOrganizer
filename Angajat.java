package org.example;

import java.io.File;
import java.util.Date;
import static org.example.ManagementPrimarie.appendStrToFile;
public class Angajat extends Utilizator{
    String nume;
    String companie;

    public Angajat(String nume, String companie) {
        this.companie = companie;
        this.nume = nume;
    }

    public String getName() {
        return nume;
    }

    @Override
    public String scriereText(Cerere.Type tipCerere) throws IllegalRequestException{
        if (tipCerere== Cerere.Type.inlocBul || tipCerere== Cerere.Type.inlocCarnetSofer || tipCerere== Cerere.Type.inrVenitSalarial) {
            return "Subsemnatul " + this.nume +", angajat la compania "+ this.companie+", va rog sa-mi aprobati urmatoarea solicitare: "+tipCerere.getTxt();
        } else {
            throw new IllegalRequestException("Utilizatorul de tip angajat nu poate inainta o cerere de tip "+tipCerere.getTxt());
        }
    }
    public Cerere createCerere(Cerere.Type tipCerere, int priority, Date date) {
        Cerere cerere= null;
        try{
            cerere= new Cerere(scriereText(tipCerere), date, priority);
        }catch(IllegalRequestException e){
            cerere=null;
        }
        return cerere;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", companie='" + companie + '\'' +
                '}';
    }
}
