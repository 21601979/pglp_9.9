package fr.uvsq._9;

public class ExistePasException extends Exception {
    /**
     * id de l'exception
     */
    private static final long serialVersionUID = 1L;

    public ExistePasException() {
        super("l'objet n'existe pas");
    }
}
