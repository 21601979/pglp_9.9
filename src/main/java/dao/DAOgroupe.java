package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.Groupe;
import forme.Rectangle;
import forme.Triangle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ExistePasException;
/**
 * DAO pour les groupe de forme.
 * @author Tanguy
 *
 */
public class DAOgroupe extends DAO<Groupe> {
    /**
     * methode qui crée un groupe dans la base de données.
     */
    @Override
    public void create(final Groupe obj) throws ExisteDejaException {
        Connection conect = null;
        String addGroupe = "INSERT INTO Groupe"
                + "(IDgroupe,IDforme,type)"
                + "VALUES(?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'groupe')";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            PreparedStatement prepsearch =
                    conect.prepareStatement(searchID);
            prepsearch.setString(1, obj.getID());
            ResultSet result = prepsearch.executeQuery();
            if (result.next()) {
                throw new ExisteDejaException();
            }

            PreparedStatement prepcreate =
                    conect.prepareStatement(addGroupe);
            Iterator<Forme> it = obj.getIterator();
            prepcreate.setString(1, obj.getID());
            while (it.hasNext()) {
                Forme temp = (Forme) it.next();
                prepcreate.setString(2, temp.getID());
                final int trois = 3;
                prepcreate.setString(trois, temp.getClass().getSimpleName());
                prepcreate.executeUpdate();
            }
            PreparedStatement prepName =
                    conect.prepareStatement(addName);
            prepName.setString(1, obj.getID());
            prepName.executeUpdate();

            prepsearch.close();
            prepcreate.close();
            prepName.close();
            conect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
    /**
     * methode qui ajoute un forme du groupe dans la BD.
     * @param result resultat de la requette
     * @return retourne la forme ajoutée
     */
    private Forme addForme(final ResultSet result) {
        try {
            DAO dao = null;
            if (result.getString("type").equals("Triangle")) {
                dao = new DAOtriangle();
            } else if (result.getString("type").equals("Carre")) {
                dao = new DAOcarre();
            } else if (result.getString("type").equals("Rectangle")) {
                dao = new DAOrectangle();
            } else if (result.getString("type").equals("Cercle")) {
                dao = new DAOcercle();
            } else if (result.getString("type").equals("Groupe")) {
                dao = new DAOgroupe();
            }
            return (Forme) dao.find(result.getString("IDforme"));
        } catch (SQLException e) { }
        return null;
    }

    /**
     * methode qui trouve un groupe dans la BD.
     */
    @Override
    public Groupe find(final String iD) {
        Connection conect = null;
        String searchGroupe = "SELECT * FROM Groupe WHERE IDgroupe = ?";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchGroupe);
        prepsearch.setString(1, iD);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Groupe res = new Groupe(iD);
            conect.commit();
            res.add(addForme(result));
            while (result.next()) {
                res.add(addForme(result));
            }
            return res;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conect != null) {
                try {
                    conect.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }
    /**
     * methode qui suprime un groupe.
     */
    @Override
    public void delete(final Groupe obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String del = "DELETE FROM Groupe WHERE IDgroupe = ?";
            String delName = "DELETE FROM Name WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(del);
                PreparedStatement prepName = conect.prepareStatement(delName);
                prep.setString(1, obj.getID());
                prepName.setString(1, obj.getID());
                prep.executeUpdate();
                conect.commit();
                prepName.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conect != null) {
                    try {
                        conect.close();
                    } catch (SQLException e) { }
                }
            }
        } else {
            throw new ExistePasException();
        }
    }
    /**
     * methode qui update un groupe.
     */
    @Override
    public Groupe update(final Groupe obj) throws ExistePasException {
        Groupe g = find(obj.getID());
        if (g != null) {
            Iterator<Forme> it = obj.getIterator();
            Forme temp;
            while (it.hasNext()) {
                temp = it.next();
                if (temp instanceof Carre) {
                    DAO<Carre> d = DAOFactory.getDAOcarre();
                    d.update((Carre) temp);
                } else if (temp instanceof Cercle) {
                    DAO<Cercle> d = DAOFactory.getDAOcercle();
                    d.update((Cercle) temp);
                } else if (temp instanceof Rectangle) {
                    DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                    d.update((Rectangle) temp);
                } else if (temp instanceof Triangle) {
                    DAO<Triangle> d = DAOFactory.getDAOtriangle();
                    d.update((Triangle) temp);
                } else {
                    DAO<Groupe> d = DAOFactory.getDAOgroupe();
                    d.update((Groupe) temp);
                }
            }
        } else {
            throw new ExistePasException();
        }
        return obj;
    }

}
