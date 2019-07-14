package dev.codemore.tilegame;

public class BrickTile extends Tile {

    public BrickTile(int id) {
        super(Assets.brick, id);
    }

    @Override
    public boolean issolid() {
        return true;
    }
}
