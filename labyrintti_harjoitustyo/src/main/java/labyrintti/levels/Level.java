/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.levels;

import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import labyrintti.chars.*;
import labyrintti.object.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class Level {
    private Pane stg;
    private ArrayList<Rectangle> walls;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> items;
    private Goal goal;
    private int startx;
    private int starty;
    
    public Level(int width, int height, int x, int y,
             ArrayList<Rectangle> arr, ArrayList<Spike> sp, ArrayList<Item> i,
             Goal go) {
        stg = new Pane();
        stg.setPrefSize(width, height);
        
        walls = arr;
        walls.forEach((r) -> {
            stg.getChildren().add(r);
        });
        
        spikes = sp;
        if (!(spikes.isEmpty())) {
            spikes.forEach((s) -> {
                stg.getChildren().add(s.getPolygon());
            });
        }
        
        items = i;
        if (!(items.isEmpty())) {
            items.forEach(s -> {
                stg.getChildren().add(s.getCircle());
            });
        }
        
        startx = x;
        starty = y;
        
        goal = go;
        stg.getChildren().add(goal.getArea());
    }

    public Pane getStg() {
        return stg;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }

    public Goal getGoal() {
        return goal;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }
    
    public void initialise(MainChara chara) {
        chara.reset();
        chara.getCircle().setCenterX(startx);
        chara.getCircle().setCenterY(starty);
        stg.getChildren().add(chara.getCircle());
    }
    
    public void removeItems(ArrayList<Integer> ids) {
        if (!(ids.isEmpty())) {
            ids.forEach(i -> {
                Item item = items.get(i);
                items.remove(item);
                stg.getChildren().remove(item.getCircle());
            });
        }
    }
    
    public void update(Map<KeyCode, Boolean> buttonPress, MainChara chara, double voffset) {
        chara.move(buttonPress, walls, voffset);
        
        if (!(spikes.isEmpty())) {
            chara.checkHit(spikes);
        }
        
        if (!(items.isEmpty())) {
            ArrayList<Integer> ids = chara.checkGet(items);
            
            removeItems(ids);
        }
        
        spikes.forEach((s) -> {
            s.move();
        });
    }
     
}
