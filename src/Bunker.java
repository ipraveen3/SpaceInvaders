import java.awt.*;

public class Bunker {

    boolean[][] isDead;
    private int startXPosition;
    private int startYPosition;

    public Bunker(int x, int y){
        isDead = new boolean[3][3];
        startXPosition = x;
        startYPosition = y;
    }

    public void drawBunker(Graphics2D g){
        g.setPaint(Color.GREEN);

        for(int i = 0; i < isDead.length; i++)
            for(int j = 0; j < isDead[0].length; j++){
                if(!isDead[i][j])
                    g.fillRect(startXPosition + j*13,startYPosition + i*13, 13,13 );
            }
    }

    public void damageBunker(int i, int j){
        isDead[i][j] = true;
    }

    public boolean isDamaged(int i, int j){
        return isDead[i][j];
    }

    public int getXpos(int i, int j){
        return startXPosition + j*13;
    }
    public int getYpos(int i, int j){
        return startYPosition + i*13;
    }


}
