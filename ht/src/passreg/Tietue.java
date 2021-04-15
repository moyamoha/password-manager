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
    
    /**
     * @return tiedon merkkijonoesityksen eli milt� se n�ytt�isi k�ytt�liittym�ss�
     */
    public String getView();
    
    /**
     * Asetetaan k:nnennen kent�n arvo
     * @param k kent�n numero
     * @param arvo asetettava arvo
     * @return virheen jos asettaminen ei onnistu
     */
    public String aseta(int k, String arvo);
    
    /**
     * Palautetaan k:nnennen kent�n arvo
     * @param k kent�n numero
     * @return kent�n arvo
     */
    public String anna(int k);
    
    /**
     * @return ensimm�isen muokattavissa olevan kent�n numero 
     */
    public int ekaKentta();
    
    /**
     * Palautetaan montako kentt� tietueell� on
     * @return muokattavien kenttien lukum��r�, 
     */
    public int kenttaLkm();
    
}
