import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Game {

    private static final int INITIAL_SPACESHIP_XPOS = 200;
    private static final int INITIAL_SPACESHIP_YPOS = 420;

    private static Spaceship spaceship;


    private static Alien[][] aliens;

    //spaceship bullets
    private static List<Bullet> bullets;
    private static long lastShotTime = 0;
    private static final long SHOOTING_GAP = 100;

    //aliens movement
    private static int movingState; //1 - moveRight, -1 = moveLeft, 2 = moveDown


    private static int nextMovingState = 1;
    private static final int MOVE_DOWN_COUNT = 7;
    private static int remainingMovesDown;

    private static List<Bullet> alienBullets;

    private static boolean gameOver;

    private static Random random;

    private static int score;

    private static List<Bunker> bunkers;

    public static void main(String[] args){
        initialize();
        drawGame();
    }

    public static void initialize(){
        spaceship = new Spaceship(INITIAL_SPACESHIP_XPOS,INITIAL_SPACESHIP_YPOS);
        bullets = new ArrayList<Bullet>();
        initializeAliens();
        movingState = 1;
        alienBullets = new ArrayList<Bullet>();

        gameOver = false;
        random = new Random();
        score = 0;
        bunkers = new ArrayList<Bunker>();

        bunkers.add(new Bunker(60,365));
        bunkers.add(new Bunker(140,365));
        bunkers.add(new Bunker(220,365));
        bunkers.add(new Bunker(300,365));
    }

    public static void drawGame(){
        new GameFrame();
    }

    public static Spaceship getSpaceship() {
        return spaceship;
    }


    public static List<Bullet> getBullets(){
        return bullets;
    }

    public static void shootSpaceShipBullet(){
        if((System.currentTimeMillis() - lastShotTime) > SHOOTING_GAP){
            bullets.add(new Bullet(spaceship.getxPos(),spaceship.getyPos(),1));
            lastShotTime = System.currentTimeMillis();
        }
    }

    private static void initializeAliens(){
        aliens = new Alien[5][7];
        for(int i = 0; i < aliens.length; i++){
            for(int j = 0; j < aliens[0].length; j++){
                aliens[i][j] = new Alien(50 + j * 40,50 + i*50);
            }
        }
    }

    public static Alien[][] getAliens(){
        return aliens;
    }

    public static void handleAlienMovement(){
        if(movingState == 1 || movingState == -1){
            updateMoveIfHorizontalBoundaryReached();
        }else{
            updateIfMoveDownCountOver();
        }

        for(int i = 0; i < aliens.length; i++)
            for(int j = 0; j < aliens[0].length; j++)
                aliens[i][j].moveAlien();

    }

    public static int getAlienMovingState(){
        return movingState;
    }

    private static void updateMoveIfHorizontalBoundaryReached(){
        boolean stateUpdated = false;
        for(int i = 0; i < aliens.length; i++){
            if(stateUpdated)
                break;
            for(int j = 0; j < aliens[0].length; j++){
                    if(aliens[i][j].isOutOfBounds()){
                        switch(movingState){
                            case 1: movingState = 2;nextMovingState = -1;remainingMovesDown = MOVE_DOWN_COUNT;  break;
                            case -1: movingState = 2;nextMovingState = 1;remainingMovesDown = MOVE_DOWN_COUNT;  break;
                        }
                        stateUpdated = true;
                        break;
                    }
            }
        }

    }

    private static void updateIfMoveDownCountOver(){
        if(remainingMovesDown == 0){
            movingState = nextMovingState;
        }else{
            remainingMovesDown--;
        }
    }

    public static List<Bullet> getAlienBullets(){
        return alienBullets;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public static void alienShootBullet(){
        int m = aliens.length;
        int n = aliens[0].length;

        List<Alien> alienInFront = new ArrayList<Alien>();

        boolean[] presentInColumn = new boolean[n];
        int bulletCount = 0;
        for(int i = m - 1; i >= 0; i--){
            if(bulletCount >=3) break;
            for(int j = 0; j < n; j++){
                if(bulletCount >=3) break;
                if(presentInColumn[j]) continue;
                if(aliens[i][j].isAlive()){
                    presentInColumn[j] = true;
                    alienInFront.add(aliens[i][j]);

                    if(random.nextInt(10000) < 30){
                        shootFromAlien(aliens[i][j]);
                        bulletCount++;
                    }

                }
            }
        }





    }

    private static void shootFromAlien(Alien a){
        alienBullets.add(new Bullet(a.getxPos(),a.getyPos(),-1));
    }


    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }

    public static void resetGame(){
        initialize();
    }

    public static List<Bunker> getBunkers(){
        return bunkers;
    }
}
