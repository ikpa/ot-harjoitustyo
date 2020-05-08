/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.dao;

import java.io.File;
import java.util.*;
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
public class HighScoreDaoTest {
    private HighScoreDao dao;
    
    public HighScoreDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new HighScoreDao("testi.db");
    }
    
    @After
    public void tearDown() {
        File f = new File("testi.db");
        f.delete();
    }

    /**
     * Test of writeScore method, of class HighScoreDao.
     */
    @Test
    public void testWriteScore() {
        dao.writeScore("testi", 100);
        ArrayList<String> results = dao.neatScores();
        assertEquals(results.get(0), "testi, 100 pistettä");
    }

    @Test
    public void testNeatScoresOrdersByPoints() {
        dao.writeScore("a", 900);
        dao.writeScore("b", 500);
        dao.writeScore("c", 600);
        ArrayList<String> results = dao.neatScores();
        assertEquals(results.get(0), "a, 900 pistettä");
        assertEquals(results.get(1), "c, 600 pistettä");
        assertEquals(results.get(2), "b, 500 pistettä");
    }
    
    @Test
    public void testNeatScoresOrdersByName() {
        dao.writeScore("b", 10);
        dao.writeScore("a", 10);
        dao.writeScore("c", 10);
        ArrayList<String> results = dao.neatScores();
        assertEquals(results.get(0), "a, 10 pistettä");
        assertEquals(results.get(1), "b, 10 pistettä");
        assertEquals(results.get(2), "c, 10 pistettä");
    }
    
    @Test
    public void testClear() {
        dao.writeScore("a", 10);
        dao.writeScore("a", 10);
        dao.clear();
        ArrayList<String> results = dao.neatScores();
        assertTrue(results.isEmpty());
    }
}
