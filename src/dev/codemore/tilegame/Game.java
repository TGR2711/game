package dev.codemore.tilegame;

import dev.codemore.tilegame.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    private String title;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gamestate;
    public State menustate;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    //camera
    private GameCamera gamecamera;

    //Handler
    private Handler handler;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }


    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gamecamera = new GameCamera(handler,0, 0);

        gamestate = new GameState(handler);
        menustate = new MenuState(handler);
        State.setstate(menustate);
    }


    private void tick() {
        keyManager.tick();

        if (State.getState() != null);
        State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //top

      if (State.getState() != null)
          State.getState().render(g);

        //bottom
        bs.show();
        g.dispose();
    }

    public void run() {

        init();

        int fps = 60;
        double timepertick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lasttime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lasttime) / timepertick;
            timer += now - lasttime;
            lasttime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
//                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;

            }

        }

        stop();
    }
    public KeyManager getKeyManager() {
        return keyManager;
    }
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGamecamera(){
        return gamecamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
            thread = new Thread(this);
            thread.start();
        }

        public synchronized void stop(){
        if (!running)
            return;
        running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


