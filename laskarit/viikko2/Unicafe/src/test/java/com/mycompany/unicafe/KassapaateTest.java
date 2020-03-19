/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    Kassapaate kp;
    Maksukortti rkortti;
    Maksukortti vkortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kp = new Kassapaate();
        rkortti = new Maksukortti(1000);
        vkortti = new Maksukortti(200);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void oikeaMaaraRahaaLuonninJalkeen() {
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void eiMyytyjaLounaitaLuonninJalkeen() {
        assertEquals(kp.maukkaitaLounaitaMyyty(),0);
        assertEquals(kp.edullisiaLounaitaMyyty(),0);
    }
    
    @Test
    public void edullinenKateisostoToimiiKunRiittaa() {
        assertEquals(kp.syoEdullisesti(250), 10);
        assertEquals(kp.edullisiaLounaitaMyyty(),1);
        assertEquals(kp.kassassaRahaa(), 100240);
    }
    
    @Test
    public void maukasKateisostoToimiiKunRiittaa() {
        assertEquals(kp.syoMaukkaasti(410),10);
        assertEquals(kp.maukkaitaLounaitaMyyty(),1);
        assertEquals(kp.kassassaRahaa(),100400);
    }
    
    @Test
    public void edullinenKateisostoToimiiKunEiRiita() {
        assertEquals(kp.syoEdullisesti(230),230);
        assertEquals(kp.edullisiaLounaitaMyyty(),0);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void maukasKateisostoToimiiKunEiRiita() {
        assertEquals(kp.syoMaukkaasti(390), 390);
        assertEquals(kp.maukkaitaLounaitaMyyty(),0);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void edullinenKorttiostoToimiiKunRiittaa() {
        assertTrue(kp.syoEdullisesti(rkortti));
        assertEquals(kp.edullisiaLounaitaMyyty(),1);
        assertEquals(rkortti.saldo(),760);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void maukasKorttiOstoToimiiKunRiittaa() {
        assertTrue(kp.syoMaukkaasti(rkortti));
        assertEquals(kp.maukkaitaLounaitaMyyty(),1);
        assertEquals(rkortti.saldo(),600);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void edullinenKorttiostoToimiiKunEiRiita() {
        assertFalse(kp.syoEdullisesti(vkortti));
        assertEquals(kp.edullisiaLounaitaMyyty(),0);
        assertEquals(vkortti.saldo(),200);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void mauksaKorttiostoToimiiKunEiRiita() {
        assertFalse(kp.syoMaukkaasti(vkortti));
        assertEquals(kp.maukkaitaLounaitaMyyty(),0);
        assertEquals(vkortti.saldo(),200);
        assertEquals(kp.kassassaRahaa(),100000);
    }
    
    @Test
    public void rahanLatausToimii() {
        kp.lataaRahaaKortille(rkortti, 100);
        assertEquals(rkortti.saldo(),1100);
        assertEquals(kp.kassassaRahaa(),100100);
    }
    
    @Test
    public void eiVoiLadataNegatiivistaSummaa() {
        kp.lataaRahaaKortille(rkortti, -10);
        assertEquals(rkortti.saldo(),1000);
        assertEquals(kp.kassassaRahaa(),100000);
    }
}
