package commande;
/**
 * exception qui signale un problème dans une commande.
 * @author Tanguy
 *
 */
public class CommandeException extends Exception {

    /**
     * identifiant de l'exeption.
     */
    private static final long serialVersionUID = 1L;
    /**
     * constructeur de l'exception CommandeException.
     * @param arg erreur a afficher
     */
    public CommandeException(final String arg) {
        super(arg);
    }
}
