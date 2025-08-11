import java.io.*; 
import java.util.*; 

public class Main {
	
	public static int [] dx = {0,0,-1,1}, dy = {1,-1,0,0}; 
	public static class Node implements Comparable<Node>{
		int cost;
		int x,y;
		public Node(int cost,int x, int y){
			this.cost = cost; 
			this.x = x; 
			this.y = y; 
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; 
		}
	}
	public static int dijkstra(int N, int[][] arr) {
		
		// 방문 처리 배열 
		boolean[][] v = new boolean [N][N];
		
		// 우선순위 큐 
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		pq.offer(new Node(arr[0][0],0,0));
		
		// 최단거리 기록 배열 
		int[][] dist = new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);}
		dist[0][0] = arr[0][0];
		
		// 0,0 -> N-1, N-1
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(v[curr.x][curr.y]) {continue;}
			
			// 인접 노드 방문 
			v[curr.x][curr.y] = true; 
			for(int i=0;i<4;i++) {
				int nx = curr.x + dx[i], ny = curr.y + dy[i]; 
				// 범위 체크 + 거리 기준 방문 필요 여부 체크
				if((0<=nx && nx<=N-1 && 0<=ny && ny<=N-1) && (dist[nx][ny]>curr.cost + arr[nx][ny])) {
					pq.offer(new Node(curr.cost + arr[nx][ny],nx,ny));
					dist[nx][ny] = curr.cost + arr[nx][ny]; 
				}
			}
			
			}
		
		
		return dist[N-1][N-1];
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); 
		int t = 0;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) {break;}
			t++; 
			int[][] arr = new int[N][N]; 
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int answer = dijkstra(N,arr); 
			sb.append("Problem ").append(t).append(": ").append(answer).append("\n"); 
		}
		
		br.close();
		System.out.println(sb.toString()); 
		
	}

}
