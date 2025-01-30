def spiralOrder(matrix):
    result = []
    if not matrix or not matrix[0]:
        return result

    top, bottom, left, right = 0, len(matrix) - 1, 0, len(matrix[0]) - 1

    while top <= bottom and left <= right:
        # Traverse from left to right along the top row
        for col in range(left, right + 1):
            result.append(matrix[top][col])
        top += 1

        # Traverse from top to bottom along the right column
        for row in range(top, bottom + 1):
            result.append(matrix[row][right])
        right -= 1

        if top <= bottom:
            # Traverse from right to left along the bottom row
            for col in range(right, left - 1, -1):
                result.append(matrix[bottom][col])
            bottom -= 1

        if left <= right:
            # Traverse from bottom to top along the left column
            for row in range(bottom, top - 1, -1):
                result.append(matrix[row][left])
            left += 1

    return result


# Test Cases...
lil_square = [[1,2],[3,4]]
medium_square = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
big_matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
chonky_matrix = [[1,2,3,4,5], [6,7,8,9,10],[11,12,13,14,15], [16,17,18,19,20], [21,22,23,24,25]]
rect_3x4_matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
long_boi = [[1, 2, 3, 4]]
tall_boi = [[1], [2], [3], [4]]
lonely_boi = [[5]]
long_rectangle = [[1, 2, 3], [4, 5, 6]]
tall_rectangle = [[1, 2], [3, 4], [5, 6]]
ten_x_one = [[1, 2], [3, 4], [5, 6]]
one_x_ten = [[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]]

test_cases = [lil_square, medium_square, big_matrix, chonky_matrix, rect_3x4_matrix, long_boi, tall_boi, lonely_boi, long_rectangle, tall_rectangle, ten_x_one, one_x_ten]# Output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

for test in test_cases:
    print(spiralOrder(test))
