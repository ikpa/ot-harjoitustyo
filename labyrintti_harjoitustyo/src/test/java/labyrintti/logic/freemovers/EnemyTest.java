/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.freemovers;

import java.util.*;
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
public class EnemyTest {
    private MainChara c;
    private Enemy e1;
    private Enemy e2;
    private Enemy e3;
    private ArrayList<Enemy> enemies;
    private ArrayList<Rectangle> walls;
    
    public EnemyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        c = new MainChara(500, 500, 10, 1);
        e1 = new Enemy(400, 400, 10, 500, 0.5);
        e2 = new Enemy(900, 900, 10, 10, 0.5);
        e3 = new Enemy(415, 400, 10, 500, 0.5);
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSetLocation() {
        e1.setLocation(0, 0);
        assertEquals(e1.getCenterX(), 0, 0.01);
    }

    /**
     * Test of xDistance method, of class Enemy.
     */
    @Test
    public void testXDistance() {
        assertEquals(e1.xDistance(c), 100, 0.01);
    }

    /**
     * Test of yDistance method, of class Enemy.
     */
    @Test
    public void testYDistance() {
        assertEquals(e1.yDistance(c), 100, 0.01);
    }

    /**
     * Test of totalDistance method, of class Enemy.
     */
    @Test
    public void testTotalDistance() {
        assertEquals(e1.totalDistance(c), Math.sqrt(20000), 0.01);
    }

    /**
     * Test of enemyAllowedDirections method, of class Enemy.
     */
    @Test
    public void testEnemyAllowedDirectionsWhenBlocked() {
        enemies.add(e1);
        enemies.add(e3);
        ArrayList<Boolean> bool = e1.enemyAllowedDirections(walls, enemies, 0);
        assertTrue(bool.get(0));
        assertFalse(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }
    
    @Test
    public void testEnemyAllowedDirectionsWhenNotBlocked() {
        enemies.add(e1);
        enemies.add(e2);
        ArrayList<Boolean> bool = e1.enemyAllowedDirections(walls, enemies, 0);
        assertTrue(bool.get(0));
        assertTrue(bool.get(1));
        assertTrue(bool.get(2));
        assertTrue(bool.get(3));
    }

    /**
     * Test of move method, of class Enemy.
     */
    @Test
    public void testMoveWhenInRange() {
        e1.move(walls, c, enemies, 0);
        assertTrue(e1.getArea().getTranslateX() != 0);
    }
    
    @Test
    public void testMoveWhenOutOfRange() {
        e2.move(walls, c, enemies, 0);
        assertTrue(e2.getArea().getTranslateX() == 0);
    }
    
}
