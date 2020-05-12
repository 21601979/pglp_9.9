package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * class qui gère la création et la supression de la BD.
 * @author Tanguy
 *
 */
public abstract class BDD {
    /**
     * methode qui initialise la BDD.
     */
    public static void initBDD() {
        Connection conect = null;
        try {
            conect = DriverManager.getConnection("jdbc:derby:BDD;create=true");
            String cercleTable = "CREATE TABLE Cercle("
            + "x int,"
            + "y int,"
            + "rayon int,"
            + "ID varchar(30),"
            + "PRIMARY KEY(ID)"
            + ")";
            String carreTable = "CREATE TABLE Carre("
            + "x int,"
            + "y int,"
            + "taille int,"
            + "ID varchar(30),"
            + "PRIMARY KEY(ID)"
            + ")";
            String carreRectangle = "CREATE TABLE Rectangle("
            + "x int,"
            + "y int,"
            + "longueur int,"
            + "hauteur int,"
            + "ID varchar(30),"
            + "PRIMARY KEY(ID)"
            + ")";
            String carreTriangle = "CREATE TABLE Triangle("
            + "x1 int,"
            + "y1 int,"
            + "x2 int,"
            + "y2 int,"
            + "x3 int,"
            + "y3 int,"
            + "ID varchar(30),"
            + "PRIMARY KEY(ID)"
            + ")";
            String Name = "CREATE TABLE Name("
                    + "ID varchar(30),"
                    + "PRIMARY KEY(ID)"
                    + ")";
            String Groupe = "CREATE TABLE Groupe("
                    + "IDgroupe varchar(30),"
                    + "IDforme varchar(30),"
                    + "type varchar(30),"
                    + "PRIMARY KEY(IDgroupe,IDforme),"
                    + "FOREIGN KEY(IDforme) REFERENCES Name(ID)"
                    + ")";
            Statement stmt = conect.createStatement();

            try {
                stmt.execute(cercleTable);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(carreTable);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(carreRectangle);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(carreTriangle);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Name);
            } catch (SQLException e) {
                e.printStackTrace();
                }
            try {
                stmt.execute(Groupe);
            } catch (SQLException e) {
                e.printStackTrace();
                }

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
                stmt.execute("DROP TABLE Groupe");
                stmt.execute("DROP TABLE Name");

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
