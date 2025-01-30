"""
    T Washington
    Spirals

    Function:
    spiral(input_matrix)

    Given an m x n matrix, return all elements of the matrix in spiral order.

    LEET code link https://leetcode.com/problems/spiral-matrix/description/
"""
import time


def get_input_list(filename):
    matrix_list = []

    with open(filename, 'r') as file:
        for line in file:
            stripped_line = line.strip()
            if stripped_line:
                matrix = eval(stripped_line)
                matrix_list.append(matrix)

    return matrix_list


def top(input_list, i):
    y = len(input_list)
    x = len(input_list[0])
    if 2 * i <= y and 2 * i < x:
        return input_list[i][i:(x - i - 1)]
    else:
        return []


def right(input_list, i):
    y = len(input_list)
    x = len(input_list[0])
    if 2 * i <= x and 2 * i < y:
        return [input_list[a][x - i - 1] for a in range(i, y - i - 1)]
    else:
        return []


def bottom(input_list, i):
    y = len(input_list)
    x = len(input_list[0])
    if 2 * i + 1 < y and 2 * i < x:
        return input_list[y - 1 - i][(x - i + 1):i:-1]
    else:
        return []


def left(input_list, i):
    y = len(input_list)
    x = len(input_list[0])
    if 2 * i + 1 < x and 2 * i < y:
        return [input_list[a][i] for a in range(y - i - 1, i, -1)]
    else:
        return []


def broken_spiralify(input_list):
    """
    Do not use. this one broken.
    :param input_list:
    :return:
    """
    y = len(input_list)
    x = len(input_list[0])
    i = 0
    output = []
    while i <= int(y / 2) or i <= int(x / 2):
        output.append(top(input_list, i))
        output.append(right(input_list, i))
        output.append(bottom(input_list, i))
        output.append(left(input_list, i))
        i = i + 1
    return output  # [b for item in output for b in item]


def spiral(matrix):
    """
    This one is the one that works. Well, one hopes.
    :param matrix:
    :return:
    """
    x0, x1, y0, y1 = 0, len(matrix[0]) - 1, 0, len(matrix) - 1
    output = []
    while x0 <= x1 and y0 <= y1:
        if x0 == x1:
            output.append([matrix[a][x0] for a in range(y0, y1 + 1)])
        elif x0 + 1 == x1:
            output.append(
                [matrix[y0][x0]] + [matrix[a][x1] for a in range(y0, y1 + 1)] + [matrix[a][x0] for a in
                                                                                 range(y1, y0, -1)])
        elif y0 == y1:
            output.append([matrix[y0][a] for a in range(x0, x1 + 1)])
        elif y0 + 1 == y1:
            output.append(
                [matrix[y0][a] for a in range(x0, x1 + 1)] + [matrix[y1][a] for a in range(x1, x0 - 1, -1)])
        else:
            output.append([matrix[y0][a] for a in range(x0, x1 + 1)])
            output.append([matrix[a][x1] for a in range(y0 + 1, y1 + 1)])
            output.append([matrix[y1][a] for a in range(x1 - 1, x0 - 1, -1)])
            output.append([matrix[a][x0] for a in range(y1 - 1, y0, -1)])
        x0 = x0 + 1
        x1 = x1 - 1
        y0 = y0 + 1
        y1 = y1 - 1
    return [b for item in output for b in item]  # output


def run_testing_code():
    input_matrix_list = generate_input()

    start = time.perf_counter_ns()
    for input_list in input_matrix_list[2:]:
        output_list = spiral(input_list)
        end = time.perf_counter_ns()
        print(f"Input: {input_list}, output: {output_list}")
        if len(output_list) != len([b for item in input_list for b in item]):
            print(f'Error, input and output lists have different number of elements')
        if len(set(output_list)) != len(output_list):
            print(
                f'Error, output list values are not unique. len(set(output_list)) ={len(set(output_list))}, len(output_list) = {len(output_list)}')
    print(f"Time taken: {(end - start):.4f} ns")


