package fr.uvsq._9;
/**
 * exception n'existe pas.
 * @author Tanguy
 */
public class ExistePasException extends Exception {
    /**
     * id de l'exception.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructeur de l'exception qui previen du fait q'un objet n'existe pas.
     */
    public ExistePasException() {
        super("l'objet n'existe pas");
    }
}
