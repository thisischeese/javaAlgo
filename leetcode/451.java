
import java.util.*;

class Solution {
    public String frequencySort(String s) {

        StringBuilder sb = new StringBuilder();

        // 문자 : 빈도수 저장
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // 빈도수를 인덱스로 가지는 버킷 생성
        List<Character>[] buckets = new List[s.length() + 1];

        for (char key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }




        // 거꾸로 버킷 돌면서 문자열 만들기
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }

        return sb.toString();
    }
}