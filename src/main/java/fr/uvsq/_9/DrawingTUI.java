package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commande.Commande;
import commande.CommandeCarre;
import commande.CommandeCercle;
import commande.CommandeDelete;
import commande.CommandeException;
import commande.CommandeGroupe;
import commande.CommandeMove;
import commande.CommandeRectangle;
import commande.CommandeTriangle;
import dao.DAO;
import dao.DAOFactory;
import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.Groupe;
import forme.Point;
import forme.Rectangle;
import forme.Triangle;
/**
 * class qui gère les entrée utilisateur.
 * @author Tanguy
 *
 */
public class DrawingTUI {
    /**
     * methode qui renvoie la commande suivante.
     * @param ut string analysé
     * @param l liste de forme
     * @return une commande
     */
    public Commande nextCommand(final String ut, final ListeForme l) {
        String comm = ut.replace(" ", "");
        try {
            if (comm.indexOf("=") != -1) { //création d'une forme
                int eql = comm.indexOf("=");
                final int trente = 30;
                if (eql >= trente) {
                    System.out.println("le nom est trop long");
                    throw new CommandeException();
                } else {
                    String nom = comm.split("=")[0];
                    String temp = comm.split("=")[1];
                    String type = temp.split("\\(")[0];
                    temp = temp.replace(type, "");
                    temp = temp.replace("(", "");
                    temp = temp.replace(")", "");
                    if (type.toUpperCase().equals("CARRE")) {
                        return carre(temp, nom, l);
                    } else if (type.toUpperCase().equals("CERCLE")) {
                        return cercle(temp, nom, l);
                    } else if (type.toUpperCase().equals("RECTANGLE")) {
                        return rectangle(temp, nom, l);
                    } else if (type.toUpperCase().equals("TRIANGLE")) {
                        return triangle(temp, nom, l);
                    } else if (type.toUpperCase().equals("GROUPE")) {
                        return groupe(temp, nom, l);
                    } else {
                        System.out.println(type + "n'est pas une forme valide");
                        throw new CommandeException();
                    }
                }
            }
            String function = comm.split("\\(")[0];
            System.out.println(function);
            String temp = comm.replace(function, "");
            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
            System.out.println(temp);
            if (function.toUpperCase().equals("MOVE")) {
                return moove(temp, l);
            } else if (function.toUpperCase().equals("DELETE")) {
                return delete(temp, l);
            } else {
                System.out.println(function + "n'est pas une fonction valide");
                throw new CommandeException();
            }
        } catch (Exception e) { }
        return null;
    }
    /**
     * methode qui renvoie une commande de supression.
     * @param temp argument de la supression
     * @param l liste de forme
     * @return commande de supression
     * @throws CommandeException est renvoyé si les arguments ne sont pas bon
     */
    private Commande delete(final String temp, final ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if (res.length != 1) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        return new CommandeDelete(res[0]);
    }
    /**
     * methode qui vérifie q'un int est contenu dans le string.
     * @param test string à tester
     * @return l'entier contenu dans le string
     * @throws CommandeException est renvoyé le string ne contient pas un entier
     */
    private int estInt(final String test) throws CommandeException {
        try {
            int val = Integer.parseInt(test);
            return val;
            } catch (Exception e) {
                System.out.println(test + " n'est pas un entier ");
                throw new CommandeException();
            }
    }
    /**
     * methode qui déplace une forme ou un groupe de forme.
     * @param temp argument du déplacement
     * @param l liste de forme
     * @return commande de déplacement
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande moove(final String temp, final ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        final int  trois = 3;
        if (res.length != trois) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[1]);
        int y = estInt(res[2]);
        return new CommandeMove(res[0], new Point(x, y), l);
    }

    /**
     * methode qui renvoi la commande de création d'un groupe.
     * @param temp paramètre du groupe
     * @param nom nom du groupe
     * @param l liste de forme
     * @return commande de création d'un groupe
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande groupe(final String temp, final String nom,
            final ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        if (res.length < 1) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int i;
        Groupe g = new Groupe(nom);
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
                        g.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("cercle")) {
                        DAO<Cercle> d = DAOFactory.getDAOcercle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("rectangle")) {
                        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("triangle")) {
                        DAO<Triangle> d = DAOFactory.getDAOtriangle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if (type.equals("groupe")) {
                        DAO<Groupe> d = DAOFactory.getDAOgroupe();
                        g.add((Forme) d.find(res[i]));
                    }
                }
            }
            conect.close();
            //System.out.println(g.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Commande c = new CommandeGroupe(g, l);
        return c;

    }
    /**
     * methode qui renvoi une commande de cr&ation d'un triangle.
     * @param temp argument du triangle
     * @param nom nom du triangle
     * @param l liste de forme
     * @return commande de création d'un triangle
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande triangle(final String temp, final String nom,
            final ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        final int trois = 3;
        final int quatre = 4;
        final int cinq = 5;
        final int six = 6;
        if (res.length != six) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x1 = estInt(res[0]);
        int y1 = estInt(res[1]);
        int x2 = estInt(res[2]);
        int y2 = estInt(res[trois]);
        int x3 = estInt(res[quatre]);
        int y3 = estInt(res[cinq]);
        Triangle t = new Triangle(new Point(x1, y1), new Point(x2, y2),
                new Point(x3, y3), nom);
        return new CommandeTriangle(t, l);
    }

    /**
     * methode qui renvoi une commande de création d'un rectangle.
     * @param temp argument du rectangle
     * @param nom nom du rectangle
     * @param l liste de forme
     * @return commande de création d'un rectangle
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande rectangle(final String temp, final String nom,
            final ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        final int quatre = 4;
        if (res.length != quatre) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        final int trois = 3;
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int lon = estInt(res[2]);
        int lar = estInt(res[trois]);
        Rectangle r = new Rectangle(new Point(x, y), lon, lar, nom);
        return new CommandeRectangle(r, l);
    }
    /**
     * methode qui renvoi une commande de création d'un cercle.
     * @param temp argument du cercle
     * @param nom nom du cercle
     * @param l liste de forme
     * @return commande de création d'un cercle.
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande cercle(final String temp, final String nom,
            final ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        final int trois = 3;
        if (res.length != trois) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int rayon = estInt(res[2]);
        Cercle c = new Cercle(new Point(x, y), rayon, nom);
        return new CommandeCercle(c, l);
    }
    /**
     * methode qui renvoie une Commande de création d'un carrée.
     * @param temp argument du carée
     * @param nom nom du carrée
     * @param l liste de forme
     * @return commande de création d'un carrée
     * @throws CommandeException est renvoyé si les argument ne sont pas bon
     */
    private Commande carre(final String temp, final String nom,
            final ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        final int trois = 3;
        if (res.length != trois) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int size = estInt(res[2]);
        Carre c = new Carre(new Point(x, y), size, nom);
        return new CommandeCarre(c, l);
    }
}
