/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import labyrintti.logic.object.Spike;
/**
 * Tämä luokka kuvaa vapaasti liikkuvaa, seinien kanssa vuorovaikuttavaa oliota
 * @author ikpa
 */
public class FreeMover {
    private Shape area;
    private Color color;
    private double speed;
    private double radius;
    private boolean hit;
    
    /**
     * Luo uuden FreeMover-olion
     * @param x Olion alku-x-koordinaatti
     * @param y Olion alku-y-koordinaatti
     * @param radius Olion säde
     * @param speed Olion liikkumisnopeus
     * @param c Olion väri
     */
    public FreeMover(double x, double y, double radius, double speed, Color c) {
        area = new Circle(x, y, radius);
        area.setFill(c);
        color = c;
        this.speed = speed;
        this.radius = radius;
    }
    
    public Shape getArea() {
        return area;
    }

    public void setArea(Shape area) {
        this.area = area;
    }
    
    /**
     * Palauttaa olion CenterX ominaisuuden
     * @return x-koordinaatti
     */
    public double getCenterX() {
        return area.getBoundsInLocal().getCenterX();
    }
    
    /**
     * Palauttaa olion CenterY ominaisuuden
     * @return y-koordinaatti
     */
    public double getCenterY() {
        return area.getBoundsInLocal().getCenterY();
    }
    
    /**
     * Asettaa oliolle uuden paikan
     * @param x Uusi x-koordinaatti
     * @param y Uusi y-koordinaatti
     */
    public void setLocation(double x, double y) {
        area = new Circle(x, y, radius);
        area.setFill(color);
    }

    public double getRadius() {
        return radius;
    }
    

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    /**
     * Tarkistaa, onko olio osunut tiettyyn Shape-olioon
     * @param s Shape, joka tarkistetaan
     * @return true jos osuu, false jos ei osu
     */
    public boolean collision(Shape s) {
        Shape sec = Shape.intersect(s, area);
        return sec.getBoundsInLocal().getWidth() > 1;
    }
    
    /**
     * Tarkistaa kaikki osumat kaikkien Arrayn Spike-olioiden kanssa ja asettaa olion Hit-parametrin todeksi tarvittaessa
     * @param spikes Array, jossa tarkistettavat Spike-oliot
     * @return true jos osuma, false jos ei
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
    
    /**
     * Palauttaa FreeMover-olion, ja annetun Shape olion leikkauskuvion keskipisteen x-koordinaatin
     * @param shape Tarkasteltava Shape-olio
     * @return Leikkauskuvion x-koordinaatti
     */
    public double intersectXLoc(Shape shape) {
        Shape sec = Shape.intersect(shape, area);
        double xloc = sec.getBoundsInLocal().getCenterX()
                        - (area.getTranslateX() + getCenterX());
        return xloc;
    }
    
    /**
     * Palauttaa FreeMover-olion, ja annetun Shape olion leikkauskuvion keskipisteen 
     * y-koordinaatin. Vaatii voffset- muttujan korjaamaan mahdollisesta infopalkista
     * johtuva koordinaattien vääristys
     * @param shape Tutkittava Shape-olio
     * @param voffset Koordinaattien korjaustermi
     * @return
     */
    public double intersectYLoc(Shape shape, double voffset) {
        Shape sec = Shape.intersect(shape, area);
        double yloc = sec.getBoundsInLocal().getCenterY()
                        - (area.getTranslateY() + getCenterY()) - voffset;
        return yloc;
    }
    
    /**
     * Tarkistaa törmäyksen kyseisen Shape-olion kanssa, ja muokkaa sallitut
     * kulkusuunnat sisältävää arrayta dirs tarvittaessa. Vaatii koordinaattien
     * korjaustermin
     * @param dirs Sallitut kulkusuunnat sisältävä array. Indeksi 0 on ylös,
     * 1 oikealle, 2 alas ja 3 vasemmalle. True jos sallittu kulkusuunta,
     * false jos ei
     * @param s Tarkistettava Shape-olio
     * @param voffset Koordinaattien korjaustermi
     */
    public void allowedDirections(ArrayList<Boolean> dirs, 
            Shape s, double voffset) {
        
        if (!collision(s)) {
            return;
        }
        
        double xloc = intersectXLoc(s);
        double yloc = intersectYLoc(s, voffset);
                
        if (yloc < -8) {
            dirs.set(0, Boolean.FALSE);
        }
                
        if (xloc > 4) {
            dirs.set(1, Boolean.FALSE);
        }
                
        if (yloc > 2.5) {
            dirs.set(2, Boolean.FALSE);
        }
                
        if (xloc < -6) {
            dirs.set(3, Boolean.FALSE);
        }
    }
    
    
    /**
     * Tarkistaa, mihin suuntiin olio voi liikkua kaikkien seinien perusteella
     * @param walls Tarkistettavat seinät
     * @param voffset Vaaditaan koordinaattien korjaamiseen, jos peliruudussa on infopalkki
     * @return Array, joka sisältää sallitut suunnat. Indeksi 0 ylös, 1 oikea, 2 alas, 3 vasen; true sallittu suunta, false kielletty suunta.
     */
    public ArrayList<Boolean> allowedDirectionsForAll(ArrayList<Rectangle> walls, double voffset) {
        ArrayList<Boolean> dirs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dirs.add(Boolean.TRUE);
        }
        
        for (Rectangle r: walls) {
            allowedDirections(dirs, r, voffset);
        }
        
        return dirs;
    }
    
    /**
     * Liikuttaa hahmoa ylös nopeuden ja x-kertoimen perusteella
     * @param x Kerroin, jolla nopeus kerrotaan
     */
    public void moveUP(double x) {
        area.setTranslateY(area.getTranslateY() - x * speed);
    }
    
    /**
     * Liikuttaa hahmoa alas opeuden ja x-kertoimen perusteella
     * @param x Kerroin, jolla nopeus kerrotaan
     */
    public void moveDOWN(double x) {
        area.setTranslateY(area.getTranslateY() + x * speed);
    }
    
    /**
     * Liikuttaa hahmoa oikealle nopeuden ja x-kertoimen perusteella
     * @param x Kerroin, jolla nopeus kerrotaan
     */
    public void moveRIGHT(double x) {
        area.setTranslateX(area.getTranslateX() + x * speed);
    }
    
    /**
     * Liikuttaa hahmoa vasemmalle nopeuden ja x-kertoimen perusteella
     * @param x Kerroin, jolla nopeus kerrotaan
     */
    public void moveLEFT(double x) {
        area.setTranslateX(area.getTranslateX() - x * speed);
    }
    
    /**
     * Palauttaa hahmon koordinaatit ennalleen
     */
    public void reset() {
        getArea().setTranslateX(0);
        getArea().setTranslateY(0);
    }
}
