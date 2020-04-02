/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import labyrintti.chars.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class level {
    private Pane stg;
    private mainChara c;
    private ArrayList<Rectangle> walls;
    
    public level(int width, int height, mainChara ch,
            ArrayList<Rectangle> arr) {
        stg = new Pane();
        stg.setPrefSize(width, height);
        
        c = ch;
        stg.getChildren().add(ch.getChara());
        
        walls = arr;
        walls.forEach((r) -> {
            stg.getChildren().add(r);
        });
    }

    public Pane getStg() {
        return stg;
    }

    public mainChara getC() {
        return c;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }
    
    public void update(Map<KeyCode, Boolean> buttonPress) {
        c.move(buttonPress, walls);
    }
    
}
