import java.util.*;

public class album {

    static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            total.put(genre, total.getOrDefault(genre, 0) + play);

            music.putIfAbsent(genre, new HashMap<>());
            music.get(genre).put(i, play);
        }

        List<String> sortedGenres = new ArrayList<>(total.keySet());
        sortedGenres.sort((a, b) -> total.get(b) - total.get(a));

        for (String genre : sortedGenres) {
            List<Integer> songs = new ArrayList<>(music.get(genre).keySet());
            songs.sort((a, b) -> music.get(genre).get(b) - music.get(genre).get(a));

            answer.add(songs.get(0));
            if (songs.size() > 1) answer.add(songs.get(1));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(Arrays.toString(solution(genres, plays))); // [4, 1, 3, 0]
    }
}
