package dev.codemore.tilegame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private static final float GRAVITY_PULL_SPEED = 6f;
    private static final float MAX_JUMP_HEIGHT_PIXELS = 65;

    private final boolean debugRenderLineUnderPlayer = false;
    private final boolean debugRenderBoundingBox = false;

    private int health;
    private int maxhealth;
    private int slash;
    private int maxslash;
    private float jumpHeight;
    private boolean jumping = false;
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

        handler.getKeyManager().onKeyPressed(KeyEvent.VK_W, this::jump);
        handler.getKeyManager().onKeyPressed(KeyEvent.VK_A, this::moveLeft);
        handler.getKeyManager().onKeyPressed(KeyEvent.VK_D, this::moveRight);

    }

    private void jump(KeyEvent e) {
        if (!jumping) {
            Point pos = getScreenPositionUnderPlayer();
            Tile tileUnderPlayer = handler.getWorld().getTileAtScreenCoords(pos.x, pos.y);
            if (tileUnderPlayer.issolid()) {
                ymove = -speed * 2f;
                jumpHeight = getY();
                jumping = true;
            }
            else {
                ymove = GRAVITY_PULL_SPEED;
            }
        }
    }

    private void moveLeft(KeyEvent e) {
        ymove = (jumping) ? -speed * 2f : GRAVITY_PULL_SPEED;
        xmove = -speed;
    }

    private void moveRight(KeyEvent e) {
        ymove = (jumping) ? -speed * 2f : GRAVITY_PULL_SPEED;;
        xmove = speed;
    }

    @Override
    public void tick() {
        animleft.tick();
        animright.tick();
        animjumpL.tick();
        animjumpR.tick();
        animstillL.tick();
        animstillR.tick();
        move();
        handler.getGamecamera().centeronentity(this);
    }

    @Override
    public void move() {
        super.move();

        // Perform a check to see if they have reached the max jump height.
        if (jumpHeight - getY() >= MAX_JUMP_HEIGHT_PIXELS) {
            jumping = false;
        }
    }

    private Point getScreenPositionUnderPlayer() {
        // Screen position is based over the whole map
        return new Point((int) (x + bounds.x),(int) (y + bounds.y) + bounds.height + 1);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getcurrentanimframe(), (int) (x - handler.getGamecamera().getXoffset()), (int) (y - handler.getGamecamera().getYoffset()), width, height, null);

        if (debugRenderBoundingBox) {
            g.setColor(Color.red);
            g.fillRect((int) (x + bounds.x - handler.getGamecamera().getXoffset()),
                    ((int) (y + bounds.y - handler.getGamecamera().getYoffset())), bounds.width, bounds.height);
        }

        if (debugRenderLineUnderPlayer) {
            Point p = getScreenPositionUnderPlayer();
            g.setColor(Color.WHITE);
            // Adjust the rendering to take into account the offset of the camera.
            g.drawLine(p.x  - (int) handler.getGamecamera().getXoffset(),
                    p.y - (int) handler.getGamecamera().getYoffset(),
                    (p.x + bounds.width) - (int) handler.getGamecamera().getXoffset(),
                    p.y - (int) handler.getGamecamera().getYoffset());
        }
    }

    @Override
    void postRender() {
        // Reset movement
        if (!jumping) {
            ymove = GRAVITY_PULL_SPEED; // Always pulls Player down to solid tile.
            xmove = 0;
        }
    }

    private BufferedImage getcurrentanimframe() {
        if (xmove < 0) {
            return animleft.getcurrentframe();
        } else if (xmove > 0) {
            return animright.getcurrentframe();
        } else if (xmove != 0 || ymove < 0) {
            return animstillL.getcurrentframe();
        } else return animstillR.getcurrentframe();
    }

}
