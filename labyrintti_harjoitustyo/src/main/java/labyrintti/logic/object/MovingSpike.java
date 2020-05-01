/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import java.util.*;
/**
 * Edestakaisin liikkuva Spike-este. Perii Spike-luokan.
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
    
    /**
     * Luo uuden MovingSpike-olion.
     * @param x Piikin alku-x-koordinaatti
     * @param y Piikin alku-y-koordinaatti
     * @param ver Määrää, onko piikin liike vertikaalista vai horisontaalista
     * @param s Piikin nopeus
     * @param endcoordinate Koordinaatti, jossa piikki vaihtaa suuntaa
     */
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
    
    /**
     * Liikuttaa piikkiä konstruktorissa määriteltyjen parametrien perusteella.
     */
    @Override
    public void move() {
        if (vert) {
            verMove();
        } else {
            horMove();
        }
    }
    
    /**
     * Liikuttaa piikkiä vertikaalisesti, riippuen piikin sijainnista ja 
     * alku- ja loppukoordinaateista.
     */
    public void verMove() {
        if (dir) {
            moveDOWN();
            
            if ((shape.getTranslateY() >= diffcoordinate2 - diffcoordinate1 && finaldir) ||
                    (shape.getTranslateY() >= 0 && !finaldir)) {
                dir = false;
                
            }
        } else {
            moveUP();
            
            if ((shape.getTranslateY() <= 0 && finaldir) ||
                    (shape.getTranslateY() <= diffcoordinate2 - diffcoordinate1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    /**
     * Liikuttaa piikkiä horisontaalisesti riippuen piikin paikasta ja alku- ja
     * loppukoordinaateista.
     */
    public void horMove() {
        if (dir) {
            moveRIGHT();
            
            if ((shape.getTranslateX() >= diffcoordinate2 - diffcoordinate1 && finaldir) ||
                    (shape.getTranslateX() >= 0 && !finaldir)) {
                dir = false;
            }
        } else {
            moveLEFT();
            
            if ((shape.getTranslateX() <= 0 && finaldir) ||
                    (shape.getTranslateX() <= diffcoordinate2 - diffcoordinate1 && !finaldir)) {
                dir = true;
            }
        }
    }
    
    /**
     * Liikuttaa piikkiä ylöstpäin.
     */
    public void moveUP() {
        shape.setTranslateY(shape.getTranslateY() - speed);
    }
    
    /**
     * Liikuttaa piikkiä alaspäin.
     */
    public void moveDOWN() {
        shape.setTranslateY(shape.getTranslateY() + speed);
    }
    
    /**
     * Liikuttaa piikkiä oikealle.
     */
    public void moveRIGHT() {
        shape.setTranslateX(shape.getTranslateX() + speed);
    }
    
    /**
     * Liikuttaa piikkiä vasemmalle.
     */
    public void moveLEFT() {
        shape.setTranslateX(shape.getTranslateX() - speed);
    }
}
