package passreg.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import passreg.Paasy.Vertailija;
import passreg.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.09 18:24:41 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PaasyTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi113 */
  @Test
  public void testRekisteroi113() {    // Paasy: 113
    Paasy gmail1 = new Paasy(); 
    Paasy gmail2 = new Paasy(); 
    assertEquals("From: Paasy line: 116", 0, gmail1.getTunnusNro()); 
    gmail1.rekisteroi(); 
    int n1 = gmail1.getTunnusNro(); 
    assertEquals("From: Paasy line: 119", 0, gmail2.getTunnusNro()); 
    gmail2.rekisteroi(); 
    int n2 = gmail2.getTunnusNro(); 
    assertEquals("From: Paasy line: 122", true, n2 == n1 + 1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString169 */
  @Test
  public void testToString169() {    // Paasy: 169
    Paasy p = new Paasy(); 
    p.parse(" 1  | 4|  soturi123 | ankkaAku"); 
    assertEquals("From: Paasy line: 172", true, p.toString().startsWith("1|4|soturi123|ankkaAku")); 
    p.parse("   2"); 
    assertEquals("From: Paasy line: 174", 2, p.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse197 */
  @Test
  public void testParse197() {    // Paasy: 197
    Paasy p = new Paasy(); 
    p.parse("   3  |1 |  ankkaTHELion "); 
    assertEquals("From: Paasy line: 200", 3, p.getTunnusNro()); 
    assertEquals("From: Paasy line: 201", 1, p.getKategoriaId()); 
    assertEquals("From: Paasy line: 202", true, p.toString().startsWith("3|1|ankkaTHELion|"));  // on enemm�kin kuin 3 kentt��, siksi loppu |
    p.rekisteroi(); 
    int n = p.getTunnusNro(); 
    p.parse(""+(n+20));  // Otetaan merkkijonosta vain tunnusnumero
    p.rekisteroi();  // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
    assertEquals("From: Paasy line: 207", n+20+1, p.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone237 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone237() throws CloneNotSupportedException {    // Paasy: 237
    Paasy p = new Paasy(); 
    p.parse("   3  | 2|  Ankka Aku   | 123"); 
    Paasy kopio = p.clone(); 
    assertEquals("From: Paasy line: 242", p.toString(), kopio.toString()); 
    p.parse("   4  | 2|  toinen gmail   | 123sKfe"); 
    assertEquals("From: Paasy line: 244", false, kopio.toString().equals(p.toString())); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna265 */
  @Test
  public void testAnna265() {    // Paasy: 265
    Paasy p = new Paasy(); 
    p.parse("1|  2|  gmail| | ankkaaku@gmail.com"); 
    assertEquals("From: Paasy line: 268", "gmail", p.anna(1)); 
    assertEquals("From: Paasy line: 269", "", p.anna(2)); 
    assertEquals("From: Paasy line: 270", "ankkaaku@gmail.com", p.anna(3)); 
    p.parse("|||||kkkl123"); 
    assertEquals("From: Paasy line: 272", "", p.anna(3)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAseta296 */
  @Test
  public void testAseta296() {    // Paasy: 296
    Paasy p = new Paasy(); 
    p.parse("1|  2|  gmail| | ankkaaku@gmail.com"); 
    assertEquals("From: Paasy line: 299", "gmail", p.anna(1)); 
    assertEquals("From: Paasy line: 300", "", p.anna(2)); 
    p.aseta(1, "toinen gmail"); 
    assertEquals("From: Paasy line: 302", "toinen gmail", p.anna(1)); 
    assertEquals("From: Paasy line: 303", "v��r� s�hk�postiosoite", p.aseta(3, "x@yahoo.com")); 
    assertEquals("From: Paasy line: 304", "ankkaaku@gmail.com", p.anna(3)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnValidi343 */
  @Test
  public void testOnValidi343() {    // Paasy: 343
    Paasy p = new Paasy(); 
    p.parse("1|1|facebook||akuAnkka@gmail.com||12345k||"); 
    assertEquals("From: Paasy line: 346", true, p.onValidi(1, p.anna(1))); 
    assertEquals("From: Paasy line: 347", true, p.onValidi(2, p.anna(2))); 
    assertEquals("From: Paasy line: 348", true, p.onValidi(5, p.anna(5))); 
    assertEquals("From: Paasy line: 349", false, p.onValidi(3, "akuankka")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEquals386 */
  @Test
  public void testEquals386() {    // Paasy: 386
    Paasy p1 = new Paasy(); 
    p1.parse("   3 | 1 | gmail   | soturi123"); 
    Paasy p2 = new Paasy(); 
    p2.parse("   3 | 1 | gmail   | soturi123"); 
    Paasy p3 = new Paasy(); 
    p3.parse("   3 | 1 | gmail   | soturi124"); 
    assertEquals("From: Paasy line: 393", true, p1.equals(p2)); 
    assertEquals("From: Paasy line: 394", true, p2.equals(p1)); 
    assertEquals("From: Paasy line: 395", false, p1.equals(p3)); 
    assertEquals("From: Paasy line: 396", false, p3.equals(p2)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKysymys411 */
  @Test
  public void testGetKysymys411() {    // Paasy: 411
    Paasy p = new Paasy(); 
    assertEquals("From: Paasy line: 413", "Otsikko", p.getKysymys(1)); 
    assertEquals("From: Paasy line: 414", false, p.getKysymys(1) == "otsikko"); 
    assertEquals("From: Paasy line: 415", "Puhelin numero", p.getKysymys(4)); 
    assertEquals("From: Paasy line: 416", null, p.getKysymys(10)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testVertailija437 */
  @Test
  public void testVertailija437() {    // Paasy: 437
    Paasy p1 = new Paasy(); 
    p1.parse("1|1|gmail2|henk.gmail"); 
    Paasy p2 = new Paasy(); 
    p2.parse("2|1|gmail1|tyogmail"); 
    Vertailija vrt = new Vertailija(1); 
    int tulos = vrt.compare(p1, p2); 
    assertEquals("From: Paasy line: 445", true, tulos > 0); 
  } // Generated by ComTest END
}