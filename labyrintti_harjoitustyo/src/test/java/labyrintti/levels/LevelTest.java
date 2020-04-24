/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.levels;

import java.util.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import labyrintti.chars.MainChara;
import labyrintti.object.Goal;
import labyrintti.object.Item;
import labyrintti.object.Spike;
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
        c = new MainChara(0, 0, 10, 1, 1);
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
        assertEquals(c.getCircle().getCenterX(), 500, 0.01);
        assertEquals(c.getCircle().getCenterY(), 500, 0.01);
    }
    
    @Test
    public void removeItemsRemovesCorrectItem() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(0);
        lvl.removeItems(ids);
        assertEquals(lvl.getItems().get(0).getType(), 1);
    }
    
    @Test
    public void testUpdateInStartPosition() {
        lvl.initialise(c);
        lvl.update(map, c, 0);
        assertThat(lvl.getItems().get(0).getCircle().getCenterX(), is(not(100)));
        assertThat(lvl.getItems().get(1).getCircle().getCenterY(), is(not(900)));
    }
    
    @Test
    public void updateWhenCharaGetsItem() {
        lvl.initialise(c);
        c.getCircle().setCenterX(650);
        c.getCircle().setCenterY(300);
        lvl.update(map, c, 0);
        assertEquals(c.getLives(), 4);
    }
    
}
