/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import java.util.ArrayList;
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
    
    public FreeMover(int x, int y, int r, double s) {
        circle = new Circle(x, y, r);
        speed = s;
    }
    
    public Circle getCircle() {
        return circle;
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
            }
        }
        
        return hit;
        
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
        
        Boolean up = true;
        Boolean down = true;
        Boolean left = true;
        Boolean right = true;
        
        for (Rectangle r: walls) {
            if (collision(r)) {
                Shape sec = Shape.intersect(r, circle);
                double xloc = sec.getBoundsInLocal().getCenterX()
                        - (circle.getTranslateX() + circle.getCenterX());
                double yloc = sec.getBoundsInLocal().getCenterY()
                        - (circle.getTranslateY() + circle.getCenterY()) - voffset;
                
                
                if (yloc < -8 && up) {
                    dirs.add(0, Boolean.FALSE);
                    up = false;
                }
                
                if (xloc > 1 && right) {
                    dirs.add(1, Boolean.FALSE);
                    right = false;
                }
                
                if (yloc > 6 && down) {
                    dirs.add(2, Boolean.FALSE);
                    down = false;
                }
                
                if (xloc < -7 && left) {
                    dirs.add(3, Boolean.FALSE);
                    left = false;
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
    
}
