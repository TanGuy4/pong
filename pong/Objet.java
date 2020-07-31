package pong;

import java.awt.Graphics;

/**
 * Classe mère pour les objets (les barres, et la balle)
 * @author TanGuy
 */
public abstract class Objet {
    
    /**
     * Position minium autorisée de l'objet en Y <br/> (ici commune à tous les objets)
     */
    public static final double YMIN=0;

    /**
     * Position maximum autorisée de l'objet en Y, dépend de la résultion. <br/> (ici commune à tous les objets)
     */
    public static final double YMAX=Pong.Y; 
    
    /**
     * Position maximum autorisée de l'objet en X, dépend de la résolution, par défaut
     */
    private double xmax = Pong.X;

    /**
     * Position minium autorisée de l'objet en X, vaut 0 par défaut
     */
    private double xmin = 0;
    
    /**
     * Position de l'objet en X, 0 par défaut
     */
    private double x=0;

    /**
     * Position de l'objet en Y, 0 par défaut
     */
    private double y=0;

    /**
     * Accélaration de l'objet en X, 0 par défaut
     */
    private double ax=0;

    /**
     * Accélaration de l'objet en Y, 0 par défaut
     */
    private double ay=0;

    /**
     * Vitesse de l'objet en X, 0 par défaut
     */
    private double vx=0;

    /**
     * Vitesse de l'objet en Y, 0 par défaut
     */
    private double vy=0;

    /**
     * Hauteur de l'objet, 20 par défaut
     */
    private double width=20;

    /**
     * Largeur de l'objet, 20 par défaut
     */
    private double height=20;

    /**
     * Retourne la largeur de l'objet
     * @reutrn #width
     */
    public final double getWidth() {
        return width;
    }

    /**
     * Défini la largeur de l'objet
     * @param width largeur de l'objet
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Retourne la hauteur de l'objet
     * @reutrn #height
     */
    public final double getHeight() {
        return height;
    }

    /**
     * Défini la hauteur de l'objet
     * @param height hauteur de l'objet
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Retourne la position en X de l'objet
     * @return #x
     */ 
    public final double getX() {
        return x;
    }

    /**
     * Défini la position en X de l'objet
     * @param x position en X de l'objet
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Retourne la position en Y de l'objet
     * @return #y
     */
    public final double getY() {
        return y;
    }

    /**
     * Défini la position en Y de l'objet
     * @param y position en Y de l'objet
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Retourne l'accélaration en X de l'objet
     * @return #ax
     */
    public final double getAx() {
        return ax;
    }

    /**
     * Défini l'accélaration en X de l'objet
     * @param ax accélaration en X de l'objet
     */
    public void setAx(double ax) {
        this.ax = ax;
    }

    /**
     * Retourne l'accélaration en Y de l'objet
     * @return #ay
     */
    public final double getAy() {
        return ay;
    }

    /**
     * Défini l'accélaration en Y de l'objet
     * @param ay accélaration en Y de l'objet
     */
    public void setAy(double ay) {
        this.ay = ay;
    }

    /**
     * Retourne la vitesse en X de l'objet
     * @return #vx
     */
    public final double getVx() {
        return vx;
    }

    /**
     * Défini la vitesse en X de l'objet
     * @param vx la vitesse en X de l'objet
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * Retourne la vitesse en Y de l'objet
     * @return #vy
     */
    public final double getVy() {
        return vy;
    }

    /**
     * Défini la vitesse en Y de lobjet
     * @param vy Vitesse en Y de l'objet
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * Retourne la position maximum autorisée en X de l'objet
     * @return #xmax
     */
    public final double getXmax() {
        return xmax;
    }

    /**
     * Change la position maximum autorisée en X de l'objet
     * @param xmax Position maximum autorisée en X
     */
    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    /**
     * Retourne la position minimum autorisée en X de l'objet
     * @return #xmin
     */
    public final double getXmin() {
        return xmin;
    }

    /**
     * Change la position minimum autorisée en X de l'objet
     * @param xmin Position minimum autorisée en X
     */
    public void setXmin(double xmin) {
        this.xmin = xmin;
    }
    
    
    /**
     * Méthode mettant à jour le modèle de l'objet
     */
    public void update() throws Exception
    {
        if(x<=xmin|| x>=xmax)
        {
            vx=-vx;
        }
        if(y<=YMIN || y>=YMAX)
        {
            vy=-vy;
        }
        x+=vx;
        y+=vy;
    }
    
    /**
     * Méthode pour dessiner l'objet dans un JPanel, doit être appelée par la méthode paintComponent d'un JPanel
     */
    public abstract void draw(Graphics g);

}