def main():
    # set to true to do development work / debugging
    debug = True

    if debug:
        run_testing_code()

    # Please include an input variable matrix (like in the examples) in your code to allow for testing your code.
    # Print out the result to the console/stdout.
    matrix = [[1, 2], [3, 4], [5, 6], [7, 8], [9, 10], [11, 12], [13, 14], [15, 16]]
    print(spiral(matrix))


def generate_input():
    input_matrix_list = []
    # input_matrix_list = get_input_list("matrix_input.txt")
    # 1 x M
    input_matrix_list.append(generate_integer_matrix(1, 1))
    input_matrix_list.append(generate_integer_matrix(1, 2))
    input_matrix_list.append(generate_integer_matrix(1, 5))
    input_matrix_list.append(generate_integer_matrix(1, 13))
    # N x 1
    input_matrix_list.append(generate_integer_matrix(2, 1))
    input_matrix_list.append(generate_integer_matrix(3, 1))
    input_matrix_list.append(generate_integer_matrix(4, 1))
    input_matrix_list.append(generate_integer_matrix(8, 1))
    input_matrix_list.append(generate_integer_matrix(12, 1))
    # 2 x M
    input_matrix_list.append(generate_integer_matrix(2, 2))
    input_matrix_list.append(generate_integer_matrix(2, 5))
    input_matrix_list.append(generate_integer_matrix(2, 8))
    input_matrix_list.append(generate_integer_matrix(2, 13))
    # N x 2
    input_matrix_list.append(generate_integer_matrix(2, 2))
    input_matrix_list.append(generate_integer_matrix(3, 2))
    input_matrix_list.append(generate_integer_matrix(4, 2))
    input_matrix_list.append(generate_integer_matrix(8, 2))
    input_matrix_list.append(generate_integer_matrix(12, 2))
    # 3 x M
    input_matrix_list.append(generate_integer_matrix(3, 3))
    input_matrix_list.append(generate_integer_matrix(3, 4))
    input_matrix_list.append(generate_integer_matrix(3, 7))
    input_matrix_list.append(generate_integer_matrix(3, 12))
    # N x 3
    input_matrix_list.append(generate_integer_matrix(2, 3))
    input_matrix_list.append(generate_integer_matrix(3, 3))
    input_matrix_list.append(generate_integer_matrix(4, 3))
    input_matrix_list.append(generate_integer_matrix(8, 3))
    input_matrix_list.append(generate_integer_matrix(12, 3))
    # Various larger matrices
    input_matrix_list.append(generate_integer_matrix(7, 7))
    input_matrix_list.append(generate_integer_matrix(7, 8))
    input_matrix_list.append(generate_integer_matrix(7, 9))
    input_matrix_list.append(generate_integer_matrix(8, 7))
    input_matrix_list.append(generate_integer_matrix(8, 8))
    input_matrix_list.append(generate_integer_matrix(8, 9))
    input_matrix_list.append(generate_integer_matrix(9, 7))
    input_matrix_list.append(generate_integer_matrix(9, 8))
    input_matrix_list.append(generate_integer_matrix(9, 9))
    input_matrix_list.append(generate_integer_matrix(23, 26))
    input_matrix_list.append(generate_integer_matrix(35, 25))
    input_matrix_list.append(generate_integer_matrix(35, 24))
    input_matrix_list.append(generate_integer_matrix(35, 23))
    input_matrix_list.append(generate_integer_matrix(35, 44))
    input_matrix_list.append(generate_integer_matrix(35, 45))
    input_matrix_list.append(generate_integer_matrix(35, 46))
    input_matrix_list.append(generate_integer_matrix(35, 47))
    input_matrix_list.append(generate_integer_matrix(36, 24))
    input_matrix_list.append(generate_integer_matrix(36, 25))
    input_matrix_list.append(generate_integer_matrix(36, 23))
    input_matrix_list.append(generate_integer_matrix(36, 44))
    input_matrix_list.append(generate_integer_matrix(36, 45))
    input_matrix_list.append(generate_integer_matrix(36, 46))
    input_matrix_list.append(generate_integer_matrix(36, 47))
    return input_matrix_list


def generate_integer_matrix(x, y):
    return [[i * x + j + 1 for j in range(x)] for i in range(y)]


if __name__ == "__main__":
    main()
