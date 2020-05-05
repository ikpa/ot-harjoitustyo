/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.levels;

import labyrintti.logic.level.Level;
import labyrintti.logic.level.LvlConstructor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author ikpa
 */
public class LvlConstructorTest {
    private LvlConstructor lc;
    
    public LvlConstructorTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of level1 method, of class LvlConstructor.
     */
    @Test
    public void testLevel1() {
        Level lvl = lc.level1();
        assertEquals(lvl.getWalls().size(), 8);
        assertEquals(lvl.getItems().size(), 6);
        assertTrue(lvl.getSpikes().isEmpty());
    }

    /**
     * Test of level2 method, of class LvlConstructor.
     */
    @Test
    public void testLevel2() {
        Level lvl = lc.level2();
        assertEquals(lvl.getWalls().size(), 4);
        assertEquals(lvl.getItems().size(), 7);
        assertEquals(lvl.getSpikes().size(), 6);
    }

    /**
     * Test of level3 method, of class LvlConstructor.
     */
    @Test
    public void testLevel3() {
        Level lvl = lc.level3();
        assertEquals(lvl.getWalls().size(), 73);
        assertEquals(lvl.getItems().size(), 12);
        assertEquals(lvl.getSpikes().size(), 7);
    }

    /**
     * Test of testlvl method, of class LvlConstructor.
     */
    @Test
    public void testTestlvl() {
        Level lvl = lc.testlvl();
        assertEquals(lvl.getWalls().size(), 3);
        assertEquals(lvl.getItems().size(), 2);
        assertEquals(lvl.getSpikes().size(), 3);
    }

    /**
     * Test of testlvl2 method, of class LvlConstructor.
     */
    @Test
    public void testTestlvl2() {
        Level lvl = lc.testlvl2();
        assertEquals(lvl.getWalls().size(), 3);
        assertTrue(lvl.getItems().isEmpty());
        assertEquals(lvl.getSpikes().size(), 1);
    }

    /**
     * Test of allLvls method, of class LvlConstructor.
     */
    @Test
    public void testAllLvls() {
        ArrayList<Level> lvls = lc.allLvls();
        assertEquals(lvls.get(4).getWalls().size(), 73);
    }
    
}
