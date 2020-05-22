package fr.uvsq._9;

public class CommandeTriangle {
    Triangle obj;
    ListeForme lObj;
    public CommandeTriangle(Triangle t, ListeForme l) {
        obj = t;
        lObj = l;
    }
    public void execute() {
        DAO<Triangle> d = DAOFactory.getDAOtriangle();
        try {
            d.create(obj);
            lObj.add(obj);
        } catch (ExisteDejaException e) {
            
            System.out.println(e.getMessage());
        }
    }
}
