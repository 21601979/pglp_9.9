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
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande groupe.
 * @author Tanguy
 *
 */
public class CommandeGroupe  implements Commande {
    /**
     * groupe à créer.
     */
    private Groupe obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande pour crée un groupe.
     * @param nom nom du groupe
     * @param res string contenant les élléments du groupe
     * @param l liste de forme
     */
    public CommandeGroupe(final String nom, final String[] res,
            final ListeForme l) {
        int i;
        lObj = l;
        obj = new Groupe(nom);
        try {
            Connection conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            String getType = "SELECT type FROM Name WHERE ID = ?";
            PreparedStatement prepType = conect.prepareStatement(getType);
            for (i = 0; i < res.length; i++) {
                prepType.setString(1, res[i]);
                ResultSet result = prepType.executeQuery();
                if (result.next()) {
                    String type = result.getString("type");
                    if (type.equals("carre")) {
                        DAO<Carre> d = DAOFactory.getDAOcarre();
                        obj.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("cercle")) {
                        DAO<Cercle> d = DAOFactory.getDAOcercle();
                        obj.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("rectangle")) {
                        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                        obj.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("triangle")) {
                        DAO<Triangle> d = DAOFactory.getDAOtriangle();
                        obj.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("groupe")) {
                        DAO<Groupe> d = DAOFactory.getDAOgroupe();
                        obj.add((Forme) d.find(res[i]));
                    }
                }
            }
            if (conect != null) {
                conect.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * methode qui crée un groupe.
     */
    public void execute() {
        DAO<Groupe> d = DAOFactory.getDAOgroupe();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }

}
