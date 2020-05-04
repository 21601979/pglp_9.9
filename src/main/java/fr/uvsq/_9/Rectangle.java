package fr.uvsq._9;

public class Rectangle implements Forme {

   Point p;
   int sizeX;
   int sizeY;

   public Rectangle(final Point setp, final int x, final int y) {
       p = setp;
       sizeX = x;
       sizeY = y;
   }
   public void deplace(final Point depl) {
      p = depl;
   }
   
   @Override
   public String toString() {
       return "Rectangle " + p.toString() + sizeX + " " + sizeY;
   }

}
