package dev.codemore.tilegame;

public class RockTile extends Tile {

    public RockTile(int id){
        super(Assets.rock, id);
    }

    @Override
    public boolean issolid() {
        return true;
    }
}
