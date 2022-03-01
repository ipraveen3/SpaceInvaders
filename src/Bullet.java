import java.awt.*;

public class Bullet extends Movable{

    private int moveDirection; //1 is up -1 is down

    private boolean isAlive;

    Bullet(int initialXPos, int intialYPos, int moveDirection) {
        super(initialXPos, intialYPos);
        this.moveDirection = moveDirection;
        isAlive = true;
    }


    public void drawBullet(Graphics2D g){
        g.setPaint(Color.WHITE);
        g.fillRect(getxPos(),getyPos(),2,10);
    }

    public void moveBullet(){
        switch (moveDirection){
            case 1 : setyPos(getyPos() - 4);break;
            case -1 : setyPos(getyPos() + 4);break;
        }
        if(isOutOfBounds(10,10)){
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

}
