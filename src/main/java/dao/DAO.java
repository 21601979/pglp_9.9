package dao;

import fr.uvsq._9.ExisteDejaException;
import fr.uvsq._9.ExistePasException;

/**
 * factory de DAO.
 * @author Tanguy
 * @param <T> objet
 */
public abstract class DAO<T> {
    /**
     * methode pour sauvegarder un objet.
     * @param obj objet a sauvegarder
     * @throws ExisteDejaException exception qui est
     * renvoyé si l'objet est déja sauvegardé.
     */
    public abstract void create(T obj) throws ExisteDejaException;
    /**
     * methode qui retrouve un objet sauvegardé.
     * @param iD id de l'objet a retrouver
     * @return l'objet correspondant a l'id ou null si il n'existe pas
     */
    public abstract T find(String iD);
    /**
     * methode qui supprime un objet.
     * @param obj objet a suprimé
     * @throws ExistePasException exeption renvoyée si on
     * cherche a supprimer un objet qui n'existe pas
     */
    public abstract void delete(T obj) throws ExistePasException;
    /**
     * methode qui update un objet.
     * @param obj a suprimer
     * @return retour de l'objet update
     * @throws ExistePasException exeption renvoyée si on
     * cherche a supprimer un objet qui n'existe pas
     */
    public abstract T update(T obj) throws ExistePasException;
}
