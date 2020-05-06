/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import labyrintti.logic.freemovers.MainChara;
import labyrintti.logic.object.*;
import java.util.*;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;
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
public class MainCharaTest {
    MainChara c;
    ArrayList<Item> items;
    Map<KeyCode, Boolean> buttonPress;
    
    public MainCharaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        c = new MainChara(250,250,10,1);
        items = new ArrayList<>();
        buttonPress = new HashMap<>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getChara method, of class mainChara.
     */
    @Test
    public void testGetChara() {
        Circle test = new Circle(250,250,10);
        assertEquals(c.getRadius(),test.getRadius(),0.01);
        assertEquals(c.getCenterX(),test.getCenterX(),0.01);
        assertEquals(c.getCenterY(),test.getCenterY(),0.01);
    }
    
    @Test
    public void testGetLives() {
        assertEquals(c.getLives(),3);
    }
    
    @Test
    public void testAddLife() {
        c.addLife();
        assertEquals(c.getLives(),4);
    }
    
    @Test
    public void testRemoveLife() {
        c.removeLife();
        assertEquals(c.getLives(),2);
    }
    
    @Test
    public void testDeadWhenNotDead() {
        assertFalse(c.isDead());
    }
    
    @Test
    public void testDeadWhenHitOnce() {
        c.excecuteHit();
        assertEquals(c.getLives(),2);
        assertFalse(c.isDead());
    }
    
    @Test
    public void testDeadWhenHitFourTimes() {
        c.excecuteHit();
        c.excecuteHit();
        c.excecuteHit();
        c.excecuteHit();
        assertTrue(c.isDead());
    }
    
    @Test
    public void testAddPoints() {
        c.addPoints(100);
        assertEquals(c.getPoints(), 100);
    }
    
    @Test
    public void testGetWhenGet() {
        Item item = new Item(250, 250, 1);
        assertTrue(c.get(item));
    }
    
    @Test
    public void testGetWhenNotGet() {
        Item item = new Item(700, 700, 1);
        assertFalse(c.get(item));
    }
    
    @Test
    public void testCheckGetWhenGet() {
        Item item = new Item(250, 250, 1);
        items.add(item);
        assertTrue(!c.checkGet(items).isEmpty());
    }
    
    @Test
    public void testCheckGetWhenNotGet() {
        Item item = new Item(600, 600, 1);
        items.add(item);
        assertTrue(c.checkGet(items).isEmpty());
    }
    
    @Test
    public void testRateOfMovementWhenTwoButtonsPressed() {
        buttonPress.put(KeyCode.UP, Boolean.TRUE);
        buttonPress.put(KeyCode.LEFT, Boolean.TRUE);
        assertTrue(c.rateOfMovement(buttonPress) != 1);
    }
    
    @Test
    public void testRateOfMovementWhenThreeButtonsPressed() {
        buttonPress.put(KeyCode.UP, Boolean.TRUE);
        buttonPress.put(KeyCode.DOWN, Boolean.TRUE);
        buttonPress.put(KeyCode.LEFT, Boolean.TRUE);
        assertTrue(c.rateOfMovement(buttonPress) == 1);
    }
    
}
