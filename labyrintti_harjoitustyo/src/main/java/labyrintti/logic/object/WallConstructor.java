/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import javafx.geometry.*;
import javafx.scene.shape.*;
import java.util.*;
/**
 * Luokka seiniä kuvaavien Rectangle-olioiden luomiseen.
 * @author ikpa
 */
public class WallConstructor {
    private final int width = 5;
    
    /**
     * Luo pystyseinän.
     * @param x Seinän vasemman yläkulman x-koordinaatti
     * @param y Seinän vasemman yläkulman y-koordinaatti
     * @param length Seinän pituus
     * @return Seinää kuvaava Rectangle-olio
     */
    public Rectangle vWall(int x, int y, int length) {
        Rectangle wall = new Rectangle();
        wall.setWidth(width);
        wall.setHeight(length);
        wall.setX(x);
        wall.setY(y);
        return wall;
    }
    
    /**
     * Luo vaakaseinän.
     * @param x Seinän vasemman yläkulman x-koordinaatti
     * @param y Seinän vasemman yläkulman y-koordinaatti
     * @param length Seinän pituus
     * @return Seinää kuvaava Rectangle-olio
     */
    public Rectangle hWall(int x, int y, int length) {
        Rectangle wall = new Rectangle();
        wall.setWidth(length);
        wall.setHeight(width);
        wall.setX(x);
        wall.setY(y);
        return wall;
    }
    
    /**
     * Luo kolmesta seinästä taskun, joka on ylösalaisin käännetyn U-kirjaimen
     * muotoinen.
     * @param x Taskun vasemmanpuoleisen seinän oikean alakulman x-koordinaatti
     * @param y Taskun vasemmanpuoleisen seinän oikean alakulman y-koordinaatti
     * @param pwidth Taskun sisäosan leveys
     * @param pheight Taskun sisäosan korkeus
     * @return ArrayList, joka sisältää taskun seiniä kuvaavat Rectangle-oliot
     */
    public ArrayList<Rectangle> upPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x, y - pheight - width, pwidth));
        walls.add(vWall(x - width, y - pheight - width, pheight + width));
        walls.add(vWall(x + pwidth, y - pheight - width, pheight + width));
        
        return walls;
    }
    
    /**
     * Luo kolmesta seinästä taskun, joka on U-kirjaimen muotoinen.
     * @param x Taskun vasemmanpuoleisen seinän oikean yläkulman x-koordinaatti
     * @param y Taskun vasemmanpuoleisen seinän oikean yläkulman y-koordinaatti
     * @param pwidth Taskun sisäosan leveys
     * @param pheight Taskun sisäosan korkeus
     * @return ArrayList, joka sisältää taskun seiniä kuvaavat Rectangle-oliot
     */
    public ArrayList<Rectangle> downPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(vWall(x + pwidth, y, pheight + width));
        walls.add(hWall(x, y + pheight, pwidth));
        walls.add(vWall(x - width, y, pheight + width));
        
        return walls;
    }
    
    /**
     * Luo kolmesta seinästä taskun, joka on oikealle kyljelle käännetyn U-kirjaimen
     * muotoinen.
     * @param x Taskun ylimmän seinän oikean alakulman x-koordinaatti
     * @param y Taskun ylimmän seinän oikean alakulman y-koordinaatti
     * @param pwidth Taskun sisäosan leveys
     * @param pheight Taskun sisäosan korkeus
     * @return ArrayList, joka sisältää taskun seiniä kuvaavat Rectangle-oliot
     */
    public ArrayList<Rectangle> leftPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x - pwidth - width, y - width, pwidth + width));
        walls.add(hWall(x - pwidth - width, y + pheight, pwidth + width));
        walls.add(vWall(x - pwidth - width, y, pheight));
        
        return walls;
    }
    
    /**
     * Luo kolmesta seinästä taskun, joka on vasemmalle kyljelle U-kirjaimen
     * muotoinen.
     * @param x Taskun ylimmän seinän vasemman alakulman x-koordinaatti
     * @param y Taskun ylimmän seinän vasemman alakulman y-koordinaatti
     * @param pwidth Taskun sisäosan leveys
     * @param pheight Taskun sisäosan korkeus
     * @return ArrayList, joka sisältää taskun seiniä kuvaavat Rectangle-oliot
     */
    public ArrayList<Rectangle> rightPocket(int x, int y, int pwidth, int pheight) {
        ArrayList<Rectangle> walls = new ArrayList<>();
        walls.add(hWall(x, y - width, pwidth + width));
        walls.add(vWall(x + pwidth, y, pheight));
        walls.add(hWall(x, y + pheight, pwidth + width));
        
        return walls;
    }
}
