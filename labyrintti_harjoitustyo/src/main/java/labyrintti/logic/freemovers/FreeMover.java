/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import labyrintti.logic.object.Spike;
/**
 *
 * @author ikpa
 */
public class FreeMover {
    private Circle circle;
    private double speed;
    private boolean hit;
    
    public FreeMover(int x, int y, int r, double s) {
        circle = new Circle(x, y, r);
        speed = s;   
    }
    
    public Circle getCircle() {
        return circle;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    /**
     * Tarkistaa, onko pelihahmo osunut tiettyyn Shape-olioon
     * @param s Shape, joka tarkistetaan
     * @return true jos osuu, false jos ei osu
     */
    public boolean collision(Shape s) {
        Shape sec = Shape.intersect(s, circle);
        return sec.getBoundsInLocal().getWidth() > 1;
    }
    
    /**
     * Tarkistaa kaikki osumat kaikkien Arrayn Hostile-olioiden kanssa ja poistaa elämän tarvittaessa. Hahmo merkitään kuolleeksi jos elämät loppuvat.
     * @param spikes Array, jossa tarkistettavat Spike-oliot
     */
    public boolean checkHit(ArrayList<Spike> spikes) {
        boolean hit = false;
        
        for (Spike s: spikes) {
            if (collision(s.getPolygon())) {
                hit = true;
                setHit(true);
            }
        }
        
        return hit;
        
    }
    
    public double intersectXLoc(Shape shape) {
        Shape sec = Shape.intersect(shape, circle);
        double xloc = sec.getBoundsInLocal().getCenterX()
                        - (circle.getTranslateX() + circle.getCenterX());
        return xloc;
    }
    
    public double intersectYLoc(Shape shape, double voffset) {
        Shape sec = Shape.intersect(shape, circle);
        double yloc = sec.getBoundsInLocal().getCenterY()
                        - (circle.getTranslateY() + circle.getCenterY()) - voffset;
        return yloc;
    }
    
    /**
     * Tarkistaa, mihin suuntiin pelihahmo voi liikkua seinien perusteella
     * @param walls Tarkistettavat seinät
     * @param voffset Vaaditaan koordinaattien korjaamiseen, jos peliruudussa on infopalkki
     * @return Array, joka sisältää sallitut suunnat. Indeksi 0 ylös, 1 oikea, 2 alas, 3 vasen; true sallittu suunta, false kielletty suunta.
     */
    public ArrayList<Boolean> allowedDirections(ArrayList<Rectangle> walls, double voffset) {
        ArrayList<Boolean> dirs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dirs.add(Boolean.TRUE);
        }
        
        for (Rectangle r: walls) {
            if (collision(r)) {
                double xloc = intersectXLoc(r);
                double yloc = intersectYLoc(r, voffset);
                
                
                if (yloc < -8) {
                    dirs.set(0, Boolean.FALSE);
                }
                
                if (xloc > 1) {
                    dirs.set(1, Boolean.FALSE);
                }
                
                if (yloc > 6) {
                    dirs.set(2, Boolean.FALSE);
                }
                
                if (xloc < -7) {
                    dirs.set(3, Boolean.FALSE);
                }
            }
        }
        
        return dirs;
    }
    
    /**
     * Liikuttaa hahmoa ylös
     */
    public void moveUP(double x) {
        circle.setTranslateY(circle.getTranslateY() - x * speed);
    }
    
    /**
     * Liikuttaa hahmoa alas
     */
    public void moveDOWN(double x) {
        circle.setTranslateY(circle.getTranslateY() + x * speed);
    }
    
    /**
     * Liikuttaa hahmoa oikealle
     */
    public void moveRIGHT(double x) {
        circle.setTranslateX(circle.getTranslateX() + x * speed);
    }
    
    /**
     * Liikuttaa hahmoa vasemmalle
     */
    public void moveLEFT(double x) {
        circle.setTranslateX(circle.getTranslateX() - x * speed);
    }
    
    /**
     * Palauttaa hahmon koordinaatit ennalleen
     */
    public void reset() {
        getCircle().setTranslateX(0);
        getCircle().setTranslateY(0);
    }
}
