import java.util.*;

class Solution {
    
    class Car implements Comparable<Car> {
        int s, e; // 진입, 진출 지점
        
        @Override
        public int compareTo(Car other) {
            return Integer.compare(this.e, other.e);
        }
        
        Car(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    
    public int solution(int[][] routes) {
        List<Car> cars = new ArrayList<>();
        for (int[] route : routes) {
            cars.add(new Car(route[0], route[1]));
        }
        
        Collections.sort(cars);
        
        int camera = -40_000;
        int answer = 0;
        for (Car car : cars) {
            if (!(car.s <= camera && camera <= car.e)) {
                camera = car.e;
                answer++;
            }
        }
        
        return answer;
    }
}