/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import labyrintti.logic.freemovers.*;
import labyrintti.logic.object.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class Door {
    private Rectangle door;
    private ArrayList<Enemy> enemies;
    private Boolean open;
    
    public Door(int x, int y, int l, boolean vert, ArrayList<Enemy> en) {
        WallConstructor wc = new WallConstructor();
        
        if (vert) {
            door = wc.vWall(x, y, l);
        } else {
            door = wc.hWall(x, y, l);
        }
        
        door.setFill(Color.RED);
        
        enemies = en;
        open = false;
    }

    public Boolean isOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Rectangle getDoor() {
        return door;
    }
    
    public void setColor(Color c) {
        door.setFill(c);
    }
    
    public void executeOpen() {
        int number = 0;
        for (Enemy e: enemies) {
            if (e.isHit()) {
                number++;
            }
        }
        
        if (number == enemies.size()) {
            open = true;
        }
        
    }
}
