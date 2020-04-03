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
    private MainChara c;
    
    public LvlConstructor() {
        wc = new WallConstructor();
        c = new MainChara(0, 0, 10, 1, 1);
    }
    
    public Level testlvl() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 200, 200));
        walls.add(wc.vWall(600, 205, 200));
        
        ArrayList<Spike> spikes = new ArrayList<>();
        spikes.add(new Spike(700, 700));
        
        c.getChara().setCenterX(500);
        c.getChara().setCenterY(500);
        
        Goal g = new Goal(100, 100, 15);

        Level lvl = new Level(1000, 1000, 500, 500, c, walls, spikes, g);
        return lvl;
    }
    

    public MainChara getC() {
        return c;
    }
    
    
}
