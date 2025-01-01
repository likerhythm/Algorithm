import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map.Entry;

class Solution {
    public String solution(String[] participants, String[] completions) {
        Map<String, Integer> map = new HashMap<>();
        for (String participant : participants) {
            map.merge(participant, 1, (oldVal, newVal) -> oldVal + newVal);
        }
        for (String completion : completions) {
            map.merge(completion, 1, (oldVal, newVal) -> oldVal - newVal);
        }
        Optional<String> found = map.entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 0)
            .map(Map.Entry::getKey)
            .findFirst();
        if (found.isPresent()) {
            return found.get();
        }
        return "";
    }
}