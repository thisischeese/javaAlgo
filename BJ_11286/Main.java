import java.io.*; 
import java.util.*; 


public class Main {
	
	public static class Number implements Comparable<Number>{
		int x,y; 
		public Number(int x,int y) {
			this.x = x;
			this.y = y; 
		}
		@Override
		public int compareTo(Number other) {
			if(this.x == other.x) {
				return (this.y-other.y);
			}
			return (this.x - other.x);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 절댓값 > 실제 값 순으로 정렬하기 
		PriorityQueue<Number> pq = new PriorityQueue<Number>();
		int N = Integer.parseInt(br.readLine()); 
		for(int i=0;i<N;i++) {
			int x = Integer.parseInt(br.readLine()); 
			if(x==0) {
				if(pq.isEmpty()) {sb.append(0).append("\n");}
				else {
					Number num = pq.poll();
					sb.append(num.x*num.y).append("\n");}}
			else {
				Number temp = new Number(Math.abs(x), x/Math.abs(x));
				pq.offer(temp); 
				}
		}
		System.out.println(sb.toString());
		br.close();
	}

}
