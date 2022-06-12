/*
 * Task4f
 *
 * Authentic author: Nir Schneider
 * I.D.: 316098052
 * Last update: 10/11/19
 */

import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        //---------------write your code BELOW this line only!--------------


	 Scanner myScanner = new Scanner (System.in);
        int n = myScanner.nextInt();
        int s = myScanner.nextInt();
        int d = myScanner.nextInt();
        int k = myScanner.nextInt();
        boolean checkf = true;
    	int counter=0;

        int b=0;       
        int x7=k;

        while(checkf && x7>0)
        {
        	b = (int)(Math.random()*n-2);
        	b=b+2;
        	int x1=d;
            int x2=1;
            int x6=1;
        	boolean checke=true;

        	while(x1>0) // x2=(b^d)%n
            {
            	x2=(x2*b)%n;
            	x1--;
            }//while
            
            if(x2!=1)
            {
            	for(int i=0; checke && i<s ;i++)
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
            			checke = false;
            	}//for
            }//if
            else
            	checke = false;
            
            if(checke)
            	counter++;
            else
            	checkf=false;
            x7--;
        }//while
        
        
        
        if(counter==k)
            System.out.println(b+" is a witness. "+ n +" is composite");
        else
            System.out.println("we assume "+ n +" is prime");


        //---------------write your code ABOVE this line only!--------------
    }

    
}