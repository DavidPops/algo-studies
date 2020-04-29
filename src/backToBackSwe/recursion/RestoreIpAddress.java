package backToBackSwe.recursion;

import java.util.ArrayList;
import java.util.List;

// there would be a valid helper. This is where I feel backtracking would be heavily used.
// Take the last 3, reduce the decision space, take the next last 3 till I finish.
// ORRRRR
// Global list variable that lists all permutations.
// Take the first character,
// Take the first 3 characters, check if valid, then continue by reducing the decision space.
// take the next 3, reducing the decision space, check valid, reduce the decision space again
// if digits.length <= 3 and > 0
// In the recursive calls, put the one for selecting 2 underneath, and 1 also.
// How do we backtrack and forget the decision we made. This is the real problem. Maybe it means we return stuff to the decision space??
// seems no loops here.
public class RestoreIpAddress {
    static int max = 255;
    static int min = 0;
    int recursiveCalls = 0; // Stanford guy said this is badddd.
    List<String> restoredAddresses = new ArrayList<>();

    private List<String> restoreIpAddresses(String rawIpString) {
        recursiveHelper(rawIpString, "");
        System.out.println(restoredAddresses);
        return restoredAddresses;
    }

    private void recursiveHelper(String rawIpString, String computed) {
        recursiveCalls++; // Marty said no globals.
        if (computed.split("\\.").length == 4 && !(rawIpString.length() > 0)) { // investigate this behaviour
//            if (rawIpString.length() > 0) { // make a compound if statement above instead.
//                return;
//            }
//            String[] segments = computed.split("\\.");
            //for (int i = 0; i < segments.length; i++) { // IS the check necessary. Stanford code jam looking guy said no. He's right.
              //  if (!validSubSegment(Integer.valueOf(segments[i]))) {
                //    return;
                //}
            //}

            restoredAddresses.add(computed.substring(0, computed.length() - 1));
            return;
        }
//        if (rawIpString.length() > 0 && rawIpString.length() < 4) {
//            restoredAddresses.add(computed);
//        }
        // Seems a loop might indeed be useful. Naahh its like printBinary NOT printDecimal
        String oneCharSegment = "", twoCharSegment = "", threeCharSegment = "";
        if (rawIpString.length() >= 1) oneCharSegment = rawIpString.substring(0, 1) + ".";
        if (rawIpString.length() >= 2) twoCharSegment = rawIpString.substring(0, 2) + ".";
        if (rawIpString.length() >= 3) threeCharSegment = rawIpString.substring(0, 3) + "."; // there was a bug here. Backtrack.

//        if (validSubSegment(Integer.valueOf(oneCharSegment))) {
        if (rawIpString.length() >= 1 && validSubSegment(Integer.valueOf(oneCharSegment.substring(0, oneCharSegment.length() - 1))))  recursiveHelper(rawIpString.substring(1), computed + oneCharSegment);
//        }
//        if (validSubSegment(Integer.valueOf(twoCharSegment))) {
        if (rawIpString.length() >= 2 && validSubSegment(Integer.valueOf(twoCharSegment.substring(0, twoCharSegment.length() - 1))))  recursiveHelper(rawIpString.substring(2), computed + twoCharSegment);
//        }
//        if (validSubSegment(Integer.valueOf(threeCharSegment))) {
        if (rawIpString.length() >= 3 && validSubSegment(Integer.valueOf(threeCharSegment.substring(0, threeCharSegment.length() - 1)))) recursiveHelper(rawIpString.substring(3), computed + threeCharSegment);
//        }
    }

    private boolean validSubSegment(int digits) {
        if (digits > min && digits <= max) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RestoreIpAddress restoreIpAddress = new RestoreIpAddress();
        restoreIpAddress.restoreIpAddresses("255255232132");
        System.out.println("Recursive Calls: " + restoreIpAddress.recursiveCalls);
        RestoreIpAddress restoreIpAddress1 = new RestoreIpAddress();
        restoreIpAddress1.restoreIpAddresses("125523213");
        System.out.println("Recursive Calls: " + restoreIpAddress1.recursiveCalls);
    }
}