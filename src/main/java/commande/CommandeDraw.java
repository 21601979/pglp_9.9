package commande;

import fr.uvsq._9.ListeForme;
/**
 * class commande draw.
 * @author Tanguy
 *
 */
public class CommandeDraw implements Commande {
    /**
     * liste de forme.
     */
    private ListeForme l;
    /**
     * constructeur de la commande draw.
     * @param lis liste de forme
     */
    public CommandeDraw(final ListeForme lis) {
        l = lis;
    }
    /**
     * methode qui affiche le dessin.
     */
    public void execute() {
        System.out.println(l.toString());
    }

}
