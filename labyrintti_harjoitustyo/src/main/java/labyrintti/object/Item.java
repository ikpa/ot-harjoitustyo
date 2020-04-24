/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import javafx.scene.shape.*;
import javafx.scene.paint.*;
/**
 *
 * @author ikpa
 */
public class Item {
    private Circle circle;
    private Integer type;
    
    public Item(int x, int y, int t) {
        circle = new Circle(x, y, 5);
        type = t;
        
        if (type == 0) {
            circle.setFill(Color.PURPLE);
        }
        
        if (type == 1) {
            circle.setFill(Color.YELLOW);
        }
    }

    public Circle getCircle() {
        return circle;
    }

    public int getType() {
        return type;
    }
    
    
}
