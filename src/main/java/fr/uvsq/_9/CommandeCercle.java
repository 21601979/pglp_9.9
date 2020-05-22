package fr.uvsq._9;

public class CommandeCercle implements Commande{
    Cercle obj;
    ListeForme lObj;
    public CommandeCercle(Cercle c, ListeForme l) {
        obj = c;
        lObj = l;
    }
    public void execute() {
        DAO<Cercle> d = DAOFactory.getDAOcercle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {
            
            System.out.println(e.getMessage());
        }
    }
}
