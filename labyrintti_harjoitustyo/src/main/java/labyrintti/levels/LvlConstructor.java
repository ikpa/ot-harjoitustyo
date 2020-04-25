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
    private WallConstructor wallconst;
    
    public LvlConstructor() {
        wallconst = new WallConstructor();
    }
    
    public Level level1() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wallconst.hWall(200, 300, 700));
        walls.add(wallconst.vWall(900, 300, 180));
        walls.add(wallconst.hWall(200, 475, 700));
        
        walls.add(wallconst.hWall(140, 360, 60));
        walls.add(wallconst.vWall(195, 300, 65));
        walls.add(wallconst.vWall(195, 415, 65));
        walls.add(wallconst.hWall(140, 415, 60));
        walls.add(wallconst.vWall(135, 360, 60));
        
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
    
    public Level level2() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wallconst.hWall(250, 50, 400));
        walls.add(wallconst.vWall(645, 55, 900));
        walls.add(wallconst.hWall(250, 950, 400));
        walls.add(wallconst.vWall(250, 55, 900));

        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new MovingSpike(635, 330, false, 2, 260));
        spikes.add(new MovingSpike(260, 350, false, 2, 635));
        
        spikes.add(new MovingSpike(635, 480, false, 2, 260));
        spikes.add(new MovingSpike(260, 500, false, 2, 635));
        
        spikes.add(new MovingSpike(635, 630, false, 2, 260));
        spikes.add(new MovingSpike(260, 650, false, 2, 635));
        
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(260, 330, 1));
        items.add(new Item(635, 330, 1));
        
        items.add(new Item(260, 490, 1));
        items.add(new Item(635, 490, 1));
        
        items.add(new Item(260, 640, 1));
        items.add(new Item(635, 640, 1));
        
        items.add(new Item(450, 490, 0));
        
        Goal g = new Goal(450, 910, 15);
        
        Level lvl = new Level(1000, 1000, 450, 80, walls, spikes, items, g);
        return lvl;
    }
    
    public Level level3() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        //740
        walls.add(wallconst.hWall(190, 80, 250));
        walls.add(wallconst.hWall(475, 80, 455));
        walls.add(wallconst.hWall(190, 115, 140));
        walls.add(wallconst.hWall(365, 115, 565));
        
        walls.add(wallconst.vWall(330, 115, 200));
        walls.add(wallconst.vWall(365, 115, 50));
        walls.add(wallconst.vWall(365, 200, 295));
        walls.add(wallconst.vWall(330, 350, 210));
        
        walls.add(wallconst.hWall(365, 530, 565));
        walls.add(wallconst.hWall(365, 495, 100));
        walls.add(wallconst.hWall(500, 495, 300));
        walls.add(wallconst.hWall(835, 495, 130));
        
        walls.add(wallconst.vWall(965, 530, 385));
        walls.add(wallconst.hWall(135, 915, 835));
        
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new MovingSpike(700, 100, false, 2, 200));
        spikes.add(new MovingSpike(350, 115, true, 2, 500));
        spikes.add(new MovingSpike(640, 515, false, 1, 350));
        spikes.add(new MovingSpike(660, 515, false, 1, 950));
        spikes.add(new MovingSpike(950, 530, true, 2, 900));
        spikes.add(new MovingSpike(935, 900, false, 1, 585));
        spikes.add(new MovingSpike(150, 900, false, 1, 565));
        
        ArrayList<Item> items = new ArrayList<>();
        
        Goal g = new Goal(50, 900, 15);
        
        Level lvl = new Level(1000, 1000, 900, 100, walls, spikes, items, g);
        return lvl;
    }
    
    public Level testlvl() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wallconst.hWall(600, 200, 200));
        walls.add(wallconst.vWall(795, 205, 200));
        walls.add(wallconst.vWall(600, 205, 200));
        
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
        walls.add(wallconst.hWall(600, 700, 200));
        walls.add(wallconst.vWall(600, 705, 200));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new Spike(200, 800));
        
        ArrayList<Item> items = new ArrayList<>();
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 200, 200, walls, spikes, items, g);
        return lvl;
    }
    
    public ArrayList<Level> allLvls() {
        ArrayList<Level> arr = new ArrayList<>();
        arr.add(testlvl());
        arr.add(testlvl2());
        
        arr.add(level1());
        arr.add(level2());
        arr.add(level3());
        
        return arr;
    }
    
}
