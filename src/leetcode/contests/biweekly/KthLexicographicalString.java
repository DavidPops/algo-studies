package leetcode.contests.biweekly;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/discuss/585590/C%2B%2B-DFS-and-Math
//https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/discuss/585569/Easiest-solution-using-bfs-C%2B%2B

public class KthLexicographicalString {
    public String getHappyString(int n, int k) {
        String base = "abc";
        List<String> setOfHappies = new ArrayList<>();
//        if(recursiveHelper(setOfHappies, base, 0, k, n, new StringBuilder())) return setOfHappies.get(0);
        recursiveHelper(setOfHappies, base, n, new StringBuilder());
        if (setOfHappies.size()>=k) return setOfHappies.get(k-1);
        return "";
    }

    // Very bad model
    /*public boolean recursiveHelper(List<String> setOfHappies, String base, int runningIndex, int k, int n, StringBuilder currentString) {
        if (currentString.length() > n) {
            return false;
        }
        if(currentString.length() == n) {
            setOfHappies.add(currentString.toString());
            return true;
        } else if(runningIndex <= n) {

            for (int i = 0; i < n; i++) {
                //            currentString.append(base.charAt(i));
                recursiveHelper(setOfHappies, base, ++runningIndex, k, n, currentString.append('a'));
                recursiveHelper(setOfHappies, base, ++runningIndex, k, n, currentString.append('b'));
                recursiveHelper(setOfHappies, base, ++runningIndex, k, n, currentString.append('c'));
            }
        }
        return false;
    }*/

    public void recursiveHelper(List<String> setOfHappies, String base, int n, StringBuilder currentString) {
        if (currentString.length() > 1 && currentString.charAt(currentString.length() - 1) == currentString.charAt(currentString.length() - 2)) return;
        if (currentString.length() > n) return;
        if(currentString.length() == n) {
            setOfHappies.add(currentString.toString());
            return;
        }

            for (int i = 0; i < base.length(); i++) {
                currentString.append(base.charAt(i));
//                if(base.charAt(i) != currentString.charAt(currentString.length() - 1)) {
//                    currentString.append(base.charAt(i));
//                }
                recursiveHelper(setOfHappies, base, n, currentString); //runningIndex is a bitch
                currentString.deleteCharAt(currentString.length() - 1);

//                recursiveHelper(setOfHappies, base, ++runningIndex, k, n, currentString.append('b'));
//                recursiveHelper(setOfHappies, base, ++runningIndex, k, n, currentString.append('c'));
            }
    }

    public static void main(String[] args) {
        KthLexicographicalString s1 = new KthLexicographicalString();
        System.out.println(s1.getHappyString(1,3));
        System.out.println(s1.getHappyString(3,9));
        System.out.println(s1.getHappyString(10,100));
    }
}

class ManualIshSolution {

    List<String> list = new ArrayList<>();

    void rec(int n, StringBuilder sb) {
        if (n == 0) {
            list.add(sb.toString());
            return;
        }

        char c = sb.charAt(sb.length()-1);
        if (c == 'a') {
            sb.append('b');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
            sb.append('c');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
        }

        if (c == 'b') {
            sb.append('a');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
            sb.append('c');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
        }

        if (c == 'c') {
            sb.append('a');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
            sb.append('b');
            rec(n-1, sb);
            sb.setLength(sb.length() - 1);
        }
    }

    public String getHappyString(int n, int k) {

        StringBuilder sb = new StringBuilder();
        sb.append('a');
        rec(n-1, sb);
        sb.setLength(sb.length() - 1);

        sb.append('b');
        rec(n-1, sb);
        sb.setLength(sb.length() - 1);

        sb.append('c');
        rec(n-1, sb);
        sb.setLength(sb.length() - 1);
        return k > list.size() ? "" : list.get(k-1);
    }
}
