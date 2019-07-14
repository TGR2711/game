package dev.codemore.tilegame;

public class Deep2Tile extends Tile{

    public Deep2Tile(int id) {
        super(Assets.deep2, id);
    }

    @Override
    public boolean issolid() {
        return false;
    }
}
