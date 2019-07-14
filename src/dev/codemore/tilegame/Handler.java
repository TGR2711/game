package dev.codemore.tilegame;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }

    public GameCamera getGamecamera(){
        return game.getGamecamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
