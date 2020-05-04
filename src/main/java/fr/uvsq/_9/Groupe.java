package fr.uvsq._9;

import java.util.ArrayList;
import java.util.Iterator;

public class Groupe implements Forme {
    ArrayList<Forme> l = new ArrayList<Forme>();
    
    public Groupe() {
        
    }

    public void deplace(Point depl) {
        Iterator<Forme> i = l.iterator();
        while(i.hasNext()) {
            i.next().deplace(depl);
        }
    }
    
    public void add(Forme f) {
        l.add(f);
    }
    
    @Override
    public String toString() {
        String res = "";
        Iterator<Forme> i = l.iterator();
        while(i.hasNext()) {
            res = res + "( " + i.next().toString() + " )";
        }
        return res;
    }
}
