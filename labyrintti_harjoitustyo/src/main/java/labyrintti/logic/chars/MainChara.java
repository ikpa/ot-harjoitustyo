/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.chars;

import labyrintti.logic.*;
import labyrintti.logic.object.Item;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;

import java.util.*;
import labyrintti.logic.object.Spike;
/**
 * Pelihahmoa kuvaava olio
 * @author ikpa
 */
public class MainChara {
    private Circle circle;
    private Point2D mvmnt;
    private int lives;
    private int points;
    private boolean dead;
    
    /**
     * Luo uuden MainChara-olion
     * @param x Alku-x-koordinaatti
     * @param y Alku-y-koordinaatti
     * @param r Säde
     * @param mvmnty Nopeus y-suunnassa
     * @param mvmntx Nopeus x-suunnassa
     */
    public MainChara(int x, int y, int r, int mvmnty, int mvmntx) {
        circle = new Circle(x, y, r, Color.PURPLE);
        mvmnt = new Point2D(mvmntx, mvmnty);
        lives = 3;
        dead = false;
    }
    
    public Circle getCircle() {
        return circle;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }
    
    /**
     * Lisää tietyn määrän pisteitä hahmolle
     * @param i Pistemäärä
     */
    public void addPoints(int i) {
        points = points + i;
    }

    public boolean isDead() {
        return dead;
    }
    
    /**
     * Lisää yhden elämän
     */
    public void addLife() {
        lives++;
    }
    
    /**
     * Poistaa yhden elämän
     */
    public void removeLife() {
        lives--;
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
     * Tarkistaa, voiko pelihahmo kerätä tietyn esineen
     * @param i Esine, joka tarkistetaan
     * @return true jos voi kerätä, false jos ei voi
     */
    public boolean get(Item i) {
        Shape sec = Shape.intersect(circle, i.getCircle());
        return sec.getBoundsInLocal().getWidth() >= 10;
    }
    
    /**
     * Tarkistaa kaikki osumat kaikkien Arrayn Hostile-olioiden kanssa ja poistaa elämän tarvittaessa. Hahmo merkitään kuolleeksi jos elämät loppuvat.
     * @param hostiles Array, jossa tarkistettavat Hostile-oliot
     */
    public void checkHit(ArrayList<Hostile> hostiles) {
        hostiles.forEach(s -> {
            if (collision(s.getShape())) {
                removeLife();
                circle.setTranslateX(0);
                circle.setTranslateY(0);
            }
        });
        
        if (lives < 0) {
            dead = true;
        }
    }
    
    /**
     * Tarkistaa kaikki Arrayn esineet, ja kerää ne tarvittaessa. Lisää kyseisille esineille ominaiset vaikutukset hahmolle, ja palauttaa kerättyjen esineiden indeksit.
     * @param items Array, jossa tarkistettavat esineet
     * @return Array, jossa kerättyjen esineiden indeksit
     */
    public ArrayList<Integer> checkGet(ArrayList<Item> items) {
        ArrayList<Integer> ids = new ArrayList<>();
        items.forEach(item -> {
            if (get(item)) {
                if (item.getType() == 0) {
                    addLife();
                }
                
                if (item.getType() == 1) {
                    addPoints(50);
                }
                
                ids.add(items.indexOf(item));
            }
        });
        
        return ids;
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
     * Liikuttaa hahmoa pelaajan napinpainallusten ja seinien perusteella
     * @param buttonPress Map, joka sisältää pelaajan napinpainallukset
     * @param walls Tarkistettavat seinät
     * @param voffset Koordinaattien korjaamiseen vaadittava luku
     */
    public void move(Map<KeyCode, Boolean> buttonPress,
            ArrayList<Rectangle> walls, double voffset) {
        ArrayList<Boolean> allowed = allowedDirections(walls, voffset);
        
        if (buttonPress.getOrDefault(KeyCode.UP, Boolean.FALSE)
                && allowed.get(0)) {
            this.moveUP();
        }
        
        if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)
                && allowed.get(1)) {
            this.moveRIGHT();
        }
        
        if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)
                && allowed.get(2)) {
            this.moveDOWN();
        }
        
        if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)
                && allowed.get(3)) {
            this.moveLEFT();
        }
    }
    
    /**
     * Liikuttaa hahmoa ylös
     */
    public void moveUP() {
        circle.setTranslateY(circle.getTranslateY() - mvmnt.getY());
    }
    
    /**
     * Liikuttaa hahmoa alas
     */
    public void moveDOWN() {
        circle.setTranslateY(circle.getTranslateY() + mvmnt.getY());
    }
    
    /**
     * Liikuttaa hahmoa oikealle
     */
    public void moveRIGHT() {
        circle.setTranslateX(circle.getTranslateX() + mvmnt.getX());
    }
    
    /**
     * Liikuttaa hahmoa vasemmalle
     */
    public void moveLEFT() {
        circle.setTranslateX(circle.getTranslateX() - mvmnt.getX());
    }
    
    /**
     * Palauttaa hahmon koordinaatit ennalleen
     */
    public void reset() {
        circle.setTranslateX(0);
        circle.setTranslateY(0);
    }
}
