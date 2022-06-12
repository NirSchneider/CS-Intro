/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
import java.math.BigInteger;
import java.util.Random;

class Part1{

    public static BigInteger sumSmaller(BigInteger n)
    {
    	BigInteger sum =  new BigInteger("0");
        //Task 1.1
    	n=n.subtract(BigInteger.ONE);//not include n
		while(0<n.intValue())
		{
			sum = sum.add(n); //sum=sum+n
			n=n.subtract(BigInteger.ONE); //n=n-1
		}
        
        return sum;
    }

    public static void printRandoms(int n)
    {
        //Task 1.2
    	Random r = new Random();
    	for (int i = 0; i < n; i++) 
    	{
        	System.out.println(r.nextInt());
		}
    	
    }

    public static   boolean isPrime(BigInteger n){
        boolean ans= true;
        //Task 1.3
        if(n.equals(BigInteger.ONE)|n.equals(BigInteger.ZERO)) // zero and one are not primes number
        	ans=false;
        else
        {
        	for (BigInteger divisor = new BigInteger("2");ans & n.compareTo(divisor.pow(2))!=-1 ; divisor = divisor.add(BigInteger.ONE))//condition is the same as divisor^2<n
        	{
				if(n.mod(divisor).equals(BigInteger.ZERO))//condition is the same as n%divisor==0
					ans=false;
			}
        }
        return ans;
    }

    public static BigInteger randomPrime(int n){
        //Task 1.4
        Random r = new Random();
        
        BigInteger ans = new BigInteger(n,r);
        while(!isPrime(ans))//search for a prime number
        	ans = new BigInteger(n,r);

        return ans;
    }

//    public static void main(String[] args)
//    {
//        //test for part 1.1
//         BigInteger biMinus = new BigInteger("-10");
//         System.out.println("sumSmaller test expected 0 - got " + sumSmaller(biMinus));
//    
//         BigInteger bi0 = new BigInteger("0");
//         System.out.println("sumSmaller test expected 0 - got " + sumSmaller(bi0));
//    
//         BigInteger bi7 = new BigInteger("7");
//         System.out.println("sumSmaller test expected 21 - got " + sumSmaller(bi7));
//    
//         BigInteger biHigh = new BigInteger("99999");
//         System.out.println("sumSmaller test expected 4999850001 - got " + sumSmaller(biHigh)+"\n");
//        
//        //test for part 1.2
//         System.out.println("printRandoms 5");
//         printRandoms(5);
//         System.out.println("");
//
//        //test for part 1.3
//         BigInteger biVeryHigh = new BigInteger("2147521927");
//         System.out.println("isPrime test expectet true got " + isPrime(biVeryHigh));
//         biVeryHigh = new BigInteger("2147521921");
//         System.out.println(("isPrime test expectet false got " +  isPrime(biVeryHigh))+"\n");
//        
//        //test for part 1.4
//         System.out.println("randomPrime test");
//         System.out.println(randomPrime(20));
//         System.out.println(randomPrime(40));
//    }
}