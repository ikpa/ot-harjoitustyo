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
