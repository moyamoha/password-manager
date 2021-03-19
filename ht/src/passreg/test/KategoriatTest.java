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
 * @version 2021.03.18 19:21:22 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KategoriatTest {



  // Generated by ComTest BEGIN
  /** testLisaa79 */
  @Test
  public void testLisaa79() {    // Kategoriat: 79
    Kategoriat kgt = new Kategoriat(); 
    Kategoria k1 = new Kategoria("ty�"); 
    Kategoria k2 = new Kategoria("opiskelu"); 
    Kategoria k3 = new Kategoria("muu"); 
    assertEquals("From: Kategoriat line: 84", 0, kgt.getLkm()); 
    kgt.lisaa(k1); 
    assertEquals("From: Kategoriat line: 86", 1, kgt.getLkm()); 
    kgt.lisaa(k2); 
    assertEquals("From: Kategoriat line: 88", 2, kgt.getLkm()); 
    kgt.lisaa(k3); 
    assertEquals("From: Kategoriat line: 90", 3, kgt.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista111 */
  @Test
  public void testPoista111() {    // Kategoriat: 111
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("opiskelu"); 
    k1.rekisteroi(); 
    int n = k1.getTunnusNro(); 
    Kategoria k2 = new Kategoria("työ"); k2.rekisteroi(); 
    assertEquals("From: Kategoriat line: 117", 0, kat.getLkm()); 
    try {
    kat.anna(0); 
    fail("Kategoriat: 118 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    kat.lisaa(k1); 
    assertEquals("From: Kategoriat line: 120", "opiskelu", kat.anna(0).getNimi()); 
    assertEquals("From: Kategoriat line: 121", 1, kat.getLkm()); 
    kat.lisaa(k2); 
    assertEquals("From: Kategoriat line: 123", "työ", kat.anna(1).getNimi()); 
    assertEquals("From: Kategoriat line: 124", 2, kat.getLkm()); 
    kat.poista(n); 
    assertEquals("From: Kategoriat line: 126", 1, kat.getLkm()); 
    kat.poista(n+1); 
    assertEquals("From: Kategoriat line: 128", 0, kat.getLkm()); 
    try {
    kat.anna(0); 
    fail("Kategoriat: 129 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna149 */
  @Test
  public void testAnna149() {    // Kategoriat: 149
    Kategoriat kgt = new Kategoriat(); 
    Kategoria kg1 = new Kategoria("ty�"); 
    kg1.rekisteroi(); 
    Kategoria kg2 = new Kategoria("muu"); 
    kg2.rekisteroi(); 
    Kategoria kg3 = new Kategoria("opiskelu"); 
    kg3.rekisteroi(); 
    kgt.lisaa(kg1); 
    kgt.lisaa(kg2); 
    kgt.lisaa(kg3); 
    assertEquals("From: Kategoriat line: 160", "ty�", kgt.anna(0).getNimi()); 
    assertEquals("From: Kategoriat line: 161", "muu", kgt.anna(1).getNimi()); 
    assertEquals("From: Kategoriat line: 162", "opiskelu", kgt.anna(2).getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLueTiedostosta177 */
  @Test
  public void testLueTiedostosta177() {    // Kategoriat: 177
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("opiskelu"), k2 = new Kategoria("työ"); 
    k1.rekisteroi(); k2.rekisteroi(); 
    String hakemisto = "testi"; 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    kat.lisaa(k1); 
    kat.lisaa(k2); 
    kat.tallenna(hakemisto); 
    kat.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
    Iterator<Kategoria> i = kat.iterator(); 
    Kategoria kTest = i.next(); 
    assertEquals("From: Kategoriat line: 193", "opiskelu", kTest.getNimi()); 
    Kategoria kTest2 = i.next(); 
    assertEquals("From: Kategoriat line: 195", "työ", kTest2.getNimi()); 
    kat.tallenna(hakemisto); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator240 */
  @Test
  public void testIterator240() {    // Kategoriat: 240
    Kategoriat kgt = new Kategoriat(); 
    Kategoria kg1 = new Kategoria("tyo"); 
    Kategoria kg2 = new Kategoria("muu"); 
    Kategoria kg3 = new Kategoria("opiskelu"); 
    Iterator<Kategoria> iter = kgt.iterator(); 
    assertEquals("From: Kategoriat line: 246", false, iter.hasNext()); 
    try {
    iter.next(); 
    fail("Kategoriat: 247 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    kgt.lisaa(kg1); 
    assertEquals("From: Kategoriat line: 249", true, iter.hasNext()); 
    assertEquals("From: Kategoriat line: 250", "tyo", iter.next().getNimi()); 
    kgt.lisaa(kg2); 
    kgt.lisaa(kg3); 
    assertEquals("From: Kategoriat line: 253", kg2, iter.next()); 
    assertEquals("From: Kategoriat line: 254", kg3, iter.next()); 
    iter = kgt.iterator(); 
    assertEquals("From: Kategoriat line: 256", false, iter.next() == iter.next()); 
    assertEquals("From: Kategoriat line: 257", "opiskelu", iter.next().getNimi()); 
  } // Generated by ComTest END
}