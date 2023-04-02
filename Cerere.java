package org.example;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Cerere {
   String text;
   Date date;
   int priority; //5 cea mai urgenta
   public enum Type{
    inlocBul("inlocuire buletin"),
    inrVenitSalarial("inregistrare venit salarial"),
    inrCupoanePensie("inregistrare cupoane de pensie"),
    inlocCarnetSofer("inlocuire carnet de sofer"),
    inlocCarnetElev("inlocuire carnet de elev"),
    creareActConstitutiv("creare act constitutiv"),
    reinnoreAutorizatie("reinnoire autorizatie");

       private final String txt;
       Type(String txt) {
           this.txt = txt;
       }

       public String getTxt() {
           return txt;
       }
       public static Type getTypeByParam(String s) {
           for (Type p : values()) {
               if (Objects.equals(p.getTxt(), s)) {
                   return p;
               }
           }
           return null;
       }

}
    public Cerere(String text, Date date, int priority) {
        this.text = text;
        this.date = date;
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        String pattern = "dd-MMM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.date);
       return date + " - " + text+"\n";
    }


}

