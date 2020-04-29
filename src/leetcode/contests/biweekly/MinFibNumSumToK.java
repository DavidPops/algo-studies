package leetcode.contests.biweekly;

import java.util.ArrayList;
import java.util.TreeSet;

class TreeSetSolution {
    public int findMinFibonacciNumbers(int k) {

        TreeSet<Integer> set = new TreeSet<>();
        int second_last = 1;
        int last = 1;
        while(last <= k){
            set.add(last);
            int current = last + second_last;
            second_last = last;
            last = current;
        }
        int steps = 0;
        while(k != 0){
            k -= set.floor(k);
            steps++;
        }

        return steps;
    }

    public static void main (String[] args) {
        TreeSetSolution treeSetSolution1 = new TreeSetSolution();
        System.out.println(treeSetSolution1.findMinFibonacciNumbers(5));
    }
}

//https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/discuss/585632/JavaC%2B%2BPython-Easy-Prove
//https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/discuss/585577/C%2B%2B-Greedy
//http://codeforces.com/blog/entry/67171
class RecursiveSolution {
    public int findMinFibonacciNumbers(int k) {
        if (k < 2) return k;
        int a = 1, b = 1, c = 2;
        while (b <= k) {
            c = a + b;
            a = b;
            b = c;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}