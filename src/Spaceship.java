import java.awt.*;

public class Spaceship extends Movable{

    private final int SPACESHIP_WIDTH = 25;
    private final int SPACESHIP_HEIGHT = 10;


    private final Color SPACESHIP_COLOR = Color.YELLOW;

    private final int MOVE_DISTANCE = 7;


    private int lives = 3;

    Spaceship(int initialXPos, int intialYPos) {
        super(initialXPos, intialYPos);
        lives = 3;
    }





    public void drawSpaceship(Graphics2D g){
        g.setPaint(SPACESHIP_COLOR);
        g.fillRect(getxPos(),getyPos(),SPACESHIP_WIDTH,SPACESHIP_HEIGHT);
    }

    public void moveRight(){
        setxPos(getxPos() + MOVE_DISTANCE);
    }

    public void moveLeft(){
        setxPos(getxPos() - MOVE_DISTANCE);
    }



    public int getLives(){
        return lives;
    }

    public void reduceLives(){
        lives--;
    }

}
