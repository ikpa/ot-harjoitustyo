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
    
    public Level level1() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(200, 300, 700));
        walls.add(wc.vWall(900, 300, 180));
        walls.add(wc.hWall(200, 475, 700));
        
        walls.add(wc.hWall(140, 360, 60));
        walls.add(wc.vWall(195, 300, 65));
        walls.add(wc.vWall(195, 415, 65));
        walls.add(wc.hWall(140, 415, 60));
        walls.add(wc.vWall(135, 360, 60));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(540, 365, 1));
        items.add(new Item(580, 365, 1));
        items.add(new Item(500, 365, 1));
        items.add(new Item(540, 405, 1));
        items.add(new Item(580, 405, 1));
        items.add(new Item(500, 405, 1));
        
        //170, 390, 15
        Goal g = new Goal(170, 390, 15);
        
        Level lvl = new Level(1000, 1000, 875, 390, walls, spikes, items, g);
        return lvl;
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
        
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(650, 300, 0));
        items.add(new Item(500, 100, 1));
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 500, 500, walls, spikes, items, g);
        return lvl;
    }
    
    public Level testlvl2() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 700, 200));
        walls.add(wc.vWall(600, 705, 200));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new Spike(200, 800));
        
        ArrayList<Item> items = new ArrayList<>();
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 200, 200, walls, spikes, items, g);
        return lvl;
    }
    
    public ArrayList<Level> allLvls() {
        ArrayList<Level> arr = new ArrayList<>();
        arr.add(level1());
        arr.add(testlvl());
        arr.add(testlvl2());
        
        return arr;
    }
    
}
