

import java.io.File;
import java.util.ArrayList;import java.util.Arrays;
import java.util.Scanner;



class Main {
	public static int espace_gauche;
	public  static int espace_droit; 
	public  static int bestK;
	public static int newS; 
	
	public static ArrayList<Integer> carLength;
	
	public static int L;
	public static int nCars;

	public static Boolean[][] visited;
	
	public static int cas;
	public static int[] bestX;  
	public static int[] currX;

	public Main() { }
	public static void main(String[] args) throws Exception
	{	
		
		Scanner in = new Scanner(System.in);
		ArrayList<int[]> solutions = new ArrayList<>();
		
		
		cas= Integer.parseInt(in.nextLine());
		in.nextLine();
		initialize(in);
		solutions.add(BackTrackSolve(0, L));
		
		for(int a=0;a<cas-1;a++) {
			in.nextLine();
			initialize(in);
			solutions.add(BackTrackSolve(0, L));
		}
	
		int res= solutions.size();
		
		
		for(int[] b : solutions) {
			System.out.println(b.length);
			for(int c:b){
			if(c==1){
				System.out.println("starboard");
			} 
			else{

				System.out.println("port");

			}
		}
		res-=1;
		if(res==0){break;}
		System.out.println();
	}



}

	public static void initialize(Scanner in) {

			
			nCars = 0; 
			carLength = new ArrayList<>();
			L = (int)  Integer.parseInt(in.nextLine())*100 ;
			String curLen = in.nextLine();
			while(!curLen.equals("0")) {
				if(curLen.equals("0")) {System.exit(0);}
				
				carLength.add(Integer.parseInt(curLen));
				curLen = in.nextLine();
				nCars++;
			}
					
		    
            espace_gauche= L;
			espace_droit = L;
			visited= new Boolean[nCars+1][L+1];
		
			currX= new int[nCars];
			bestK=-1;

			
	
}
public  static int[] BackTrackSolve(int curr_nb_car_added, int left_space_left) {
		
		if(curr_nb_car_added>bestK) {
			bestK=curr_nb_car_added;
			bestX= Arrays.copyOf(currX, bestK);
			
		}
		
		
		if(curr_nb_car_added<nCars) {
			
			if(( carLength.get(curr_nb_car_added)<=espace_gauche) && (visited[curr_nb_car_added+1][left_space_left-carLength.get(curr_nb_car_added)]==null)){
				currX[curr_nb_car_added]=1;
				newS= left_space_left- carLength.get(curr_nb_car_added);
				espace_gauche-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added +1, newS);
				espace_gauche+=carLength.get(curr_nb_car_added);
				visited[curr_nb_car_added+1][newS]=true;	

			}
			
			if((carLength.get(curr_nb_car_added)<= espace_droit)&&(visited[curr_nb_car_added+1][left_space_left]==null)) {
				currX[curr_nb_car_added]=0;
				espace_droit-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added +1, left_space_left);
				espace_droit+=carLength.get(curr_nb_car_added);
				visited[curr_nb_car_added +1][left_space_left]=true;

			}

		}
		
				return bestX;	
	 }	

}
	




	
