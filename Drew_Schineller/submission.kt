// ---------------------------------------------------------- //
// -------------- Original code: do not modify -------------- //
class Solution {
    var totalLength: Int = 0;
    var x: Int = 0;
    var y: Int = 0;
    var sub: Int = 0;
    var dir: Char = 'R';

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        this.totalLength = matrix.size * matrix[0].size;
        val result: MutableList<Int> = mutableListOf();

        return crawl(matrix, result).toList();
    }

    fun crawl(matrix: Array<IntArray>, result: MutableList<Int>): MutableList<Int> {
        if (result.size == this.totalLength) {
            return result;
        }
        result.add(matrix[this.y][this.x]);

        if (dir == 'R') {
            // Move right
            if (this.x + 1 < matrix[y].size - sub) {
                this.x += 1;
            } else {
                this.dir = 'D';
            }
        }
        if (dir == 'D') {
            // Move down
            if (this.y + 1 < matrix.size - sub) {
                this.y += 1;
            } else {
                this.dir = 'L';
            }
        }
        if (dir == 'L') {
            // Move left
            if (this.x - 1 >= 0 + sub) {
                this.x -= 1;
            } else {
                this.dir = 'U';
                this.sub += 1;
            }
        }
        if (dir == 'U') {
            // Move up
            if (this.y - 1 >= 0 + sub) {
                this.y -= 1;
            } else {
                this.dir = 'R';
                // Move right
                if (this.x + 1 < matrix[y].size - sub) {
                    this.x += 1;
                } else {
                    this.dir = 'D';
                }
            }
        }

        return crawl(matrix, result);
    }
}

// ------------------ End of original code ------------------ //
// ---------------------------------------------------------- //

