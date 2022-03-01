import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{

    public static final int PANEL_WIDTH = 400;
    public static final int PANEL_HEIGHT = 500;

    Timer timer;

    GamePanel(){

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);


        timer = new Timer(30,this);
        timer.start();

    }



    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        if(Game.isGameOver()){
            g2d.setPaint(Color.RED);
            //show score after game is over
            g2d.drawString("Score: " + Game.getScore(),150,150);

            //game over text
            g2d.drawString("Game Over",150,200);
            g2d.drawString("Press 'y' to restart ",150,250);
            return;
        }

        //score zone
        g2d.setPaint(Color.ORANGE);
        g2d.drawLine(0,470,400,470);

        Game.getSpaceship().drawSpaceship(g2d);


        for(Bullet bullet : Game.getBullets()){
            bullet.drawBullet(g2d);
        }

        Alien[][] aliens = Game.getAliens();
        for(int i = 0; i < aliens.length; i++)
            for(int j = 0; j < aliens[0].length; j++){
                if(aliens[i][j].isAlive())
                    aliens[i][j].drawAlien(g2d);
            }


        for(Bullet alienBullet : Game.getAlienBullets()){
            alienBullet.drawBullet(g2d);
        }

        g2d.setPaint(Color.ORANGE);
        g2d.drawString("Score : " + Game.getScore(),250,490);

        for(Bunker b : Game.getBunkers()){
            b.drawBunker(g2d);
        }

        g2d.setPaint(Color.ORANGE);
        g2d.drawString("Lives : " + Game.getSpaceship().getLives(),50,490);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(Game.isGameOver()){
            return;
        }



        //update bullet;
        java.util.List<Bullet> bulletList =  Game.getBullets();
        for(int i = bulletList.size() - 1; i>=0; i--){
            bulletList.get(i).moveBullet();
            //remove if dead
            if(!bulletList.get(i).isAlive()){
                bulletList.remove(i);
            }
        }

        Collision.handleBulletCollision(Game.getBullets(),Game.getAliens());
        Collision.handleAlienBulletCollision(Game.getAlienBullets(),Game.getSpaceship());

        Game.handleAlienMovement();


        java.util.List<Bullet> alienBulletList =  Game.getAlienBullets();
        for(int i = alienBulletList.size() - 1; i>=0; i--){
            alienBulletList.get(i).moveBullet();
            //remove if dead
            if(!alienBulletList.get(i).isAlive()){
                alienBulletList.remove(i);
            }
        }

        Game.alienShootBullet();

        Collision.handleBulletCollisionWithBunker(Game.getAlienBullets(),Game.getBullets(),Game.getBunkers());

        repaint();
    }

}
