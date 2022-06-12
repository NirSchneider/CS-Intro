/*
 * Task4d
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 3/11/19
 */

import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------


	 Scanner myScanner = new Scanner (System.in);
        int n = myScanner.nextInt(); 
        n--;
        int s=0;
        while(n%2==0)
        {
        	s++;
        	n=n/2;
        }
        System.out.println(s);
        System.out.println(n);



        //---------------write your code ABOVE this line only!--------------
    }

    
}