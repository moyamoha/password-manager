/**
 * 
 */
package passreg;

/**
 * @author Yahya
 * @version 18.2.2021
 *
 */
public class Passreg {
    
    private final Paasyt paasyt = new Paasyt();
    
    /**
     * @return rekisterin p‰‰sym‰‰r‰
     */
    public int getPaasytLkm() {
        return paasyt.getLkm();
    }
    
    /**
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     * @return montako p‰‰sy‰ poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
        //
    }
    
    /**
     * @param paasy lis‰tt‰v‰ p‰‰sy
     */
    public void lisaa(Paasy paasy) {
        paasyt.lisaa(paasy);
    }
    
    /**
     * @param i p‰‰sy indeksi
     * @return viite kyseiseen j‰seneen
     */
    public Paasy annaPaasy(int i) {
        return paasyt.anna(i);
    }
}
