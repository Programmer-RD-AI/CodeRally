def opposite_face(face):
    # Returns the opposite face index (0-based)
    return [1, 0, 3, 2, 5, 4][face]

def solve_case(cubes, case_num):
    n = len(cubes)
    # dp[i][f] will store the maximum height of a tower with cube i and face f on top
    dp = [[0] * 6 for _ in range(n)]
    # To store the predecessor cube and face for backtracking
    predecessor = [[(-1, -1)] * 6 for _ in range(n)]

    max_height = 0
    max_cube, max_face = -1, -1

    # Iterate over each cube as the current top of the tower
    for i in range(n):
        for f in range(6):
            # Initially, a tower with only the current cube has height 1
            dp[i][f] = 1

            # Check all previous cubes to see if we can stack cube i on top of cube j
            for j in range(i):
                for g in range(6):
                    # Check if cube j's face g can support cube i's face f
                    if cubes[j][g] == cubes[i][opposite_face(f)]:
                        if dp[j][g] + 1 > dp[i][f]:
                            dp[i][f] = dp[j][g] + 1
                            predecessor[i][f] = (j, g)

            # Update the maximum tower height and its top cube and face
            if dp[i][f] > max_height:
                max_height = dp[i][f]
                max_cube, max_face = i, f

    # Output the result
    print(f"{case_num}")
    print(max_height)

    # Backtrack to recover the sequence of cubes and faces
    result = []
    cube, face = max_cube, max_face
    while cube != -1:
        result.append((cube + 1, ['front', 'back', 'left', 'right', 'top', 'bottom'][face]))
        cube, face = predecessor[cube][face]

    # Print the sequence from top to bottom
    for cube_info in reversed(result):
        print(cube_info[0], cube_info[1])

def main():
    case_num = 1
    while True:
        n = int(input())
        if n == 0:
            break

        cubes = [list(map(int, input().split())) for _ in range(n)]
        solve_case(cubes, case_num)
        case_num += 1
        print()  # Blank line between cases

if __name__ == "__main__":
    main()
