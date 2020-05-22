package commande;

import dao.DAO;
import dao.DAOFactory;
import forme.Groupe;
import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ListeForme;

/**
 * class commande groupe.
 * @author Tanguy
 *
 */
public class CommandeGroupe  implements Commande {
    /**
     * groupe à créer.
     */
    private Groupe obj;
    /**
     * liste de forme.
     */
    private ListeForme lObj;
    /**
     * constructeur de la commande pour crée un groupe.
     * @param g groupe a créer.
     * @param l liste de forme
     */
    public CommandeGroupe(final Groupe g, final ListeForme l) {
        obj = g;
        lObj = l;
    }
    /**
     * methode qui crée un groupe.
     */
    public void execute() {
        DAO<Groupe> d = DAOFactory.getDAOgroupe();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {

            System.out.println(e.getMessage());
        }
    }

}
