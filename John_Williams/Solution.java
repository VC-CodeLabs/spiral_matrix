import java.util.ArrayList;
import java.util.List;

public class Solution {
   enum Direction {
      EAST,
      SOUTH,
      WEST,
      NORTH
   }

   public List<Integer> spiralOrder(int[][] matrix) {
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

   public void test1() {
      int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}};
      System.out.println("TEST 1: " + spiralOrder(matrix));
   }

   public void test2() {
      int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12}
      };
      System.out.println("TEST 2: " + spiralOrder(matrix));
   }

   public void test3() {
      int[][] matrix = {
            {1,2,3}
      };
      System.out.println("TEST 3: " + spiralOrder(matrix));
   }

   public void test4() {
      int[][] matrix = {
            {11},
            {12},
            {13}
      };
      System.out.println("TEST 4: " + spiralOrder(matrix));
   }

   public void test5() {
      int[][] matrix = {
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {10,11,12,13,14,15,16,17,18,19},
            {20,21,22,23,24,25,26,27,28,29}
      };
      System.out.println("TEST 5: " + spiralOrder(matrix));
   }

   public void test6() {
      int[][] matrix = {
      };
      System.out.println("TEST 6: " + spiralOrder(matrix));
   }

   public void test7() {
      int[][] matrix = {
            { 0, 1, 2, 3},
            {10,11,12,13},
            {20,21,22,23},
            {30,31,32,33},
            {40,41,42,43}
      };
      System.out.println("TEST 7: " + spiralOrder(matrix));
   }


   public static void main(String[] args) {
      Solution solution = new Solution();

      solution.test1();
      solution.test2();
      solution.test3();
      solution.test4();
      solution.test5();
      solution.test6();
      solution.test7();
   }

}