/*
I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/
class Part2{

    public static boolean change(int [] coins, int n)
    {
        //Task 2.1        	
        return change(coins,n,0);
    }
    public static boolean change(int [] coins, int n,int index)
    {
    	boolean ans;
    	if(n==0) // exact change has found
    		ans = true;
    	else if(n < 0 | index >= coins.length)//change isn't exact
    		ans = false;
    	else
    		ans = change(coins,n-coins[index],index) || change(coins,n,index+1); // take a coin or don't take coin and move to the next coin.
    	return ans;
    }

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse)
    {
        //Task 2.2
        return changeLimited(coins, n, numOfCoinsToUse, 0);
    }
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse,int index)
    {
    	boolean ans;
    	if(n==0 & numOfCoinsToUse==0)// exact change has found by number of coins allowed to use
    	{
    		ans = true;
   		}
    	else if(n < 0 | index >= coins.length | numOfCoinsToUse<=0)//change isn't exact by number of coins allowed to use
    		ans = false;
    	else
    		ans = changeLimited(coins,n-coins[index],numOfCoinsToUse-1,index) || changeLimited(coins,n,numOfCoinsToUse,index+1);// take a coin or don't take coin and move to the next coin.
    	
    	return ans;
    }

    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse)
    {
        //Task 2.3
    	String s = "";
        printChangeSolution(coins,n,numOfCoinsToUse,0,s);
    	
    }
    public static boolean printChangeSolution( int coins[], int n,int numOfCoinsToUse,int index,String s) 
    {
    	boolean b=false; // purpose-to stop when find first solution
    	if (n == 0 & numOfCoinsToUse==0)// exact change has found by number of coins allowed to use
    	{
    		b=true;
    		System.out.println(s.substring(1) + "");//substring purpose - cut the first ","
    	}
   
    	else if (n < 0 | index >= coins.length | numOfCoinsToUse<=0)  //change isn't exact by number of coins allowed to use	
    		return false;
    	
    	else
    	{
			b= printChangeSolution(coins,n-coins[index],numOfCoinsToUse-1,index,s + "," + coins[index]) || printChangeSolution(coins,n,numOfCoinsToUse,index+1,s);// take a coin or don't take coin and move to the next coin.
    	}
    	return b;
        
    }
    
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
    	
        //Task 2.4
        return countChangeLimited(coins, n, numOfCoinsToUse,0);
    }
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse,int index)
    {
    	int ans=0;
    	if(n==0 & numOfCoinsToUse==0)// exact change has found by number of coins allowed to use
    		ans=1;
    	else if(n < 0 | index >= coins.length | numOfCoinsToUse<=0)//change isn't exact by number of coins allowed to use
    		ans = 0;
    	else
    		ans = countChangeLimited(coins,n-coins[index],numOfCoinsToUse-1,index)  + countChangeLimited(coins,n,numOfCoinsToUse,index+1);// take a coin or don't take coin and move to the next coin.
    	
    	return ans;
    }
    

    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
    	String s = "";
        printActualSolution(coins,n,numOfCoinsToUse,0,s);
    }
    public static void printActualSolution(int coins[],int n,int numOfCoinsToUse,int index,String s) 
    {
    	if (n == 0 & numOfCoinsToUse==0)// exact change has found by number of coins allowed to use
    		System.out.println(s.substring(1) + " ");//substring purpose - cut first ","
   
    	else if (n < 0 | index >= coins.length | numOfCoinsToUse<=0)  //change isn't exact by number of coins allowed to use	
    		return;
    	
    	else
    	{
			printActualSolution(coins,n-coins[index],numOfCoinsToUse-1,index, s+ "," +coins[index]);// take a coin 
			printActualSolution(coins,n,numOfCoinsToUse,index+1,s);//don't take coin and move to the next coin.
    	}
    }
    public static int changeInCuba(int cuc){
        int ans = 0;
        //Task 2.6
        int []CUCAndCUP = {1,3,3,5,9,10,15,20,30,50,60,100,150,300}; // all possibles bills value by CUP rate-value
        ans=cubaCount(CUCAndCUP, cuc*3, 0);
        return ans;
    }
    public static int cubaCount(int[] cuc, int n,int index)
    {
    	int ans=0;
    	if(n==0)// exact change has found
    		ans++;
    	else if(n < 0 | index >= cuc.length)//change isn't exact
    		ans = 0;
    	else
    		ans = cubaCount(cuc,n-cuc[index],index) + cubaCount(cuc,n,index+1);// take a coin or don't take coin and move to the next coin.
    	
    	return ans;
    }

//    public static void main(String[] args){
//        //tests for part 2.1
//         int []changee1 = {1,5,10};
//         int n = 7;
//         System.out.println("change test 1:expected true, got " + change(changee1,n));
//         int []cchange2 = {2,20,10,100};
//         n = 15;
//         System.out.println("change test 2: expected false, got " + change(cchange2,n)+"\n");
//
//         //Stests for part 2.2
//         int []changeLimited1 = {1,12,17,19};
//         n = 20;
//         int numOfCoinsToUse = 2;
//         System.out.println("ChangeLimited test 1: expected true, got " + changeLimited(changeLimited1,n ,numOfCoinsToUse));
//         int []changeLimited2 = {5,7,12};
//         n = 8;
//         numOfCoinsToUse = 2;
//         System.out.println("ChangeLimited test 2: expected false, got " + changeLimited(changeLimited2,n ,numOfCoinsToUse));
//         int []changeLimited3 = {1,7,12,10};
//         n = 10;
//         numOfCoinsToUse = 5;
//         System.out.println("ChangeLimited test 3: expected false, got " + changeLimited(changeLimited3,n ,numOfCoinsToUse)+"\n");
//         int []changeLimited4 = {2};
//         n = 4;
//         numOfCoinsToUse = 2;
//         System.out.println("ChangeLimited test 4: expected true, got " + changeLimited(changeLimited4,n ,numOfCoinsToUse)+"\n");
//        
//        // tests for part 2.3
//         int []printChangeLimited1 = {1,2,3};
//         n = 4;
//         numOfCoinsToUse = 2;
//         System.out.println("PrintChangeLimited test 1: expected 2,2 or 1,3 , got ");
//         printChangeLimited(printChangeLimited1,n ,numOfCoinsToUse);
//         int []printChangeLimited2 = {1,7,12};
//         n = 10;
//         numOfCoinsToUse = 5;
//         System.out.println("PrintChangeLimited test 2: expected printing nothing, got ");
//         printChangeLimited(printChangeLimited2,n ,numOfCoinsToUse);
//         System.out.println("");
//
//        //tests for part 2.4
//         int []countChangeLimited1 = {1,2,3};
//         n = 4;
//         numOfCoinsToUse = 2;
//         System.out.println("CountChangeLimited test 1: expected 2, got " + countChangeLimited(countChangeLimited1,n ,numOfCoinsToUse));
//         int []countChangeLimited2 = {5,10,20,50,100};
//         n = 100;
//         numOfCoinsToUse = 5;
//         System.out.println("CountChangeLimited test 2: expected 3, got " + countChangeLimited(countChangeLimited2,n ,numOfCoinsToUse));
//         int []countChangeLimited3 ={5,10,50};
//         n = 65;
//         numOfCoinsToUse = 2;
//         System.out.println("CountChangeLimited test 3: expected 0, got " + countChangeLimited(countChangeLimited3,n ,numOfCoinsToUse)+"\n");
//        
//       // tests for part 2.5
//         int []printAllChangeLimited1 = {1,2,3};
//         n = 4;
//         numOfCoinsToUse = 2;
//         System.out.println("PrintAllChangeLimited test 1: expected : \n 2,2 \n 1,3 \n or \n 1,3 \n 2,2 , got " );
//         printAllChangeLimited(printAllChangeLimited1,n ,numOfCoinsToUse);
//         int []printAllChangeLimited2 = {1,5,10,20};
//         n = 13;
//         numOfCoinsToUse = 2;
//         System.out.println("PrintAllChangeLimited  test 2: expected printing nothing, got ");
//         printAllChangeLimited(printAllChangeLimited2,n ,numOfCoinsToUse);
//         System.out.println("");
//
//        //tests for part 2.6
//         System.out.println("ChangeInCuba 1");
//         System.out.println(changeInCuba(1));
//         System.out.println("ChangeInCuba 2");
//         System.out.println(changeInCuba(2));
//         System.out.println("ChangeInCuba 20");
//         System.out.println(changeInCuba(20));
//         System.out.println("ChangeInCuba 50");
//         System.out.println(changeInCuba(50));
//    }
}
