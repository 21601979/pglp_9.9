package fr.uvsq._9;

import java.util.Scanner;

import commande.CommandeException;
/**
 * class drawingApp.
 * @author Tanguy
 *
 */
public class DrawingAPP {
    /**
     * methode run.
     */
    public void run() {
        ListeForme l = new ListeForme();
        Scanner scan = new Scanner(System.in);
        DrawingTUI ui = new DrawingTUI();
        String cmd = "";
        while (l.getExit() == 0) {
            cmd = scan.nextLine();
            try {
                ui.nextCommand(cmd, l).execute();
                ui.nextCommand("draw", l).execute();
            } catch (CommandeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
