package commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import dao.DAOFactory;
import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.Groupe;
import forme.Rectangle;
import forme.Triangle;
import fr.uvsq._9.ListeForme;
/**
 * class commande load.
 * @author Tanguy
 *
 */
public class CommandeLoad implements Commande {
    /**
     * non de l'ojet a charger.
     */
    private String objLoad;
    /**
     * liste de forme.
     */
    private ListeForme l;
    /**
     * constructeur de la commande load.
     * @param temp Forme a charger
     * @param lis liste de forme
     */
    public CommandeLoad(final String temp, final ListeForme lis) {
        objLoad = temp;
        l = lis;
    }
    /**
     * execution de la commande load.
     */
    public void execute() {
        Connection conect;
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            String getType = "SELECT type FROM Name WHERE ID = ?";
            PreparedStatement prepType = conect.prepareStatement(getType);
            prepType.setString(1, objLoad);
            ResultSet result = prepType.executeQuery();
            if (l.search(objLoad) != null) {
                System.out.println("la forme est déja présente dans le dessin");
            }
            if (result.next()) {
                String type = result.getString("type");
                conect.close();
                result.close();
                Forme c;
                if (type.equals("carre")) {
                    DAO<Carre> d = DAOFactory.getDAOcarre();
                    c = d.find(objLoad);
                    l.add(c);
                } else if (type.equals("cercle")) {
                    DAO<Cercle> d = DAOFactory.getDAOcercle();
                    c = d.find(objLoad);
                    l.add(c);
                } else if (type.equals("rectangle")) {
                    DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                    c = d.find(objLoad);
                    l.add(c);
                } else if (type.equals("triangle")) {
                    DAO<Triangle> d = DAOFactory.getDAOtriangle();
                    c = d.find(objLoad);
                    l.add(c);
                } else if (type.equals("groupe")) {
                    DAO<Groupe> d = DAOFactory.getDAOgroupe();
                    c = d.find(objLoad);
                    l.add(c);
                }
            } else {
                System.out.println("la forme " + objLoad + " n'existe pas"
                    + " dans la BD");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
