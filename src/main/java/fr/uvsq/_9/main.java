package fr.uvsq._9;

public class main {

    public static void main(String[] args) {
        
        BDD.delBDD();
        BDD.initBDD();
        DrawingTUI t = new DrawingTUI();
        ListeForme l = new ListeForme();
        t.nextCommand("c1 = carre(1,(1,1))",l).execute();
        t.nextCommand("t1 = triangle((1,1),(1,1),(1,1))", l).execute();
        t.nextCommand("r1 = rectangle((1,1),14,2)", l).execute();
        t.nextCommand("ce1 = cercle((1,1),10)", l).execute();

        t.nextCommand("g1 = groupe(c1,t1,r1,ce1)",l).execute();
        //t.nextCommand("22 = groupe(c1)",l).execute();
        //t.nextCommand("move(c1,(10,10))", l).execute();
        //t.nextCommand("move(g1,(10,10))", l).execute();
        t.nextCommand("delete(c1)", l).execute();
        t.nextCommand("delete(t1)", l).execute();
        t.nextCommand("delete(r1)", l).execute();
        t.nextCommand("delete(ce1)", l).execute();
       /* Groupe g = new Groupe("22");
        DAO<Groupe> d = DAOFactory.getDAOgroupe();
        DAO<Carre> dc = DAOFactory.getDAOcarre();
        Carre c = new Carre(new Point(1,1),1, "carr");
        g.add(c);
        try {
            dc.create(c);
            d.create(g);
            d.delete(g);
        } catch (ExisteDejaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExistePasException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

}
