package dev.codemore.tilegame;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 25;
        bounds.y = 21;
        bounds.width = 16;
        bounds.height = 37;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree,(int) (x - handler.getGamecamera().getXoffset()),(int) (y - handler.getGamecamera().getYoffset()), width, height, null);
    }
}
