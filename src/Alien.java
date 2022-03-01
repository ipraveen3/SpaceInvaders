import java.awt.*;

public class Alien extends Movable{

    private final int ALIEN_WIDTH = 15;
    private final int ALIEN_HEIGHT = 10;

    private final Color ALIEN_COLOR = Color.RED;


    private final int MOVE_DISTANCE = 1;


    private boolean isAlive;

    Alien(int initialXPos, int intialYPos) {
        super(initialXPos, intialYPos);
        isAlive = true;
    }

    public void drawAlien(Graphics2D g){
        if(!isAlive)
            return;
        g.setPaint(ALIEN_COLOR);
        g.fillRect(getxPos(),getyPos(),ALIEN_WIDTH,ALIEN_HEIGHT);
    }

    public void moveAlien(){
        switch(Game.getAlienMovingState()){
            case 1 : setxPos(getxPos() + MOVE_DISTANCE);break; // Move Right
            case -1 : setxPos(getxPos() - MOVE_DISTANCE);break; // Move Left
            case 2 : setyPos(getyPos() + MOVE_DISTANCE);break; //Move Down
        }
    }


    public void killAlien(){
        isAlive = false;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public boolean isOutOfBounds(){
        return isHorizontalOutOfBounds(ALIEN_WIDTH,ALIEN_HEIGHT);
    }


}
