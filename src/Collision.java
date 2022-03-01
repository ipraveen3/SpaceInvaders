import java.util.List;

public class Collision {

    private static final int COLLISION_RADIUS = 15;

    public static void handleBulletCollision(List<Bullet> bullets, Alien[][] aliens){

        for(int i = bullets.size() - 1; i >= 0; i--){
            boolean bulletPresent = true;
            //for every alien
            for(int j = 0; j < aliens.length; j++)
                for(int k = 0; k < aliens[0].length; k++){
                    if(aliens[j][k].isAlive() && bulletPresent && ifCollided(bullets.get(i),aliens[j][k])){
                        aliens[j][k].killAlien();
                        bullets.remove(i);
                        bulletPresent = false;

                        Game.setScore(Game.getScore() + 10);
                    }
                }
        }

    }

    public static void handleAlienBulletCollision(List<Bullet> alienBullets, Spaceship spaceship){
        for(int i = alienBullets.size() - 1; i >= 0; i--){
            Bullet alienBullet = alienBullets.get(i);
                if(ifCollided(spaceship,alienBullet)){
                    Game.getSpaceship().reduceLives();
                    if(Game.getSpaceship().getLives() == 0)
                        Game.setGameOver(true);
                    alienBullets.remove(i);
                }
        }
    }

    public static void handleBulletCollisionWithBunker(List<Bullet> alienBullets, List<Bullet> spaceShipBullets, List<Bunker> bunkers){

        for(int i = spaceShipBullets.size() - 1; i >= 0; i-- ){
            boolean bulletPresent = true;
            for(Bunker b : bunkers){
                for(int j = 0; j < 3; j++)
                    for(int k = 0; k < 3; k++){
                        if(bulletPresent && !b.isDamaged(j,k) && ifCollided(new Movable(b.getXpos(j,k),b.getYpos(j,k)),spaceShipBullets.get(i))){
                            spaceShipBullets.remove(i);
                            b.damageBunker(j,k);
                            bulletPresent = false;
                        }
                    }


            }
        }


        for(int i = alienBullets.size() - 1; i >= 0; i-- ){
            boolean bulletPresent = true;
            for(Bunker b : bunkers){
                for(int j = 0; j < 3; j++)
                    for(int k = 0; k < 3; k++){
                        if(bulletPresent && !b.isDamaged(j,k) && ifCollided(new Movable(b.getXpos(j,k),b.getYpos(j,k)),alienBullets.get(i))){
                            alienBullets.remove(i);
                            b.damageBunker(j,k);
                            bulletPresent = false;
                        }
                    }


            }
        }

    }

    public static boolean ifCollided(Movable a, Movable b){
        if(Math.abs(a.getxPos() + 7 - b.getxPos()) <= COLLISION_RADIUS && Math.abs(a.getyPos() + 5 - b.getyPos()) <= COLLISION_RADIUS)
            return true;
        return false;
    }



}
