package kanta.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static kanta.Numbers.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.25 13:53:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NumerotTest {


  // Generated by ComTest BEGIN
  /** testRand21 */
  @Test
  public void testRand21() {    // Numbers: 21
    int num = rand(10, 20); 
    assertEquals("From: Numbers line: 23", true, (num >= 10 && num <= 20)); 
    int num2 = rand(0, 100); 
    assertEquals("From: Numbers line: 25", false, (num2 >= 100 && num <= 0)); 
    int num3 = rand(10, 10); 
    assertEquals("From: Numbers line: 27", 10, num3); 
  } // Generated by ComTest END
}