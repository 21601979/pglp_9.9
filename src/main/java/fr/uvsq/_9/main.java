package fr.uvsq._9;

public class main {

    public static void main(String[] args) {
        
       
        BDD.delBDD();
        BDD.initBDD();
       
        Triangle c = new Triangle(new Point(1,1), new Point(10,1),new Point(1,10), "1");
        Triangle c2 = new Triangle(new Point(1,1), new Point(10,1),new Point(1,10), "2");
        DAOtriangle dao = new DAOtriangle();
        try {
            dao.create(c);
            dao.create(c2);
        } catch (ExisteDejaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Groupe g = new Groupe("g");
        DAOgroupe daoG = new DAOgroupe();
        g.add(c);
        g.add(c2);
        try {
            daoG.create(g);
        } catch (ExisteDejaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(daoG.find("g").toString());
        try {
            daoG.update(g);
        } catch (ExistePasException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
