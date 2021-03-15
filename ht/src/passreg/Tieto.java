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
     * rekister�id��n
     */
    public void rekisteroi();
    
    /**
     * Palauttaa tunnusnumeron
     * @return tunnusnumero 
     */
    public int getTunnusNro();
    
    /**
     * Osittaa rivin ja lukee kent�t rivilt�
     * @param rivi josta luetaan kent�t
     */
    public void parse(String rivi);
    
    /**
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out);
    
    @Override
    public String toString();
}
