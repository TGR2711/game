
package dev.codemore.tilegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.codemore.tilegame.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftpressed, rightpressed;
    private int mousex, mousey;
    private UIManager uiManager;

    public MouseManager(){

    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public boolean isLeftpressed(){
        return leftpressed;
    }
    public boolean isRightpressed(){
        return rightpressed;
    }
    public int getMousex(){
        return mousex;
    }
    public int getMousey(){
        return mousey;
    }


    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1)
            leftpressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightpressed = true;

        if (uiManager != null)
            uiManager.onMousePressed(e);
    }
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1)
            leftpressed = false;
         else if (e.getButton() == MouseEvent.BUTTON3)
            rightpressed = false;

        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }
    public void mouseMoved(MouseEvent e){
        mousex = e.getX();
        mousey = e.getY();

        if (uiManager != null)
            uiManager.onMouseMove(e);
    }
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
}
