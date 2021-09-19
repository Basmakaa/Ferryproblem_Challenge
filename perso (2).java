
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
 class Main {
	public  int bestK, newS; 
	public int[] bestX; 
	public int[] currX;
	public ArrayList<Integer> carLength ;
	public int L;
	public int nCars;

	public Boolean[][] visited;
	public int spaceLeft, spaceRight; 
	public int numProb;

	public Main() { 	
	}
	public void initialize(Scanner in) {

			carLength = new ArrayList<>();
			nCars = 0; 
			
			L = (int) 100* Integer.parseInt(in.nextLine());
			
			String curLen = in.nextLine();
			while(!curLen.equals("0")) {
				if(curLen.equals("0")) {System.exit(0);}
				
				carLength.add(Integer.parseInt(curLen));
				curLen = in.nextLine();
				nCars++;
			}
			visited= new Boolean[nCars+1][L+1];
			
			currX= new int[nCars];

			bestK=-1;
			spaceLeft = L;
			spaceRight = L;
	
}
public  int[] BackTrackSolve(int curr_nb_car_added, int left_space_left) {
		
		if(curr_nb_car_added>bestK) {
			bestK=curr_nb_car_added;
			bestX= Arrays.copyOf(currX, bestK);

		}
		
		if(curr_nb_car_added<nCars) {
			
			if((spaceLeft - carLength.get(curr_nb_car_added)>=0) && (visited[curr_nb_car_added+1][left_space_left-carLength.get(curr_nb_car_added)]==null)){
				currX[curr_nb_car_added]=1;
				newS= left_space_left- carLength.get(curr_nb_car_added);
				spaceLeft-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added+1, newS);
				spaceLeft+=carLength.get(curr_nb_car_added);
				visited[curr_nb_car_added+1][newS]=true;	

			}
			 
			if((spaceRight- carLength.get(curr_nb_car_added)>=0)&&(visited[curr_nb_car_added+1][left_space_left]==null)) {
				currX[curr_nb_car_added]=0;
				spaceRight-=carLength.get(curr_nb_car_added);
				BackTrackSolve(curr_nb_car_added+1, left_space_left);
				spaceRight+=carLength.get(curr_nb_car_added);
				visited[curr_nb_car_added+1][left_space_left]=true;

			}

		}
		for (int i=0; i<bestK; i++) {
			System.out.println(bestX[i]);
		}

				return bestX;
	 }



	public static void main(String[] args) throws Exception
	{	
		
		ArrayList<int[]> results = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		Main m = new Main(); 
		m.numProb= Integer.parseInt(in.nextLine());
		in.nextLine();
		m.initialize(in);
		results.add(m.BackTrackSolve(0, m.L));
		
		for(int j=0;j<m.numProb-1 ;j++) {
			in.nextLine();
			m.initialize(in);
			results.add(m.BackTrackSolve(0, m.L));
		}
	
		int a= results.size();
		
		
		for(int[] i : results) {
			System.out.println(i.length);
			for(int j:i){
			if(j==1){
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


}

	
}