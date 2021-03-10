package passreg.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import passreg.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.04 17:33:42 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PaasytTest {



  // Generated by ComTest BEGIN
  /** 
   * testAnna118 
   * @throws IndexOutOfBoundsException when error
   */
  @Test
  public void testAnna118() throws IndexOutOfBoundsException {    // Paasyt: 118
    Paasyt pst = new Paasyt(); 
    assertEquals("From: Paasyt line: 121", 0, pst.getLkm()); 
    Paasy gmail1 = new Paasy(); 
    gmail1.rekisteroi(); 
    gmail1.taytaGmailTiedoilla(); 
    pst.lisaa(gmail1); 
    assertEquals("From: Paasyt line: 126", 1, pst.getLkm()); 
    Paasy gmail2 = new Paasy(); 
    gmail2.rekisteroi(); 
    gmail2.taytaGmailTiedoilla(); 
    pst.lisaa(gmail2); 
    assertEquals("From: Paasyt line: 131", 2, pst.getLkm()); 
    try {
    pst.anna(2); 
    fail("Paasyt: 132 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}