import java.io.*;
import java.util.*;

public class Main {

	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int N;
	static int[][] classroom;
	static Map<Integer, List<Integer>> friendsMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		classroom = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			List<Integer> friends = new ArrayList<>(4);
			for (int j = 0; j < 4; j++) friends.add(Integer.parseInt(st.nextToken()));
			friendsMap.put(student, friends);
			seatStudent(student, friends);
		}

		System.out.println(calculateSatisfaction());
	}

	private static void seatStudent(int student, List<Integer> friends) {
		PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> {
			if (a.favor == b.favor) {
				if (a.empty == b.empty) {
					if (a.i == b.i) return a.j - b.j;
					return a.i - b.i;
				}
				return b.empty - a.empty;
			}
			return b.favor - a.favor;
		});

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (classroom[i][j] != 0) continue;

				int empty = 0, favor = 0;
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d], nj = j + dj[d];
					if (isInBounds(ni, nj)) {
						if (classroom[ni][nj] == 0) empty++;
						else if (friends.contains(classroom[ni][nj])) favor++;
					}
				}
				pq.offer(new Point(i, j, empty, favor));
			}
		}

		Point best = pq.poll();
		classroom[best.i][best.j] = student;
	}

	private static int calculateSatisfaction() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int student = classroom[i][j];
				List<Integer> friends = friendsMap.get(student);
				int like = 0;

				for (int d = 0; d < 4; d++) {
					int ni = i + di[d], nj = j + dj[d];
					if (isInBounds(ni, nj) && friends.contains(classroom[ni][nj])) like++;
				}

				if (like > 0) total += Math.pow(10, like - 1);
			}
		}
		return total;
	}

	private static boolean isInBounds(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}

	static class Point {
		int i, j, empty, favor;
		public Point(int i, int j, int empty, int favor) {
			this.i = i;
			this.j = j;
			this.empty = empty;
			this.favor = favor;
		}
	}
}
