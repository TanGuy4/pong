package pong;

import java.awt.Color;
import java.awt.Graphics;
import pong.exceptions.BallOutException;

/**
 * Ball
 * @author TanGuy
 */
public class Ball extends Objet 
{
    private int rebond = 0;
    
    public Ball()
    {
        super();
        setXmin(getXmin()-getWidth());
        setXmax(getXmax()+getWidth());
        setX((Pong.X/2)-(getWidth()/2));
        setY((Pong.Y/2)-(getHeight()/2));
        int r = (int) (Math.random()*100);
        switch(r%2)
        {
            case 0:
                setVx(-2);
                break;
            case 1:
                setVx(2);
                break;
        }
    }
    
    @Override
    public void update() throws BallOutException
    {
        if(getX()<=getXmin())
        {
            throw new BallOutException(false);
        }
        else if(getX()>=getXmax())
        {
            throw new BallOutException(true);
        }
        setX(getX()+getVx());
        setY(getY()+getVy());
        if(getY()<=(YMIN+(getHeight()/2)) || getY()>=(YMAX-(getHeight()/2)))
        {
            setVy(-getVy());
        }
    }
    
    public void rebondit()
    {
        rebond++;
        if(getVy()!=0 && getVy()<=2 && getVy()>=-2) setVy(getVy()*(rebond/5));
        else setVy((rebond/2));
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect((int)((getX())-(getWidth()/2)),(int)((getY())-(getHeight()/2))
                   ,(int)getWidth(),(int)getHeight());
    }
    
}
