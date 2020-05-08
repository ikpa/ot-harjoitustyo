/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.levels;

import labyrintti.logic.level.Level;
import labyrintti.logic.level.LvlConstructor;
import labyrintti.logic.object.*;
import java.util.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import labyrintti.logic.freemovers.MainChara;
import labyrintti.logic.level.Goal;
import labyrintti.logic.object.Item;
import labyrintti.logic.object.Spike;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author ikpa
 */
public class LevelTest {
    private LvlConstructor lc;
    private Level lvl;
    private Level lvl2;
    private MainChara c;
    private HashMap<KeyCode, Boolean> map;
    
    public LevelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lc = new LvlConstructor();
        lvl = lc.testlvl();
        lvl2 = lc.testlvl2();
        c = new MainChara(0, 0, 10, 1);
        map = new HashMap<>();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetStg() {
        assertEquals(lvl.getStg().getPrefHeight(), 1000, 0.01);
        assertEquals(lvl.getStg().getPrefWidth(), 1000, 0.01);
    }
    
    @Test
    public void testInitialise() {
        lvl.initialise(c);
        assertEquals(c.getCenterX(), 940, 0.01);
        assertEquals(c.getCenterY(), 500, 0.01);
    }
    
    @Test
    public void removeItemsRemovesCorrectItem() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(0);
        lvl.removeItems(ids);
        assertEquals(lvl.getItems().get(0).getType(), 1);
    }
    
    @Test
    public void removeEnemiesRemovesCorrectEnemy() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(0);
        lvl2.removeEnemies(ids);
        assertTrue(!lvl2.getStg().getChildren().contains(lvl2.getEnemies().get(0).getArea()));
    }
    
    @Test
    public void testRemoveDoor() {
        Door d = lvl2.getDoors().get(0);
        lvl2.removeDoor(d);
        assertFalse(lvl2.getStg().getChildren().contains(d.getDoor()));
        assertFalse(lvl.getWalls().contains(d.getDoor()));
    }
    
    @Test
    public void testResetEnemies() {
        lvl2.getEnemies().get(0).moveUP(50);
        lvl2.getEnemies().get(1).moveDOWN(50);
        lvl2.resetEnemies();
        assertEquals(lvl2.getEnemies().get(0).getArea().getTranslateX(), 0, 0.01);
        assertEquals(lvl2.getEnemies().get(1).getArea().getTranslateX(), 0, 0.01);
    }
    
    @Test
    public void testUpdateSpikesMovesSpikes() {
        lvl.updateSpikes(c);
        assertTrue(lvl.getSpikes().get(1).getPolygon().getTranslateY() != 0);
        assertTrue(lvl.getSpikes().get(2).getPolygon().getTranslateX() != 0);
    }
    
    @Test
    public void testUpdateSpikesWhenCharaIsHit() {
        c.setLocation(200, 800);
        assertTrue(lvl2.updateSpikes(c));
    }
    
    @Test
    public void testUpdateEnemiesMovesEnemies() {
        lvl2.updateEnemies(c, 0);
        assertFalse(lvl2.getEnemies().get(0).getArea().getTranslateX() == 0);
        assertFalse(lvl2.getEnemies().get(1).getArea().getTranslateX() == 0);
    }
    
    @Test
    public void testUpdateEnemiesWhenCharaIsHit() {
        c.setLocation(500, 500);
        assertTrue(lvl2.updateEnemies(c, 0));
    }
    
    @Test
    public void testUpdateDoorsWhenNotHit() {
        lvl2.updateDoors();
        assertFalse(lvl2.getDoors().get(0).isOpen());
    }
    
    @Test
    public void testUpdateDoorsWhenHit() {
        lvl2.getEnemies().get(0).setHit(true);
        lvl2.getEnemies().get(1).setHit(true);
        lvl2.updateDoors();
        assertTrue(lvl2.getDoors().get(0).isOpen());
    }
    
    @Test
    public void testUpdateInStartPosition() {
        lvl.initialise(c);
        lvl.update(map, c, 0);
        assertThat(lvl.getItems().get(0).getCircle().getCenterX(), is(not(100)));
        assertThat(lvl.getItems().get(1).getCircle().getCenterY(), is(not(900)));
    }
    
    @Test
    public void updateItemsWhenCharaGetsItem() {
        lvl.initialise(c);
        c.setLocation(650, 300);
        lvl.updateItems(c);
        assertEquals(c.getLives(), 4);
    }
    
}
