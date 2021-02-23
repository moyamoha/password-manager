/**
 * 
 */
package passreg;

/**
 * @author Yahya
 * @version 17.2.2021
 *
 */
public class Paasyt {
    
    private static int MAX_KOKO = 3;
    private Paasy[] alkiot;
    private int lkm;
    
    /**
     * 
     */
    public Paasyt() {
        this.alkiot = new Paasy[MAX_KOKO];
    }
    
    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Paasyt paasyt = new Paasyt();
        
        Paasy gmail1 = new Paasy();
        gmail1.rekisteroi();
        gmail1.taytaGmailTiedoilla();
        
        
        Paasy gmail2 = new Paasy();
        gmail2.rekisteroi();
        gmail2.taytaGmailTiedoilla();
        
        Paasy gmail3 = new Paasy();
        gmail3.rekisteroi();
        gmail3.taytaGmailTiedoilla();
        
        paasyt.lisaa(gmail1); paasyt.lisaa(gmail2);
        paasyt.lisaa(gmail3);
        
        for (int i = 0; i < paasyt.alkiot.length; i++) {
            paasyt.alkiot[i].tulosta(System.out);
            System.out.println();
        }
       
        Paasy gmail4 = new Paasy();
        gmail4.rekisteroi();
        gmail4.taytaGmailTiedoilla();
        
        gmail4.tulosta(System.out);
        
        paasyt.lisaa(gmail4);
    }
    
    /**
     * Lis�t��n yksitt�inen p��sy tietorakenteeseen
     * @param paasy lis�tt�v� p��sy
     */
    public void lisaa(Paasy paasy) {        
        if (getLkm() >= alkiot.length) {
            luoJaKopioi(); 
            lisaa(paasy);
        }
        else this.alkiot[lkm++] = paasy;
    }

    /**
     * Kun p��syt tulee t�yteen kutsutaan t�t�. Luodaan uusi taulukko, jonka tilaa on kaksinkertainen. 
     * Samalla kopioidaan alkiot uuteen taulukkoon ja Tuhotaan vanha taulukko.
     */
    private final void luoJaKopioi() {
        MAX_KOKO = 2 * MAX_KOKO;
        Paasy[] apuTaul = new Paasy[MAX_KOKO];
        for (int i = 0; i < getLkm(); i++) {
            apuTaul[i] = this.alkiot[i];
        }
        this.alkiot = apuTaul;
        apuTaul = null;
    }

    /**
     * @return p��syjen lukum��r�
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * @param i p��syn indeksi taulukossa <b>alkiot</b>
     * @return viite P��sy-olioon
     * @throws IndexOutOfBoundsException jos indeksi ei ole sopiva
     */
    public Paasy anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) {
            throw new IndexOutOfBoundsException("Huono indeksi: " + i);
        }
        return this.alkiot[i];
    }
}