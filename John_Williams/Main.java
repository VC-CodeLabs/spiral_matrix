import java.util.*;

public class Main {

    // -------------------------------------------------------------------------- //
    // --------------------- Original code - do not modify ---------------------- //
    enum Direction {
        EAST,
        SOUTH,
        WEST,
        NORTH
     }
  
     public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rowMax = matrix.length-1;
        if (rowMax < 0) {
           return result;
        }
        int colMax = matrix[0].length-1;
        if (colMax < 0) {
           return result;
        }
  
        int rowMin = 0;
        int colMin = 0;
  
        int col = 0, row = 0;
        int colInc = 1;
        int rowInc = 0;
        Direction direction = Direction.EAST;
  
        while (colMin <= colMax && rowMin <= rowMax) {
  
           result.add(matrix[row][col]);
           //System.out.println("["+row+"/"+rowMin+"-"+rowMax+","+col+"/"+colMin+"-"+colMax+"]" + result);
  
           if (direction == Direction.EAST && col == colMax) {
              direction = Direction.SOUTH;
              colInc = 0;
              rowInc = 1;
              rowMin++;
           } else if (direction == Direction.SOUTH && row == rowMax) {
              direction = Direction.WEST;
              colInc = -1;
              rowInc = 0;
              colMax--;
           } else if (direction == Direction.WEST && col == colMin) {
              direction = Direction.NORTH;
              colInc = 0;
              rowInc = -1;
              rowMax--;
           } else if (direction == Direction.NORTH && row == rowMin) {
              direction = Direction.EAST;
              colInc = 1;
              rowInc = 0;
              colMin++;
           }
  
           col += colInc;
           row += rowInc;
        }
        return result;
     }
    // --------------------------- End of original code ------------------------- //
    // -------------------------------------------------------------------------- //

    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
            new TestCase(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, Arrays.asList(1,2,3,6,9,8,7,4,5)),
            new TestCase(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}, Arrays.asList(1,2,3,4,8,12,11,10,9,5,6,7)),
            new TestCase(new int[][]{{1}}, Arrays.asList(1)),
            new TestCase(new int[][]{{2,3}}, Arrays.asList(2,3)),
            new TestCase(new int[][]{{3},{2}}, Arrays.asList(3,2)),
            new TestCase(new int[][]{{6,9,7}}, Arrays.asList(6,9,7)),
            new TestCase(new int[][]{{7},{9},{6}}, Arrays.asList(7,9,6)),
            new TestCase(new int[][]{{1,2},{3,4}}, Arrays.asList(1,2,4,3)),
            new TestCase(new int[][]{{2,5},{8,4},{0,-1}}, Arrays.asList(2,5,4,-1,0,8)),
            new TestCase(new int[][]{{2,5,8},{4,0,-1}}, Arrays.asList(2,5,8,-1,0,4)),
            new TestCase(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20},{21,22,23,24}}, Arrays.asList(1,2,3,4,8,12,16,20,24,23,22,21,17,13,9,5,6,7,11,15,19,18,14,10)),
            new TestCase(new int[][]{{1,2,3,4,5,6,7,8,9,10}}, Arrays.asList(1,2,3,4,5,6,7,8,9,10)),
            new TestCase(new int[][]{{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}}, Arrays.asList(1,2,3,4,5,6,7,8,9,10)),
            new TestCase(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}, Arrays.asList(1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10)),
            new TestCase(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}}, Arrays.asList(1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13)),
            new TestCase(new int[][]{{2,3,4},{5,6,7},{8,9,10},{11,12,13}}, Arrays.asList(2,3,4,7,10,13,12,11,8,5,6,9)),
            new TestCase(new int[][]{{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}}, Arrays.asList(2,3,4,7,10,13,16,15,14,11,8,5,6,9,12)),
            new TestCase(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}}, Arrays.asList(1,2,3,4,8,12,16,20,19,18,17,13,9,5,6,7,11,15,14,10)),
            new TestCase(new int[][]{{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24},{25,26,27,28,29,30}}, Arrays.asList(1,2,3,4,5,6,12,18,24,30,29,28,27,26,25,19,13,7,8,9,10,11,17,23,22,21,20,14,15,16)),
            new TestCase(new int[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}}, Arrays.asList(1,2,3,4,5,6,7,8,9,10,20,19,18,17,16,15,14,13,12,11)),
            new TestCase(new int[][]{{1,11},{2,12},{3,13},{4,14},{5,15},{6,16},{7,17},{8,18},{9,19},{10,20}}, Arrays.asList(1,11,12,13,14,15,16,17,18,19,20,10,9,8,7,6,5,4,3,2)),
            new TestCase(new int[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20},{21,22,23,24,25,26,27,28,29,30},{31,32,33,34,35,36,37,38,39,40},{41,42,43,44,45,46,47,48,49,50},{51,52,53,54,55,56,57,58,59,60},{61,62,63,64,65,66,67,68,69,70},{71,72,73,74,75,76,77,78,79,80},{81,82,83,84,85,86,87,88,89,90},{91,92,93,94,95,96,97,98,99,100}}, Arrays.asList(1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,99,98,97,96,95,94,93,92,91,81,71,61,51,41,31,21,11,12,13,14,15,16,17,18,19,29,39,49,59,69,79,89,88,87,86,85,84,83,82,72,62,52,42,32,22,23,24,25,26,27,28,38,48,58,68,78,77,76,75,74,73,63,53,43,33,34,35,36,37,47,57,67,66,65,64,54,44,45,46,56,55)),
            new TestCase(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}}, Arrays.asList(1,2,3,4,5,10,15,14,13,12,11,6,7,8,9)),
            new TestCase(new int[][]{{23,18,20,26,25},{24,22,3,4,4},{15,22,2,24,29},{18,15,23,28,28}}, Arrays.asList(23,18,20,26,25,4,29,28,28,23,15,18,15,24,22,3,4,24,2,22)),
            new TestCase(new int[][]{{1,2,3},{4,6,0},{7,8,9}}, Arrays.asList(1,2,3,0,9,8,7,4,6)),
            new TestCase(new int[][]{{1,11},{2,0},{3,13},{0,14},{5,0},{6,16},{0,0},{8,18},{9,19},{10,20}}, Arrays.asList(1,11,0,13,14,0,16,0,18,19,20,10,9,8,0,6,5,0,3,2))
                );

        boolean allTestsPassed = true;

        // Run tests once
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            List<Integer> output = spiralOrder(testCase.input);
            if (output.equals(testCase.expectedOutput)) {
                System.out.println("Test " + (i + 1) + " passed.");
            } else {
                System.out.println("Test " + (i + 1) + " failed. Expected: " + testCase.expectedOutput + ", but got: " + output);
                allTestsPassed = false;
            }
        }

        // If all tests passed, run 10000 iterations and time it
        if (allTestsPassed) {
            long startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                for (TestCase testCase : testCases) {
                    spiralOrder(testCase.input);
                }
            }
            long endTime = System.nanoTime();
            System.out.println("All tests passed. Time for 1000 iterations: " + (endTime - startTime) / 1e6 + " ms");
        } else {
            System.out.println("Some tests failed. Fix the issues and try again.");
        }
    }

    public static class TestCase {
        int[][] input;
        List<Integer> expectedOutput;

        TestCase(int[][] input, List<Integer> expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
