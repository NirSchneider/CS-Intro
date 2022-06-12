/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
import java.math.BigInteger;

public class NumberAsBits {

    private Bit[] bits;

    public NumberAsBits(Bit[] bits) 
    {
        //Task 3.4
    	if(bits != null)    	
    	{
        	this.bits = new Bit[bits.length];
    		for (int i = 0; i < bits.length; i++) 
        	{
				Bit tmp;
    			if(bits[i]==null)
    				throw new IllegalArgumentException("invalid input - array has null bit");
    			else if(bits[i].toInt()==1)
    				tmp = new Bit(true);
    			else
    				tmp = new Bit(false);

    			this.bits[i]=tmp;//necessary not to point to an accessible variable for user
    		}
    	}
    	else
    		throw new IllegalArgumentException("invalid input - matrix is null");
    	
    }
    
    public String toString() { 
        //Task 3.5
        BigInteger ans = BigInteger.ZERO;
        BigInteger binary = BigInteger.ONE;
        BigInteger counter = BigInteger.ONE;

        BigInteger two = BigInteger.valueOf(2);

        if(this.bits!=null)
        {
        	for (BigInteger i =  BigInteger.valueOf(this.bits.length-1); i.compareTo(BigInteger.ZERO)!=-1 ; i = i.subtract(BigInteger.ONE))
            {
        		
    			if(this.bits[i.intValue()].toString()=="1")
    				ans = ans.add(binary.multiply(counter));//building base 10 bit value
    			binary = binary.multiply(two); //enlarge variable to modify bit value from base 2 to base 10
    		}
        }
        return ans.toString();
    }
      

    public String base2() {
        String ans ="";
        //Task 3.6
        if(this.bits!=null)
        {
        	for (int i = 0; i < this.bits.length ; i++)
            { 		
                ans = ans+bits[i].toString();//bit represent binary value
    		}
        }
        return ans;
    }
    
//    public static void main(String[] args) 
//    {   
//    	Bit bitT = new Bit(true); 
//    	Bit[] bits = { bitT, new Bit(false), new Bit(false), new Bit(false)};
//    	NumberAsBits number = new NumberAsBits (bits);
//    	System.out.println(number.toString()); // 8
//    	System.out.println(number.base2()); // 1000
//    	bitT = new Bit(false);
//    	bits[1] = new Bit(true);
//    	System.out.println(number.toString()); // 8 
//    	System.out.println(number.base2()); // 1000 
//    	
//    	Bit[] longBits = new Bit[60];
//    	for(int i = 0; i < longBits.length; i++) {
//    		if(i%2 == 0) {
//    			longBits[i] = new Bit(true);
//    		}
//    		else {
//    			longBits[i] = new Bit(false);
//    		}
//    	}
//    	NumberAsBits longNumber = new NumberAsBits(longBits);
//    	System.out.println(longNumber.toString());
//    	System.out.println(longNumber.base2());
//    }
}


