import java.io.*;
import java.util.*;


public class Main {

    static ArrayList<Integer>[] arr;
    static int[] result;
    static int[] visited;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        result = new int[N];
        visited = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            Collections.sort(arr[i]);
        }

        dfs(R - 1);

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int index) {
        result[index] = count++;
        visited[index] = 1;
        for (int next : arr[index]) {
            if (visited[next] == 0) {
                dfs(next);
            }
        }
    }
}
