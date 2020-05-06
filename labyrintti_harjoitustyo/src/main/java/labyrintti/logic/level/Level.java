/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.level;

import labyrintti.logic.object.*;
import labyrintti.logic.level.Goal;
import labyrintti.logic.freemovers.Enemy;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import java.util.*;
import labyrintti.logic.freemovers.Enemy;
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
    private ArrayList<Enemy> enemies;
    private ArrayList<Door> doors;
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
             ArrayList<Enemy> ene, ArrayList<Door> door, Goal go) {
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
        
        enemies = ene;
        if (!(enemies.isEmpty())) {
            enemies.forEach(e -> {
                stg.getChildren().add(e.getArea());
            });
        }
        
        doors = door;
        if (!doors.isEmpty()) {
            doors.forEach(d -> {
                stg.getChildren().add(d.getDoor());
                walls.add(d.getDoor());
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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
    
    /**
     * Resetoi pelihahmon ja asettaa sen lähtökoordinaatteihinsa
     * @param chara Käytettävä pelihahmo
     */
    public void initialise(MainChara chara) {
        chara.reset();
        chara.setLocation(startx, starty);
        stg.getChildren().add(chara.getArea());
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
    
    public void removeEnemies(ArrayList<Integer> ids) {
        if (!(ids.isEmpty())) {
            ids.forEach(i -> {
                Enemy enemy = enemies.get(i);
                stg.getChildren().remove(enemy.getArea());
            });
        }
    }
    
    public void removeDoor(Door d) {
        walls.remove(d.getDoor());
        stg.getChildren().remove(d.getDoor());
    }
    
    public void resetDoors() {
        if (doors.isEmpty()) {
            return;
        }
        
        for (Door d: doors) {
            if (d.isOpen()) {
                d.setOpen(false);
                stg.getChildren().add(d.getDoor());
                walls.add(d.getDoor());
            }
        }
    }
    
    public void resetEnemies() {
        for (Enemy e: enemies) {
            e.reset();
            
            if (e.isHit()) {
                e.setHit(false);
                stg.getChildren().add(e.getArea());
            }
        }
    }
    
    public void resetLevel() {
        resetDoors();
        resetEnemies();
    }
    
    public boolean updateSpikes(MainChara chara) {
        boolean hit = false;
        
        if (!(spikes.isEmpty())) {
            for (Spike s: spikes) {
                s.move();
                
                if (chara.collision(s.getPolygon())) {
                    hit = true;
                }
            }
        }
        
        return hit;
    }
    
    public void updateItems(MainChara chara) {
        if (!(items.isEmpty())) {
            ArrayList<Integer> ids = chara.checkGet(items);
            
            removeItems(ids);
        }
    }
    
    public boolean updateEnemies(MainChara chara, double voffset) {
        boolean hit = false;
        
        if (!(enemies.isEmpty())) {
            ArrayList<Integer> ids = new ArrayList<>();
            
            for (Enemy e: enemies) {
                if (e.isHit()) {
                    continue;
                }
                
                e.move(walls, chara, enemies, voffset);
                
                if (e.checkHit(spikes)) {
                    ids.add(enemies.indexOf(e));
                }
                
                if (chara.collision(e.getArea())) {
                    hit = true;
                }
            }
            
            removeEnemies(ids);
        }
        
        return hit;
    }
    
    public void updateDoors() {
        if (doors.isEmpty()) {
            return;
        }
        
        for (Door d: doors) {
            d.executeOpen();
            
            if (d.isOpen()) {
                removeDoor(d);
            }
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
        
        boolean spikehit = updateSpikes(chara);
        boolean enemyhit = updateEnemies(chara, voffset);
        updateItems(chara);
        updateDoors();
        
        if (spikehit || enemyhit) {
            chara.excecuteHit();
            resetLevel();
        }
        
    }
     
}
