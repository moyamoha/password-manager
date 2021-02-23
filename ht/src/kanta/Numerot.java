/**
 * 
 */
package kanta;

import java.util.Random;

/**
 * @author Yahya
 * @version 22.2.2021
 *
 */
public class Numerot {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }
    
    /**
     * Generoidaan satunnainen kokonaisluku annetulta väliltä
     * @param alaraja luku josta alkaen generoidaan satunnaisluku
     * @param ylaraja luku johon asti generoidaan satunnaisluku
     * @return satunnainen kokonaisluku
     */
    public static int rand(int alaraja, int ylaraja) {
        Random rand = new Random();
        double n = rand.nextDouble() * (ylaraja - alaraja) + alaraja;
        return (int) Math.round(n);
    }

}
