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
    private double linecoor;
    private double diffcoor1;
    private double diffcoor2;
    private double speed;
    
    public MovingSpike(int x, int y, Boolean ver, int s, int endcoor) {
        super(x, y);
        vert = ver;
        diffcoor2 = endcoor;
        speed = s;
        
        if (vert) {
            linecoor = x;
            diffcoor1 = y;
        } else {
            linecoor = y;
            diffcoor1 = x;
        }
        
        dir = diffcoor2 - diffcoor1 > 0;
        finaldir = diffcoor2 - diffcoor1 > 0;
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
            
            if ((p.getTranslateY() >= diffcoor2 - diffcoor1 && finaldir) ||
                    (p.getTranslateY() >= 0 && !finaldir)) {
                dir = false;
                
            }
        } else {
            moveUP();
            
            if ((p.getTranslateY() <= 0 && finaldir) ||
                    (p.getTranslateY() <= diffcoor2 - diffcoor1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    public void horMove() {
        if (dir) {
            moveRIGHT();
            
            if ((p.getTranslateX() >= diffcoor2 - diffcoor1 && finaldir) ||
                    (p.getTranslateX() >= 0 && !finaldir)) {
                dir = false;
            }
        } else {
            moveLEFT();
            
            if ((p.getTranslateX() <= 0 && finaldir) ||
                    (p.getTranslateX() <= diffcoor2 - diffcoor1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    public void moveUP() {
        p.setTranslateY(p.getTranslateY() - speed);
    }
    
    public void moveDOWN() {
        p.setTranslateY(p.getTranslateY() + speed);
    }
    
    public void moveRIGHT() {
        p.setTranslateX(p.getTranslateX() + speed);
    }
    
    public void moveLEFT() {
        p.setTranslateX(p.getTranslateX() - speed);
    }
}
