
/* 
 I, Nir Schneider (316098052), assert that the work I submitted is entirely my own.
 I have not received any part from any other student in the class, nor did I give parts of it for use to others.
 I realize that if my work is found to contain code that is not originally my own, a
 formal case will be opened against me with the BGU disciplinary committee.
*/

// Last Update: 05/12/19

public class Assignment2 {

	
	/*--------------------------------------------------------
	   Part a: instance representation & Solution verification 
	  -------------------------------------------------------
	 */
	
	// Task 1
	public static boolean hasFlight(int[][] flights, int i, int j) { 
		// Add your code here
		boolean answer = false;
		for(int k=0; !answer & k<flights[i].length ;k++)
		{
			if(flights[i][k]==j)//check if there is a flight from i to j
				answer = true;
		}
			
		return answer;	
	}
	
	// Task 2
	public static boolean isLegalInstance(int[][] flights) {
		// Add your code here
		 boolean ans =true;
		 
		 if(flights == null)
			 ans =false;	
		 if ( ans && 1 < flights.length) //not null and bigger than 1
		 {
			 int n=flights.length;
	            
			 for (int i = 0; ans && i < n; i++) 
			 {
				 ans=(flights[i] != null);
				 
				 for (int j = 0; ans && j < flights[i].length; j = j + 1)
				 {
					 if (hasFlight(flights, i, i) | hasFlight(flights, i, j) != hasFlight(flights, j, i))//flights[i] has j and the other way around and flight[i] dont have i
						 ans = false;
					 else if (ans && (flights[i] == null || flights[i][j] < 0 | flights[i][j] > n - 1)) //variables between 0 to n-1
						 ans = false;
				 }
			 }
		 } 
		 else
			 ans = false;
		 return ans;
	}
	
	// Task 3
	public static boolean isSolution(int[][] flights, int[] tour) {
		// Add your code here
		int[] check = new int[tour.length];
		int counter=0;
		boolean answer = true;
		
		
		if(tour.length!=flights.length)
			throw new IllegalArgumentException ("");
		for(int i=0;i<tour.length;i++)
		{
			if(tour[i]<0 | tour[i]>=flights.length)
				throw new IllegalArgumentException ("");
			check[tour[i]]++;
		}
		
		for (int j = 0; j < check.length; j++) //check if all variables included
		{
			if(check[j]!=1)
				answer = false;
		}
		
		if(answer==true)
		{
			for (int k = 0; k < tour.length-1; k++) 
			{
				for (int z = 0; z < flights[tour[k]].length; z++)
				{
					if(flights[tour[k]][z]==tour[k+1])// check if there is flight between tour[i] to tour[i+1]
						counter++;
				}
			}
			for (int x = 0; x < flights[tour[tour.length-1]].length; x++)
			{
				int tmp=flights[tour[tour.length-1]][x];
				if(tmp==tour[0])// check if there is flight between last city to the first
					counter++;
			}
			
			if(counter!=tour.length)
				answer = false;
		}
		
		return answer;
	}
	
	/*------------------------------------------------------
	  Part b: Express the problem as a CNF formula, solve 
	  it with a SAT solver, and decode the solution
	  from the satisfying assignment
	  -----------------------------------------------------
	 */
	
	// Task 4
	public static int[][] atLeastOne(int[] vars) {
		// Add your code here
		int[][] ans = new int[1][vars.length];
		
		ans[0]=vars;
		
		
		return ans;
	}
	
	// Task 5
	public static int[][] atMostOne(int[] vars){
		// Add your code here
		int[][] ans = new int[(vars.length*(vars.length-1))/2][2];//formula to choose 2 from n
		int x =0;

		for (int i=0; i < vars.length; i++) 
		{	
			for (int j = i+1; j < vars.length; j++,x++)
			{
				int []arr = {-vars[i],-vars[j]};//with this condition both cant be true
				ans[x]=arr;
			}
		}
		
	
		return ans;
	}

	// Task 6
	public static int[][] append(int[][] arr1, int[][] arr2) {
		// Add your code here
		int[][] arr3 = new int[arr1.length+arr2.length][];
		for (int i = 0; i < arr1.length ; i++)
		{
				arr3[i]=arr1[i];
		}
			
		for (int j = arr1.length; j < arr2.length+arr1.length ; j++)
		{
				arr3[j]=arr2[j-arr1.length];
		}
		
			
		
			
		return arr3;
	}
	
	// Task 7
	public static int[][] exactlyOne(int[] vars){
		// Add your code here
		int[][] a1 = atLeastOne(vars);
		int[][] a2= atMostOne(vars);
		
		int[][] ans= append(a1, a2); // at least one + at most one --> exactly one

		return ans;
	}
	
	// Task 8
	public static int[][] diff(int[] I1, int[] I2){
		// Add your code here
		int[][] ans = new int[I1.length][2];
		
		for (int i = 0; i < ans.length; i++) //same index in arr1 and arr2 cant be the same
		{
			ans[i][0] = -I1[i];
			ans[i][1] = -I2[i];
		}

		
		return ans;
	}

	// Task 9
	public static int[][] createVarsMap(int n) {
		// Add your code here
		int[][]ans = new int[n][n];
		
		for (int i = 0; i < ans.length; i++)
		{
			for (int j = 0; j < ans[i].length; j++)
			{
				ans[i][j]= i*n+1+j;
			}
		}
		
		return ans;
	}

	// Task 10
	public static int[][] declareInts(int[][] map) {
		// Add your code here		
		int[][] ans;
		int[][] tmp;
		ans = exactlyOne(map[0]); // append can't get null array
		for (int i = 0; i < map.length; i++) 
		{
			tmp = exactlyOne(map[i]);
			ans = append(ans, tmp); 
		}
		return ans;
	}
	
