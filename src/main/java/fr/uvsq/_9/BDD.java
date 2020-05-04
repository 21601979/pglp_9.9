package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {
    public static void initBDD() {
        Connection conect = null;
        try {
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            String CercleTable = "CREATE TABLE Cercle("
            + "x int,"
            + "y int,"
            + "rayon int,"
            + "ID int,"
            + "PRIMARY KEY(ID)"
            + ")";
            String CarreTable = "CREATE TABLE Carre("
            + "x int,"
            + "y int,"
            + "taille int,"
            + "ID int,"
            + "PRIMARY KEY(ID)"
            + ")";
            String CarreRectangle = "CREATE TABLE Rectangle("
            + "x int,"
            + "y int,"
            + "longueur int,"
            + "hauteur int,"
            + "ID int,"
            + "PRIMARY KEY(ID)"
            + ")";
            String CarreTriangle = "CREATE TABLE Triangle("
            + "x1 int,"
            + "y1 int,"
            + "x2 int,"
            + "y2 int,"
            + "x3 int,"
            + "y3 int,"
            + "ID int,"
            + "PRIMARY KEY(ID)"
            + ")";
            Statement stmt = conect.createStatement();

            try {
                stmt.execute(CercleTable);
            } catch(SQLException e) { e.printStackTrace(); }
            try {
                stmt.execute(CarreTable);
            } catch(SQLException e) { e.printStackTrace(); }
            try {
                stmt.execute(CarreRectangle);
            } catch(SQLException e) { e.printStackTrace(); }
            try {
                stmt.execute(CarreTriangle);
            } catch(SQLException e) { e.printStackTrace(); }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * methode qui supprime la base de données.
     */
    public static void delBDD() {
        Connection conect = null;
        try {
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            try {
                Statement stmt = conect.createStatement();
                stmt.execute("DROP TABLE Cercle");
                stmt.execute("DROP TABLE Carre");
                stmt.execute("DROP TABLE Rectangle");
                stmt.execute("DROP TABLE Triangle");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conect != null) {
                try {
                    System.out.println("close");
                    conect.close();
                } catch (SQLException e) { }
            }
        }
    }
}
