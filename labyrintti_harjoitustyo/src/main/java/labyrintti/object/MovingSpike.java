/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.object;

import java.util.*;
/**
 *
 * @author ikpa
 */
public class MovingSpike extends Spike {
    private Boolean vert;
    private Boolean dir;
    private final Boolean finaldir;
    private double linecoordinate;
    private double diffcoordinate1;
    private double diffcoordinate2;
    private double speed;
    
    public MovingSpike(int x, int y, Boolean ver, int s, int endcoordinate) {
        super(x, y);
        vert = ver;
        diffcoordinate2 = endcoordinate;
        speed = s;
        
        if (vert) {
            linecoordinate = x;
            diffcoordinate1 = y;
        } else {
            linecoordinate = y;
            diffcoordinate1 = x;
        }
        
        dir = diffcoordinate2 - diffcoordinate1 > 0;
        finaldir = diffcoordinate2 - diffcoordinate1 > 0;
    }
    
    @Override
    public void move() {
        if (vert) {
            verMove();
        } else {
            horMove();
        }
    }
    
    public void verMove() {
        if (dir) {
            moveDOWN();
            
            if ((polygon.getTranslateY() >= diffcoordinate2 - diffcoordinate1 && finaldir) ||
                    (polygon.getTranslateY() >= 0 && !finaldir)) {
                dir = false;
                
            }
        } else {
            moveUP();
            
            if ((polygon.getTranslateY() <= 0 && finaldir) ||
                    (polygon.getTranslateY() <= diffcoordinate2 - diffcoordinate1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    public void horMove() {
        if (dir) {
            moveRIGHT();
            
            if ((polygon.getTranslateX() >= diffcoordinate2 - diffcoordinate1 && finaldir) ||
                    (polygon.getTranslateX() >= 0 && !finaldir)) {
                dir = false;
            }
        } else {
            moveLEFT();
            
            if ((polygon.getTranslateX() <= 0 && finaldir) ||
                    (polygon.getTranslateX() <= diffcoordinate2 - diffcoordinate1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    public void moveUP() {
        polygon.setTranslateY(polygon.getTranslateY() - speed);
    }
    
    public void moveDOWN() {
        polygon.setTranslateY(polygon.getTranslateY() + speed);
    }
    
    public void moveRIGHT() {
        polygon.setTranslateX(polygon.getTranslateX() + speed);
    }
    
    public void moveLEFT() {
        polygon.setTranslateX(polygon.getTranslateX() - speed);
    }
}
