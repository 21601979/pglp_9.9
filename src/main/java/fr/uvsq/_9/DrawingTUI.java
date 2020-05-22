package fr.uvsq._9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrawingTUI {
    
    public Commande nextCommand(String ut, ListeForme l) {
        String comm = ut.replace(" ","");
        try {
            if(comm.indexOf("=") != -1) {//création d'une forme
                int eql = comm.indexOf("=");
                if(eql >= 30) {
                    System.out.println("le nom est trop long");
                    throw new CommandeException();
                } else {
                    String nom = comm.split("=")[0];
                    String temp = comm.split("=")[1];
                    String type = temp.split("\\(")[0];
                    temp = temp.replace(type,"");
                    temp = temp.replace("(", "");
                    temp = temp.replace(")", "");
                    if (type.toUpperCase().equals("CARRE")) {
                        return carre(temp,nom,l);
                    } else if (type.toUpperCase().equals("CERCLE")) {
                        return cercle(temp,nom,l);
                    } else if(type.toUpperCase().equals("RECTANGLE")) {
                        return rectangle(temp,nom,l);
                    } else if(type.toUpperCase().equals("TRIANGLE")) {
                        return triangle(temp,nom,l);
                    } else if(type.toUpperCase().equals("GROUPE")) {
                        return groupe(temp,nom,l);
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
            if(function.toUpperCase().equals("MOVE")) {
                return moove(temp, l);
            } else if(function.toUpperCase().equals("DELETE")) {
                return delete(temp, l);
            } else {
                System.out.println(function + "n'est pas une fonction valide");
                throw new CommandeException();
            }
        } catch(Exception e) { }
        
        return null;
    }
    
    private Commande delete(String temp, ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 1) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        return null;
    }

    private int estInt(String test) throws CommandeException {
        try {
            int val = Integer.parseInt(test);
            return val;
            } catch(Exception e) {
                System.out.println(test + " n'est pas un entier ");
                throw new CommandeException();
            }
    }
    
    private Commande moove(String temp, ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 3) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[1]);
        int y = estInt(res[2]);
        return new CommandeMove(res[0], new Point(x,y));
    }

    private Commande groupe(String temp, String nom, ListeForme l) throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length < 1) {
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
            for(i=0;i<res.length;i++) {
                prepType.setString(1, res[i]);
                ResultSet result = prepType.executeQuery();
                if(result.next()) {
                    String type = result.getString("type");
                    if(type.equals("carre")) {
                        DAO<Carre> d = DAOFactory.getDAOcarre();
                        g.add((Forme) d.find(res[i]));
                    }
                    if(type.equals("cercle")) {
                        DAO<Cercle> d = DAOFactory.getDAOcercle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if(type.equals("rectangle")) {
                        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if(type.equals("triangle")) {
                        DAO<Triangle> d = DAOFactory.getDAOtriangle();
                        g.add((Forme) d.find(res[i]));
                    }
                    if(type.equals("groupe")) {
                        DAO<Groupe> d = DAOFactory.getDAOgroupe();
                        g.add((Forme) d.find(res[i]));
                    }
                }
            }
            //System.out.println(g.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Commande c = new CommandeGroupe(g, l);
        return c;
        
    }

    private Commande triangle(String temp, String nom, ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 6) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x1 = estInt(res[0]);
        int y1 = estInt(res[1]);
        int x2 = estInt(res[2]);
        int y2 = estInt(res[3]);
        int x3 = estInt(res[4]);
        int y3 = estInt(res[5]);
        Triangle t = new Triangle(new Point(x1,y1),new Point(x2,y2),new Point(x3,y3),nom);
        return (Commande) new CommandeTriangle(t,l);
    }

    private Commande rectangle(final String temp, String nom, ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 4) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int lon = estInt(res[2]);
        int lar = estInt(res[3]);
        Rectangle r = new Rectangle(new Point(x,y),lon, lar,nom);
        return new CommandeRectangle(r, l);
    }

    private Commande cercle(final String temp, String nom, ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 3) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int rayon = estInt(res[2]);
        Cercle c = new Cercle(new Point(x,y),rayon, nom);
        return new CommandeCercle(c,l);
    }
    
    private Commande carre(final String temp, String nom, ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if(res.length != 3) {
            System.out.println("le nombre d'argument n'est pas le bon");
            throw new CommandeException();
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int size = estInt(res[2]);
        Carre c = new Carre(new Point(x,y),size,nom);
        return new CommandeCarre(c,l);
    }
}