package commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import dao.DAO;
import dao.DAOFactory;
import forme.Forme;
import forme.Groupe;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;
/**
 * class commande save.
 * @author Tanguy
 *
 */
public class CommandeSave implements Commande {
    /**
     * nom du groupe contenant la sauvegarde.
     */
    private String objLoad;
    /**
     * liste de forme.
     */
    private ListeForme l;
    /**
     * constructeur de la commande save.
     * @param temp nom du groupe contenant la sauvegarde
     * @param lis liste de forme
     */
    public CommandeSave(final String temp, final ListeForme lis) {
        objLoad = temp;
        l = lis;
    }
    /**
     * methode qui sauvegarde un dessin.
     */
    public void execute() {
        Connection conect;
        try {
            conect = DriverManager.getConnection("jdbc:"
                    + "derby:BDD;create=true");
            String getType = "SELECT type FROM Name WHERE ID = ?";
            PreparedStatement prepType = conect.prepareStatement(getType);
            prepType.setString(1, objLoad);
            ResultSet result = prepType.executeQuery();
            if (result.next()) {
                System.out.println("le nom " + objLoad + " est déja utilisé");
            } else {
                Groupe g = new Groupe(objLoad);
                Iterator<Forme> it = l.getIterator();
                while (it.hasNext()) {
                    g.add(it.next());
                }
                DAO<Groupe> d = DAOFactory.getDAOgroupe();
                try {
                    d.create(g);
                    System.out.println("le dessin a été sauvegardé");
                } catch (ExisteDejaException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
