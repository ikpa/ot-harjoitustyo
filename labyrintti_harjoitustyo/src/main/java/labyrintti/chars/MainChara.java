/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.chars;

import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import labyrintti.object.*;

import java.util.*;
/**
 *
 * @author ikpa
 */
public class MainChara {
    private Circle chara;
    private Point2D mvmnt;
    private int lives;
    private boolean dead;
    
    public MainChara(int x, int y, int r, int mvmnty, int mvmntx) {
        chara = new Circle(x, y, r, Color.PURPLE);
        mvmnt = new Point2D(mvmntx, mvmnty);
        lives = 3;
        dead = false;
    }
    
    public Circle getChara() {
        return chara;
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead() {
        return dead;
    }
    
    public void addLife() {
        lives++;
    }
    
    public void removeLife() {
        lives--;
    }
    
    public boolean collision(Shape s) {
        Shape sec = Shape.intersect(s, chara);
        return sec.getBoundsInLocal().getWidth() > 1;
    }
    
    public void checkHit(ArrayList<Spike> spikes) {
        spikes.forEach(s -> {
            if (collision(s.getP())) {
                removeLife();
                chara.setTranslateX(0);
                chara.setTranslateY(0);
            }
        });
        
        if (lives < 0) {
            dead = true;
        }
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
    
    public void resetChara() {
        chara.setCenterX(0);
        chara.setCenterY(0);
        chara.setTranslateX(0);
        chara.setTranslateY(0);
    }
}