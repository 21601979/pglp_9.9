package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRectangle {
    
    @Test
    public void Testrectangle() {
        Rectangle r = new Rectangle(new Point(1,1), 10, 20,"1");
        assertEquals(r.toString(),"Rectangle (1,1) 10 20");
    }
    
    @Test
    public void TestDeplaceRectangle() {
        Rectangle c = new Rectangle(new Point(1,1), 10, 20, "1");
        c.deplace(new Point(10,10));
        assertEquals(c.toString(),"Rectangle (10,10) 10 20");
    }

}
