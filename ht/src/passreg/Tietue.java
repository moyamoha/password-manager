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
public interface Tietue {

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
    
    /**
     * @return tiedon merkkijonoesityksen eli miltä se näyttäisi käyttöliittymässä
     */
    public String getView();
    
    /**
     * Asetetaan k:nnennen kentän arvo
     * @param k kentän numero
     * @param arvo asetettava arvo
     * @return virheen jos asettaminen ei onnistu
     */
    public String aseta(int k, String arvo);
    
    /**
     * Palautetaan k:nnennen kentän arvo
     * @param k kentän numero
     * @return kentän arvo
     */
    public String anna(int k);
    
    /**
     * @return ensimmäisen muokattavissa olevan kentän numero 
     */
    public int ekaKentta();
    
    /**
     * Palautetaan montako kenttä tietueellä on
     * @return muokattavien kenttien lukumäärä, 
     */
    public int kenttaLkm();
    
}
