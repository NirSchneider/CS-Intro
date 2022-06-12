/*
 * Task2
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 1/11/19
 */

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
        Scanner myScanner = new Scanner (System.in);

        int a  = myScanner.nextInt(); //min input
        int b  = myScanner.nextInt(); //max input
	  int range = b-a+1; // define the range
	  System.out.println((int)(Math.random()*range)+a);




        //---------------write your code ABOVE this line only!--------------
    }

    
}