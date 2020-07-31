package pong.exceptions;

/**
 * Exception déclanchée lorsque la balle et l'une des barres rentre en contact
 * @author TanGuy
 */
public class Toucher extends Exception
{
    /**
     * Partie de la barre avec laquelle la balle est rentrée en contact
     */
    private int part;

    /**
     * Partie Haute de la barre
     */    
    public static final int UP=1;

    /**
     * Partie centrale de la barre
     */
    public static final int MIDDLE=2;

    /**
     * Partie basse de la barre
     */
    public static final int DOWN=3;
    
    /**
     * Constructeur
     * @param part Partie de la barre avec laquelle la balle est rentrée en contact
     */
    public Toucher(int part)
    {
        this.part = part;
    }
    
    /**
     * Retourne la partie de la barre qui est rentrée en contact avec la balle
     * @return #part
     */
    public final int getPart()
    {
        return part;
    }

}
