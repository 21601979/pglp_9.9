package fr.uvsq._9;

public class main {

    public static void main(String[] args) {
        
        BDD.delBDD();
        BDD.initBDD();
        DrawingTUI t = new DrawingTUI();
        ListeForme l = new ListeForme();
        t.nextCommand("c1 = carre(1,(1,1))",l).execute();
        t.nextCommand("g1 = groupe(c1)",l).execute();
        t.nextCommand("move(c1,(10,10))", l).execute();
    }

}
