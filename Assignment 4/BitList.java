/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;
import java.util.LinkedList;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
    	if (element == null)
        	throw new IllegalArgumentException("element cannot be null");
        if (element.toInt()==1)
        	this.numberOfOnes++;
        
        super.addLast(element);
    }

    public void addFirst(Bit element) {
        if (element == null)
        	throw new IllegalArgumentException("element cannot be null");
        if (element.toInt()==1)
        	this.numberOfOnes++;
        
        super.addFirst(element);
        
    }

    public Bit removeLast() {
    	if(super.getLast().toInt()==1)
    		this.numberOfOnes--;
    	
    	return super.removeLast();
    }

    public Bit removeFirst() {
    	if(super.getFirst().toInt()==1)
    		this.numberOfOnes--;
    	
    	return super.removeFirst();  
    	}

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
//    	if(this==null)
//    		throw new IllegalArgumentException("illagle argument");
    	String output = ">";
    	BitList current = this;
    	for(Bit item  : current)
    	{
    		output = item.toString() + output;
    	}
    	
    	output ="<" + output;
    			
    	return output;
    }
    
    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
    	if(other==null)
    		throw new IllegalArgumentException("input cannot be null");
    	this.numberOfOnes=0;
    	for(Bit item  : other)
    	{
    		this.addLast(item);
    	}
    	
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
    	
    	boolean output = true;
    	if(super.size()<1) // number cannot has size less than one
    		output = false;
    	else if (this.numberOfOnes==1 & super.getLast().toInt()==1) // binary number with the form 100... has no meaning
    		output = false;
    	
    	return output;
    }
    
    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
    	boolean output = false;
    	BitList tmp = new BitList(this);

    	if(isNumber())
    	{
    		if(tmp.size()==1 & tmp.getFirst().toInt()==0) // <0> is OK
    			output = true;
    		else if (tmp.size()==2 && ((tmp.getFirst().toInt()==0 & tmp.getLast().toInt()==1)) | (tmp.getFirst().toInt()==1 & tmp.getLast().toInt()==1)) // <01>,<11> is OK
        		output = true;
        	else if(this.getLast().toInt()==1)
        		{
        			tmp.removeLast();
        			if(tmp.numberOfOnes==1 & tmp.getLast().toInt()==1) // 11 at the end is OK
        				output = true;
        			else if(tmp.getLast().toInt()==0) // 10 at the end is OK
        				output = true;
        		}
        	else 
        	{
        		tmp.removeLast();
        		if(tmp.getLast().toInt()==1) // 01 at the end is OK
        			output = true;
        	}
    	}
    	
    	return output;
    }

    public void reduce() {
    	while(!this.isReduced() & this.size()>0)
    		this.removeLast();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
    	BitList ans = new BitList();
    	for(Bit item  : this)
    	{
    		if(item.toInt()==1) //for every bit=1 add to ans bit=0 at the end
    			ans.addLast(Bit.ZERO);
    		else //for every bit=0 add to ans bit=1 at the end
    		{
    			ans.addLast(Bit.ONE);
    			ans.numberOfOnes++;
    		}
    	}
    	return ans;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
    	Bit ans;
    	if(this.size()==0)
    		ans=null;
    	else
    		ans = this.removeFirst();
    	return ans;
    }

    public void shiftLeft() {
    	this.addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
    	if(this.getLast().toInt()==1)
    	{
    		while(this.size()<newLength)
    			this.addLast(Bit.ONE);
    	}
    	else
    	{
    		while(this.size()<newLength)
    			this.addLast(Bit.ZERO);
    	}
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
    public BitList negate()
    {
    	boolean zeroFound = false;
    	BitList ans = new BitList();

    	Iterator <Bit> it = this.complement().iterator(); // iterator running on the complement form of the object
    	while(it.hasNext())
    	{
    		Bit nextbit = it.next();
    		if(!zeroFound) // need to negate every bit until a zero found (include)
    		{
    			ans.addLast(nextbit.negate());
    			if(nextbit.equals(Bit.ZERO))
    				zeroFound = true;	
    		}
    		else // if a zero found copy the bits as they appear in the complement form
    			ans.addLast(nextbit);
       	}
    	return ans;
    }
    
    public int toInt()
    {
    	this.reduce();
    	int counter = 0;
    	int binary =1;
    	boolean isNegative=false;
    	BitList tmp = new BitList(this);
    	if(this.getLast().toInt()==1)
    	{
    		tmp = tmp.negate();
    		isNegative=true;
    	}
    	for (Bit item : tmp)
    	{
    		if(item.toInt()%2 == 1) //if got bit=1 add to counter and multiply the binary by 2 (as needed in base2)
    			counter = counter + binary;
    		binary = binary*2;
    	}
    	if(isNegative)
    		counter=counter-2*counter;
    	
    	return counter;
    }
    
    public BitList reverse() // return a new bit list with the order reversed without the signed bit
    {
    	BitList bl = new BitList(this);
    	bl.removeLast();
    	BitList ans = new BitList();
    	while(!bl.isEmpty())
    	{
        	ans.addLast(bl.removeLast());
    	}
    	return ans;
    }
    
//    public static void main(String[] args) {
//
//    	System.out.println("test for 2.2 :");
//    	BitList b3 = new BitList(); //    <> 
//    	b3.addFirst(Bit.ZERO);          //   <0>    
//    	b3.addFirst(Bit.ZERO);          //  <00>   
//    	b3.addFirst(Bit.ONE);           // <001>  
//    	System.out.println(b3); // prints <001>
//    	System.out.println(b3.toInt());
//    	System.out.println();
//
//    	System.out.println("test for 2.3 :");
//    	BitList b31 = new BitList();   //       <>
//    	b31.addFirst(Bit.ZERO);            //      <0>
//    	b31.addFirst(Bit.ZERO);            //     <00>
//    	b31.addFirst(Bit.ONE);             //    <001>
//    	BitList b32 = new BitList(b31); //    <001>
//    	System.out.println(b32);       //    <001>
//    	b32.addFirst(Bit.ONE);             //   <1001>
//    	b32.addFirst(Bit.ONE);             //  <10011>
//    	b32.addFirst(Bit.ONE);             //  <10011>
//    	System.out.println(b31);       // <001111>
//    	System.out.println(b32);       //   <1001> 
//    	System.out.println();
//    	
//    	System.out.println("test for 2.4 :");
//    	BitList b4 = new BitList(); // <>
//    	System.out.println(b4.isNumber()); // prints false
//    	b4.addFirst(Bit.ONE); // <1>
//    	b4.addFirst(Bit.ZERO); // <10>
//    	b4.addFirst(Bit.ZERO); // <100>
//    	System.out.println(b4.isNumber()); // prints false
//    	b4.addLast(Bit.ONE); // <1100>
//    	System.out.println(b4.isNumber()); // prints true
//    	System.out.println();
//
//    	System.out.println("test for 2.5 :");
//    	BitList b51 = new BitList();   
//    	b51.addFirst(Bit.ZERO);            
//    	b51.addFirst(Bit.ZERO);           
//    	b51.addFirst(Bit.ZERO); 
//    	b51.addFirst(Bit.ZERO); 
//    	b51.addFirst(Bit.ZERO); //<00000>
//    	System.out.println(b51.isReduced());
//    	b51.reduce();
//    	System.out.println(b51);
//    	BitList b52 = new BitList();   
//    	b52.addFirst(Bit.ONE);        
//    	b52.addFirst(Bit.ONE);          
//    	b52.addFirst(Bit.ONE); 
//    	b52.addFirst(Bit.ZERO); 
//    	b52.addFirst(Bit.ONE); //<11101>
//    	System.out.println(b52.numberOfOnes);
//    	System.out.println(b52.isReduced());
//    	b52.reduce();
//    	System.out.println(b52);
//    	BitList b53 = new BitList();   
//    	b53.addFirst(Bit.ONE);        
//    	b53.addFirst(Bit.ONE);          
//    	b53.addFirst(Bit.ONE); 
//    	b53.addFirst(Bit.ONE); 
//    	b53.addFirst(Bit.ONE); 
//    	b53.addFirst(Bit.ZERO); 
//    	b53.addFirst(Bit.ZERO); //<1111100>
//    	System.out.println(b53.isReduced());
//    	b53.reduce();
//    	System.out.println(b53);
//    	System.out.println();
//
//    	System.out.println("test for 2.6 :");
//    	BitList b6 = new BitList(); //    <>
//    	b6.addFirst(Bit.ZERO);          //   <0> 
//    	b6.addFirst(Bit.ZERO);          //  <00>
//    	b6.addFirst(Bit.ONE);           // <001>
//    	BitList c = b6.complement();// <110>
//    	System.out.println(b6);     // prints <001>
//    	System.out.println(c);      // prints <110> 
//    	System.out.println();
//
//    	System.out.println("test for 2.7 :");
//    	BitList b71 = new BitList();   //    <>
//    	b71.addFirst(Bit.ZERO);            //   <0> 
//    	b71.addFirst(Bit.ZERO);            //  <00>
//    	b71.addFirst(Bit.ONE);             // <001>
//    	b71.shiftRight();              //  <00>
//    	System.out.println(b71);       //  prints <00> 
//    	BitList b72 = new BitList();   //     <>
//    	b72.addFirst(Bit.ZERO);            //    <0> 
//    	b72.addFirst(Bit.ZERO);            //   <00>
//    	b72.addFirst(Bit.ONE);             //  <001> 
//    	b72. shiftLeft ();              // <0010>
//    	System.out.println(b72);       // prints <0010> 
//    	System.out.println();
//
//    	System.out.println("test for 2.8 :");
//    	BitList b8 = new BitList();   //         <>
//    	b8.addFirst(Bit.ZERO);            //        <0>
//    	b8.addFirst(Bit.ZERO);            //       <00>
//    	b8.addFirst(Bit.ONE);             //      <001> 
//    	b8.padding(10);               // <0000000001>
//    	System.out.println(b8);       // prints <0000000001>
//    	b8.padding(5);                // <0000000001> 
//    	System.out.println(b8);       // prints <0000000001> 
//    	  
//    	BitList b9 = null;   //         <>
//    	System.out.println(b9);
//    }
}
