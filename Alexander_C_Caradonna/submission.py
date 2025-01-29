# ---------------------------------------------------------- #
# -------------- Original code: do not modify -------------- #
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
# ------------------ End of original code ------------------ #
# ---------------------------------------------------------- #

import time

class TestCase:
    def __init__(self, input_matrix, expected_output):
        self.input = input_matrix
        self.expected_output = expected_output


# Define test cases
test_cases = [
    TestCase([[1,2,3],[4,5,6],[7,8,9]], [1,2,3,6,9,8,7,4,5]),
    TestCase([[1,2,3,4],[5,6,7,8],[9,10,11,12]], [1,2,3,4,8,12,11,10,9,5,6,7]),
    TestCase([[1]], [1]),
    TestCase([[2,3]], [2,3]),
    TestCase([[3],[2]], [3,2]),
    TestCase([[6,9,7]], [6,9,7]),
    TestCase([[7],[9],[6]], [7,9,6]),
    TestCase([[1,2],[3,4]], [1,2,4,3]),
    TestCase([[2,5],[8,4],[0,-1]], [2,5,4,-1,0,8]),
    TestCase([[2,5,8],[4,0,-1]], [2,5,8,-1,0,4]),
    TestCase([[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16],[17,18,19,20],[21,22,23,24]], [1,2,3,4,8,12,16,20,24,23,22,21,17,13,9,5,6,7,11,15,19,18,14,10]),
    TestCase([[1,2,3,4,5,6,7,8,9,10]], [1,2,3,4,5,6,7,8,9,10]),
    TestCase([[1],[2],[3],[4],[5],[6],[7],[8],[9],[10]], [1,2,3,4,5,6,7,8,9,10]),
    TestCase([[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]),
    TestCase([[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]], [1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13]),
    TestCase([[2,3,4],[5,6,7],[8,9,10],[11,12,13]], [2,3,4,7,10,13,12,11,8,5,6,9]),
    TestCase([[2,3,4],[5,6,7],[8,9,10],[11,12,13],[14,15,16]], [2,3,4,7,10,13,16,15,14,11,8,5,6,9,12]),
    TestCase([[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16],[17,18,19,20]], [1,2,3,4,8,12,16,20,19,18,17,13,9,5,6,7,11,15,14,10]),
    TestCase([[1,2,3,4,5,6],[7,8,9,10,11,12],[13,14,15,16,17,18],[19,20,21,22,23,24],[25,26,27,28,29,30]], [1,2,3,4,5,6,12,18,24,30,29,28,27,26,25,19,13,7,8,9,10,11,17,23,22,21,20,14,15,16]),
    TestCase([[1,2,3,4,5,6,7,8,9,10],[11,12,13,14,15,16,17,18,19,20]], [1,2,3,4,5,6,7,8,9,10,20,19,18,17,16,15,14,13,12,11]),
    TestCase([[1,11],[2,12],[3,13],[4,14],[5,15],[6,16],[7,17],[8,18],[9,19],[10,20]], [1,11,12,13,14,15,16,17,18,19,20,10,9,8,7,6,5,4,3,2]),
    TestCase([[1,2,3,4,5,6,7,8,9,10],[11,12,13,14,15,16,17,18,19,20],[21,22,23,24,25,26,27,28,29,30],[31,32,33,34,35,36,37,38,39,40],[41,42,43,44,45,46,47,48,49,50],[51,52,53,54,55,56,57,58,59,60],[61,62,63,64,65,66,67,68,69,70],[71,72,73,74,75,76,77,78,79,80],[81,82,83,84,85,86,87,88,89,90],[91,92,93,94,95,96,97,98,99,100]], [1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,99,98,97,96,95,94,93,92,91,81,71,61,51,41,31,21,11,12,13,14,15,16,17,18,19,29,39,49,59,69,79,89,88,87,86,85,84,83,82,72,62,52,42,32,22,23,24,25,26,27,28,38,48,58,68,78,77,76,75,74,73,63,53,43,33,34,35,36,37,47,57,67,66,65,64,54,44,45,46,56,55]),
    TestCase([[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15]], [1,2,3,4,5,10,15,14,13,12,11,6,7,8,9]),
    TestCase([[23,18,20,26,25],[24,22,3,4,4],[15,22,2,24,29],[18,15,23,28,28]], [23,18,20,26,25,4,29,28,28,23,15,18,15,24,22,3,4,24,2,22]),
    TestCase([[1,2,3],[4,6,0],[7,8,9]], [1,2,3,0,9,8,7,4,6]),
    TestCase([[1,11],[2,0],[3,13],[0,14],[5,0],[6,16],[0,0],[8,18],[9,19],[10,20]], [1,11,0,13,14,0,16,0,18,19,20,10,9,8,0,6,5,0,3,2]),
]

all_tests_passed = True

# Run tests once
for i, test_case in enumerate(test_cases, 1):
    output = spiralOrder(test_case.input)
    if output == test_case.expected_output:
        print(f"Test {i} passed.")
    else:
        print(f"Test {i} failed. Expected: {test_case.expected_output}, but got: {output}")
        all_tests_passed = False

# If all tests passed, run 1000 iterations and time it
if all_tests_passed:
    start_time = time.time()
    for _ in range(1000):
        for test_case in test_cases:
            spiralOrder(test_case.input)
    end_time = time.time()
    print(f"All tests passed. Time for 1000 iterations: {(end_time - start_time) * 1000:.2f} ms")
else:
    print("Some tests failed. Fix the issues and try again.")
