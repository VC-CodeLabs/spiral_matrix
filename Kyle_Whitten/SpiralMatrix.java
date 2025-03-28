import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        // Parse input matrix into 2D array
        int[][] matrix = Arrays.stream(args[0]
                                    .trim()
                                    .replaceAll("\\[","")
                                    .replaceAll("\\]\\]","").split("\\],"))
                                .map(row -> Arrays.stream(row.split(","))
                                    .map(String::trim)
                                    .mapToInt(Integer::parseInt)
                                    .toArray())
                                .toArray(int[][]::new);

        // Exception case for empty arrays
        if (matrix.length < 1) {
            System.out.println("[]");
            return;
        }

        // Establish starting cell position and max bounds
        int startingColIdx = 0;
        int maxColIdx = matrix[0].length-1;
        int startingRowIdx = 0;
        int maxRowIdx = matrix.length-1;

        List<Integer> outputList = new ArrayList<>();

        // If the starting cell's position would be greater than the halfway
        // point of either height or width, we've completed the spiral.
        // Otherwise, we do a "lap" around the outside of the 2D array.
        while ((startingRowIdx*2) < matrix.length && (startingColIdx*2) < matrix[0].length) {
            // Traverse along the top row...
            for (int colIdx = startingColIdx; colIdx <= maxColIdx; colIdx++) {
                outputList.add(matrix[startingRowIdx][colIdx]);
            }

            // ...down the right column...
            for (int rowIdx = startingRowIdx+1; rowIdx <= maxRowIdx; rowIdx++) {
                outputList.add(matrix[rowIdx][maxColIdx]);
            }

            // ...backwards along the bottom row...
            for (int colIdx = maxColIdx-1; colIdx >= startingColIdx 
                    && startingRowIdx != maxRowIdx; colIdx--) {
                outputList.add(matrix[maxRowIdx][colIdx]);
            }

            // ...and up the left column
            for (int rowIdx = maxRowIdx-1; rowIdx > startingRowIdx 
                    && startingColIdx != maxColIdx; rowIdx--) {
                outputList.add(matrix[rowIdx][startingColIdx]);
            }

            // Update starting cell position and max bounds
            startingColIdx++;
            maxColIdx--;
            startingRowIdx++;
            maxRowIdx--;
        }

        System.out.println(outputList);
    }
}
