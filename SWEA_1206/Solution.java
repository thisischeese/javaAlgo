import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        
        // System.setIn(new FileInputStream("res/input_SWEA_1206"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t < 11; t++) {
            int answer = 0;

            int N = Integer.parseInt(br.readLine());
            int[] buildings = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            // 양쪽 끝 2칸은 조망권 확보 x
            // i는 2부터 N-2까지만 순회
            for (int i = 2; i < N - 2; i++) {
                // 현재 빌딩의 높이
                int currentHeight = buildings[i];

                // 좌우 2칸 내의 이웃 빌딩 중 가장 높은 빌딩 높이 찾기 
                int maxNeighborHeight = 0;
                maxNeighborHeight = Math.max(buildings[i - 2], buildings[i - 1]);
                maxNeighborHeight = Math.max(maxNeighborHeight, buildings[i + 1]);
                maxNeighborHeight = Math.max(maxNeighborHeight, buildings[i + 2]);

                // 현재 빌딩이 주변의 가장 높은 빌딩보다 높다면 조망권 확보o
                if (currentHeight > maxNeighborHeight) {
                    // 확보된 조망권 세대 수는 (현재 빌딩 높이 - 주변 최고 높이)
                    answer += currentHeight - maxNeighborHeight;
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        br.close();
        System.out.println(sb.toString());
    }
}
