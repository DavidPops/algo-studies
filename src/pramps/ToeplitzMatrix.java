package pramps;

public class ToeplitzMatrix {
    static boolean isToeplitz(int[][] arr) {
        // your code goes here
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {
                if (arr[i][j] != arr[i+1][j+1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] array1 = new int[][]{{1,2,3,4},{5,1,2,3},{6,5,1,2}};
        int[][] array2 = new int[][]{{1,2,3,4},{5,1,9,3},{6,5,1,2}};
        System.out.println(isToeplitz(array1));
        System.out.println(isToeplitz(array2));
    }
}
