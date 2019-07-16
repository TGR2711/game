package dev.codemore.tilegame;

import java.awt.*;

import static dev.codemore.tilegame.Tile.TILEHEIGHT;
import static dev.codemore.tilegame.Tile.TILEWIDTH;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnx, spawny;
    private int[][] tiles;

    private  EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 260));
        entityManager.addentity(new Tree(handler, 300,261));
        entityManager.addentity(new Tree(handler, 400,261));

        loadworld(path);

        entityManager.getPlayer().setX(spawnx);
        entityManager.getPlayer().setY(spawny);
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xstart =(int) Math.max(0, handler.getGamecamera().getXoffset() / TILEWIDTH);
        int xend = (int) Math.min(width, (handler.getGamecamera().getXoffset() + handler.getWidth()) / TILEWIDTH + 1);
        int ystart = (int) Math.max(0, handler.getGamecamera().getYoffset() / TILEHEIGHT);
        int yend = (int) Math.min(height, (handler.getGamecamera().getYoffset() + handler.getHeight()) / TILEHEIGHT  + 1);

        for (int y = ystart; y < yend; y++) {
            for (int x = xstart; x < xend; x++) {
                gettile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGamecamera().getXoffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGamecamera().getYoffset()));
            }
        }
        entityManager.render(g);
    }

    public Tile gettile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            System.err.println(String.format("X (%d) and Y (%d) are incorrect", x, y));
            return Tile.dirttile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            System.err.println(String.format("Tile at X (%d) and Y (%d) is NULL", x, y));
            return Tile.dirttile;
        }
        return t;
    }

    Tile getTileAtScreenCoords(int screenX, int screenY) {
        return gettile((screenX / TILEWIDTH), (screenY / TILEHEIGHT));
    }

    private void loadworld(String path) {
       String file = Utils.loadfileasstring(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseint(tokens[0]);
        height = Utils.parseint(tokens[1]);
        spawnx = Utils.parseint(tokens[2]);
        spawny = Utils.parseint(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0;y < height;y++)
            for (int x = 0;x < width;x++)
                tiles[x][y] = Utils.parseint(tokens[(x + y * width) + 4]);
    }

public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}

