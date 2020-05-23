package commande;

import fr.uvsq._9.ListeForme;

public class CommandeDraw implements Commande {
    private ListeForme l;
    public CommandeDraw(ListeForme lis) {
        l = lis;
    }
    public void execute() {
        System.out.println(l.toString());
    }

}
