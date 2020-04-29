package backToBackSwe.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class phoneNumberMnemonics {
    static Map<Integer, Character[]> keyMappings = new HashMap<>();
    static int recursiveCalls = 0;

    public static void createMappings() {
        keyMappings.put(2, new Character[]{'a','b', 'c'});
        keyMappings.put(3, new Character[]{'d','e','f'});
        keyMappings.put(4, new Character[]{'g','h','i'});
        keyMappings.put(5, new Character[]{'j','k','l'});
        keyMappings.put(6, new Character[]{'m','n','o'});
        keyMappings.put(7, new Character[]{'p','q','r','s'});
        keyMappings.put(8, new Character[]{'t','u','v'});
        keyMappings.put(9, new Character[]{'w','x','y','z'});
    }

    // choice, constraints and goal, forLoop stackframe
    public static List<String> letterCombinations(String digits) {
        int length = digits.length();
        List<String> combinations = new ArrayList<>();
        recursiveHelper(digits,"", combinations, length);
        return combinations;
    }

    private static void recursiveHelper(String digits, String append, List<String> combinations, int length) {
        recursiveCalls++;
        if (append.length() == length) {
            combinations.add(append);
        } else {
//            for (int i = 0, len = digits.length(); i < len; i++) {
            for (int i = 0; i < 1; i++) {
                char current = digits.charAt(i);
                Integer currentKey = Integer.valueOf(current) - 48;
                Character[] keySet = keyMappings.get(currentKey);

                for (int j = 0, keySetLen = keySet.length; j < keySetLen; j++) {
                    String currentAppend = append + String.valueOf(keySet[j]);
                    recursiveHelper(digits.substring(i+1), currentAppend, combinations, length);
                }
            }
        }
    }

    public static void main(String[] args) {
        createMappings();
//        List<String> combinationsOf43 = letterCombinations("43");
//        System.out.println(combinationsOf43);
//        System.out.println(combinationsOf43.size());
//        System.out.println(recursiveCalls);
        List<String> combinationsOf239 = letterCombinations("239");
        System.out.println(combinationsOf239);
        System.out.println(combinationsOf239.size());
        System.out.println(recursiveCalls);
    }
}
