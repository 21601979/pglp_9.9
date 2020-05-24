package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import forme.Carre;
import forme.Cercle;
import forme.Groupe;
import forme.Point;

public class GroupeTest {

    @Test
    public void Groupetest() {
        Carre r = new Carre(new Point(1, 1), 10, "1");
        Cercle c = new Cercle(new Point(1,1), 10, "1");
        Groupe g = new Groupe("a");
        g.add(r);
        g.add(c);
        assertEquals(g.toString(),"groupe a ( Carre 1 (1,1) 10 ) ( Cercle 1 (1,1) 10 )");
    }
    
    @Test
    public void groupeTestDeplace() {
        Carre r = new Carre(new Point(1, 1), 10, "1");
        Cercle c = new Cercle(new Point(1,1), 10, "1");
        Groupe g = new Groupe("a");
        g.add(r);
        g.add(c);
        g.deplace(new Point(10,10));
        assertEquals(g.toString(),"groupe a ( Carre 1 (11,11) 10 ) ( Cercle 1 (11,11) 10 )");
    }
}
