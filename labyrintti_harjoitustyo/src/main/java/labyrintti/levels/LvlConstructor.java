/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.levels;

import javafx.scene.shape.Rectangle;
import labyrintti.chars.MainChara;
import java.util.ArrayList;
import labyrintti.object.*;
/**
 *
 * @author ikpa
 */
public class LvlConstructor {
    private WallConstructor wc;
    
    public LvlConstructor() {
        wc = new WallConstructor();
    }
    
    public Level testlvl() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 200, 200));
        walls.add(wc.vWall(795, 205, 200));
        walls.add(wc.vWall(600, 205, 200));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new Spike(700, 700));
        spikes.add(new MovingSpike(100, 200, true, 1, 900));
        spikes.add(new MovingSpike(100, 900, false, 2, 900));
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 500, 500, walls, spikes, g);
        return lvl;
    }
    
    public Level testlvl2() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 700, 200));
        walls.add(wc.vWall(600, 705, 200));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new Spike(200, 800));
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 200, 200, walls, spikes, g);
        return lvl;
    }
    
    public ArrayList<Level> allLvls() {
        ArrayList<Level> arr = new ArrayList<>();
        arr.add(testlvl());
        arr.add(testlvl2());
        
        return arr;
    }
    
}
