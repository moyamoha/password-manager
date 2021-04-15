package kanta.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static kanta.Tarkistukset.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.26 18:13:05 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TarkistuksetTest {



  // Generated by ComTest BEGIN
  /** testOnValidiEmail29 */
  @Test
  public void testOnValidiEmail29() {    // Tarkistukset: 29
    assertEquals("From: Tarkistukset line: 30", false, onValidiEmail("abc@gmail.com")); 
    assertEquals("From: Tarkistukset line: 31", true, onValidiEmail("abcdef@edu.hel.fi")); 
    assertEquals("From: Tarkistukset line: 32", false, onValidiEmail("@yahoo.")); 
    assertEquals("From: Tarkistukset line: 33", false, onValidiEmail("...@yahoo.com")); 
    assertEquals("From: Tarkistukset line: 34", true, onValidiEmail("kkk11kk@hotmail.com")); 
    assertEquals("From: Tarkistukset line: 35", false, onValidiEmail("matti.meikäläinen@gmail.com.")); 
    assertEquals("From: Tarkistukset line: 36", false, onValidiEmail("matti.meikäläinen@gmail.com")); 
    assertEquals("From: Tarkistukset line: 37", true, onValidiEmail("matti.meikalainen@gmail.com")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnValidiTunnus51 */
  @Test
  public void testOnValidiTunnus51() {    // Tarkistukset: 51
    assertEquals("From: Tarkistukset line: 52", true, onValidiTunnus("soturi123", 6, 30)); 
    assertEquals("From: Tarkistukset line: 53", false, onValidiTunnus("?!jdkds", 2, 10)); 
    assertEquals("From: Tarkistukset line: 54", false, onValidiTunnus("123UpQz", 10, 14)); 
    assertEquals("From: Tarkistukset line: 55", true, onValidiTunnus("MattiM" , 6, 12)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnValidiPuhelinNro66 */
  @Test
  public void testOnValidiPuhelinNro66() {    // Tarkistukset: 66
    assertEquals("From: Tarkistukset line: 67", true, onValidiPuhelinNro("0123456789")); 
    assertEquals("From: Tarkistukset line: 68", true, onValidiPuhelinNro("0093788351233")); 
    assertEquals("From: Tarkistukset line: 69", false, onValidiPuhelinNro("00abc")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnValidiSalasana82 */
  @Test
  public void testOnValidiSalasana82() {    // Tarkistukset: 82
    assertEquals("From: Tarkistukset line: 83", true, onValidiSalasana("abcde", 5, 20)); 
    assertEquals("From: Tarkistukset line: 84", false, onValidiSalasana("", 5, 20)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnValidiOtsikko95 */
  @Test
  public void testOnValidiOtsikko95() {    // Tarkistukset: 95
    assertEquals("From: Tarkistukset line: 96", false, onValidiOtsikko("")); 
    assertEquals("From: Tarkistukset line: 97", false, onValidiOtsikko("    ")); 
    assertEquals("From: Tarkistukset line: 98", true, onValidiOtsikko(".")); 
    assertEquals("From: Tarkistukset line: 99", true, onValidiOtsikko("abc")); 
  } // Generated by ComTest END
}