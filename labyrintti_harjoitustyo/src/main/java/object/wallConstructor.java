/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import javafx.geometry.*;
import javafx.scene.shape.*;
/**
 *
 * @author ikpa
 */
public class wallConstructor {
    private int width=5;
    public Rectangle vWall(int x, int y, int length) {
        Rectangle wall = new Rectangle();
        wall.setWidth(width);
        wall.setHeight(length);
        wall.setX(x);
        wall.setY(y);
        return wall;
    }
    
    public Rectangle hWall(int x, int y, int length) {
        Rectangle wall = new Rectangle();
        wall.setWidth(length);
        wall.setHeight(width);
        wall.setX(x);
        wall.setY(y);
        return wall;
    }
}
