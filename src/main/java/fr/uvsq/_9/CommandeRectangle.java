package fr.uvsq._9;

public class CommandeRectangle implements Commande{
    Rectangle obj;
    ListeForme lObj;
    public CommandeRectangle(Rectangle r, ListeForme l) {
        obj = r;
        lObj = l;
    }
    public void execute() {
        DAO<Rectangle> d = DAOFactory.getDAOrectangle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {
            
            System.out.println(e.getMessage());
        }
    }
}
