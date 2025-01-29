import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Assume we always start with 0,0
        int leftBound = 0;
        int rightBound = columns-1;
        int topBound = 0;
        int bottomBound = rows-1;
        List<Integer> spirally = new ArrayList<>();

        int totalNumbers = rows * columns;

        while (spirally.size() < totalNumbers) {
            // Move left to right
            for (int j = leftBound; j <= rightBound && spirally.size() < totalNumbers; j++) {
                spirally.add(matrix[topBound][j]);
            }
            topBound++;
            // Move top to bottom
            for (int j = topBound; j <= bottomBound && spirally.size() < totalNumbers; j++) {
                spirally.add(matrix[j][rightBound]);
            }
            rightBound--;
            // Move right to left
            for (int j = rightBound; j >= leftBound && spirally.size() < totalNumbers; j--) {
                spirally.add(matrix[bottomBound][j]);
            }
            bottomBound--;
            // Move bottom to top
            for (int j = bottomBound; j >= topBound && spirally.size() < totalNumbers; j--) {
                spirally.add(matrix[j][leftBound]);
            }
            leftBound++;
        }
        return spirally;


//        System.out.println(matrix);


//        return spirally;
    }
}