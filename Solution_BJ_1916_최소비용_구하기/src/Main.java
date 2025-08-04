import java.io.*; 
import java.util.*; 

public class Main {

	public static int dijkstra(int N,int start,int end,int[][] bus_info) {
		int dist = Integer.MAX_VALUE; 
		int[] distance = new int[N]; 
		return dist; 
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); 
		
		int N = Integer.parseInt(br.readLine()); 
		int M = Integer.parseInt(br.readLine()); 
		int[][] bus_info = new int[M][3]; 
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			bus_info[i][0] = Integer.parseInt(st.nextToken()); 
			bus_info[i][1] = Integer.parseInt(st.nextToken()); 
			bus_info[i][2] = Integer.parseInt(st.nextToken()); 
		}
		st = new StringTokenizer(br.readLine());
		int start =  Integer.parseInt(st.nextToken()); 
		int end =  Integer.parseInt(st.nextToken()); 
		
		int answer = dijkstra(N,start,end,bus_info); 

	}

}
