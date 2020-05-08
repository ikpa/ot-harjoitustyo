/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logic.level;

import labyrintti.logic.freemovers.*;
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
public class GoalTest {
    private MainChara in;
    private MainChara out;
    private Goal g;
    
    public GoalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        in = new MainChara(100, 100, 10, 1);
        out = new MainChara(900, 900, 10, 1);
        g = new Goal(100, 100, 15);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testInGoalWhenInGoal() {
        assertTrue(g.inGoal(in));
    }
    
    @Test
    public void testInGoalWhenNotInGoal() {
        assertFalse(g.inGoal(out));
    }
    
}
