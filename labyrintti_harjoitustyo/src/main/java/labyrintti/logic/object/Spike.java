/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import labyrintti.logic.*;
/**
 * Piikkieste, joka vie pelihahmolta elämän ja vie sen tason alkuun.
 * @author ikpa
 */
public class Spike extends Hostile {
    
    /**
     * Luo uuden Spike-olion
     * @param x piikkiesteen x-koordinaatti
     * @param y piikkiesteen y-koordinaatti
     */
    public Spike(double x, double y) {
        super();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            x, y - 10,
            x + 2, y - 5,
            x + 10, y - 10,
            x + 5, y - 2,
            x + 10, y,
            x + 5, y + 2,
            x + 10, y + 10,
            x + 2, y + 5,
            x, y + 10,
            x - 2, y + 5,
            x - 10, y + 10,
            x - 5, y + 2,
            x - 10, y,
            x - 5, y - 2,
            x - 10, y - 10,
            x - 2, y - 5
        });
        
        setShape(polygon);
        
    }
    
    /**
     * Overridatava metodi. Ei tee tässä luokassa mitään.
     */
    @Override
    public void move() {
        
    }
}
