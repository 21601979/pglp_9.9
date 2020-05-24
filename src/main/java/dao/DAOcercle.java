package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import forme.Cercle;
import forme.Point;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ExistePasException;

/**
 * DAO pour l'objet cercle.
 * @author Tanguy
 */
public class DAOcercle extends DAO<Cercle> {

    /**
     * methode qui crée un cercle.
     */
    @Override
    public void create(final Cercle obj) throws ExisteDejaException {
        Connection conect = null;
        String addCreate = "INSERT INTO Cercle"
                + "(x,y,rayon,ID)"
                + "VALUES(?,?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'cercle')";
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
            final int trois = 3;
            final int quatre = 4;
            PreparedStatement prep = conect.prepareStatement(addCreate);
            PreparedStatement prepName = conect.prepareStatement(addName);
            prepName.setString(1, obj.getID());
            prepName.executeUpdate();
            prep.setInt(1, obj.getCentre().getX());
            prep.setInt(2, obj.getCentre().getY());
            prep.setInt(trois, obj.getRayon());
            prep.setString(quatre, obj.getID());
            prep.executeUpdate();
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
     * methode qui trouve un cercle.
     */
    @Override
    public Cercle find(final String iD) {
        Connection conect = null;
        String searchCercle = "SELECT * FROM Cercle WHERE ID = ?";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchCercle);
        prepsearch.setString(1, iD);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Cercle res = new Cercle(new Point(result.getInt("x"),
                    result.getInt("y")), result.getInt("rayon"),
                    result.getString("ID"));
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
     * methode qui suprime un cercle.
     */
    @Override
    public void delete(final Cercle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String del = "DELETE FROM Cercle WHERE ID = ?";
            String delName = "DELETE FROM Name WHERE ID = ?";
            String delGroupe = "DELETE FROM groupe WHERE IDforme = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(del);
                PreparedStatement prepName = conect.prepareStatement(delName);
                PreparedStatement prepdelGP =
                        conect.prepareStatement(delGroupe);
                prepdelGP.setString(1, obj.getID());
                prep.setString(1, obj.getID());
                prepName.setString(1, obj.getID());
                prepdelGP.executeUpdate();
                prep.executeUpdate();
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
     * methode qui update un cercle.
     */
    @Override
    public Cercle update(final Cercle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String updateCercle = "UPDATE Cercle "
                    + "SET x = ?, y = ?, rayon = ? "
                    + "WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(updateCercle);
                final int trois = 3;
                final int quatre = 4;
                prep.setInt(1, obj.getCentre().getX());
                prep.setInt(2, obj.getCentre().getY());
                prep.setInt(trois, obj.getRayon());
                prep.setString(quatre, obj.getID());
                prep.executeUpdate();
                return obj;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            throw new ExistePasException();
        }
        return null;
    }

}
