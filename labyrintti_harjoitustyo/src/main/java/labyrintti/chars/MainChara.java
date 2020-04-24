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
    private Circle circle;
    private Point2D mvmnt;
    private int lives;
    private int points;
    private boolean dead;
    
    public MainChara(int x, int y, int r, int mvmnty, int mvmntx) {
        circle = new Circle(x, y, r, Color.PURPLE);
        mvmnt = new Point2D(mvmntx, mvmnty);
        lives = 3;
        dead = false;
    }
    
    public Circle getCircle() {
        return circle;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }
    
    public void addPoints(int i) {
        points = points + i;
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
        Shape sec = Shape.intersect(s, circle);
        return sec.getBoundsInLocal().getWidth() > 1;
    }
    
    public boolean get(Item i) {
        Shape sec = Shape.intersect(circle, i.getCircle());
        return sec.getBoundsInLocal().getWidth() >= 10;
    }
    
    public void checkHit(ArrayList<Spike> spikes) {
        spikes.forEach(s -> {
            if (collision(s.getPolygon())) {
                removeLife();
                circle.setTranslateX(0);
                circle.setTranslateY(0);
            }
        });
        
        if (lives < 0) {
            dead = true;
        }
    }
    
    public ArrayList<Integer> checkGet(ArrayList<Item> items) {
        ArrayList<Integer> ids = new ArrayList<>();
        items.forEach(item -> {
            if (get(item)) {
                if (item.getType() == 0) {
                    addLife();
                }
                
                if (item.getType() == 1) {
                    addPoints(50);
                }
                
                ids.add(items.indexOf(item));
            }
        });
        
        return ids;
    }
      
    public ArrayList<Boolean> allowedDirections(ArrayList<Rectangle> walls, double voffset) {
        ArrayList<Boolean> dirs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dirs.add(Boolean.TRUE);
        }
        
        Boolean up = true;
        Boolean down = true;
        Boolean left = true;
        Boolean right = true;
        
        for (Rectangle r: walls) {
            if (collision(r)) {
                Shape sec = Shape.intersect(r, circle);
                double xloc = sec.getBoundsInLocal().getCenterX()
                        - (circle.getTranslateX() + circle.getCenterX());
                double yloc = sec.getBoundsInLocal().getCenterY()
                        - (circle.getTranslateY() + circle.getCenterY()) - voffset;
                
                
                if (yloc < -8 && up) {
                    dirs.add(0, Boolean.FALSE);
                    up = false;
                }
                
                if (xloc > 1 && right) {
                    dirs.add(1, Boolean.FALSE);
                    right = false;
                }
                
                if (yloc > 6 && down) {
                    dirs.add(2, Boolean.FALSE);
                    down = false;
                }
                
                if (xloc < -7 && left) {
                    dirs.add(3, Boolean.FALSE);
                    left = false;
                }
            }
        }
        
        return dirs;
    }
    
    public void move(Map<KeyCode, Boolean> buttonPress,
            ArrayList<Rectangle> a, double voffset) {
        ArrayList<Boolean> allowed = allowedDirections(a, voffset);
        
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
        circle.setTranslateY(circle.getTranslateY() - mvmnt.getY());
    }
    
    public void moveDOWN() {
        circle.setTranslateY(circle.getTranslateY() + mvmnt.getY());
    }
    
    public void moveRIGHT() {
        circle.setTranslateX(circle.getTranslateX() + mvmnt.getX());
    }
    
    public void moveLEFT() {
        circle.setTranslateX(circle.getTranslateX() - mvmnt.getX());
    }
    
    public void reset() {
        circle.setTranslateX(0);
        circle.setTranslateY(0);
    }
}
