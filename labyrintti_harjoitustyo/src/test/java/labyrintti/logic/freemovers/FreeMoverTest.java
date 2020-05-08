/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import labyrintti.logic.object.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import java.util.*;
import javafx.scene.shape.Rectangle;
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
public class FreeMoverTest {
    private FreeMover f;
    private Spike hit;
    private Spike nothit;
    private ArrayList<Spike> spikes;
    
    public FreeMoverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        f = new FreeMover(100, 100, 10, 1, Color.BLACK);
        hit = new Spike(115, 100);
        nothit = new Spike(700, 700);
        spikes = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCollisionWhenIsCollided() {
        assertTrue(f.collision(hit.getPolygon()));
    }
    
    @Test
    public void testCollisionWhenNotCollided() {
        assertFalse(f.collision(nothit.getPolygon()));
    }
    
    @Test
    public void testCheckHitWhenNotHit() {
        spikes.add(nothit);
        assertFalse(f.checkHit(spikes));
        assertFalse(f.isHit());
    }
    
    @Test
    public void testCheckHitWhenHit() {
        spikes.add(hit);
        assertTrue(f.checkHit(spikes));
        assertTrue(f.isHit());
    }
    
    @Test
    public void testXLocAndYLocWhenHit() {
        double xloc = f.intersectXLoc(hit.getPolygon());
        double yloc = f.intersectYLoc(hit.getPolygon(), 0);
        assertTrue(xloc != 0);
        assertTrue(yloc != 0);
    }
    
    @Test
    public void testMoveUP() {
        f.moveUP(1);
        assertEquals(f.getArea().getTranslateY(),-1,0.01);
    }
    
    @Test
    public void testMoveDOWN() {
        f.moveDOWN(1);
        assertEquals(f.getArea().getTranslateY(),1,0.01);
    }
    
    @Test
    public void testMoveRIGHT() {
        f.moveRIGHT(1);
        assertEquals(f.getArea().getTranslateX(),1,0.01);
    }
    
    @Test
    public void testMoveLEFT() {
        f.moveLEFT(1);
        assertEquals(f.getArea().getTranslateX(),-1,0.01);
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromAbove() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle up = new Rectangle(95,87,10,5);
        arr.add(up);
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertFalse(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromRight() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle right = new Rectangle(107,95,5,10);
        arr.add(right);
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertTrue(bool.get(0));
        assertFalse(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromBelow() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle down = new Rectangle(95,107,10,5);
        arr.add(down);
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertFalse(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromLeft() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle left = new Rectangle(88,95,5,10);
        arr.add(left);
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertFalse(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromAllDirs() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle up = new Rectangle(95,87,10,5);
        Rectangle right = new Rectangle(107,95,5,10);
        Rectangle down = new Rectangle(95,107,10,5);
        Rectangle left = new Rectangle(88,95,5,10);
        arr.add(up);
        arr.add(right);
        arr.add(down);
        arr.add(left);
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertFalse(bool.get(0));
        assertFalse(bool.get(1));
        assertFalse(bool.get(2));
        assertFalse(bool.get(3));
    }
    
    @Test
    public void testAllowedDirsWhenNotBlocked() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        ArrayList<Boolean> bool = f.allowedDirectionsForAll(arr, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testReset() {
        f.moveDOWN(1);
        f.moveDOWN(1);
        f.moveLEFT(1);
        f.moveLEFT(1);
        f.reset();
        assertEquals(f.getArea().getTranslateX(),0,0.01);
        assertEquals(f.getArea().getTranslateY(),0,0.01);
    }
    
}
