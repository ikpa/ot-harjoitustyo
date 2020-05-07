/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import javafx.scene.paint.*;
import java.util.*;
import javafx.scene.shape.*;
/**
 * Pelihahmoa seuraavaa vihollista kuvaava luokka
 * @author ikpa
 */
public class Enemy extends FreeMover {
    private double aggrorange;
    
    /**
     * Luo uuden Enemy-olion
     * @param x Olion alku-x-koordinaatti
     * @param y Olion alku-y-koordinaatti
     * @param radius Olion säde
     * @param aggro Pelihahmon joutuessa tämän säteen sisälle vihollinen ryhtyy
     * liikkumaan
     * @param speed Vihollisen nopeus
     */
    public Enemy(double x, double y, int radius, double aggro, double speed) {
        super(x, y, radius, speed, Color.RED);
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            x, y - (3 * radius / 5),
            x + radius, y - radius,
            x + (3 * radius / 5), y,
            x + radius, y + radius,
            x, y + (3 * radius / 5),
            x - radius, y + radius,
            x - (3 * radius / 5), y,
            x - radius, y - radius
        });
        
        Shape newshape = Shape.union(polygon, getArea());
        newshape.setFill(Color.RED);
        
        setArea(newshape);
        
        aggrorange = aggro;
    }
    
    /**
     * Asettaa oliolle uuden paikan
     * @param x Uusi x-koordinaatti
     * @param y Uusi-y-koordinaatti
     */
    @Override
    public void setLocation(double x, double y) {
        double r = getRadius();
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            x, y - (3 * r / 5),
            x + r, y - r,
            x + (3 * r / 5), y,
            x + r, y + r,
            x, y + (3 * r / 5),
            x - r, y + r,
            x - (3 * r / 5), y,
            x - r, y - r
        });
        
        Circle newcircle = new Circle(x, y, r);
        
        Shape newshape = Shape.union(polygon, newcircle);
        newshape.setFill(Color.RED);
        
        setArea(newshape);
    }
   
    /**
     * Laskee x-akselin suuntaisen etäisyyden tiettyyn MainChara-olioon
     * @param chara Tutkittava pelihahmo
     * @return X-akselin suuntainen etäisyys
     */
    public double xDistance(MainChara chara) {
        double enemyx = getCenterX() + getArea().getTranslateX();
        double charax = chara.getCenterX() + chara.getArea().getTranslateX();
        
        return charax - enemyx;
    }
    
    /**
     * Laskee y-akselin suuntaisen etäisyyden tiettyyn MainChara-olioon
     * @param chara Tutkittava pelihahmo
     * @return Y-akselin suuntainen etäisyys
     */
    public double yDistance(MainChara chara) {
        double enemyy = getCenterY() + getArea().getTranslateY();
        double charay = chara.getCenterY() + chara.getArea().getTranslateY();
        
        return charay - enemyy;
    }
    
    /**
     * Laskee kokonaisetäisyyden tiettyyn MainChara-olioon
     * @param chara Tutkittava pelihahmo
     * @return Kokonaisetäisyys
     */
    public double totalDistance(MainChara chara) {
        double xdistance = xDistance(chara);
        double ydistance = yDistance(chara);
        double value = Math.pow(xdistance, 2) + Math.pow(ydistance, 2);
        
        return Math.sqrt(value);
    }
    
    /**
     * Tarkistaa kaikki arrayiden seinät ja viholliset, ja palauttaa sallitut
     * kulkusuunnat (ks. allowedDirections ja allowedDirectionsForAll)
     * @param walls Tarkistettavat seinät
     * @param enemies Tarkistettavat muut viholliset
     * @param voffset Koordinaattien korjaustermi
     * @return ks. allowedDirections ja allowedDirectionsForAll
     */
    public ArrayList<Boolean> enemyAllowedDirections(ArrayList<Rectangle> walls, 
            ArrayList<Enemy> enemies, double voffset) {
        ArrayList<Boolean> dirs = allowedDirectionsForAll(walls, voffset);
        
        for (Enemy e: enemies) {
            if (e.equals(this) || e.isHit()) {
                continue;
            }
            
            if (!collision(e.getArea())) {
                continue;
            }
            
            allowedDirections(dirs, e.getArea(), voffset);
        }
        
        return dirs;
    }
    
    /**
     * Liikuttaa vihollishahmoa seinien, pelihahmon sekä muiden vihollisten 
     * perusteella
     * @param walls Tutkittavat seinät
     * @param chara Tutkittava pelihahmo
     * @param enemies Tutkittavat viholliset
     * @param voffset Koordinaattien korjaustermi
     */
    public void move(ArrayList<Rectangle> walls, MainChara chara,
            ArrayList<Enemy> enemies, double voffset) {
        double xdist = xDistance(chara);
        double ydist = yDistance(chara);
        double totdist = totalDistance(chara);
        
        if (totdist > aggrorange) {
            return;
        }
        
        ArrayList<Boolean> allowed = enemyAllowedDirections(walls, enemies, voffset);
        
        double sine = ydist / totdist;
        double cosine = xdist / totdist;
        
        if (allowed.get(0) && ydist < 0) {
            moveUP(-sine);
        }
        
        if (allowed.get(1) && xdist > 0) {
            moveRIGHT(cosine);
        }
        
        if (allowed.get(2) && ydist > 0) {
            moveDOWN(sine);
        }
        
        if (allowed.get(3) && xdist < 0) {
            moveLEFT(-cosine);
        }
    }
    
}
