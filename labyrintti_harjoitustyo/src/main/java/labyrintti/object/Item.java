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
    private Circle s;
    private Integer type;
    
    public Item(int x, int y, int t) {
        s = new Circle(x, y, 5);
        type = t;
        
        if (type == 0) {
            s.setFill(Color.PURPLE);
        }
        
        if (type == 1) {
            s.setFill(Color.YELLOW);
        }
    }

    public Circle getS() {
        return s;
    }

    public Integer getType() {
        return type;
    }
    
    
}
