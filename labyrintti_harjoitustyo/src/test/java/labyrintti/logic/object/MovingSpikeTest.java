/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import labyrintti.logic.object.MovingSpike;
import labyrintti.logic.freemovers.MainChara;
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
public class MovingSpikeTest {
    MovingSpike vert;
    MovingSpike hor;
    MovingSpike vertf;
    MovingSpike horf;
    
    public MovingSpikeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vert = new MovingSpike(250, 250, true, 1, 251);
        hor = new MovingSpike(250, 250, false, 1, 251);
        vertf = new MovingSpike(250, 251, true, 1, 250);
        horf = new MovingSpike(251, 250, false, 1, 250);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testMoveUP() {
        vert.moveUP();
        assertEquals(vert.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vert.getPolygon().getTranslateY(), -1, 0.01);
    }
    
    @Test
    public void testMoveDOWN() {
        vert.moveDOWN();
        assertEquals(vert.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vert.getPolygon().getTranslateY(), 1, 0.01);
    }
    
    @Test
    public void testMoveRIGHT() {
        hor.moveRIGHT();
        assertEquals(hor.getPolygon().getTranslateX(), 1, 0.01);
        assertEquals(hor.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testMoveLEFT() {
        hor.moveLEFT();
        assertEquals(hor.getPolygon().getTranslateX(), -1, 0.01);
        assertEquals(hor.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testHorMoveWhenFinaldirTrueOnce() {
        hor.horMove();
        assertEquals(hor.getPolygon().getTranslateX(), 1, 0.01);
        assertEquals(hor.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testHorMoveWhenFinaldirTrueTwice() {
        hor.horMove();
        hor.horMove();
        assertEquals(hor.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(hor.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testHorMoveWhenFinaldirFalseOnce() {
        horf.horMove();
        assertEquals(horf.getPolygon().getTranslateX(), -1, 0.01);
        assertEquals(horf.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testHorMoveWhenFinaldirFalseTwice() {
        horf.horMove();
        horf.horMove();
        assertEquals(horf.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(horf.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testVerMoveWhenFinaldirTrueOnce() {
        vert.verMove();
        assertEquals(vert.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vert.getPolygon().getTranslateY(), 1, 0.01);
    }
    
    @Test
    public void testVerMoveWhenFinaldirTrueTwice() {
        vert.verMove();
        vert.verMove();
        assertEquals(vert.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vert.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testVerMoveWhenFinaldirFalseOnce() {
        vertf.verMove();
        assertEquals(vertf.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vertf.getPolygon().getTranslateY(), -1, 0.01);
    }
    
    @Test
    public void testVerMoveWhenFinaldirFalseTwice() {
        vertf.verMove();
        vertf.verMove();
        assertEquals(vertf.getPolygon().getTranslateX(), 0, 0.01);
        assertEquals(vertf.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testMoveWhenVerIsFalse() {
        hor.move();
        assertEquals(hor.getPolygon().getTranslateY(), 0, 0.01);
    }
    
    @Test
    public void testMoveWhenVerIsTrue() {
        vert.move();
        assertEquals(vert.getPolygon().getTranslateX(), 0, 0.01);
    }
}
