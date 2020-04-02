/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.chars;

import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;

import java.util.*;
/**
 *
 * @author ikpa
 */
public class mainChara {
    private Circle chara;
    private Point2D mvmnt;
    
    public mainChara(int x, int y, int r, int mvmnty, int mvmntx) {
        chara = new Circle(x, y, r);
        mvmnt = new Point2D(mvmntx, mvmnty);
    }
    
    public Circle getChara() {
        return chara;
    }
    
    public boolean collision(Shape s) {
        Shape sec = Shape.intersect(s, chara);
        return sec.getBoundsInLocal().getWidth() > 1;
    }
    
    public ArrayList<Boolean> allowedDirs(ArrayList<Rectangle> arr) {
        ArrayList<Boolean> dirs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dirs.add(Boolean.TRUE);
        }
        
        for (Rectangle r: arr) {
            if (collision(r)) {
                Shape sec = Shape.intersect(r, chara);
                double xloc = sec.getBoundsInLocal().getCenterX()
                        - (chara.getTranslateX() + chara.getCenterX());
                double yloc = sec.getBoundsInLocal().getCenterY()
                        - (chara.getTranslateY() + chara.getCenterY());
                
                if (yloc < -8) {
                    dirs.add(0, Boolean.FALSE);
                }
                
                if (xloc > 0) {
                    dirs.add(1, Boolean.FALSE);
                }
                
                if (yloc > 8) {
                    dirs.add(2, Boolean.FALSE);
                }
                
                if (xloc < 0) {
                    dirs.add(3, Boolean.FALSE);
                }
            }
        }
        
        return dirs;
    }
    
    public void move(Map<KeyCode, Boolean> buttonPress,
            ArrayList<Rectangle> a) {
        ArrayList<Boolean> allowed = allowedDirs(a);
        
        if (buttonPress.getOrDefault(KeyCode.UP, Boolean.FALSE)
                && allowed.get(0)) {
            this.moveUP();
        }
        
        if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)
                && allowed.get(1)) {
            this.moveRIGHT();
        }
        
        if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)
                && allowed.get(2)) {
            this.moveDOWN();
        }
        
        if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)
                && allowed.get(3)) {
            this.moveLEFT();
        }
    }
    
    public void moveUP() {
        chara.setTranslateY(chara.getTranslateY() - mvmnt.getY());
    }
    
    public void moveDOWN() {
        chara.setTranslateY(chara.getTranslateY() + mvmnt.getY());
    }
    
    public void moveRIGHT() {
        chara.setTranslateX(chara.getTranslateX() + mvmnt.getX());
    }
    
    public void moveLEFT() {
        chara.setTranslateX(chara.getTranslateX() - mvmnt.getX());
    }
}
