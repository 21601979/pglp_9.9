package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import forme.Point;
import forme.Triangle;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ExistePasException;
/**
 * DAO d'un triangle.
 * @author Tanguy
 */
public class DAOtriangle extends DAO<Triangle> {

    /**
     * methode qui crée un triangle.
     */
    @Override
    public void create(final Triangle obj) throws ExisteDejaException {
        Connection conect = null;
        String addCreate = "INSERT INTO Triangle"
                + "(x1,y1,x2,y2,x3,y3,ID)"
                + "VALUES(?,?,?,?,?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'triangle')";
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
            final int six = 6;
            final int sept = 7;
            PreparedStatement prep = conect.prepareStatement(addCreate);
            PreparedStatement prepName = conect.prepareStatement(addName);
            prepName.setString(1, obj.getID());
            prepName.executeUpdate();
            prep.setInt(1, obj.getp1().getX());
            prep.setInt(2, obj.getp1().getY());
            prep.setInt(trois, obj.getp2().getX());
            prep.setInt(quatre, obj.getp2().getY());
            prep.setInt(cinq, obj.getp3().getX());
            prep.setInt(six, obj.getp3().getY());
            prep.setString(sept, obj.getID());
            prep.executeUpdate();
            System.out.println("le triangle est enregistré avec l'ID "
            + obj.getID());
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
    public Triangle find(final String iD) {
        Connection conect = null;
        String searchtriangle = "SELECT * FROM Triangle WHERE ID = iD";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchtriangle);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Triangle res = new Triangle(
                    new Point(result.getInt("x1"), result.getInt("y1")),
                    new Point(result.getInt("x2"), result.getInt("y2")),
                    new Point(result.getInt("x3"), result.getInt("y3")),
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
     * methode qui supprime un triangle.
     */
    @Override
    public void delete(final Triangle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String del = "DELETE FROM Triangle WHERE ID = ?";
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
     * methode qui update un triangle.
     */
    @Override
    public Triangle update(final Triangle obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String updateCercle = "UPDATE Triangle "
                    + "SET x1 = ?, y1 = ?, x2 = ?, y2 = ?, x3 = ?, y3 = ? "
                    + "WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(updateCercle);
                final int trois = 3;
                final int quatre = 4;
                final int cinq = 5;
                final int six = 6;
                final int sept = 7;
                prep.setInt(1, obj.getp1().getX());
                prep.setInt(2, obj.getp1().getY());
                prep.setInt(trois, obj.getp2().getX());
                prep.setInt(quatre, obj.getp2().getY());
                prep.setInt(cinq, obj.getp3().getX());
                prep.setInt(six, obj.getp3().getY());
                prep.setString(sept, obj.getID());
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
