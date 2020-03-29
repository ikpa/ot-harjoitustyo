/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.chars;

import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
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
    
}
