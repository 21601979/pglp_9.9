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

public class CommandeSave implements Commande {

    String objLoad;
    ListeForme l;
    public CommandeSave(final String temp, final ListeForme lis) {
        System.out.println("-------"+ temp);
        objLoad = temp;
        l = lis;
    }
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
                System.out.println("le nom "+ objLoad +" est déja utilisé");
            }
            else {
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
