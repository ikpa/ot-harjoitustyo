/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import javafx.geometry.*;
import javafx.scene.shape.*;
import java.util.*;
/**
 *
 * @author ikpa
 */
public class WallConstructor {
    private final int width = 5;
    
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
    
    public ArrayList<Rectangle> upPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x, y - pheight - width, pwidth));
        walls.add(vWall(x - width, y - pheight - width, pheight + width));
        walls.add(vWall(x + pwidth, y - pheight - width, pheight + width));
        
        return walls;
    }
    
    public ArrayList<Rectangle> downPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(vWall(x + pwidth, y, pheight + width));
        walls.add(hWall(x, y + pheight, pwidth));
        walls.add(vWall(x - width, y, pheight + width));
        
        return walls;
    }
    
    public ArrayList<Rectangle> leftPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x - pwidth - width, y - width, pwidth + width));
        walls.add(hWall(x - pwidth - width, y + pheight, pwidth + width));
        walls.add(vWall(x - pwidth - width, y, pheight));
        
        return walls;
    }
    
    public ArrayList<Rectangle> rightPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x, y - width, pwidth + width));
        walls.add(vWall(x + pwidth, y, pheight));
        walls.add(hWall(x, y + pheight, pwidth + width));
        
        return walls;
    }
}
