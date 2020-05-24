package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import forme.Point;
import forme.Rectangle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ExistePasException;

/**
 * DAO pour rectangle.
 * @author Tanguy
 *
 */
public class DAOrectangle extends DAO<Rectangle> {

    @Override
    /**
     *  methode qui crée un triangle.
     */
    public void create(final Rectangle obj) throws ExisteDejaException {
        Connection conect = null;
        String addCreate = "INSERT INTO Rectangle"
                + "(x,y,longueur,hauteur,ID)"
                + "VALUES(?,?,?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'rectangle')";
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
            final int cinq = 5;
            PreparedStatement prep = conect.prepareStatement(addCreate);
            PreparedStatement prepName = conect.prepareStatement(addName);
            prepName.setString(1, obj.getID());
            prepName.executeUpdate();
            prep.setInt(1, obj.getp().getX());
            prep.setInt(2, obj.getp().getY());
            prep.setInt(trois, obj.getSizeX());
            prep.setInt(quatre, obj.getSizeY());
            prep.setString(cinq, obj.getID());
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
     * methode qui trouve un triangle.
     */
    @Override
    public Rectangle find(final String iD) {
        Connection conect = null;
        String searchRectangle = "SELECT * FROM Rectangle WHERE ID = ?";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchRectangle);
        prepsearch.setString(1, iD);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Rectangle res = new Rectangle(new Point(result.getInt("x"),
                    result.getInt("y")), result.getInt("longueur"),
                    result.getInt("hauteur"), result.getString("ID"));
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
     * methode qui supprime un triangle.
     */
    @Override
    public void delete(final Rectangle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String del = "DELETE FROM Rectangle WHERE ID = ?";
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
                prepName.executeUpdate();
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
        } else {
            throw new ExistePasException();
        }
    }

    /**
     * methode qui update un rectangle.
     */
    @Override
    public Rectangle update(final Rectangle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String updateCercle = "UPDATE Rectangle "
                    + "SET x = ?, y = ?, longueur = ?, hauteur = ? "
                    + "WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(updateCercle);
                final int trois = 3;
                final int quatre = 4;
                final int cinq = 5;
                prep.setInt(1, obj.getp().getX());
                prep.setInt(2, obj.getp().getY());
                prep.setInt(trois, obj.getSizeX());
                prep.setInt(quatre, obj.getSizeY());
                prep.setString(cinq, obj.getID());
                prep.executeUpdate();
                return obj;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ExistePasException();
        }
        return null;
    }

}
