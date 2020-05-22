package fr.uvsq._9;

public class DAOFactory {
    public static DAO<Carre> getDAOcarre() {
        return new DAOcarre();
    }
    public static DAO<Cercle> getDAOcercle() {
        return new DAOcercle();
    }
    public static DAO<Rectangle> getDAOrectangle() {
        return new DAOrectangle();
    }
    public static DAO<Triangle> getDAOtriangle() {
        return new DAOtriangle();
    }
    public static DAO<Groupe> getDAOgroupe() {
        return new DAOgroupe();
    }
}
