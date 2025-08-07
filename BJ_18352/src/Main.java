import java.io.*; 
import java.util.*; 

// 2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N

public class Main {
	
	public static class Node{
		int idx;
		int cost; 
		Node(int idx,int cost){
			this.idx = idx; 
			this.cost = cost; 
		}
	}
	public static int[] dijkstra(int N, int start,ArrayList<ArrayList<Integer>> arr) {
		int[] dist = new int[N+1]; 
		boolean[] visited = new boolean[N+1]; 
		PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
		queue.add(new Node(start,dist[start]));
		visited[start] = true; 
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll(); 
			for(int next : arr.get(curr.idx)) {
				if(visited[next]) {continue;}
				visited[next] = true; 
				dist[next] = curr.cost +1; 
				queue.offer(new Node(next,dist[next])); 
			}
		}
		
		return dist; 
	}
	
	public static List<Integer> find_answer(int N, int K, int[] dist) {
		List<Integer> answer = new ArrayList<>(); 
		
		for(int i=1;i<N+1;i++) {
			if(dist[i]==K) {
				answer.add(i);
			}
		}
		return answer; 
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 
		int X = Integer.parseInt(st.nextToken()); 

		// 인접 리스트 초기화 
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>(); 
		for(int i=0;i<N+1;i++) {
			arr.add(new ArrayList<>()); 
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			arr.get(a).add(b); 
		}
		
		// 다익스트라 호출 
		int[] dist = dijkstra(N,X,arr); 
		List<Integer> answer = find_answer(N,K,dist); 
		
		if(answer.size()==0) {
			System.out.println(-1);
		}
		else {
			for(int i=0;i<answer.size();i++) {
				System.out.println(answer.get(i));
			}
		}
		
		br.close();
	}

}
