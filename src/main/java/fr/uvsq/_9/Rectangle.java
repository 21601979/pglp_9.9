package fr.uvsq._9;
/**
 * class rectangle.
 * @author Tanguy
 */
public class Rectangle implements Forme {

   private Point p;
   /**
    * longueur du rectangle.
    */
   private int sizeX;
   /**
    * hauteur du rectangle.
    */
   private int sizeY;

   /**
    * methode qui initialise un rectangle.
    * @param setp point de reférence du rectangle
    * @param x longueur du rectangle
    * @param y hauteur du rectangle
    */
   public Rectangle(final Point setp, final int x, final int y) {
       p = setp;
       sizeX = x;
       sizeY = y;
   }
   /**
    * methode qui deplace un rectangle.
    */
   public void deplace(final Point depl) {
      p = depl;
   }
   /**
    * methode qui renvoi un string décrivant un rectangle.
    */
   @Override
   public String toString() {
       return "Rectangle " + p.toString() + " " + sizeX + " " + sizeY;
   }

}
