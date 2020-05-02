/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.chars;

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
    ArrayList<Spike> spikes;
    
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
        spikes = new ArrayList<>();
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
        assertEquals(c.getCircle().getRadius(),test.getRadius(),0.01);
        assertEquals(c.getCircle().getCenterX(),test.getCenterX(),0.01);
        assertEquals(c.getCircle().getCenterY(),test.getCenterY(),0.01);
    }
    
    @Test
    public void testCollisionWhenIsCollided() {
        Circle test = new Circle(245,245,10);
        assertTrue(c.collision(test));
    }
    
    @Test
    public void testCollisionWhenNotCollided() {
        Circle test = new Circle(600,600,10);
        assertFalse(c.collision(test));
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
    public void testCheckHitWhenNotHit() {
        Spike s = new Spike(10,10);
        spikes.add(s);
        c.excecuteHit(spikes);
        assertEquals(c.getLives(), 3);
    }
    
    @Test
    public void testCheckHitWhenHit() {
        Spike s = new Spike(250,250);
        spikes.add(s);
        c.excecuteHit(spikes);
        assertEquals(c.getLives(),2);
    }
    
    @Test
    public void testDeadWhenNotDead() {
        assertFalse(c.isDead());
    }
    
    @Test
    public void testDeadWhenHitOnce() {
        Spike s = new Spike(250,250);
        spikes.add(s);
        c.excecuteHit(spikes);
        assertEquals(c.getLives(),2);
        assertFalse(c.isDead());
    }
    
    @Test
    public void testDeadWhenHitFourTimes() {
        Spike s = new Spike(250,250);
        spikes.add(s);
        c.excecuteHit(spikes);
        c.excecuteHit(spikes);
        c.excecuteHit(spikes);
        c.excecuteHit(spikes);
        assertTrue(c.isDead());
    }
    
    @Test
    public void testMoveUP() {
        c.moveUP(1);
        assertEquals(c.getCircle().getTranslateY(),-1,0.01);
    }
    
    @Test
    public void testMoveDOWN() {
        c.moveDOWN(1);
        assertEquals(c.getCircle().getTranslateY(),1,0.01);
    }
    
    @Test
    public void testMoveRIGHT() {
        c.moveRIGHT(1);
        assertEquals(c.getCircle().getTranslateX(),1,0.01);
    }
    
    @Test
    public void testMoveLEFT() {
        c.moveLEFT(1);
        assertEquals(c.getCircle().getTranslateX(),-1,0.01);
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromAbove() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle up = new Rectangle(245,238,10,5);
        arr.add(up);
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertFalse(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromRight() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle right = new Rectangle(258,245,5,10);
        arr.add(right);
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertTrue(bool.get(0));
        assertFalse(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromBelow() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle down = new Rectangle(245,258,10,5);
        arr.add(down);
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertFalse(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromLeft() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle left = new Rectangle(237,245,5,10);
        arr.add(left);
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertFalse(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromAllDirs() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle up = new Rectangle(245,238,10,5);
        Rectangle right = new Rectangle(258,245,5,10);
        Rectangle down = new Rectangle(245,258,10,5);
        Rectangle left = new Rectangle(237,245,5,10);
        arr.add(up);
        arr.add(right);
        arr.add(down);
        arr.add(left);
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertFalse(bool.get(0));
        assertFalse(bool.get(1));
        assertFalse(bool.get(2));
        assertFalse(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenNotBlocked() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        ArrayList<Boolean> bool = c.allowedDirections(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testResetChara() {
        c.moveDOWN(1);
        c.moveDOWN(1);
        c.moveLEFT(1);
        c.moveLEFT(1);
        c.reset();
        assertEquals(c.getCircle().getTranslateX(),0,0.01);
        assertEquals(c.getCircle().getTranslateY(),0,0.01);
    }
    
    @Test
    public void testAddPoints() {
        c.addPoints(100);
        assertEquals(c.getPoints(), 100);
    }
    
}
