package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortillaOikeaMaara() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void laataaminenKasvattaaMaaraa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void voiOttaaRahaaKunTarpeeksi() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void eiVoiOttaaRahaaKunEiTarpeeksi() {
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void palauttaaTrueKunRahatRiittaa() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void palauttaaFalseKunRahaEiRiita() {
        assertFalse(kortti.otaRahaa(1100));
    }
    
    @Test
    public void saldoAntaaOikeanSaldon() {
        assertEquals(1000,kortti.saldo());
    }
}
