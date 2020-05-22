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
import forme.Point;
import forme.Rectangle;
import forme.Triangle;
import fr.uvsq._9.ExistePasException;
import fr.uvsq._9.ListeForme;
/**
 * class commande de déplacement .
 * @author Tanguy
 *
 */
public class CommandeMove implements Commande {
    /**
     * id de l'objet a déplacer.
     */
    private String iD;
    /**
     * point de référence pour le déplacement.
     */
    private Point p;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande de déplcament.
     * @param name nom de l'objet a déplacer
     * @param depl point de référence pour le déplacement
     * @param l liste de forme
     */
    public CommandeMove(final String name, final Point depl,
            final ListeForme l) {
        iD = name;
        p = depl;
        lObj = l;
    }
    /**
     * éxécution de la commande de déplacement.
     */
    public void execute() {
        Forme f = lObj.search(iD);
        if (f == null) {
            System.out.println("l'objet " + iD + " n'existe"
                    + " pas dans le dessin");
        } else {
            try {
                Connection conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                String getType = "SELECT type FROM Name WHERE ID = ?";
                PreparedStatement prepType = conect.prepareStatement(getType);
                prepType.setString(1, iD);
                ResultSet result = prepType.executeQuery();
                if (result.next()) {
                    String type = result.getString("type");
                    conect.close();
                    if (type.equals("carre")) {
                        DAO<Carre> d = DAOFactory.getDAOcarre();
                        Carre c = d.find(iD);
                        c.deplace(p);
                        d.update(c);
                    }
                    if (type.equals("cercle")) {
                        DAO<Cercle> d = DAOFactory.getDAOcercle();
                        Cercle c = d.find(iD);
                        c.deplace(p);
                        d.update(c);
                    }
                    if (type.equals("rectangle")) {
                        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                        Rectangle c = d.find(iD);
                        c.deplace(p);
                        d.update(c);
                    }
                    if (type.equals("triangle")) {
                        DAO<Triangle> d = DAOFactory.getDAOtriangle();
                        Triangle c = d.find(iD);
                        c.deplace(p);
                        d.update(c);
                    }
                    if (type.equals("groupe")) {
                        DAO<Groupe> d = DAOFactory.getDAOgroupe();
                        Groupe c = d.find(iD);
                        c.deplace(p);
                        d.update(c);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ExistePasException e) {
                e.printStackTrace();
            }
        }
    }

}
