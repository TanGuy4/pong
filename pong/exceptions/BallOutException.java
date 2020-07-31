package pong.exceptions;

/**
 * Exception déckanché lorsque la balle est en dehors de la zone de jeu.
 * @author TanGuy
 */
public class BallOutException extends Exception 
{
    /**
     * Booléen définissant le côté de sortie de la balle <br/>
     * true = la balle est sortie à droite <br/>
     * false = la balle est sortie à gauche
     */
    private boolean right;
    
    /**
     * Constructeur <br/>
     * @param right Booléen définissant le côté de sortie de la balle <br/>
     * true = la balle est sortie à droite <br/>
     * false = la balle est sortie à gauche
     */
    public BallOutException(boolean right)
    {
        this.right = right;
    }
    
    /**
     * Retourne vrai si la balle est sortie du côté droite, faux si elle est sortie du coté gauche.
     * @reutrn #right
     */
    public final boolean isRight()
    {
        return right;
    }
}
