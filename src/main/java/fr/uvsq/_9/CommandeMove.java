package fr.uvsq._9;


public class CommandeMove implements Commande{
    String iD;
    Point p;
    public CommandeMove(String name, Point depl) { 
        iD = name;
        p = depl;
    }
    public void execute() {
        
    }

}
