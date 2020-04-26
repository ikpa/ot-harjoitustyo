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
        
        walls.add(wallconst.hWall(155, 80, 285));
        walls.add(wallconst.hWall(475, 80, 455));
        walls.addAll(wallconst.upPocket(440, 80, 35, 35));
        walls.add(wallconst.hWall(155, 115, 180));
        walls.add(wallconst.vWall(930, 80, 40));
        walls.add(wallconst.hWall(365, 115, 565));
        walls.add(wallconst.vWall(150, 80, 40));
        
        walls.add(wallconst.vWall(330, 120, 190));
        walls.add(wallconst.vWall(365, 115, 50));
        walls.addAll(wallconst.rightPocket(365, 165, 35, 35));
        walls.add(wallconst.vWall(365, 200, 295));
        walls.add(wallconst.vWall(330, 355, 180));
        walls.addAll(wallconst.leftPocket(335, 315, 40, 35));
        walls.addAll(wallconst.downPocket(335, 535, 30, 35));
        
        walls.addAll(wallconst.downPocket(633, 535, 35, 35));
        walls.add(wallconst.hWall(365, 530, 268));
        walls.add(wallconst.hWall(668, 530, 262));
        walls.add(wallconst.hWall(365, 495, 100));
        walls.addAll(wallconst.upPocket(465, 495, 370, 80));
        walls.add(wallconst.hWall(500, 495, 300));
        walls.add(wallconst.hWall(835, 495, 135));
        
        walls.add(wallconst.vWall(965, 495, 425));
        walls.add(wallconst.vWall(925, 535, 75));
        walls.add(wallconst.vWall(925, 655, 90));
        walls.add(wallconst.vWall(925, 790, 90));
        walls.addAll(wallconst.leftPocket(930, 615, 40, 35));
        walls.addAll(wallconst.leftPocket(930, 750, 40, 35));
        walls.addAll(wallconst.downPocket(930, 920, 35, 35));
        
        walls.add(wallconst.hWall(135, 915, 795));
        walls.add(wallconst.hWall(785, 875, 140));
        walls.addAll(wallconst.upPocket(750, 875, 35, 35));
        walls.add(wallconst.hWall(635, 875, 115));
        walls.addAll(wallconst.upPocket(600, 875, 35, 35));
        walls.add(wallconst.hWall(485, 875, 115));
        walls.addAll(wallconst.upPocket(450, 875, 35, 35));
        walls.add(wallconst.hWall(385, 875, 65));
        walls.addAll(wallconst.upPocket(350, 875, 35, 35));
        walls.add(wallconst.hWall(235, 875, 115));
        walls.addAll(wallconst.upPocket(200, 875, 35, 35));
        walls.add(wallconst.hWall(135, 875, 65));
        
        walls.add(wallconst.vWall(135, 920, 20));
        walls.addAll(wallconst.leftPocket(140, 855, 110, 85));
        walls.add(wallconst.vWall(135, 855, 20));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new MovingSpike(700, 100, false, 2, 200));
        spikes.add(new MovingSpike(350, 115, true, 2, 500));
        spikes.add(new MovingSpike(640, 515, false, 1, 350));
        spikes.add(new MovingSpike(660, 515, false, 1, 950));
        spikes.add(new MovingSpike(950, 530, true, 2, 900));
        spikes.add(new MovingSpike(935, 900, false, 2, 585));
        spikes.add(new MovingSpike(150, 900, false, 3, 565));
        
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(160, 100, 1));
        items.add(new Item(170, 100, 1));
        items.add(new Item(180, 100, 1));
        items.add(new Item(350, 555, 1));
        
        items.add(new Item(560, 445, 1));
        items.add(new Item(650, 445, 1));
        items.add(new Item(740, 445, 1));
        
        items.add(new Item(650, 555, 0));
        
        items.add(new Item(947, 940, 0));
        
        items.add(new Item(80, 900, 1));
        items.add(new Item(105, 900, 1));
        items.add(new Item(130, 900, 1));
        
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
