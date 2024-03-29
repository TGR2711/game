package dev.codemore.tilegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> rendersorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight()< b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addentity(player);

    }
    public void tick(){
       for (int i = 0;i < entities.size();i++){
           Entity e = entities.get(i);
           e.tick();
       }
       entities.sort(rendersorter);
    }
    public void render(Graphics g){
        for (Entity e : entities){
            e.render(g);
            e.postRender();
        }
    }

    public void addentity(Entity e){
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
