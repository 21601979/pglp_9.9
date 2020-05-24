package fr.uvsq._9;

import commande.Commande;
import commande.CommandeCarre;
import commande.CommandeCercle;
import commande.CommandeDelete;
import commande.CommandeDraw;
import commande.CommandeException;
import commande.CommandeExit;
import commande.CommandeGroupe;
import commande.CommandeLoad;
import commande.CommandeMove;
import commande.CommandeRectangle;
import commande.CommandeSave;
import commande.CommandeTriangle;
import forme.Point;
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
     * @throws CommandeException envoyé si il y a une érreur dans la commande
     */
    public Commande nextCommand(final String ut, final ListeForme l)
            throws CommandeException {
        String comm = ut.replace(" ", "");
        if (comm.indexOf("=") != -1) { //création d'une forme
            int eql = comm.indexOf("=");
            final int trente = 30;
            if (eql >= trente) {
                throw new CommandeException("le nom est trop long");
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
                    throw new CommandeException(type + " n'est pas"
                            + " une forme valide");
                }
            }
        }
        String function = comm.split("\\(")[0];
        String temp = comm.replace(function, "");
        temp = temp.replace("(", "");
        temp = temp.replace(")", "");
        if (function.toUpperCase().equals("MOVE")) {
            return moove(temp, l);
        } else if (function.toUpperCase().equals("DELETE")) {
            return delete(temp, l);
        } else if (function.toUpperCase().equals("DRAW")) {
            return new CommandeDraw(l);
        } else if (function.toUpperCase().equals("EXIT")) {
            return new CommandeExit(l);
        } else if (function.toUpperCase().equals("LOAD")) {
            return load(temp, l);
        } else if (function.toUpperCase().equals("SAVE")) {
            return save(temp, l);
        } else {
            throw new CommandeException(function + "n'est pas"
                    + " une fonction valide");
        }
    }
    /**
     * methode qui renvoi une commande save.
     * @param temp nom du groupe de sauvegarde
     * @param l liste de forme
     * @return commande de sauvegarde
     * @throws CommandeException est renvoyé si les arguments ne sont pas bon
     */
    private Commande save(final String temp, final ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if (res[0].equals("")) {
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        return new CommandeSave(temp, l);
    }
    /**
     * methode qui renvoi la commande load.
     * @param temp nom de l'objet
     * @param l liste de forme
     * @return commande load
     * @throws CommandeException est renvoyé si les arguments ne sont pas bon
     */
    private Commande load(final String temp, final ListeForme l)
            throws CommandeException {
        String[] res;
        res = temp.split(",");
        if (res.length != 1) {
            throw new CommandeException("le nombre d'argument "
                    + "n'est pas le bon");
        }
        return new CommandeLoad(temp, l);
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
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        return new CommandeDelete(res[0], l);
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
                throw new CommandeException(test + " n'est pas un entier ");
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
            throw new CommandeException("le nombre"
                    + " d'argument n'est pas le bon");
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
        if (res[0].equals("")) {
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        return new CommandeGroupe(nom, res, l);

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
            throw new CommandeException("le nombre"
                    + " d'argument n'est pas le bon");
        }
        int x1 = estInt(res[0]);
        int y1 = estInt(res[1]);
        int x2 = estInt(res[2]);
        int y2 = estInt(res[trois]);
        int x3 = estInt(res[quatre]);
        int y3 = estInt(res[cinq]);
        return new CommandeTriangle(new Point(x1, y1), new Point(x2, y2),
                new Point(x3, y3), nom, l);
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
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        final int trois = 3;
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int lon = estInt(res[2]);
        int lar = estInt(res[trois]);
        return new CommandeRectangle(x, y, lon, lar, nom, l);
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
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int rayon = estInt(res[2]);
        return new CommandeCercle(x, y, rayon, nom, l);
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
            System.out.println();
            throw new CommandeException("le nombre d'argument"
                    + " n'est pas le bon");
        }
        int x = estInt(res[0]);
        int y = estInt(res[1]);
        int size = estInt(res[2]);
        return new CommandeCarre(x, y, size, nom, l);
    }
}
