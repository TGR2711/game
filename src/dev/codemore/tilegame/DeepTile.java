package dev.codemore.tilegame;

public class DeepTile extends Tile {

    public DeepTile(int id) {
        super(Assets.deep1, id);
    }

    @Override
    public boolean issolid() {
        return true;
    }
}