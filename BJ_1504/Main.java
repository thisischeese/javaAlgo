import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int E;
    public static List<int[]>[] graph;
    public static int v1;
    public static int v2;
    public static final int MAX = 200_000_000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long a = dijkstra(1, v1);
        long b = dijkstra(v1, v2);
        long c = dijkstra(v2, N);
        long ans1 = a + b + c;

        long a1 = dijkstra(1, v2);
        long b1 = dijkstra(v2, v1);
        long c1 = dijkstra(v1, N);
        long ans2 = a1 + b1 + c1;

        if (ans1 >= MAX && ans2 >= MAX) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(ans1, ans2));
        }
    }

    public static long dijkstra(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        long[] table = new long[N + 1];
        Arrays.fill(table, MAX);
        table[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int cost = now[1];

            if (table[cur] < cost) continue;

            for (int[] next : graph[cur]) {
                int child = next[0];
                int weight = next[1];
                if (table[child] > table[cur] + weight) {
                    table[child] = table[cur] + weight;
                    pq.offer(new int[]{child, (int)table[child]});
                }
            }
        }
        return table[end];
    }
}
