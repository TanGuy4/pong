package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pong.exceptions.BallOutException;
import pong.exceptions.Toucher;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Principale, contenat la boucle de jeu
 * @author TanGuy
 */
public class Pong
{
    public static final int X=800;
    public static final int Y=600;
    public static final double FPS = 60;
    private boolean gameOver=false, ballwait=true;
    private long before, after, diff, period;
    private Scoring scores;
    
    private Frame f;
    
    /**
     * Méthode lanceant une partie
     */
    public void run()
    {
        scores = new Scoring();
        f = new Frame();
        gameLoop();
    }
    
    /**
     * Boucle de jeu
     */
    private void gameLoop()
    {
        period = (1000L/(long)FPS);
        do
        {
            before = System.currentTimeMillis();

            f.update();
            f.render();

            after = System.currentTimeMillis();
            
            diff = period - (after - before);
            if(diff>0)
            {
                try
                {
                    Thread.sleep(diff);
                }
                catch(InterruptedException ex){}
            }      
        }
        while(!isGameOver());
    }

    public final boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    

    class Frame extends JFrame
    {
        private GamePanel gp;
        
        public Frame()
        {
            super("Pong");
            gp = new GamePanel();
            add(gp);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setMaximumSize(new Dimension(X,Y));
            setMinimumSize(new Dimension(X,Y));
            pack();
            setVisible(true);
            gp.repaint();
        }
        
        public void update()
        {
            gp.update();
        }
        
        public void render()
        {
            gp.repaint();
        }
    }
    
    class GamePanel extends JPanel
    {
        private Ball b;
        private Bar bl, br;
        
        public GamePanel()
        {
            super();
            b = new Ball();
            bl = new Bar(false);
            br = new Bar(true);
            setPreferredSize(new Dimension(X,Y));
            setSize(X,Y);
            addKeyListener(new KeyController());
            setFocusable(true);
            requestFocus();
        }
        
        public void update()
        {
            try
            {
                b.update();
                bl.update();
                br.update();
                checkRebonds();
            }
            catch(BallOutException e)
            {
                if(!ballwait)
                {    
                    if(e.isRight())
                    {
                        scores.pointLeft();
                    }
                    else
                    {
                        scores.pointRight();
                    }
                    ballwait=true;
                }
            }
            catch(Toucher t)
            {
                b.rebondit();
                b.setVx(-b.getVx());
                switch(t.getPart())
                {
                    case Toucher.UP:
                        b.setVy(-Math.abs(b.getVy()));
                        break;
                    case Toucher.MIDDLE:
                        double r = (Math.random()*4)-2;
                        b.setVy(r);
                        break;
                    case Toucher.DOWN:
                        b.setVy(Math.abs(b.getVy()));
                        break;
                }
            }
        }
        
        private void checkRebonds() throws Toucher
        {
//            if((b.getX()>=br.getX()) 
//                    && 
//               (b.getY()>=br.getY() && b.getY()<=br.getY()+br.getHeight())
//              )
//            {
//                throw new Toucher(true);
//            }
//            else if((b.getX()<=bl.getX()+bl.getWidth()) 
//                    && 
//               (b.getY()>=bl.getY() && b.getY()<=bl.getY()+bl.getHeight())
//              )
//            {
//                throw new Toucher(false);
//            }
            if(b.getX()==br.getX())
            {
                if(b.getY()>=br.getY() && b.getY()<br.getY()+(br.getHeight()/3))
                {
                    throw new Toucher(1);
                }
                else if(b.getY()>=br.getY()+(br.getHeight()/3) && b.getY()<br.getY()+((br.getHeight()/3)*2))
                {
                    throw new Toucher(2);
                }
                else if(b.getY()>=br.getY()+((br.getHeight()/3)*2) && b.getY()<=br.getY()+br.getHeight())
                {
                    throw new Toucher(3);
                }
            }
            else if(b.getX()==bl.getX()+bl.getWidth())
            {
                if(b.getY()>=bl.getY() && b.getY()<bl.getY()+(bl.getHeight()/3))
                {
                    throw new Toucher(1);
                }
                else if(b.getY()>=bl.getY()+(bl.getHeight()/3) && b.getY()<bl.getY()+((bl.getHeight()/3)*2))
                {
                    throw new Toucher(2);
                }
                else if(b.getY()>=bl.getY()+((bl.getHeight()/3)*2) && b.getY()<=bl.getY()+bl.getHeight())
                {
                    throw new Toucher(3);
                }
            }
                    
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, X, Y);
            g.setColor(Color.GRAY);
            for(int i = 0;i<Y;i+=40)
            {
                g.fillRect(X/2, i, 10, 20);
            }
            if(ballwait)
            {
                String s = "PRESS SPACE TO CONTINUE";
                g.setColor(Color.WHITE);
                g.setFont(new Font("Impact",48,48));
                char[] data = new char[23];
                s.getChars(0, 23, data, 0);
                g.drawChars(data,0,23,200,400);
            }
            else b.draw(g);
            bl.draw(g);
            br.draw(g);
            scores.draw(g);
        }
    
        class KeyController extends KeyAdapter
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        br.setVy(-2);
                        break;
                    case KeyEvent.VK_DOWN:
                        br.setVy(2);
                        break;
                    case KeyEvent.VK_Z:
                        bl.setVy(-2);
                        break;
                    case KeyEvent.VK_S:
                        bl.setVy(2);
                        break;
                    case KeyEvent.VK_SPACE:
                        if(ballwait) b = new Ball();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e)
            {
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        br.setVy(0);
                        break;
                    case KeyEvent.VK_Z:
                    case KeyEvent.VK_S:
                        bl.setVy(0);
                        break;
                    case KeyEvent.VK_SPACE:
                        ballwait=false;
                        break;
                }
            }
        }
    }
 
    class Scoring
    {
        private int score_right = 0;
        private int score_left = 0;
        
        public void pointLeft()
        {
            score_left++;
        }
        
        public void pointRight()
        {
            score_right++;
        }
        
        public final int getLeft()
        {
            return score_left;
        }
        
        public final int getRight()
        {
            return score_right;
        }
        
        public void draw(Graphics g)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",48,48));
            char[] data = new char[7];
            String s = "";
            if(score_left<10) s += "0"+score_left;
            else s+=score_left;
            s +=" - ";
            if(score_right<10) s+="0"+score_right;
            else s+=score_right;
            s.getChars(0, 7, data, 0);
            g.drawChars(data, 0, 7, 325, 50);
        }
    }
}