	// Task 11
	public static int[][] allDiff(int[][] map) {
		// Add your code here
		int[][] ans = diff(map[0],map[1]); // append can't get null array

		for (int i = 1; i < map.length; i++) 
		{
			ans = append(ans, diff(map[0],map[i])); // continue check for map[0]
			for (int j = 1; j < map.length-1; j++)
			{
				for (int k = j+1; k < map.length; k++)
				{
					ans= append(ans, diff(map[j],map[k])); // check diff for the rest of sub-arrays in map
				}
			}
			
		}
		return ans;

	}
	
	// Task 12
	public static int[][] allStepsAreLegal(int[][] flights, int[][] map) {
		// Add your code here
		int[][] ans=new int[0][0];

		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map.length; j++)
			{
				for (int k = 0; k < map.length; k++)
				{
					//map[i][j]^map[i+1][k] -> hasflights(flights,j,k);
					if(j!=k & !hasFlight(flights,j,k))
					{
						int tmp [][] = {{-map[i][j],-map[(i+1)%map.length][k]}};
						ans = append(ans,tmp);
					}
			
				}
			}	
		}

		return  ans;
	}
	
	// Task 13
	public static void encode(int[][] flights, int[][] map) {
		// Add your code here
		
		if((map==null) | (flights==null))
		    throw new IllegalArgumentException("the matrix is null");
	      if(!isLegalInstance(flights))        
	     	throw new IllegalArgumentException("flights matrix doesnt match the settings");
	      if(flights.length != map.length)
	        throw new IllegalArgumentException("map matrix doesnt match the settings");
	      
	      int counter=1;
	      
	      for (int i = 0; i < map.length; i++) 
	      {
				if(map[i].length != map.length) // map is nxn matrix
	    	        throw new IllegalArgumentException("map matrix doesnt match the settings");
				
				for (int j = 0; j < map[i].length; j++) //variables in map are from 1 to n^2
				{
					if(map[i][j]!=counter)
		    	        throw new IllegalArgumentException("map matrix doesnt match the settings");
					counter++;
				}
	      }
	      
	      int[][]ans=append(declareInts(map), allStepsAreLegal(flights,map) );//add constraints to sat-solver
	      ans = append(ans, allDiff(map));
	      SATSolver.addClauses(ans);   
   	      
	     
	}
	 
	// Task 14
	public static int[] decode(boolean[] assignment, int[][] map) {
		// Add your code here
		if(assignment.length != map.length*map.length+1)
			throw new IllegalArgumentException("assigment matrix doesnt match the settings");
		
		int ans[] = new int [map.length];
		int pointer=0;
		for (int i = 1; i < assignment.length; i++)
		{
				
			if(assignment[i])
			{
				ans[pointer] = ((map[(i-1)/map.length][(i-1)%map.length])-1)%map.length; //change back variables from map to origin cities numbers
				pointer++;
			}
			
		}
		
		return ans;
	}
	
	// Task 15
	public static int[] solve(int[][] flights) {
		// Add your code here
		if(flights==null || flights.length==0 | !(isLegalInstance(flights)))
			throw new IllegalArgumentException("the matrix doesnt match the settings");

		int[][] map= createVarsMap(flights.length)	;
		
		SATSolver.init(flights.length*flights.length);             
	    encode(flights,map);   
	    boolean[] solutioncnf= SATSolver.getSolution();
		
	    if(solutioncnf==null)
	    	   throw new IllegalArgumentException("TIMEOUT");
	    
	    int[] ans=null;
	    if(solutioncnf.length != 0)
	    {
	    	ans= decode(solutioncnf,map);
	       
	    	if(!(isSolution(flights,ans)))
	    		throw new IllegalArgumentException("Illegal solution");
	    }
	       		
		return ans;
	}
	
	// Task 16
	public static boolean solve2(int[][] flights, int s, int t) {
		// Add your code here
		
		if(flights == null || !isLegalInstance(flights))
			throw new IllegalArgumentException("illegal flights matrix");
		else if (s<0 | s>=flights.length |t<0 | t>=flights.length)
			throw new IllegalArgumentException("illegal input");
		
		boolean check=true;
				
		int[][] fromToClause = {{s+1},{flights.length*flights.length-(1+flights.length-t)}};// force to start from s and to end at t (according to map variables)
		
		SATSolver.init((flights.length*flights.length));
		int[][] map = createVarsMap(flights.length);
		encode(flights,map);
		SATSolver.addClauses(fromToClause);	
		
		boolean[] SATans1 = SATSolver.getSolution();// first solution
			
		if (SATans1==null)
			throw new IllegalArgumentException("arguments are not positive");
		
		if (SATans1.length!=flights.length*flights.length+1)//if there is no solution there will not be 2 solutions.
			check=false;
		
		int counter=flights.length;
		
		int[][] firstSolClause = new int[flights.length-2][1];//number of steps from s to t
		for(int i=0;check && i<flights.length-2;i++) 
		{
			int[] arr1 = decode(SATSolver.getSolution(),createVarsMap(flights.length));
			firstSolClause[i][0]=-(counter+arr1[i+1])-1; // must be diffrent cities between s and t (acording to map variables)
			counter= counter+flights.length;
		
		}
		
		SATSolver.init((flights.length*flights.length));
		encode(flights,map);
		SATSolver.addClauses(fromToClause);
		SATSolver.addClauses(firstSolClause);	
		
		boolean [] SATans2 = SATSolver.getSolution();
		if (check && SATans2==null) // check second solution
			throw new IllegalArgumentException("arguments are not positive");
		
		if (SATans2.length!=flights.length*flights.length+1)//check second solution - if it is ok there are 2 solutions
			check=false;
		
		return check;
	}
}
