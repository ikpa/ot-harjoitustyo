/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
/**
 *
 * @author ikpa
 */
public class Spike {
    private Polygon p;
    
    public Spike(double x, double y) {
        p = new Polygon();
        p.getPoints().addAll(new Double[]{
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
    }

    public Polygon getP() {
        return p;
    }
}