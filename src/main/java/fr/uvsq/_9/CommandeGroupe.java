package fr.uvsq._9;

public class CommandeGroupe  implements Commande {
    Groupe obj;
    ListeForme lObj;
    public CommandeGroupe(Groupe g, ListeForme l) {
        obj = g;
        lObj = l;
    }
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
