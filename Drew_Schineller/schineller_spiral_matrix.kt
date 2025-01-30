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