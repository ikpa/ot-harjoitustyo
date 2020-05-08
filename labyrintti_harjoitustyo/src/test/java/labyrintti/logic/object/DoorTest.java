/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.object;

import labyrintti.logic.freemovers.Enemy;
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
public class DoorTest {
    private Enemy e;
    private Door d;
    
    public DoorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        e = new Enemy(0, 0, 10, 100, 1);
        d = new Door(0, 0, 100, true, e);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testExecuteOpenWhenNotHit() {
        d.executeOpen();
        assertFalse(d.isOpen());
    }
    
    @Test
    public void testExecuteOpenWhenHit() {
        e.setHit(true);
        d.executeOpen();
        assertTrue(d.isOpen());
    }
}
