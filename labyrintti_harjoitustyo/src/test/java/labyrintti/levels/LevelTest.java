/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.levels;

import java.util.ArrayList;
import java.util.Map;
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

/**
 *
 * @author ikpa
 */
public class LevelTest {
    private LvlConstructor lc;
    private Level lvl;
    private MainChara c;
    
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
        assertEquals(c.getChara().getCenterX(), 500, 0.01);
        assertEquals(c.getChara().getCenterY(), 500, 0.01);
    }
    
}
