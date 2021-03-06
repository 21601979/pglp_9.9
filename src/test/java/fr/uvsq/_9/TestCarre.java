package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import forme.Carre;
import forme.Point;

public class TestCarre {
    @Test
    public void Testcarre() {
        Carre r = new Carre(new Point(1, 1), 10, "1");
        assertEquals(r.toString(),"Carre 1 (1,1) 10");
    }
    
    @Test
    public void TestDeplaceCarre() {
        Carre c = new Carre(new Point(1,1), 10, "1");
        c.deplace(new Point(10,10));
        assertEquals(c.toString(),"Carre 1 (11,11) 10");
    }
}
