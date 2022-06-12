/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
    	if(c < '0' | c>'9')
    		throw new IllegalArgumentException("input not in range");
    	
        bits = new BitList();
    	int tmp = c-48; // 48 is the int value of char '0'
    	
    	while(tmp!=0)
    	{
    		if(tmp%2 == 0) //find whether the last bit is 1 or 0
    			bits.addLast(Bit.ZERO);
    		else
    			bits.addLast(Bit.ONE);
    		tmp=tmp/2; // needed to find the next bit according to base2
    	}
    	bits.addLast(Bit.ZERO); // 0-9 are positive numbers so the signed bit is 0
    	
    }

  //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        
    	String output = "";

    	for(Bit item  : this.bits) //running from right to left
    	{
    		output = item.toString() + output; //ad to the left the current bit 
    	}
    	
    	return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
//    	if(!isLegal())
//    		throw new IllegalArgumentException("invalid input");
    	if(!(other instanceof BinaryNumber))
    		throw new IllegalArgumentException("not a binary number");

    	boolean ans = false;
    	if(this.compareTo((BinaryNumber)other)==0) // if equals compare to return 0
    		ans=true;
    		
    	return ans;
    	
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {

    	BinaryNumber ans = new BinaryNumber(0);
    	ans.bits.removeLast();
    	
    	if(this.bits.size()>addMe.bits.size()) // pad by the larger binary number (by size)
    		addMe.bits.padding(this.bits.size());
    	else
    		this.bits.padding(addMe.bits.size());
    	
    	if(this.compareTo(addMe.negate())!=0) // if this=x and addMe=-x skip the check
    	{
    		Iterator<Bit> itThis = this.bits.iterator();
        	Iterator<Bit> itAddMe = addMe.bits.iterator();
        	Bit carry = new Bit(0);
        	while(itThis.hasNext())
        	{
        		Bit bitThis = itThis.next();
        		Bit bitAddMe = itAddMe.next();
        		ans.bits.addLast(Bit.fullAdderSum(bitThis, bitAddMe, carry)); // add every two bit in the same index considering the carry as well
        		carry = Bit.fullAdderCarry(bitThis, bitAddMe, carry);
        	}
        	if(carry.toInt()==1)
        		ans.bits.addLast(carry);
        	if((this.signum()!=-1 & addMe.signum()!=-1)) // if both are positive
        		ans.bits.addLast(Bit.ZERO);
        	else if (this.signum()==1 & addMe.signum()==-1 & this.compareTo(addMe.negate())==1) // if the negative number absolute value is smaller don't take carry
    			ans.bits.removeLast();
        	else if (this.signum()==-1 & addMe.signum()==1 & this.negate().compareTo(addMe)==-1)
    			ans.bits.removeLast();
        	else 
        		ans.bits.addLast(Bit.ONE);
        	ans.bits.reduce();
    	}
    	else
    		ans.bits.addLast(Bit.ZERO);	
    	
    	return ans;

    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
    	BinaryNumber negative = new BinaryNumber('0');
    	negative.bits.removeLast();
    	boolean zeroFound = false;
    	
    	Iterator <Bit> it = this.bits.complement().iterator();
    	while(it.hasNext())
    	{
    		Bit nextbit = it.next();
    		if(!zeroFound) // need to negate every bit until a zero found (include)
    		{
    			negative.bits.addLast(nextbit.negate());
    			if(nextbit.equals(Bit.ZERO))
    				zeroFound = true;	
    		}
    		else // if a zero found copy the bits as they appear in the complement form
    			negative.bits.addLast(nextbit);
    	}
    	
    	return negative;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	BinaryNumber ans = new BinaryNumber('0');
        //ans.bits.removeFirst();
        ans = ans.add(this);
        ans = ans.add(subtractMe.negate()); // x-y == x+(-y)
        return ans;
        
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
    	int output=7; // 7 is a meaningless value for the code to compile
    	this.bits.reduce();
    	if(this.bits.getLast().toInt()==1) // signed bit 1 is a negative number
    		output = -1;
    	else if (this.bits.getLast().toInt()==0 & this.bits.size()>1) // signed bit 0 is a positive number
    		output = 1;
    	else // if (this.bits.getLast().toInt()==0 & this.bits.size()==1)
    		output=0;
    	
    	return output;

    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
    	int output=0;
    	
    	BinaryNumber tmpThis = new BinaryNumber(this);
    	BinaryNumber tmpOther = new BinaryNumber(other);
    	tmpOther.bits.reduce();
    	tmpThis.bits.reduce();
    	
      	if(tmpThis.signum() == tmpOther.signum() ) // both positive or negative
      	{
      		if(tmpThis.bits.size()== tmpOther.bits.size()) // in reduced positive form the bigger size equals to the bigger value
      		{
      			tmpThis.bits.removeLast();
      			tmpOther.bits.removeLast();
      			while(output==0 & tmpThis.bits.size()!=0)
          		{
          			
          			if(tmpThis.bits.getLast().toInt() > tmpOther.bits.getLast().toInt()) //checking for diffrence in the most significant bit (the last bit)
          				output =1;
          			else if(tmpThis.bits.getLast().toInt() < tmpOther.bits.getLast().toInt()) //checking for diffrence in the most significant bit (the last bit)
          				output=-1;
          			tmpThis.bits.removeLast();
          			tmpOther.bits.removeLast();
          		}
      		}
      		else if((tmpThis.bits.size() > tmpOther.bits.size() & tmpThis.signum()==1) | (tmpThis.bits.size() < tmpOther.bits.size() & tmpThis.signum()==-1)) // in (reduced) negative numbers bigger size equals to smaller value
      			output=1;
      		else if((tmpThis.bits.size() < tmpOther.bits.size() & tmpThis.signum()==1) | (tmpThis.bits.size() > tmpOther.bits.size() & tmpThis.signum()==-1)) // in (reduced) positive numbers bigger size equals to bigger value
      			output=-1;			
      	}
      	else
      	{
      		if(tmpThis.signum()==1)
      			output=1;
      		else
      			output=-1;
      	}
    	
		return output;

    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        if(this.bits.size()>32)
        	throw new RuntimeException("over int max value");
        return this.bits.toInt();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	if(!multiplyMe.isLegal())
    		throw new IllegalArgumentException();
    	BinaryNumber ans = new BinaryNumber(ZERO);
    	if(this.signum()== multiplyMe.signum() & this.signum()!=-1) // positive*positive=positive
    		ans = this.multiplyPositive(multiplyMe);
    	else if(this.signum()== multiplyMe.signum() & this.signum()==-1) // negative*negative=positive
    		ans = this.negate().multiplyPositive(multiplyMe.negate());
    	else if(this.signum()!= multiplyMe.signum() & this.signum()==-1) // negative*positive=negative
    	{
    		ans = this.negate().multiplyPositive(multiplyMe);
    		ans = ans.negate();
    	}
    	else // positive*negative=negative
    	{
    		ans = this.multiplyPositive(multiplyMe.negate());
    		ans = ans.negate();
    	}

    	return ans;
    	
    }

    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	int counter=0;
    	BinaryNumber ans = new BinaryNumber(ZERO);
    	BinaryNumber addMe;
    	Iterator<Bit> it;
    	
   
    	if(this.bits.getNumberOfOnes()<multiplyMe.bits.getNumberOfOnes()) // choosing the binary number with less numberofones to run on for efficiency
    	{
    		it = this.bits.iterator();
    		addMe = new BinaryNumber(multiplyMe);
    	}
    	else
    	{
    		it = multiplyMe.bits.iterator();
    		addMe = new BinaryNumber(this);
    	}
    	while(it.hasNext())
    	{
   			if(it.next().toInt()==1) // only add in case of bit=1
   			{
   				for (; counter >0; counter--) // counter- count how many shifting left is needed (in case of encountering in bit=0
   				{
					addMe.bits.shiftLeft();
				}
   				ans=ans.add(addMe);
   			}
   			counter++; // if bit=0 the counter keep getting bigger to count how many time shifting left is needed
   		}
    	return ans;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	
    	if(!divisor.isLegal())
    		throw new IllegalArgumentException();   	
    	BinaryNumber ans = new BinaryNumber(ZERO);
    	if(this.signum()== divisor.signum() & this.signum()!=-1) // positive/positive=positive
    		ans = this.dividePositive(divisor);
    	else if(this.signum()== divisor.signum() & this.signum()==-1) // negative/negative=positive
    		ans = this.negate().dividePositive(divisor.negate());
    	else if(this.signum()!= divisor.signum() & this.signum()==-1) // negative/positive=negative
    	{
    		ans = this.negate().dividePositive(divisor);
    		ans = ans.negate();
    	}
    	else // positive/negative=negative
    	{
    		ans = this.dividePositive(divisor.negate());
    		ans = ans.negate();
    	}

    	return ans;  	
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {
    	
    	BinaryNumber ans = new BinaryNumber('0');
    	if(this.compareTo(divisor)==-1)
    		return ans;
    	BinaryNumber bn = new BinaryNumber(this);
    	bn.bits = bn.bits.reverse();

        Iterator<Bit> it = bn.bits.iterator();
        BinaryNumber remainder = new BinaryNumber(it.next().toInt());
        while(it.hasNext()) // long number division
        {
        	if(remainder.compareTo(divisor)==-1) // if remainder is smaller than divisor, add the next bit and to the answer 0
        	{
        		remainder.bits.addFirst(it.next());
        		ans.bits.addFirst(Bit.ZERO);
        	}
        	else // if remainder is bigger than divisor subtract divisor, from remainder and add the next bit and add to the answer 1
        	{
        		remainder = remainder.subtract(divisor);
        		remainder.bits.addFirst(it.next());
        		ans.bits.addFirst(Bit.ONE);
        	}
        }
        if(remainder.compareTo(divisor)!=-1) // if remainder is equal or bigger from divisor add 1 again
    		ans.bits.addFirst(Bit.ONE);
        else
    		ans.bits.addFirst(Bit.ZERO);
        ans.bits.reduce();
        return ans;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
    	if(s==null | s=="" /* | don't represent number - the check achieved during the code*/)
    		throw new IllegalArgumentException("invalid input");
    	
    	BinaryNumber ten = new BinaryNumber('9');
    	ten=ten.add(ONE);

        bits = new BitList();
        BinaryNumber ans= new BinaryNumber(0);
        
        boolean isNegative = false;
        if(s.charAt(0)=='-')
    		isNegative = true;
    	BinaryNumber tmp;
      
        for (int i = 0;(isNegative & i+1 < s.length()-1) | (!isNegative & i<s.length()-1); i++) // if positive scan all "numbers" if negative don't scan the '-'
        {
        	if(isNegative)
        		tmp = new BinaryNumber(s.charAt(i+1)); // don't take the '-'
        	else
        		tmp = new BinaryNumber(s.charAt(i));

        	ans=ans.add(tmp); // add the current unity digit
        	ans=ans.multiply(ten); // multiply by ten to add another digit/the next unity digit
		}
        tmp = new BinaryNumber(s.charAt(s.length()-1)); // last digit by the inputed string
    	ans=ans.add(tmp); // add final unity digit

        if(isNegative) // if negative negate the final answer
        	ans = ans.negate();
        bits = ans.bits; // change the field (bits) of the operating object
    	
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
       
        String s ="";
        if(this.equals(ZERO))
        	return s+0;
        BinaryNumber bn = new BinaryNumber(this);
        BinaryNumber ten = new BinaryNumber('9');
    	ten=ten.add(ONE);
    	boolean isNegative = false;
    	if(bn.signum()==-1) // check if negative
    	{
    		isNegative=true;
    		bn=bn.negate(); // if negative negate to positive
    	}
    	while(bn.bits.size()!=1)
    	{
        	int counter=0;
        	int base=1; // will multiply by 2 according to base2
    		Iterator<Bit> it = bn.bits.iterator();
        	while(it.hasNext())
        	{
        		Bit tmp = it.next();
        		if(tmp.toInt()==1) 
        			counter = (counter + base*tmp.toInt())%10; //unity digit - (counter + 1* 1/2/4/8/6)%10
        		
        		base=(base*2)%10; // calculating the unity digit [binary base - 1 2 4 8 (1)6 (3)2 (6)4 (12)8 (25)6 (51)2...]
        	}
    		s = counter + s; // add current unity digit on the left
        	bn = bn.divide(ten); // dividing by ten to calculate the next digit/the next unity digit
    	}
    	if(isNegative) // if is negative add "-" at the end
    		s= "-" +s;
    	
    	return s;
        
    }

    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
    
    //functions for testing:
    public BitList getBits(){
    	return bits;
    	}
    public BinaryNumber(BitList bits) {
        this.bits = new BitList(bits);
    }
    
    
