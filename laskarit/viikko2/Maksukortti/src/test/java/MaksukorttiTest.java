import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         String vastaus = kortti.toString();

         assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldonOikein() {
         kortti.syoEdullisesti();
         assertEquals("Kortilla on rahaa 7.5 euroa",kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVahentaaSaldoaOikein() {
         kortti.syoMaukkaasti();
         assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         kortti.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortilleVoiLadataRahaa() {
         kortti.lataaRahaa(25);
         assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortinSaldoEiYlitaMaksimiarvoa() {
         kortti.lataaRahaa(200);
         assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
     }
     
     @Test
     public void korttiinEiVoiLisataNegatiivistaSummaa() {
         kortti.lataaRahaa(-3);
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void voiOstaaEdullisenKunVainEdullisenVerranRahaa() {
         Maksukortti newkortti = new Maksukortti(2.5);
         newkortti.syoEdullisesti();
         assertEquals("Kortilla on rahaa 0.0 euroa", newkortti.toString());
     }
     
     @Test
     public void voiOstaaMaukkaanKunVainMaukkaanVerranRahaa() {
         Maksukortti newkortti = new Maksukortti(4);
         newkortti.syoMaukkaasti();
         assertEquals("Kortilla on rahaa 0.0 euroa", newkortti.toString());
     }
}
