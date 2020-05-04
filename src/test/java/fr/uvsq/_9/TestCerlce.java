package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCerlce {

    @Test
    public void TestCercle() {
        Cercle c = new Cercle(new Point(1,1), 10);
        assertEquals(c.toString(),"Cercle (1,1) 10");
    }
    
    @Test
    public void TestDeplaceCercle() {
        Cercle c = new Cercle(new Point(1,1), 10);
        c.deplace(new Point(10,10));
        assertEquals(c.toString(),"Cercle (10,10) 10");
    }
}
