package passreg.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import passreg.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.23 19:54:53 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KategoriaTest {



  // Generated by ComTest BEGIN
  /** testTaytaSomeenTiedoilla53 */
  @Test
  public void testTaytaSomeenTiedoilla53() {    // Kategoria: 53
    Kategoria kg = new Kategoria(); 
    kg.rekisteroi(); 
    kg.taytaSomeenTiedoilla(); 
    assertEquals("From: Kategoria line: 58", "some", kg.getNimi()); 
  } // Generated by ComTest END
}