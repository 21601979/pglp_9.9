package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * DAO qui gère les opération CRUD pour un carrée dans la BDD.
 * @author Tanguy
 */
public class DAOcarre extends DAO<Carre> {
    /**
     * methode qui cree un carrée dans la BDD.
     */
    @Override
    public void create(final Carre obj) throws ExisteDejaException {
        Connection conect = null;
        String addCreate = "INSERT INTO Carre"
                + "(x,y,taille,ID)"
                + "VALUES(?,?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'carre')";
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
            prep.setInt(1, obj.getP().getX());
            prep.setInt(2, obj.getP().getY());
            prep.setInt(trois, obj.getSize());
            prep.setString(quatre, obj.getID());
            prep.executeUpdate();
            System.out.println("le carré est enregistré avec l'ID " + obj.getID());
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
     * methode qui permet de trouver un carré dans la BDD.
     */
    @Override
    public Carre find(final String iD) {
        Connection conect = null;
        String searchCarre = "SELECT * FROM Carre WHERE ID = iD";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchCarre);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Carre res = new Carre(new Point(result.getInt("x"),
                    result.getInt("y")), result.getInt("taille"),result.getString("ID"));
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
     * methode qui supprime un carre.
     */
    @Override
    public void delete(final Carre obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String del = "DELETE FROM Carre WHERE ID = ?";
            String delName = "DELETE FROM Name WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(del);
                PreparedStatement prepName = conect.prepareStatement(delName);
                prep.setString(1, obj.getID());
                prepName.setString(1, obj.getID());
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
     * methode qui update un carre.
     */
    @Override
    public Carre update(final Carre obj) throws ExistePasException {
        Connection conect = null;
        if (find(obj.getID() + "") != null) {
            String updateCarre = "UPDATE Carre "
                    + "SET x = ?, y = ?, taille = ? "
                    + "WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(updateCarre);
                final int trois = 3;
                final int quatre = 4;
                prep.setInt(1, obj.getP().getX());
                prep.setInt(2, obj.getP().getY());
                prep.setInt(trois, obj.getSize());
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
