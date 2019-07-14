 package dev.codemore.tilegame;

public class GameCamera {

    private Handler handler;
    private float xoffset, yoffset;

    public GameCamera(Handler handler, float xoffset, float yoffset) {
        this.handler = handler;
        this.xoffset = xoffset;
        this.yoffset = yoffset;

    }

        public void checkblankspace(){
            if(xoffset < 0){
               xoffset = 0;
            }else if(xoffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
                xoffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
            }

            if (yoffset < 0){
                yoffset = 0;
            }else if(yoffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
                xoffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
            }
    }

    public void centeronentity(Entity e){
        xoffset = e.getX() -handler.getWidth() / 2 + e.getWidth() / 2;
        yoffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkblankspace();
    }
    public void move(float xamt, float yamt){
        xoffset += xamt;
        yoffset += yamt;
        checkblankspace();
    }

    public float getXoffset() {
        return xoffset;
    }

    public void setXoffset() {
        this.xoffset = xoffset;
    }

    public float getYoffset() {
        return yoffset;
    }

    public void setYoffset(float yoffset) {
        this.yoffset = yoffset;
    }


    }