package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Birou <T extends Utilizator>{//specializat pe un anumit tip de utilizator
    private PriorityQueue<Cerere> coadaCereri;
    private List <FunctionarPublic> functionariPublici=new ArrayList<>();
    public Birou() {
        // custom comparator to sort the requests by priority and then by date
        this.coadaCereri = new PriorityQueue<>(new Comparator<Cerere>() {
            @Override
            public int compare(Cerere c1, Cerere c2) {
                if (c1.getPriority() != c2.getPriority()) {
                    return c2.getPriority() - c1.getPriority();
                } else  return c1.getDate().compareTo(c2.getDate());
            }
        });
    }

    public void addRequest(Cerere request) {
        coadaCereri.add(request);
    }

    public PriorityQueue<Cerere> getCoadaCereri() {
        return coadaCereri;
    }

    public List<FunctionarPublic> getListaFunct(){
        return functionariPublici;
    }

    public void addFunctionar(FunctionarPublic f){
        functionariPublici.add(f);
    }
}
