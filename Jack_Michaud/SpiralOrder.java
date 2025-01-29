// Jack Michaud
// 2025-01-29
import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9, 10, 11, 12},
                          {13, 14, 15, 16},
                          {17, 18, 19, 20}};
//        int[][] matrix = {{1, 2, 3, 4},
//                          {5, 6, 7, 8},
//                          {9, 10, 11, 12}};
//        int[][] matrix = {{1, 2, 3},
//                          {4, 5, 6},
//                          {7, 8, 9}};
//        int[][] matrix = {{1, 2, 3}};
//        int[][] matrix = {{1}, {2}, {3}};
        SpiralOrder instance = new SpiralOrder();
        System.out.println(instance.spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int rowMin = 0;
        int colMin = 0;
        int rowMax = matrix.length - 1;
        int colMax = matrix[0].length - 1;
        int goal = matrix.length * matrix[0].length;

        while (true) {

            // move east
            for (int col = colMin; col <= colMax; col++) {
                result.add(matrix[rowMin][col]);
            }
            if (result.size() == goal)
                break;

            rowMin++;

            // move south
            for (int row = rowMin; row <= rowMax; row++) {
                result.add(matrix[row][colMax]);
            }
            if (result.size() == goal)
                break;

            colMax--;

            // move west
            for (int col = colMax; col >= colMin; col--) {
                result.add(matrix[rowMax][col]);
            }
            if (result.size() == goal)
                break;

            rowMax--;

            // move north
            for (int row = rowMax; row >= rowMin; row--) {
                result.add(matrix[row][colMin]);
            }
            if (result.size() == goal)
                break;

            colMin++;
        }

        return result;
    }
}