fun main() {
    val testCases = listOf(
arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12)) to listOf(1,2,3,4,8,12,11,10,9,5,6,7),
arrayOf(intArrayOf(1)) to listOf(1),
arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9)) to listOf(1,2,3,6,9,8,7,4,5),
arrayOf(intArrayOf(2,3)) to listOf(2,3),
arrayOf(intArrayOf(3),intArrayOf(2)) to listOf(3,2),
arrayOf(intArrayOf(6,9,7)) to listOf(6,9,7),
arrayOf(intArrayOf(7),intArrayOf(9),intArrayOf(6)) to listOf(7,9,6),
arrayOf(intArrayOf(1,2),intArrayOf(3,4)) to listOf(1,2,4,3),
arrayOf(intArrayOf(2,5),intArrayOf(8,4),intArrayOf(0,-1)) to listOf(2,5,4,-1,0,8),
arrayOf(intArrayOf(2,5,8),intArrayOf(4,0,-1)) to listOf(2,5,8,-1,0,4),
arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12),intArrayOf(13,14,15,16),intArrayOf(17,18,19,20),intArrayOf(21,22,23,24)) to listOf(1,2,3,4,8,12,16,20,24,23,22,21,17,13,9,5,6,7,11,15,19,18,14,10),
arrayOf(intArrayOf(1,2,3,4,5,6,7,8,9,10)) to listOf(1,2,3,4,5,6,7,8,9,10),
arrayOf(intArrayOf(1),intArrayOf(2),intArrayOf(3),intArrayOf(4),intArrayOf(5),intArrayOf(6),intArrayOf(7),intArrayOf(8),intArrayOf(9),intArrayOf(10)) to listOf(1,2,3,4,5,6,7,8,9,10),
arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12),intArrayOf(13,14,15,16)) to listOf(1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10),
arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(6,7,8,9,10),intArrayOf(11,12,13,14,15),intArrayOf(16,17,18,19,20),intArrayOf(21,22,23,24,25)) to listOf(1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13),
arrayOf(intArrayOf(2,3,4),intArrayOf(5,6,7),intArrayOf(8,9,10),intArrayOf(11,12,13)) to listOf(2,3,4,7,10,13,12,11,8,5,6,9),
arrayOf(intArrayOf(2,3,4),intArrayOf(5,6,7),intArrayOf(8,9,10),intArrayOf(11,12,13),intArrayOf(14,15,16)) to listOf(2,3,4,7,10,13,16,15,14,11,8,5,6,9,12),
arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12),intArrayOf(13,14,15,16),intArrayOf(17,18,19,20)) to listOf(1,2,3,4,8,12,16,20,19,18,17,13,9,5,6,7,11,15,14,10),
arrayOf(intArrayOf(1,2,3,4,5,6),intArrayOf(7,8,9,10,11,12),intArrayOf(13,14,15,16,17,18),intArrayOf(19,20,21,22,23,24),intArrayOf(25,26,27,28,29,30)) to listOf(1,2,3,4,5,6,12,18,24,30,29,28,27,26,25,19,13,7,8,9,10,11,17,23,22,21,20,14,15,16),
arrayOf(intArrayOf(1,2,3,4,5,6,7,8,9,10),intArrayOf(11,12,13,14,15,16,17,18,19,20)) to listOf(1,2,3,4,5,6,7,8,9,10,20,19,18,17,16,15,14,13,12,11),
arrayOf(intArrayOf(1,11),intArrayOf(2,12),intArrayOf(3,13),intArrayOf(4,14),intArrayOf(5,15),intArrayOf(6,16),intArrayOf(7,17),intArrayOf(8,18),intArrayOf(9,19),intArrayOf(10,20)) to listOf(1,11,12,13,14,15,16,17,18,19,20,10,9,8,7,6,5,4,3,2),
arrayOf(intArrayOf(1,2,3,4,5,6,7,8,9,10),intArrayOf(11,12,13,14,15,16,17,18,19,20),intArrayOf(21,22,23,24,25,26,27,28,29,30),intArrayOf(31,32,33,34,35,36,37,38,39,40),intArrayOf(41,42,43,44,45,46,47,48,49,50),intArrayOf(51,52,53,54,55,56,57,58,59,60),intArrayOf(61,62,63,64,65,66,67,68,69,70),intArrayOf(71,72,73,74,75,76,77,78,79,80),intArrayOf(81,82,83,84,85,86,87,88,89,90),intArrayOf(91,92,93,94,95,96,97,98,99,100)) to listOf(1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,99,98,97,96,95,94,93,92,91,81,71,61,51,41,31,21,11,12,13,14,15,16,17,18,19,29,39,49,59,69,79,89,88,87,86,85,84,83,82,72,62,52,42,32,22,23,24,25,26,27,28,38,48,58,68,78,77,76,75,74,73,63,53,43,33,34,35,36,37,47,57,67,66,65,64,54,44,45,46,56,55),
arrayOf(intArrayOf(1,2,3,4,5),intArrayOf(6,7,8,9,10),intArrayOf(11,12,13,14,15)) to listOf(1,2,3,4,5,10,15,14,13,12,11,6,7,8,9),
arrayOf(intArrayOf(23,18,20,26,25),intArrayOf(24,22,3,4,4),intArrayOf(15,22,2,24,29),intArrayOf(18,15,23,28,28)) to listOf(23,18,20,26,25,4,29,28,28,23,15,18,15,24,22,3,4,24,2,22),
arrayOf(intArrayOf(1,2,3),intArrayOf(4,6,0),intArrayOf(7,8,9)) to listOf(1,2,3,0,9,8,7,4,6),
arrayOf(intArrayOf(1,11),intArrayOf(2,0),intArrayOf(3,13),intArrayOf(0,14),intArrayOf(5,0),intArrayOf(6,16),intArrayOf(0,0),intArrayOf(8,18),intArrayOf(9,19),intArrayOf(10,20)) to listOf(1,11,0,13,14,0,16,0,18,19,20,10,9,8,0,6,5,0,3,2)
    )

    var solution = Solution()
    var allTestsPassed = true

    for ((index, testCase) in testCases.withIndex()) {

        solution = Solution()
        val (input, expected) = testCase
        val output = try {
            solution.spiralOrder(input)
        } catch (e: Exception) {
            println("Test ${index + 1} threw an exception: ${e.message}")
            emptyList<Int>()
        }
        if (output == expected) {
            println("Test ${index + 1} passed.")
        } else {
            println("Test ${index + 1} failed. Expected: $expected, but got: $output")
            allTestsPassed = false
        }
    }

    if (allTestsPassed) {
        println("Running 10000 iterations for benchmark...")
        val startTime = System.nanoTime()
        repeat(10000) {
            for (testCase in testCases) {
                solution = Solution()
                solution.spiralOrder(testCase.first)
            }
        }
        val endTime = System.nanoTime()
        println("Time for 1000 iterations: ${(endTime - startTime) / 1e6} ms")
    } else {
        println("Some tests failed. Fix the issues and try again.")
    }
}
