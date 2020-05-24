package commande;

import fr.uvsq._9.ListeForme;
/**
 * class commande exit.
 * @author Tanguy
 *
 */
public class CommandeExit implements Commande {
    /**
     * liste de forme.
     */
    private ListeForme l;
    /**
     * constructeur de la commande exit.
     * @param lis liste de forme
     */
    public CommandeExit(final ListeForme lis) {
        l = lis;
    }
    /**
     * methode qui permet de quiter le programme.
     */
    public void execute() {
        l.exit();
    }

}
