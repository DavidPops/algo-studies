package leetcode.AprilThirtyDays;

// Complexity Analysis
//Time complexity : O(n^n). Recursive function is called n^n times. I'll say it is a convenient upper bound. Big Theta would be quite smaller than this.
// n choose n I believe. Like Marty and Gayle, branches ^ depth
//Space complexity : O(n). Depth of recursion is n. Recursion/decision tree I believe.
// Max it will ever go is n deep at any point on our stackframe. I mean recursive calls that can spin off from a certain frame on the stackframe.
// I even said the correct thing and IntelliJ made this more clear.

// Took time during this to familiarise myself with all of IntelliJ's features, or most and I somehow intuitively got it especially Profiler. Remembered some old tricks too eg ^^ and double shift.
class BruteForceSolution {
    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    public int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }

    public static void main(String[] args) {
        BruteForceSolution solution = new BruteForceSolution();
        solution.maxProfit(new int[]{1, 7, 2, 3, 6, 7, 6, 7});
        solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }
}

// Just like all those annoying case analysis stuff. But it shows the logic behind why the solutions work and are valid.
// Proof of correctness using case analysis it is then.
// Time is O(n) single pass. Space is O(1)
class PeakValleySolution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) // keep ascending on the slope till the summit
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        PeakValleySolution solution = new PeakValleySolution();
        solution.maxProfit(new int[]{1, 7, 2, 3, 6, 7, 6, 7});
        solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }
}

// I'll say greedy and not DP. Proof of correctness like Benyam said would be tough. Coz proof is the logic
// I guess max contiguous sub-array sum is greedy as well. Can it be modeled as a peak finding problem??
// Peak finding vid might help too. I'll say peak finding helps with the greedy strategy
class SimpleOnePassSolution {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        SimpleOnePassSolution solution = new SimpleOnePassSolution();
        solution.maxProfit(new int[]{1, 7, 2, 3, 6, 7, 6, 7});
        solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }
}
