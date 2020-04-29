package pramps;//package pramps;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MatrixSpiral {
//
//    public static List<Integer> printSpiral (int[][] input) {
//        List<Integer> outputList = new ArrayList<>();
//
//        int topLeftRow = 0;
//        int topLeftCol = 0;
//        int bottomRightRow = input[input.length - 1].length - 1;
//        int bottomRightCol = input[input.length - 1].length - 1;
//        int i = 0; int j = 0;
//
//        int topRight = input.length - 1;
//
//        while (topLeftRow < bottomRightRow) {
//            while (topLeftCol < bottomRightCol) {
//                outputList.add(input[topLeftRow][topLeftCol]);
//                topLeftCol++;
//            }
//            topLeftRow--;
//            topLeftCol = bottomRightCol;
//            bottomRightCol
//        }
//        while (topLeftCol <= topRight && topLeftRow <= bottomRightRow) {
//            while (topLeftCol <= topRight) {
//                outputList.add(input[topLeftRow][topLeftCol]);
//                topLeftCol++;
//            }
//            topLeftRow--; topLeftCol = topRight;
//            while ()
//        }
//
//        int totalElementsSize = input.length * input[0].length;
//
//        while (outputList.size() < totalElementsSize) {
//            if (j == topRight) {
//                i++;
//            }
//
//            outputList.add(input[i][j]);
//            j++;
//        }
//
//        return outputList;
//    }
//
//    public static void main (String[] args) {
//        int[][] inputArray = new int[][]{{1,2,3,4},{12,13,14,5},{11,16,156}, {10,9,8,7}};
//        System.out.println(printSpiral(inputArray));
//    }
//}

//function spiralCopy(inputMatrix):
//        numRows = inputMatrix.length
//        numCols = inputMatrix[0].length
//
//        topRow = 0
//        btmRow = numRows - 1
//        leftCol = 0
//        rightCol = numCols - 1
//        result = []
//
//        while (topRow <= btmRow AND leftCol <= rightCol):
//        # copy the next top row
//        for i from leftCol to rightCol:
//        result.push(inputMatrix[topRow][i])
//        topRow++
//
//        # copy the next right hand side column
//        for i from topRow to btmRow:
//        result.push(inputMatrix[i][rightCol])
//        rightCol--
//
//        # copy the next bottom row
//        if (topRow <= btmRow):
//        for i from rightCol to leftCol:
//        result.push(inputMatri[btmRow][i])
//        btmRow--
//
//        # copy the next left hand side column
//        if (leftCol <= rightCol):
//        for i from btmRow to topRow:
//        result.push(inputMatrix[i][leftCol])
//        leftCol++
//
//        return result