/*
 * Task4e
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 10/11/19
 */

import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------
	 Scanner myScanner = new Scanner (System.in);
        int n = myScanner.nextInt();
        int b = myScanner.nextInt();
        int s = myScanner.nextInt();
        int d = myScanner.nextInt();
        int x1=d;
        int x2=1;
        boolean check = true;
        int x6;
        
        while(x1>0) // x2=(b^d)%n
        {
        	x2=(x2*b)%n;
        	x1--;
        }//while
        
        if(x2!=1)
        {
        	for(int i=0; check && i<s ;i++)
        	{
        		int x3=i;
        		int x4=1;
        		while(x3>0) // x4 = 2^i
        		{
        			x4=x4*2;
        			x3--;
        		}//while
        		
        		int x5 = x4*d; // x5=d*(2^i)
        		x6=1;
        		while(x5>0) // x6=(b^(d*2^i))%n
                {
                	x6=(x6*b)%n;
                	x5--;
                }//while
        		
        		if(x6==n-1)
        			check = false;
        	}//for
        }//if
        else
        	check = false;
        
       
        
        if(check)
            System.out.println(b +" is a witness. "+ n +" is composite");
        else
            System.out.println("we assume "+ n +" is prime");

        //---------------write your code ABOVE this line only!--------------
    }

    
}