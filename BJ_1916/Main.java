import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int city;
        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static int dijkstra(int N, int start, int end, List<List<Node>> graph) {
        int[] distance = new int[N + 1]; // 도시 번호 1부터 시작
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (distance[now.city] < now.cost) continue;

            for (Node next : graph.get(now.city)) {
                int newDist = distance[now.city] + next.cost;
                if (newDist < distance[next.city]) {
                    distance[next.city] = newDist;
                    pq.offer(new Node(next.city, newDist));
                }
            }
        }

        return distance[end];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 도시 수
        int M = Integer.parseInt(br.readLine()); // 버스 수

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = dijkstra(N, start, end, graph);
        System.out.println(answer);
    }
}
