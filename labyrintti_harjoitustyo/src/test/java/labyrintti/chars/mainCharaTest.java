/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.chars;

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
public class mainCharaTest {
    mainChara c;
    
    public mainCharaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        c = new mainChara(250,250,10,1,1);
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
        assertEquals(c.getChara().getRadius(),test.getRadius(),0.01);
        assertEquals(c.getChara().getCenterX(),test.getCenterX(),0.01);
        assertEquals(c.getChara().getCenterY(),test.getCenterY(),0.01);
    }
    
    @Test
    public void testCollision() {
        Circle test = new Circle(245,245,10);
        assertTrue(c.collision(test));
    }
    
    @Test
    public void testMoveUP() {
        c.moveUP();
        assertEquals(c.getChara().getTranslateY(),-1,0.01);
    }
    
    @Test
    public void testMoveDOWN() {
        c.moveDOWN();
        assertEquals(c.getChara().getTranslateY(),1,0.01);
    }
    
    @Test
    public void testMoveRIGHT() {
        c.moveRIGHT();
        assertEquals(c.getChara().getTranslateX(),1,0.01);
    }
    
    @Test
    public void testMoveLEFT() {
        c.moveLEFT();
        assertEquals(c.getChara().getTranslateX(),-1,0.01);
    }
    
    @Test
    public void testAllowedDirsWhenBlockedFromAbove() {
        ArrayList<Rectangle> arr = new ArrayList<>();
        Rectangle up = new Rectangle(245,238,10,5);
        arr.add(up);
        ArrayList<Boolean> bool = c.allowedDirs(arr);
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
        ArrayList<Boolean> bool = c.allowedDirs(arr);
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
        ArrayList<Boolean> bool = c.allowedDirs(arr);
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
        ArrayList<Boolean> bool = c.allowedDirs(arr);
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
        ArrayList<Boolean> bool = c.allowedDirs(arr);
        assertFalse(bool.get(0));
        assertFalse(bool.get(1));
        assertFalse(bool.get(2));
        assertFalse(bool.get(3));
    }
    
}
