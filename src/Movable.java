public class Movable {

    private int xPos;
    private int yPos;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setPos(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    Movable(int initialXPos, int intialYPos){
        this.xPos = initialXPos;
        this.yPos = intialYPos;
    }

    public boolean isHorizontalOutOfBounds(int width, int height){
        if(xPos <= 0 || xPos + width >= GamePanel.PANEL_WIDTH)
            return true;
        return false;
    }

    public boolean isOutOfBounds(int width, int height){
        if(xPos <= 0 || xPos + width  >= GamePanel.PANEL_WIDTH)
            return true;
        if(yPos <= 0 || yPos + height  >= GamePanel.PANEL_HEIGHT)
            return true;
        return false;
    }

}
