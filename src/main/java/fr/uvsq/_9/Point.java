package fr.uvsq._9;

public class Point {
    private int x;
    private int y;
    public Point(int setx, int sety) {
        x = setx;
        y = sety;
    }
    public int getX() {
        return x;
    }
    public int getY() {
       return y;
    }
  
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
