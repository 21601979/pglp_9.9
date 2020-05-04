package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOcarre extends DAO<Carre>{

    @Override
    public void create(Carre obj) throws ExisteDejaException {
        Connection conect = null;
        int iD = 0;
        String addCreate = "INSERT INTO Carre"
                + "(x,y,taille,ID)"
                + "VALUES(?,?,?,?)";
        String searchMAXID = "SELECT MAX(ID) FROM Carre";
        String searchID = "SELECT ID FROM Carre WHERE ID = ?";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            if(obj.getID() == 0) {
                PreparedStatement prepsearch = conect.prepareStatement(searchMAXID);
                ResultSet result = prepsearch.executeQuery();
                if (result.next()) {
                    iD = result.getInt("1") + 1;
                }
            } else {
                PreparedStatement prepsearch = conect.prepareStatement(searchID);
                prepsearch.setInt(1, obj.getID());
                ResultSet result = prepsearch.executeQuery();
                if(result.next()) {
                    throw new ExisteDejaException();
                }
                iD = obj.getID();
            }
            PreparedStatement prep = conect.prepareStatement(addCreate);
            prep.setInt(1, obj.getP().getX());
            prep.setInt(2, obj.getP().getY());
            prep.setInt(3, obj.getSize());
            prep.setInt(4, iD);
            prep.executeUpdate();
            obj.SetID(iD);
            System.out.println("le carré est enregistré avec l'ID " + iD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Carre find(String iD) {
        Connection conect = null;
        String searchCarre = "SELECT * FROM Carre WHERE ID = iD";
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
        PreparedStatement prepsearch = conect.prepareStatement(searchCarre);
        ResultSet result = prepsearch.executeQuery();
        if (result.next()) {
            Carre res = new Carre(new Point(result.getInt("x"),
                    result.getInt("y")),result.getInt("taille"));
            res.SetID(result.getInt("ID"));
            return res;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Carre obj) throws ExistePasException {
        Connection conect = null;
        if(find(obj.getID()+"") != null) {
            String del = "DELETE FROM Carre WHERE ID = ?";
            try {
                conect = DriverManager.getConnection("jdbc:"
                        + "derby:BDD;create=true");
                PreparedStatement prep = conect.prepareStatement(del);
                prep.setInt(1, obj.getID());
                prep.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new ExistePasException();
        }
    }

    @Override
    public Carre update(Carre obj) throws ExistePasException {
        if (find(obj.getID()+"") != null) {
            delete(obj);
            try {
                create(obj);
            } catch (ExisteDejaException e) {
                e.printStackTrace();
            }
        } else {
            throw new ExistePasException();
        }
        return null;
    }

}
