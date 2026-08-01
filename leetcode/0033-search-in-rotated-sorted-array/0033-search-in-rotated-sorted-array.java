class Solution {

    int n;

    public int search(int[] nums, int target) {
        n = nums.length;
        int left = 0, right = n - 1;
        int newStart = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                newStart = i + 1;
                break;
            }
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = nums[getIdx(mid, newStart)];
            if (midValue == target) {
                return getIdx(mid, newStart);
            }
            if (midValue > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    int getIdx(int idx, int newStart) {
        return (idx + newStart) % n;
    }
}