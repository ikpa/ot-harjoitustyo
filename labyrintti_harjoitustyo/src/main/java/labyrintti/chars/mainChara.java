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
    
    public mainChara(int x, int y,int r, int mvmnty, int mvmntx) {
        chara = new Circle(x,y,r);
        mvmnt = new Point2D(mvmntx,mvmnty);
    }
    
    public Circle getChara() {
        return chara;
    }
    
    public boolean collision(Shape s) {
        Shape sec = Shape.intersect(s, chara);
        return sec.getBoundsInLocal().getWidth()>1;
    }
    
    public boolean arrayCollision(ArrayList<Rectangle> a){
        for (Rectangle r : a) {
            if (collision(r)) {
                return true;
            }
        }
        return false;
    }
    
    public void collisionMove(Map<KeyCode, Boolean> buttonPress, ArrayList<Rectangle> a) {
        ArrayList<Rectangle> coll = new ArrayList<>();
        for (Rectangle re: a) {
            if (collision(re)){
                coll.add(re);
                break;
            }
        }
        
        
        Shape r = coll.get(0);
        Shape sec = Shape.intersect(r, chara);
        double xloc = sec.getBoundsInLocal().getCenterX()-(chara.getTranslateX()+chara.getCenterX());
        double yloc = sec.getBoundsInLocal().getCenterY()-(chara.getTranslateY()+chara.getCenterY());
        System.out.println(xloc);
        System.out.println(yloc);
        
        if (xloc>0) {
            if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                chara.setTranslateX(chara.getTranslateX() - mvmnt.getX());
            }
        } else if (xloc<0) {
            if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                chara.setTranslateX(chara.getTranslateX() + mvmnt.getX());
            }
        } else {
            if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                chara.setTranslateX(chara.getTranslateX() - mvmnt.getX());
            }
                
            if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                chara.setTranslateX(chara.getTranslateX() + mvmnt.getX());
            }
        }
        
        if (yloc>8) {
            if (buttonPress.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
                chara.setTranslateY(chara.getTranslateY() - mvmnt.getY());
            }
        } else if (yloc<-8) {
            if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
                chara.setTranslateY(chara.getTranslateY() + mvmnt.getY());
            }
        } else {
            if (buttonPress.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
                chara.setTranslateY(chara.getTranslateY() - mvmnt.getY());
            }
        
            if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
                chara.setTranslateY(chara.getTranslateY() + mvmnt.getY());
            }
        }
    }
    
    public void move(Map<KeyCode, Boolean> buttonPress) {
        if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
            chara.setTranslateX(chara.getTranslateX() - mvmnt.getX());
        }
                
        if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
            chara.setTranslateX(chara.getTranslateX() + mvmnt.getX());
        }
        
        if (buttonPress.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            chara.setTranslateY(chara.getTranslateY() - mvmnt.getY());
        }
        
        if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            chara.setTranslateY(chara.getTranslateY() + mvmnt.getY());
        }
    }
}
