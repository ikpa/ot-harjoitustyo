/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import javafx.scene.paint.*;
/**
 *
 * @author ikpa
 */
public class Enemy extends FreeMover {
    
    public Enemy(int x, int y, int r, double s) {
        super(x, y, r, s);
        getCircle().setFill(Color.RED);
    }
    
    
}
