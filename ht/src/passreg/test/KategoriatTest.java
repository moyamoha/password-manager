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
 * @version 2021.04.05 12:08:09 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KategoriatTest {



  // Generated by ComTest BEGIN
  /** testLisaa66 */
  @Test
  public void testLisaa66() {    // Kategoriat: 66
    Kategoriat kgt = new Kategoriat(); 
    Kategoria k1 = new Kategoria("tyo"); 
    Kategoria k2 = new Kategoria("opiskelu"); 
    Kategoria k3 = new Kategoria("muu"); 
    assertEquals("From: Kategoriat line: 71", 0, kgt.getLkm()); 
    kgt.lisaa(k1); 
    assertEquals("From: Kategoriat line: 73", 1, kgt.getLkm()); 
    kgt.lisaa(k2); 
    assertEquals("From: Kategoriat line: 75", 2, kgt.getLkm()); 
    kgt.lisaa(k3); 
    assertEquals("From: Kategoriat line: 77", 3, kgt.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista98 */
  @Test
  public void testPoista98() {    // Kategoriat: 98
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("opiskelu"); 
    k1.rekisteroi(); 
    int n = k1.getTunnusNro(); 
    Kategoria k2 = new Kategoria("tyo"); k2.rekisteroi(); 
    assertEquals("From: Kategoriat line: 104", 0, kat.getLkm()); 
    try {
    kat.anna(0); 
    fail("Kategoriat: 105 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    kat.lisaa(k1); 
    assertEquals("From: Kategoriat line: 107", "opiskelu", kat.anna(0).getNimi()); 
    assertEquals("From: Kategoriat line: 108", 1, kat.getLkm()); 
    kat.lisaa(k2); 
    assertEquals("From: Kategoriat line: 110", "tyo", kat.anna(1).getNimi()); 
    assertEquals("From: Kategoriat line: 111", 2, kat.getLkm()); 
    kat.poista(n); 
    assertEquals("From: Kategoriat line: 113", 1, kat.getLkm()); 
    kat.poista(n+1); 
    assertEquals("From: Kategoriat line: 115", 0, kat.getLkm()); 
    try {
    kat.anna(0); 
    fail("Kategoriat: 116 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna136 */
  @Test
  public void testAnna136() {    // Kategoriat: 136
    Kategoriat kgt = new Kategoriat(); 
    Kategoria kg1 = new Kategoria("tyo"); 
    kg1.rekisteroi(); 
    Kategoria kg2 = new Kategoria("muu"); 
    kg2.rekisteroi(); 
    Kategoria kg3 = new Kategoria("opiskelu"); 
    kg3.rekisteroi(); 
    kgt.lisaa(kg1); 
    kgt.lisaa(kg2); 
    kgt.lisaa(kg3); 
    assertEquals("From: Kategoriat line: 147", "tyo", kgt.anna(0).getNimi()); 
    assertEquals("From: Kategoriat line: 148", "muu", kgt.anna(1).getNimi()); 
    assertEquals("From: Kategoriat line: 149", "opiskelu", kgt.anna(2).getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLueTiedostosta164 */
  @Test
  public void testLueTiedostosta164() {    // Kategoriat: 164
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("opiskelu"), k2 = new Kategoria("tyo"); 
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
    assertEquals("From: Kategoriat line: 180", "opiskelu", kTest.getNimi()); 
    Kategoria kTest2 = i.next(); 
    assertEquals("From: Kategoriat line: 182", "tyo", kTest2.getNimi()); 
    kat.tallenna(hakemisto); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator227 */
  @Test
  public void testIterator227() {    // Kategoriat: 227
    Kategoriat kgt = new Kategoriat(); 
    Kategoria kg1 = new Kategoria("tyo"); 
    Kategoria kg2 = new Kategoria("muu"); 
    Kategoria kg3 = new Kategoria("opiskelu"); 
    Iterator<Kategoria> iter = kgt.iterator(); 
    assertEquals("From: Kategoriat line: 233", false, iter.hasNext()); 
    try {
    iter.next(); 
    fail("Kategoriat: 234 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    kgt.lisaa(kg1); 
    assertEquals("From: Kategoriat line: 236", true, iter.hasNext()); 
    assertEquals("From: Kategoriat line: 237", "tyo", iter.next().getNimi()); 
    kgt.lisaa(kg2); 
    kgt.lisaa(kg3); 
    assertEquals("From: Kategoriat line: 240", kg2, iter.next()); 
    assertEquals("From: Kategoriat line: 241", kg3, iter.next()); 
    iter = kgt.iterator(); 
    assertEquals("From: Kategoriat line: 243", false, iter.next() == iter.next()); 
    assertEquals("From: Kategoriat line: 244", "opiskelu", iter.next().getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testKorvaaTaiLisaa287 */
  @Test
  public void testKorvaaTaiLisaa287() {    // Kategoriat: 287
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("tyo"); 
    Kategoria k2 = new Kategoria("opiskelu"); 
    assertEquals("From: Kategoriat line: 291", false, kat.onMuutettu()); 
    kat.lisaa(k1); 
    kat.lisaa(k2); 
    kat.setMuutettu(false); 
    assertEquals("From: Kategoriat line: 295", "tyo", kat.anna(0).getNimi()); 
    k1.parse("1|viihde"); 
    kat.korvaaTaiLisaa(k1); 
    assertEquals("From: Kategoriat line: 298", "viihde", kat.anna(0).getNimi()); 
    assertEquals("From: Kategoriat line: 299", true, kat.onMuutettu()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKategoriat317 */
  @Test
  public void testAnnaKategoriat317() {    // Kategoriat: 317
    Kategoriat kat = new Kategoriat(); 
    Kategoria k1 = new Kategoria("tyo"); 
    Kategoria k2 = new Kategoria("opiskelu"); 
    kat.lisaa(k1); kat.lisaa(k2); 
    List<Kategoria> katet = kat.annaKategoriat(); 
    Iterator<Kategoria> i = katet.iterator(); 
    assertEquals("From: Kategoriat line: 325", k2, i.next()); 
    assertEquals("From: Kategoriat line: 326", true, i.hasNext()); 
    assertEquals("From: Kategoriat line: 327", k1, i.next()); 
    assertEquals("From: Kategoriat line: 328", false, i.hasNext()); 
  } // Generated by ComTest END
}