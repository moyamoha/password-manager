package passreg.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import passreg.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.20 11:22:40 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PaasytTest {



  // Generated by ComTest BEGIN
  /** testLisaa91 */
  @Test
  public void testLisaa91() {    // Paasyt: 91
    Paasyt pst = new Paasyt(); 
    Paasy p1 = new Paasy(); 
    Paasy p2 = new Paasy(); 
    Paasy p3 = new Paasy(4); 
    assertEquals("From: Paasyt line: 96", 0, pst.getLkm()); 
    try {
    pst.anna(1); 
    fail("Paasyt: 97 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    pst.lisaa(p1); 
    assertEquals("From: Paasyt line: 99", p1, pst.anna(0)); 
    assertEquals("From: Paasyt line: 100", 1, pst.getLkm()); 
    pst.lisaa(p2); 
    pst.lisaa(p3); 
    assertEquals("From: Paasyt line: 103", 3, pst.getLkm()); 
    assertEquals("From: Paasyt line: 104", p2, pst.anna(1)); 
    assertEquals("From: Paasyt line: 105", 4, pst.anna(2).getKategoriaId()); 
    Paasy p4 = new Paasy(2); 
    pst.lisaa(p4);  // Ei pitäisi heittää poikkeusta
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnna151 
   * @throws IndexOutOfBoundsException when error
   */
  @Test
  public void testAnna151() throws IndexOutOfBoundsException {    // Paasyt: 151
    Paasyt pst = new Paasyt(); 
    assertEquals("From: Paasyt line: 154", 0, pst.getLkm()); 
    Paasy gmail1 = new Paasy(); 
    gmail1.rekisteroi(); 
    gmail1.taytaGmailTiedoilla(); 
    pst.lisaa(gmail1); 
    assertEquals("From: Paasyt line: 159", 1, pst.getLkm()); 
    Paasy gmail2 = new Paasy(); 
    gmail2.rekisteroi(); 
    gmail2.taytaGmailTiedoilla(); 
    pst.lisaa(gmail2); 
    assertEquals("From: Paasyt line: 164", 2, pst.getLkm()); 
    try {
    pst.anna(2); 
    fail("Paasyt: 165 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLueTiedostosta179 */
  @Test
  public void testLueTiedostosta179() {    // Paasyt: 179
    Paasyt pst = new Paasyt(); 
    Paasy p1 = new Paasy(1), p2 = new Paasy(2); 
    p1.taytaGmailTiedoilla(); 
    p2.taytaGmailTiedoilla(); 
    String hakemisto = "testi"; 
    File fTied = new File(hakemisto); 
    fTied.mkdir(); 
    pst.lisaa(p1); 
    pst.lisaa(p2); 
    pst.tallenna(hakemisto); 
    pst.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
    Iterator<Paasy> i = pst.iterator(); 
    Paasy pTest = i.next(); 
    assertEquals("From: Paasyt line: 196", 1, pTest.getKategoriaId()); 
    Paasy pTest2 = i.next(); 
    assertEquals("From: Paasyt line: 198", 2, pTest2.getKategoriaId()); 
    pst.tallenna(hakemisto); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista245 */
  @Test
  public void testPoista245() {    // Paasyt: 245
    Paasyt pst = new Paasyt(); 
    Paasy p1 = new Paasy(); 
    p1.taytaGmailTiedoilla(); 
    p1.rekisteroi(); 
    Paasy p2 = new Paasy(); 
    p2.taytaGmailTiedoilla(); 
    p2.rekisteroi(); 
    assertEquals("From: Paasyt line: 253", 0, pst.getLkm()); 
    pst.lisaa(p1); 
    pst.lisaa(p2); 
    assertEquals("From: Paasyt line: 256", 2, pst.getLkm()); 
    pst.poista(p1.getTunnusNro()); 
    assertEquals("From: Paasyt line: 258", 1, pst.getLkm()); 
    pst.poista(p2.getTunnusNro()); 
    assertEquals("From: Paasyt line: 260", 0, pst.getLkm()); 
    try {
    pst.anna(0); 
    fail("Paasyt: 261 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    try {
    pst.anna(1); 
    fail("Paasyt: 262 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator292 */
  @Test
  public void testIterator292() {    // Paasyt: 292
    Paasyt pst = new Paasyt(); 
    Paasy p1 = new Paasy(); 
    Paasy p2 = new Paasy(); 
    Iterator<Paasy> i = pst.iterator(); 
    assertEquals("From: Paasyt line: 298", false, i.hasNext()); 
    try {
    i.next(); 
    fail("Paasyt: 299 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    pst.lisaa(p1); 
    assertEquals("From: Paasyt line: 301", p1, i.next()); 
    assertEquals("From: Paasyt line: 302", false, i.hasNext()); 
    pst.lisaa(p2); 
    assertEquals("From: Paasyt line: 304", true, i.hasNext()); 
    assertEquals("From: Paasyt line: 305", p2, i.next()); 
    try {
    i.next(); 
    fail("Paasyt: 306 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}