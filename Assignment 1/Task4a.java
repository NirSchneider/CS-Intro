/*
 * Task4a
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 1/11/19
 */

import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------

	  Scanner myScanner = new Scanner (System.in);
        int num = myScanner.nextInt(); 
        boolean isPrime = true;
        int divisor =2;
        
        while(divisor*divisor<=num && isPrime)
        {
        	if(num%divisor==0)
        		isPrime = false;
        	divisor++;
        }
        if(isPrime)
            System.out.println("prime");
        else
            System.out.println("composite");




        //---------------write your code ABOVE this line only!--------------
    }

    
}