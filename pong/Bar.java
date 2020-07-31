package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Barre
 * @author TanGuy
 */
public class Bar extends Objet
{
    public Bar(boolean right)
    {
        super();
        setHeight(100);
        if(right)
        {
            setX(getXmax()-getWidth());
        }
        setY((YMAX/2)-(getHeight()/2)-(getHeight()/8));
    }

    @Override
    public void update()
    {
       double newy = (getY()+getVy());
       if(newy>YMIN && newy <(YMAX-getHeight()))
       {
           setY(newy);
       }
    }

    @Override
    public void draw(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect((int)getX(),(int)(getY())
                ,(int)getWidth(),(int)getHeight());
//        g.setColor(Color.BLUE);
//        g.fillRect((int)getX(),(int)getY()
//                    ,(int)getWidth(),(int)(getHeight()/3)+1);
//        g.setColor(Color.GREEN);
//        g.fillRect((int)getX(),(int)(getY()+(getHeight()/3))
//                    ,(int)getWidth(),(int)(getHeight()/3));
//        g.setColor(Color.RED);
//        g.fillRect((int)getX(),(int)(getY()+((getHeight()/3)*2)-1)
//                    ,(int)getWidth(),(int)(getHeight()/3));
    }
    
}
