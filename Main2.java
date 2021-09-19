import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

class Main2 {
	public  int bestK;
	public int newS; 
	public int[] bestX;  
	public int[] currX;
	public ArrayList<Integer> carLength;
	
	public int L;
	public int nCars;

	
	public int spaceLeft;
	public int spaceRight; 
	public int numProb;

	public Main2() { 
		
	}

	public void initialize(Scanner in) {

			carLength = new ArrayList<>();

			nCars = 0; 
			
			L = (int) 100 * Integer.parseInt(in.nextLine());
			String curLen = in.nextLine();
			while(!curLen.equals("0")) {
				if(curLen.equals("0")) {System.exit(0);}
				
				carLength.add(Integer.parseInt(curLen));
				curLen = in.nextLine();
				nCars++;
			}
					
		   
			
		
			currX= new int[nCars];
			bestK=-1;
			spaceLeft = L;
			spaceRight = L;
	
}
   public Integer myFunction(int k, int s) {
   	return (L-s +k );
   	//return k+s*(2^L);

   }
public  int[] BackTrackSolve(int curr_nb_car_added, int left_space_left) {
		
		if(curr_nb_car_added>bestK) {
			bestK=curr_nb_car_added;
			bestX= Arrays.copyOf(currX, bestK);
			
		}
		
		
		if(curr_nb_car_added<nCars) {
			
			if((spaceLeft - carLength.get(curr_nb_car_added)>=0) && (visited.get(myFunction(curr_nb_car_added+1,left_space_left-carLength.get(curr_nb_car_added)))==false)){
				currX[curr_nb_car_added]=1;
				newS= left_space_left- carLength.get(curr_nb_car_added);
				spaceLeft-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added +1, newS);
				spaceLeft+=carLength.get(curr_nb_car_added);
				visited.put(myFunction(curr_nb_car_added+1,newS),true);	

			}
			
			if((spaceRight- carLength.get(curr_nb_car_added)>=0)&&(visited.get(myFunction(curr_nb_car_added+1,left_space_left))==false)) {
				currX[curr_nb_car_added]=0;
				spaceRight-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added +1, left_space_left);
				spaceRight+=carLength.get(curr_nb_car_added);
				visited.put(myFunction(curr_nb_car_added +1,left_space_left),true);

			}

		}
		
				return bestX;

		
	 }



	public static void main(String[] args) throws Exception
	{	
		long c = System.currentTimeMillis();
		ArrayList<int[]> results = new ArrayList<>();
		Scanner in = new Scanner(new File("input2.txt"));
		//Scanner in = new Scanner(System.in);
		Main2 m = new Main2(); 
		m.numProb= Integer.parseInt(in.nextLine());
		in.nextLine();
		m.initialize(in);
		results.add(m.BackTrackSolve(0, m.L));
		
		for(int j=0;j<m.numProb-1;j++) {
			in.nextLine();
			m.initialize(in);
			results.add(m.BackTrackSolve(0, m.L));
		}
	
		int a= results.size();
		
		
		for(int[] i : results) {
			System.out.println(i.length);
			for(int j:i){
			if(j==0){
				System.out.println("port");
			} 
			else{

				System.out.println("starboard");

			}
		}
		a--;
		if(a==0){
			break;
		}
		System.out.println();
	}


	long b= System.currentTimeMillis();
	System.out.println(b-c);
}


}