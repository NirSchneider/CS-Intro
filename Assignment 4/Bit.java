/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

public class Bit {

    private boolean value;
    public static final Bit ONE  = new Bit(true);
    public static final Bit ZERO = new Bit(false);

    public Bit(boolean value) {
        this.value = value;
    }

    public Bit(int intValue) {
        if (intValue == 0)
            value = false;
        else {
            if (intValue == 1)
                value = true;
            else throw new IllegalArgumentException(value + " is neither 0 nor 1.");
        }
    }

    public String toString() {
        return "" + toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Bit))
            return false;
        else return value == ((Bit) obj).value;
    }

    public Bit negate() {
        Bit output;
        if (value)
            output = ZERO;
        else output = ONE;
        return output;
    }

    public int toInt() {
        int output;
        if(value)
            output = 1;
        else
            output = 0;
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 1.1 ================================================
    public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {
        Bit sum = new Bit(true);
        
        if(!bit1.value & !bit2.value & !bit3.value) //0+0+0=0
        	sum.value = false;
        else if((bit1.value & bit2.value & !bit3.value) | (bit1.value & bit3.value & !bit2.value) | (bit2.value & bit3.value & !bit1.value)) //1+1=0 (with carry of 1)
        	sum.value = false;
      
        return sum;
    }

    public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
    	Bit carry = new Bit(false);
    	
        if((bit1.value & bit2.value) | (bit1.value & bit3.value) | (bit2.value & bit3.value)) // for at least two "1", got carry
        	carry.value = true;
      
        return carry;
    }
    
    
//    public static void main(String[] args) {
//    	 Bit b1 = new Bit(true);
//    	 Bit b0 = new Bit(false);
//    	 System.out.println(Bit.fullAdderCarry( b0, b0, b0)+" " + Bit.fullAdderSum( b0, b0, b0)); // prints 0 0
//    	 System.out.println(Bit.fullAdderCarry( b1, b0, b0)+" " + Bit.fullAdderSum( b1, b0, b0)); // prints 0 1
//    	 System.out.println(Bit.fullAdderCarry( b1, b1, b0)+" " + Bit.fullAdderSum( b1, b1, b0)); // prints 1 0
//    	 System.out.println(Bit.fullAdderCarry( b1, b1, b1)+" " + Bit.fullAdderSum( b1, b1, b1)); // prints 1 1
//    	}


}
