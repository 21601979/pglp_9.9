package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class DAOgroupe extends DAO<Groupe>{

    @Override
    public void create(Groupe obj) throws ExisteDejaException {
        Connection conect = null;
        String addGroupe = "INSERT INTO Groupe"
                + "(IDgroupe,IDforme,type)"
                + "VALUES(?,?,?)";
        String searchID = "SELECT ID FROM Name WHERE ID = ?";
        String addName = "INSERT INTO Name(ID,type) VALUES(?,'groupe')";;
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
            while(it.hasNext()) {
                Forme temp = (Forme) it.next();
                prepcreate.setString(2, temp.getID());
                prepcreate.setString(3, temp.getClass().getSimpleName());
                prepcreate.executeUpdate();
            }
            PreparedStatement prepName = 
                    conect.prepareStatement(addName);
            prepName.setString(1, obj.getID());
            System.out.println("le groupe est enregistré avec le nom: "
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
    
    private Forme addForme(ResultSet result) {
        try {
            DAO dao = null;
            System.out.println(result.getString("type"));
            if(result.getString("type").equals("Triangle")) {
                dao = new DAOtriangle();
            }
            else if(result.getString("type").equals("Carre")) {
                dao = new DAOcarre();
            }
            else if(result.getString("type").equals("Rectangle")) {
                dao = new DAOrectangle();
            }
            else if(result.getString("type").equals("Cercle")) {
                dao = new DAOcercle();
            }
            else if(result.getString("type").equals("Groupe")) {
                dao = new DAOgroupe();
            }
            System.out.println("m " +dao);
            return (Forme) dao.find(result.getString("IDforme"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    @Override
    public Groupe find(String iD) {
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
            res.add(addForme(result));
            while(result.next()) {
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

    @Override
    public void delete(Groupe obj) throws ExistePasException {
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

    @Override
    public Groupe update(Groupe obj) throws ExistePasException {
        if (find(obj.getID() + "") != null) {
            delete(obj);
            try {
                create(obj);
            } catch (ExisteDejaException e) {
                e.printStackTrace();
            }
        } else {
            throw new ExistePasException();
        }
        return obj;
    }

}
