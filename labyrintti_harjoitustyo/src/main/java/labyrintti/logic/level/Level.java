/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.level;

import labyrintti.logic.object.Item;
import labyrintti.logic.level.Goal;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import java.util.*;
import labyrintti.logic.object.Item;
import labyrintti.logic.freemovers.MainChara;
import labyrintti.logic.object.Spike;
/**
 * Luokka, joka kuvaa yhtä tasoa.
 * @author ikpa
 */
public class Level {
    private Pane stg;
    private ArrayList<Rectangle> walls;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> items;
    private Goal goal;
    private int startx;
    private int starty;
    
    /**
     * Luo uuden Level-olion
     * @param width Tason leveys pikseleissä
     * @param height Tason korkeus pikseleissä
     * @param x Pelihahmon aloitus-x-koordinaatti
     * @param y Pelihahmon aloitus-y-koordinaatti
     * @param wall Array, joka sisältää kaikki tason seinät (Rectangle-olioina)
     * @param spi Array, joka sisältää kaikki tason piikit
     * @param i Array, joka sisältää kaikki tason kerättävät esineet
     * @param go Tason maali
     */
    public Level(int width, int height, int x, int y,
             ArrayList<Rectangle> wall, ArrayList<Spike> spi, ArrayList<Item> i,
             Goal go) {
        stg = new Pane();
        stg.setPrefSize(width, height);
        
        walls = wall;
        walls.forEach((r) -> {
            stg.getChildren().add(r);
        });
        
        spikes = spi;
        if (!(spikes.isEmpty())) {
            spikes.forEach((s) -> {
                stg.getChildren().add(s.getPolygon());
            });
        }
        
        items = i;
        if (!(items.isEmpty())) {
            items.forEach(s -> {
                stg.getChildren().add(s.getCircle());
            });
        }
        
        startx = x;
        starty = y;
        
        goal = go;
        stg.getChildren().add(goal.getArea());
    }

    public Pane getStg() {
        return stg;
    }

    public ArrayList<Rectangle> getWalls() {
        return walls;
    }

    public Goal getGoal() {
        return goal;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }
    
    /**
     * Resetoi pelihahmon ja asettaa sen lähtökoordinaatteihinsa
     * @param chara Käytettävä pelihahmo
     */
    public void initialise(MainChara chara) {
        chara.reset();
        chara.getCircle().setCenterX(startx);
        chara.getCircle().setCenterY(starty);
        stg.getChildren().add(chara.getCircle());
    }
    
    /**
     * Poistaa tasosta kaikki esineet, joiden indeksi löytyy Arraysta
     * @param ids Array, jossa poistettavien esineiden indeksit
     */
    public void removeItems(ArrayList<Integer> ids) {
        if (!(ids.isEmpty())) {
            ids.forEach(i -> {
                Item item = items.get(i);
                items.remove(item);
                stg.getChildren().remove(item.getCircle());
            });
        }
    }
    
    /**
     * Päivittää peliruudun
     * @param buttonPress Map, joka sisältää pelaajan napinpainallukset
     * @param chara Käytettävä pelihahmo
     * @param voffset Luku, joka vaaditaan seinien kanssa törmäysten havaitsemiseen (vain jos peliruutu sisältää infopalkin)
     */
    public void update(Map<KeyCode, Boolean> buttonPress, MainChara chara, double voffset) {
        chara.move(buttonPress, walls, voffset);
        
        if (!(spikes.isEmpty())) {
            chara.excecuteHit(spikes);
        }
        
        if (!(items.isEmpty())) {
            ArrayList<Integer> ids = chara.checkGet(items);
            
            removeItems(ids);
        }
        
        spikes.forEach((s) -> {
            s.move();
        });
    }
     
}
