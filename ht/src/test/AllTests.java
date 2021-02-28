package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses; 
/**
 * Test suite passreg-ohjelmalle
 * @author yahya
 * @version 27.2.2021
 */
@RunWith(Suite.class)
@SuiteClasses({
    kanta.test.MerkkijonotTest.class,
    kanta.test.NumerotTest.class,
    kanta.test.TarkistuksetTest.class,
    passreg.test.PaasyTest.class,
    passreg.test.PaasytTest.class,
    })
public class AllTests {
 //
 }