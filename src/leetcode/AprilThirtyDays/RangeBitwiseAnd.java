package leetcode.AprilThirtyDays;

public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;

        while (n >= 1) {
            if ((n & (n-1)) == 0) return n;
            n--;
        }
        return -1;
    }

    public int rangeBitwiseAnd2(int m, int n) {
        while (n > m) {
            int first = n & m;
            int second = n & (m + 1);

            if (second > first) {
                n--;
            }
        }
        return n & m; // OR n+1
    }

    public int rangeBitwiseAnd3(int m, int n) {
        return m & n & (-1 << (32 - Integer.numberOfLeadingZeros(n - m)));

//        Essentially all it does is mask out the least significant bytes of the difference of the two number (as if you &'d these values together
//        they would always end up as zero since they differ by 1 i.e. 00 & 01 = 0, 10 & 01 = 0, and yes 11 & 10/01 does not equal 0
//        but since we are &'d in series each bit is going to be zero'd at least once so all will be).
//        Finally you just &'d the most significant bits (i.e. the common bits of the number not ones in the difference -
//        hense the mask out of the lsb so if we just use 6 bits then [17,19] is [0b010001,0b010011] with a difference of 2 [0b000010] and mask of [0b111100]
//        which changes reduces the inputs to [0b010000,0b010000] and a result of 16 [0b010000] when &'d)
    }

    public int BrianKernighan(int m, int n) {
        while(n>m)
            n = n & n-1;
        return m&n;
//        We can also check if there is some power of 2 lies between (m,n], then result is always 0.
//        And if there is no power of 2 lies between(m,n], then we can check using while loop.
    }

    public static void main(String[] args) {
        RangeBitwiseAnd solution3 = new RangeBitwiseAnd();
        solution3.rangeBitwiseAnd3(5,7);
        solution3.BrianKernighan(5,12);
    }

    private static long getLogBase2(int a) {
        return (long)(Math.log(a)/Math.log(2));
    }

    public int rangeBitwiseAndLog(int m, int n) {
        if (m == 0) return 0;
        long nextVal = getLogBase2(m);
        if (Math.pow(2, nextVal + 1) <= n) return 0;

        long value = m;
        for (long i=value+1; i<=n; i++) {
            value = value & i;
            if (value == 0) return (int)value;
        }

        return (int)value;
    }
}
