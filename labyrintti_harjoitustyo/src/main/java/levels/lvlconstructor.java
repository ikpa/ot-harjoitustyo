/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import javafx.scene.shape.Rectangle;
import labyrintti.chars.mainChara;
import java.util.ArrayList;
import object.wallConstructor;
/**
 *
 * @author ikpa
 */
public class lvlconstructor {
    private wallConstructor wc;
    
    public lvlconstructor() {
        wc = new wallConstructor();
    }
    
    public level testlvl() {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(wc.hWall(600, 200, 200));
        walls.add(wc.vWall(600, 205, 200));

        mainChara c = new mainChara(500, 500, 10, 1, 1);

        level lvl = new level(1000, 1000, c, walls);
        return lvl;
    }
}
