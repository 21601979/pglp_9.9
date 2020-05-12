package fr.uvsq._9;
/**
 * class rectangle.
 * @author Tanguy
 */
public class Rectangle implements Forme {

   /**
    * point de référence du rectangle.
    */
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
    * id d'un rectangle.
    */
   private String iD;

   /**
    * methode qui initialise un rectangle.
    * @param setp point de reférence du rectangle
    * @param x longueur du rectangle
    * @param y hauteur du rectangle
    * @param name nom du rectangle
    */
   public Rectangle(final Point setp, final int x,
           final int y, final String name) {
       p = setp;
       sizeX = x;
       sizeY = y;
       iD = name;
   }
   /**
    * methode qui deplace un rectangle.
    * @param depl qui sert pour le déplacement
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
   /**
    * methode qui renvoi l'id d'un rectangle.
    * @return id d'un rectangle
    */
   public String getID() {
       return iD;
    }
    /**
     * methode qui renvoi p.
     * @return p
     */
    public Point getp() {
        return p;
    }
    /**
     * methode qui renvoi sizeY.
     * @return sizey
     */
    public int getSizeY() {
        return sizeY;
    }
    /**
     * methode qui renvoi sizex.
     * @return sizey
     */
    public int getSizeX() {
       return sizeY;
    }

}
