package backToBackSwe.recursion;

import java.util.ArrayList;
import java.util.List;

class B2BPermutations {
    public List<List<Integer>> permute(int[] originalArray) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        generateAllPermutations(new ArrayList<>(), originalArray, allPermutations);

        return allPermutations;
    }

    private void generateAllPermutations(List<Integer> runningChoices, int[] originalArray, List<List<Integer>> allPermutations) {
        if (runningChoices.size() == originalArray.length) {
            allPermutations.add(new ArrayList<>(runningChoices)); // Deep copying to create a proper copy?? Else we are updating everythin inside with whatever is in runningChoices at every point in time.
            return;
        }

    /*
      Every stack frame of this function call represents the expression of trying (almost) all items in every "slot" in the array.
      The recursion stops when we are choosing on 1 past the final "slot".
    */
        for (int i = 0; i < originalArray.length; i++) {
            int choice = originalArray[i];

            // Skip if element already exists in 'runningChoices'
            if (runningChoices.contains(choice)) {
                continue;
            }

            // 1.) Choose - Add the item to the 'runningChoices'
            runningChoices.add(choice);

            // 2.) Explore - Recurse on the choice
            generateAllPermutations(runningChoices, originalArray, allPermutations);

            // 3.) Unchoose - We have returned from the recursion, remove the choice we made.
            // The next iteration will try another item in the "slot" we are working on.
            runningChoices.remove(runningChoices.size() - 1);
        }
    }

    public static void main (String[] args) {
        B2BPermutations example1 = new B2BPermutations();
        System.out.println(example1.permute(new int[]{1,2,3}));
    }
}
