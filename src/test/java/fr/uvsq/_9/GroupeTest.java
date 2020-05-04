package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GroupeTest {

    @Test
    public void Groupetest() {
        Carre r = new Carre(new Point(1, 1), 10);
        Cercle c = new Cercle(new Point(1,1), 10);
        Groupe g = new Groupe();
        g.add(r);
        g.add(c);
        assertEquals(g.toString(),"( Carre (1,1) 10 )( Cercle (1,1) 10 )");
    }
}
