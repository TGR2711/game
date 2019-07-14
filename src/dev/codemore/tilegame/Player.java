package dev.codemore.tilegame;

import dev.codemore.tilegame.Creature;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private int health;
    private int maxhealth;
    private int slash;
    private int maxslash;
    private boolean dead;
    private Animation animleft;
    private Animation animright;
    private Animation animjumpL;
    private Animation animjumpR;
    private Animation animstillL;
    private Animation animstillR;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 25;
        bounds.y = 21;
        bounds.width = 16;
        bounds.height = 37;

        animleft = new Animation(135, Assets.player_left);
        animright = new Animation(135, Assets.player_right);
        animjumpL = new Animation(135, Assets.player_jumpL);
        animjumpR = new Animation(135, Assets.player_jumpR);
        animstillL = new Animation(135, Assets.player_stillL);
        animstillR = new Animation(135, Assets.player_stillR);

    }

    @Override
    public void tick() {
        animleft.tick();
        animright.tick();
        animjumpL.tick();
        animjumpR.tick();
        animstillL.tick();
        animstillR.tick();
        getinput();
        move();
        handler.getGamecamera().centeronentity(this);

    }

    private void getinput() {
        xmove = 0;
        ymove = 6;


        if (handler.getKeyManager().jump) {
            Tile tileUnderPlayer = handler.getWorld().getTileAtScreenCoords((int) (x - handler.getGamecamera().getXoffset()),
                    (int) (y - handler.getGamecamera().getYoffset() + bounds.height) + 1);
            System.out.println(tileUnderPlayer);
            System.out.println(y + " " + handler.getGamecamera().getYoffset());
            if (tileUnderPlayer.issolid()) {
                System.out.println("Jump x=" + x + ", y=" + y + ", bounds=" + bounds.height);
                ymove = -speed * 2;
            }
        }

        if (handler.getKeyManager().left)
            xmove = -speed;
        if (handler.getKeyManager().right) {
            xmove = speed;
            System.out.println(x + " " + handler.getGamecamera().getXoffset());
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getcurrentanimframe(), (int) (x - handler.getGamecamera().getXoffset()), (int) (y - handler.getGamecamera().getYoffset()), width, height, null);

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGamecamera().getXoffset()),
//        ((int) (y + bounds.y - handler.getGamecamera().getYoffset())), bounds.width, bounds.height);
    }

    private BufferedImage getcurrentanimframe() {
        if (xmove < 0) {
            return animleft.getcurrentframe();
        } else if (xmove > 0) {
            return animright.getcurrentframe();
        } else if (xmove != 0 || ymove < 0) {
            return animstillL.getcurrentframe();
        }else return animstillR.getcurrentframe();

    }
}
