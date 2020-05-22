package fr.uvsq._9;

public class CommandeCarre implements Commande {

    Carre obj;
    ListeForme lObj;
    public CommandeCarre(Carre c, ListeForme l) {
        obj = c;
        lObj = l;
    }
    public void execute() {
        DAO<Carre> d = DAOFactory.getDAOcarre();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {
            
            System.out.println(e.getMessage());
        }
    }

}
