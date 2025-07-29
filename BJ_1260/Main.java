import java.io.*;
import java.util.*;

public class Main {
    public static void DFS(int v, List<Integer>[] arr, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int next : arr[v]) {
            if (!visited[next]) {
                DFS(next, arr, visited);
            }
        }
    }

    public static void BFS(int v, List<Integer>[] arr, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            for (int next : arr[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        List<Integer>[] arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s].add(e);
            arr[e].add(s);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }

        boolean[] visited = new boolean[N + 1];
        DFS(V, arr, visited);
        System.out.println();

        visited = new boolean[N + 1];
        BFS(V, arr, visited);
    }
}
