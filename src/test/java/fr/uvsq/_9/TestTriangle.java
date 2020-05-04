package fr.uvsq._9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTriangle {
    @Test
    public void Testtriangle() {
        Triangle r = new Triangle(new Point(1, 1), new Point(10, 1), new Point(1, 10));
        assertEquals(r.toString(),"Triangle (1,1) (10,1) (1,10)");
    }
    
    @Test
    public void TestDeplaceTriangle() {
        Triangle r = new Triangle(new Point(1, 1), new Point(10, 1), new Point(1, 10));
        r.deplace(new Point(10,10));
        assertEquals(r.toString(),"Triangle (10,10) (19,10) (10,19)");
    }
}
