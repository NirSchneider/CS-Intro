/*
 * Task3b
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 1/11/19
 */

import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------


        Scanner myScanner = new Scanner (System.in);
        int n = myScanner.nextInt(); 
        int k = myScanner.nextInt(); 
        int sum = 1;
	   
	   while (n>0)
	   {
		   sum=(sum*2)%k;	
		   n=n-1;	
	   }
	
	   System.out.println(sum);




        //---------------write your code ABOVE this line only!--------------
    }

    
}