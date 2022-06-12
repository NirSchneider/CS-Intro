/*
 * Task4b
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 1/11/19
 */

import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------

Scanner myScanner = new Scanner (System.in);
        int n = myScanner.nextInt(); 
        int counter=0;
        
        while(n>1)
        {
            int divisor =2;
            boolean isPrime = true;
        	while(divisor*divisor<=n && isPrime)
        	{
            	if(n%divisor==0)
            		isPrime = false;
            	divisor++;
            }
        	if(isPrime)
        		counter++;
        	n--;
        }
        System.out.println(counter);        //---------------write your code ABOVE this line only!--------------
    }

    
}