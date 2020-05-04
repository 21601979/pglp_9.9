package fr.uvsq._9;

public class main {

    public static void main(String[] args) {
        BDD.delBDD();
        BDD.initBDD();
        
        Carre c = new Carre(new Point(1,1), 10);
        Carre c2 = new Carre(new Point(1,1), 10);
        DAOcarre dao = new DAOcarre();
        try {
            dao.create(c);
            c.deplace(new Point(4,4));
            try {
                dao.update(c);
            } catch (ExistePasException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(dao.find(1+""));
        } catch (ExisteDejaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
