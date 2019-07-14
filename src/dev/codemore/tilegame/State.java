package dev.codemore.tilegame;

import java.awt.*;

public abstract class State {

    private static State currentstate = null;

    public static void setstate(State state){
        currentstate = state;
    }

    public static State getState(){
        return currentstate;
    }

    //class
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
