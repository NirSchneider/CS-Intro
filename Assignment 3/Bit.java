/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
public class Bit {

    private boolean value;

    public Bit(boolean value)
    {
        //Task 3.1
    	this.value=value;
    }

    public int toInt(){ 
        int ans = 0;
        //Task 3.2
        if(this.value)//true value is 1 and false value is 0
        	ans=1;
        return ans;

    }

    public String toString(){
        String ans = "";
        //Task 3.3
        if(this.value)//true value is 1 and false value is 0
        	ans = "1";
        else
        	ans="0";
        return ans;
    }
    
//	public static void main(String[] args) {
//
//		 Bit bit1 = new Bit(true);    Bit bit0 = new Bit(false);
//		 System.out.println(bit0.toString()+" "+bit1.toString());
//		 int sum = bit1.toInt() + bit0.toInt();
//		 System.out.println(sum);
//	}
}

