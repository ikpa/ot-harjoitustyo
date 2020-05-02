/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

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
public class MainChara extends FreeMover{
    private int lives;
    private int points;
    private boolean dead;
    
    /**
     * Luo uuden MainChara-olion
     * @param x Alku-x-koordinaatti
     * @param y Alku-y-koordinaatti
     * @param r Säde
     * @param s Hahmon nopeus
     */
    public MainChara(int x, int y, int r, int s) {
        super(x, y, r, s);
        getCircle().setFill(Color.PURPLE);
        lives = 3;
        dead = false;
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
     * Tarkistaa, voiko pelihahmo kerätä tietyn esineen
     * @param i Esine, joka tarkistetaan
     * @return true jos voi kerätä, false jos ei voi
     */
    public boolean get(Item i) {
        Shape sec = Shape.intersect(getCircle(), i.getCircle());
        return sec.getBoundsInLocal().getWidth() >= 10;
    }
    
    /**
     * Tarkistaa kaikki osumat kaikkien Arrayn Hostile-olioiden kanssa ja poistaa elämän tarvittaessa. Hahmo merkitään kuolleeksi jos elämät loppuvat.
     * @param spikes Array, jossa tarkistettavat Hostile-oliot
     */
    public void excecuteHit(ArrayList<Spike> spikes) {
        if (checkHit(spikes)) {
            reset();
            removeLife();
        }
        
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
            this.moveUP(1);
        }
        
        if (buttonPress.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)
                && allowed.get(1)) {
            this.moveRIGHT(1);
        }
        
        if (buttonPress.getOrDefault(KeyCode.DOWN, Boolean.FALSE)
                && allowed.get(2)) {
            this.moveDOWN(1);
        }
        
        if (buttonPress.getOrDefault(KeyCode.LEFT, Boolean.FALSE)
                && allowed.get(3)) {
            this.moveLEFT(1);
        }
    }
    
    /**
     * Palauttaa hahmon koordinaatit ennalleen
     */
    public void reset() {
        getCircle().setTranslateX(0);
        getCircle().setTranslateY(0);
    }
}
