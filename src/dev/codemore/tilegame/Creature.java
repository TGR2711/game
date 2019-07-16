package dev.codemore.tilegame;

import dev.codemore.tilegame.Entity;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xmove, ymove;

    public Creature(Handler handler, float x, float y, int width, int height){
        super(handler, x,y, width, height);
        speed = DEFAULT_SPEED;
        xmove = 0;
        ymove = 0;
    }

    public void move() {
        if (!checkentitycollisions(xmove,0f))
            movex();
        if (!checkentitycollisions(0f, ymove))
            movey();
    }

    public void movex(){
        if (xmove > 0) {

            int tx = (int) (x + xmove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            int collisionTest1 = (int) (y + bounds.y) / Tile.TILEHEIGHT;
            int collisionTest2 = (int) ((y + bounds.y + bounds.height) / Tile.TILEHEIGHT);

            if (collisionTest1 > 9 || collisionTest2 > 9) {
                System.err.println("Collision test 1 = " + collisionTest1);
                System.err.println("Collision test 2 = " + collisionTest2);
            }

            if (!collisionWtile(tx, collisionTest1) && !collisionWtile(tx, collisionTest2)) {
                x += xmove;
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }else if (xmove < 0){
            int tx = (int) (x + xmove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWtile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWtile(tx,(int) ((y + bounds.y + bounds.height) / Tile.TILEHEIGHT))){
                x += xmove;
            }else{
                x = tx *Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void movey(){
       if (ymove < 0) {
           int ty = (int) (y + ymove + bounds.y) / Tile.TILEHEIGHT;

           if(!collisionWtile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                   !collisionWtile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
               y += ymove;
           }else{
               y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
           }
       }else if (ymove > 0){
           int ty = (int) (y + ymove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

           if(!collisionWtile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                   !collisionWtile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
               y += ymove;
           }else{
               y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
           }
       }
    }


    protected boolean collisionWtile(int x, int y){
        return handler.getWorld().gettile(x, y).issolid();
    }


    //GETTERS AND SETTERS
    public float getXmove() {
        return xmove;
    }

    public void setXmove(float xmove) {
        this.xmove = xmove;
    }

    public float getYmove() {
        return ymove;
    }

    public void setYmove(float ymove) {
        this.ymove = ymove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
