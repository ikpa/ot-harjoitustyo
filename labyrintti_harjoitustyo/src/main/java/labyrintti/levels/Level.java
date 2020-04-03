/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.levels;

import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.*;
import labyrintti.chars.*;
import labyrintti.object.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class Level {
    private Pane stg;
    private MainChara c;
    private ArrayList<Rectangle> walls;
    private ArrayList<Spike> spikes;
    private Goal g;
    private int startx;
    private int starty;
    
    public Level(int width, int height, int x, int y, MainChara ch,
            ArrayList<Rectangle> arr, ArrayList<Spike> sp, Goal go) {
        stg = new Pane();
        stg.setPrefSize(width, height);
        
        walls = arr;
        walls.forEach((r) -> {
            stg.getChildren().add(r);
        });
        
        spikes = sp;
        spikes.forEach((s) -> {
            stg.getChildren().add(s.getP());
        });
        
        startx = x;
        starty = y;
        
        g = go;
        stg.getChildren().add(g.getArea());
        
        c = ch;
        stg.getChildren().add(ch.getChara());
    }

    public Pane getStg() {
        return stg;
    }

    public MainChara getC() {
        return c;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }

    public Goal getG() {
        return g;
    }
    
    public void update(Map<KeyCode, Boolean> buttonPress) {
        c.move(buttonPress, walls);
        c.checkHit(spikes);
    }
    
    public void play(Map<KeyCode, Boolean> buttonPress) {
        c.getChara().setCenterX(startx);
        c.getChara().setCenterY(starty);
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                if (!c.isDead()) {
                    update(buttonPress);
                } else {
                    stop();
                }
            }
        }.start();
        
    }
    
}