//    public static void main(String[] args) 
//    {
//    	System.out.println("test for 3.1 + 3.2 :");
//
//    	BinaryNumber bn2 = new BinaryNumber ('5'); // 0101 (5)
//    	System.out.println(bn2); // prints 0101 
//    	System.out.println();
//    	
//    	System.out.println("test for 3.3 :");
//    	BinaryNumber bn31 = new BinaryNumber('5');  // 0101 (5)
//    	BinaryNumber bn32 = new BinaryNumber('5'); // 0101 (5)
//    	BinaryNumber bn33 = new BinaryNumber('6');  // 0110 (6) 
//    	System.out.println(bn31.equals(bn32)); // prints true 
//    	System.out.println(bn31.equals(bn33));  // prints false
//    	System.out.println();
//    	
//    	System.out.println("test for 3.4 :");
//    	BinaryNumber bn41  = new BinaryNumber('3'); //   011 (3) 
//    	BinaryNumber bn42  = new BinaryNumber('5'); //  0101 (5)
//    	BinaryNumber bn43  = new BinaryNumber('8'); // 01000 (8) 
//    	System.out.println(bn41.add(bn42)); // prints 01000 which is the minimal binary representation of 8. 
//    	System.out.println(bn43.add(bn41)); // prints 01011 which is the minimal binary representation of 11. 
//    	
//    	BinaryNumber bn44  = new BinaryNumber(0);
//    	bn44.bits.addLast(Bit.ONE);
//    	bn44.bits.addLast(Bit.ONE);
//    	BinaryNumber bn45  = new BinaryNumber(0);
//    	bn45.bits.addLast(Bit.ONE);
//    	bn45.bits.addLast(Bit.ONE);
//    	System.out.println(bn45.add(bn44)); // -4 
//    	System.out.println();
//    	
//    	System.out.println("test for 3.5 :");
//    	BinaryNumber bn51 = new BinaryNumber('5'); // 0101 (5) 
//    	BinaryNumber bn52 = bn51.negate();         // 1011 (-5) 
//    	System.out.println(bn52); //prints 1011 
//    	System.out.println();
//
//    	System.out.println("test for 3.6 :");
//    	BinaryNumber bn61  = new BinaryNumber ('5'); // 0101    (5) 
//    	BinaryNumber bn62  = new BinaryNumber ('3'); //  011    (3) 
//    	BinaryNumber bn63 = bn62.subtract(bn61);      //  110    (-2)                
//    	BinaryNumber bn64  = bn61.subtract(bn62);      //  010    (2) 
//    	System.out.println(bn64);                    // prints 010 
//    	System.out.println(bn63.subtract(bn64));      // prints 1100 
//    	System.out.println();
//
//    	System.out.println("test for 3.7 :");
//    	BinaryNumber bn71 = new BinaryNumber('5');     // 0101    (5) 
//    	BinaryNumber bn72 = new BinaryNumber('2'); //  010    (2) 
//    	BinaryNumber bn73 = bn71.subtract(bn72);  //  011    (3)  
//    	System.out.println(bn73.signum());    // prints 1 
//    	BinaryNumber bn74 = bn73.subtract(bn71); //  101    (-3) 
//    	System.out.println(bn74.signum());    //prints -1 
//    	System.out.println();
//    	
//    	System.out.println("test for 3.8 :");
//    	BinaryNumber bn81 = new BinaryNumber('5'); // 0101 (5) 
//    	BinaryNumber bn82 = new BinaryNumber('4'); // 0100 (4) 
//    	BinaryNumber bn83 = new BinaryNumber('4'); // 0100 (4) 
//    	System.out.println(bn81.compareTo(bn82)); // prints 1 
//    	System.out.println(bn83.compareTo(bn83)); // prints 0 
//    	System.out.println(bn82.compareTo(bn81)); // print -1 
//    	System.out.println();
//
//    	System.out.println("test for 3.9 :");
//    	BinaryNumber bn91 = new BinaryNumber('5'); // 0101 (5) 
//    	BinaryNumber bn92 = new BinaryNumber('4'); // 0100 (4) 
//    	System.out.println(bn91.add(bn92).toInt());      // prints 9 
//    	System.out.println(bn92.subtract(bn91).toInt()); // prints -1
//    	System.out.println();
//    	
//    	System.out.println("test for 3.10 :");
//    	BinaryNumber bn101   = new BinaryNumber('5'); // 0101 (5)  
//    	BinaryNumber bn102  = bn101.negate();          // 1011 (-5) 
//    	BinaryNumber bn103   = new BinaryNumber('4'); // 0100 (4) 
//    	BinaryNumber bn104 = bn102.multiply(bn103);    // 101100 (-20) 
//    	System.out.println(bn104.toInt());          // prints -20 
//    	System.out.println();
//
//    	System.out.println("test for 3.11 :");
//    	BinaryNumber bn111   = new BinaryNumber('9'); // 01001 (9) 
//    	BinaryNumber bn112  = bn111.negate();          // 10111 (-9) 
//    	BinaryNumber bn113   = new BinaryNumber('3'); //   011 (3) 
//    	BinaryNumber bn114   = new BinaryNumber('2'); //   010 (2) 
//    	BinaryNumber bn115  = bn112.divide(bn113);      //   101 (-3) 
//    	BinaryNumber bn116  = bn112.divide(bn114);      //  1100 (-4) 
//    	System.out.println(bn115.toInt());           // prints -3 
//    	System.out.println(bn116.toInt());           // prints -4
//    	System.out.println();
//    	
//    	System.out.println("test for 3.12 :");
//    	BinaryNumber bn121  = new BinaryNumber("10");   // 01010 (10) 
//    	BinaryNumber bn122 = new BinaryNumber("-10");  // 10110 (-10) 
//    	System.out.println(bn121.toInt());  // prints 10 
//    	System.out.println(bn122.toInt()); // prints -10 
//    	System.out.println();
//    	
//    	System.out.println("test for 3.13 :");
//    	BinaryNumber fib100 = new BinaryNumber("354224848179261915075");    // 0100110011001111011011011101101010011111000101100101001011111111000011 0100110011001111011011011101101010011111000101100101001011111110111110
//    	System.out.println(fib100.toIntString()); // prints 354224848179261915075 
//    	BinaryNumber mFib100 = fib100.negate(); // 1011001100110000100100100010010101100000111010011010110100000000111101
//    	System.out.println(mFib100.toIntString()); // prints -354224848179261915075 
//    	
//    	
//    	BinaryNumber t1 = new BinaryNumber("179333359355391");  
//    	BinaryNumber t2 = new BinaryNumber("633602849417528");  
//    	System.out.println(t1.divide(t2));
//
//    }


}
