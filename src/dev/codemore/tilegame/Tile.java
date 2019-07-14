package dev.codemore.tilegame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile dirttile = new DirtTile(0);
    public static Tile rocktile = new RockTile(1);
    public static Tile deeptile = new DeepTile(2);
    public static Tile deep2tile = new Deep2Tile(3);
    public static Tile skytile = new SkyTile(4);
    public static Tile cloud1tile = new Cloud1Tile(5);
    public static Tile cloud2tile = new Cloud2Tile(6);
    public static Tile bricktile = new BrickTile(7);

//CLASS
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

        protected BufferedImage texture;
        protected final int id;

        public Tile(BufferedImage texture, int id){
            this.texture = texture;
            this.id = id;

            tiles[id] = this;
    }

    public void tick(){


    }

    public void render(Graphics g, int x, int y){
            g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean issolid(){
            return false;
    }

    public int getId(){
            return  id;
    }

}
