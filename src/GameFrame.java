import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    GamePanel panel;


    private static final int RIGHT_CODE_ = 39;
    private static final int LEFT_CODE = 37;
    private static final int KEY_X_CODE = 88;
    private static final int KEY_Y_CODE = 89;


    GameFrame(){
        panel = new GamePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.addKeyListener(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(!Game.isGameOver()){

            switch (e.getKeyCode()){
                case LEFT_CODE : Game.getSpaceship().moveLeft(); break;
                case RIGHT_CODE_ : Game.getSpaceship().moveRight(); break;
                case KEY_X_CODE : Game.shootSpaceShipBullet();break;
            }
        }else{
            if (e.getKeyCode() == KEY_Y_CODE){
                Game.resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }




}
