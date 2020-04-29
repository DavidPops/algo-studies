package backToBackSwe.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class B2BphoneNumberSolution {
    // This can be a hashtable, any structure to map 'number' to 'letters' it can manifest as
    private List<String> digitToPossibleLetters = Arrays.asList(
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    );

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        List<String> mnemonics = new ArrayList<>();
        exploreCombinations(0, new StringBuilder(), digits, mnemonics);
        return mnemonics;
    }

    private void exploreCombinations(
            int currentDigit,
            StringBuilder partialMnemonic,
            String digits,
            List<String> mnemonics
    ) {
        if (currentDigit == digits.length()) {
            mnemonics.add(partialMnemonic.toString());
            return;
        }

        char digitCharacter = digits.charAt(currentDigit);
        int digitAsInt = digitCharacter - '0';

        String letters = digitToPossibleLetters.get(digitAsInt);

        for (char possibleLetter: letters.toCharArray()) {
            // 1.) Choose - Append the letter that this digit can materialize into
            partialMnemonic.append(possibleLetter);

            // 2.) Explore - Recurse on the decision with changed state. We advance the digit we are working on.
            exploreCombinations(currentDigit + 1, partialMnemonic, digits, mnemonics);

            // 3.) Unchoose - We have returned to this stack frame of choice. Remove the choice, next loop will choose again.
            partialMnemonic.deleteCharAt(partialMnemonic.length() - 1);
        }
    }
}
