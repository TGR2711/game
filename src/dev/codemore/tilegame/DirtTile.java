package dev.codemore.tilegame;

public class DirtTile extends Tile {

    public DirtTile(int id){
        super(Assets.dirt, id);
    }

    @Override
    public boolean issolid() {
        return true;
    }


}
