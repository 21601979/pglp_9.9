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
import forme.Groupe;
import forme.Rectangle;
import forme.Triangle;
import fr.uvsq._9.ExistePasException;
/**
 * commande pour supprimer une forme.
 * @author Tanguy
 *
 */
public class CommandeDelete implements Commande {
    /**
     * identifiant de la forme a supprimer.
     */
    private String iD;
    /**
     * constructeur de la commande pour suprimer un objet.
     * @param name nom de la forme a supprimer
     */
    public CommandeDelete(final String name) {
        iD = name;
    }
    /**
     * methode qui suprime un objet de la base de données.
     */
    public void execute() {
        try {
            System.out.println("ok " + iD);
            Connection conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            String getType = "SELECT type FROM Name WHERE ID = ?";
            PreparedStatement prepType = conect.prepareStatement(getType);
            prepType.setString(1, iD);
            ResultSet result = prepType.executeQuery();
            if (result.next()) {
                String type = result.getString("type");
                conect.close();
                result.close();
                if (type.equals("carre")) {
                    System.out.println("ok " + "pwet");
                    DAO<Carre> d = DAOFactory.getDAOcarre();
                    Carre c = d.find(iD);
                    System.out.println("ok " + "trouvé");
                    d.delete(c);
                    System.out.println("ok " + "del");
                } else if (type.equals("cercle")) {
                    DAO<Cercle> d = DAOFactory.getDAOcercle();
                    Cercle c = d.find(iD);
                    d.delete(c);
                } else if (type.equals("rectangle")) {
                    DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                    Rectangle c = d.find(iD);
                    d.delete(c);
                } else if (type.equals("triangle")) {
                    DAO<Triangle> d = DAOFactory.getDAOtriangle();
                    Triangle c = d.find(iD);
                    d.delete(c);
                } else if (type.equals("groupe")) {
                    DAO<Groupe> d = DAOFactory.getDAOgroupe();
                    Groupe c = d.find(iD);
                    d.delete(c);
                } else {
                    System.out.println("errr");
                }
            } else {
                System.out.println("errr2");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExistePasException e) {
            e.printStackTrace();
        }
    }

}
