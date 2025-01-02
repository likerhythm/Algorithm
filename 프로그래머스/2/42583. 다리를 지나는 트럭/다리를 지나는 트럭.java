import java.util.*;

class Solution {
    
    static class Bridge {
        
        int length;
        int weight;
        int[] occupied;
        
        public Bridge(int length, int weight) {
            this.length = length;
            this.weight = weight;
            this.occupied = new int[length];
        }
        
        public boolean canAdd(int truck) {
            int sum = 0;
            for (int o : occupied) {
                sum += o;
            }
            return sum + truck <= weight;
        }
        
        public void add(int truck) {
            occupied[occupied.length - 1] = truck;
        }
        
        public int move() { // 리턴 값이 0인 경우 건넌 트럭이 없다는 뜻
            int crossed = occupied[0];
            for (int i=1; i<length; i++) {
                occupied[i - 1] = occupied[i];
            }
            occupied[occupied.length - 1] = 0;
            return crossed;
        }
    }
    
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Bridge bridge = new Bridge(bridgeLength, weight);
        List<Integer> crossedTrucks = new ArrayList<>();
        int cursor = 0;
        int time = 0;
        while (crossedTrucks.size() < truckWeights.length) {
            time += 1;
            if (cursor < truckWeights.length) {
                if (bridge.canAdd(truckWeights[cursor])) {
                    bridge.add(truckWeights[cursor]);
                    cursor++;
                }
            }
            int crossed = bridge.move();
            if (crossed > 0) {
                crossedTrucks.add(crossed);
            }
        }
        
        return time + 1;
    }
}