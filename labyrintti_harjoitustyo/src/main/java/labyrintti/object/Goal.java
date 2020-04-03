/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import labyrintti.chars.MainChara;
/**
 *
 * @author ikpa
 */
public class Goal {
    private Circle area;
    
    public Goal(int x, int y, int r) {
        area = new Circle(x, y, r, Color.FORESTGREEN);
    }
    
    public boolean inGoal(MainChara c) {
        Shape sec = Shape.intersect(area, c.getChara());
        double x = sec.getBoundsInLocal().getWidth();
        
        return x >= c.getChara().getRadius() * 2;
    }

    public Circle getArea() {
        return area;
    }
    
    
}