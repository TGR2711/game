package dev.codemore.tilegame;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0,0, width, height);
    }
    public abstract void tick();

    public abstract void render(Graphics g);

    abstract void postRender();

    public boolean checkentitycollisions(float xoffset, float yoffset){
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if (e.getcollisionbounds(0f, 0f).intersects(getcollisionbounds(xoffset, yoffset)))
                return true;
        }
        return false;
    }

    public Rectangle getcollisionbounds(float xoffset, float yoffset) {
        return new Rectangle((int) (x + bounds.x + xoffset), (int) (y + bounds.y + yoffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

