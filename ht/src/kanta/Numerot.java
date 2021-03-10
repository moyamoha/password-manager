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
     * Generoidaan satunnainen kokonaisluku annetulta väliltä
     * @param alaraja luku josta alkaen generoidaan satunnaisluku
     * @param ylaraja luku johon asti generoidaan satunnaisluku
     * @return satunnainen kokonaisluku
     * @example
     * <pre name="test">
     *    int num = rand(10, 20);
     *    (num >= 10 && num <= 20)  === true;
     *    int num2 = rand(0, 100);
     *    (num2 >= 100 && num <= 0) === false; 
     *    int num3 = rand(10, 10);
     *    num3 === 10;
     * </pre>
     */
    public static int rand(int alaraja, int ylaraja) {
        if (ylaraja == alaraja) return alaraja;
        Random rand = new Random();
        double n = rand.nextDouble() * (ylaraja - alaraja) + alaraja;
        return (int) Math.round(n);
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }
    

}
