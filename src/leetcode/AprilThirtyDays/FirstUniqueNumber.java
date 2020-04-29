package leetcode.AprilThirtyDays;

import java.util.*;

public class FirstUniqueNumber {
    Queue<Integer> numbers;
    Set<Integer> uniques = new LinkedHashSet<>();
    Map<Integer, Boolean> uniques2 = new LinkedHashMap<>();

    public FirstUniqueNumber(int[] nums) {
        numbers = new ArrayDeque<>(nums.length);
        for (int number : nums) {
            numbers.add(number);
            uniques.add(number);
//            uniques2.putIfAbsent(number, false);
            if (uniques2.get(number) != null) {
                uniques2.put(number, false);
            } else {
                uniques2.put(number, true);
            }
        }
    }

    public int showFirstUnique() {
        for (Map.Entry<Integer, Boolean> entry: uniques2.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public void add(int value) {
        if (uniques2.get(value) != null) {
            uniques2.put(value, false);
        } else {
            uniques2.put(value, true);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
