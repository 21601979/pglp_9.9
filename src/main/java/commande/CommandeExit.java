package commande;

import fr.uvsq._9.ListeForme;

public class CommandeExit implements Commande {
    private ListeForme l;
    public CommandeExit(ListeForme lis) {
        l = lis;
    }
    public void execute() {
        l.exit();
    }

}
