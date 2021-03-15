/**
 * 
 */
package passreg;

import java.io.PrintStream;

/**
 * @author Yahya
 * @version 14.3.2021
 *
 */
public interface Tieto {

    /**
     * rekisteröidään
     */
    public void rekisteroi();
    
    /**
     * Palauttaa tunnusnumeron
     * @return tunnusnumero 
     */
    public int getTunnusNro();
    
    /**
     * Osittaa rivin ja lukee kentät riviltä
     * @param rivi josta luetaan kentät
     */
    public void parse(String rivi);
    
    /**
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out);
    
    @Override
    public String toString();
}
