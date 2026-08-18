package Graphs.Graph2_BFS;

import java.util.ArrayList;

/*
Problem Description
Given a 2-D board A of size N x M containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.


Problem Constraints
1 <= N, M <= 1000


Input Format
First and only argument is a N x M character matrix A.


Output Format
Return nothing. Make changes to the the input only as matrix is passed by reference.


Example Input
Input 1:
 A = [
       [X, X, X, X],
       [X, O, O, X],
       [X, X, O, X],
       [X, O, X, X]
     ]
Input 2:
 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]


Example Output
Output 1:
 After running your function, the board should be:
 A = [
       [X, X, X, X],
       [X, X, X, X],
       [X, X, X, X],
       [X, O, X, X]
     ]
Output 2:
 After running your function, the board should be:
 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]


Example Explanation
Explanation 1:
 O in (4,2) is not surrounded by X from below.
Explanation 2:
 No O's are surrounded.
 */
public class Q7_CaptureRegionsOnBoard {
    public void dfs(ArrayList<ArrayList<Character>> a, int i, int j, int n, int m) {

        // Outside the board
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }

        // We only want to visit O
        if (a.get(i).get(j) != 'O') {
            return;
        }

        // Mark this O as safe
        a.get(i).set(j, 'S');

        // Up
        dfs(a, i - 1, j, n, m);

        // Down
        dfs(a, i + 1, j, n, m);

        // Left
        dfs(a, i, j - 1, n, m);

        // Right
        dfs(a, i, j + 1, n, m);
    }

    public void solve(ArrayList<ArrayList<Character>> a) {

        int n = a.size();
        int m = a.get(0).size();

        // Step 1: Start DFS from top and bottom boundaries

        for (int j = 0; j < m; j++) {

            // Top row
            if (a.get(0).get(j) == 'O') {
                dfs(a, 0, j, n, m);
            }

            // Bottom row
            if (a.get(n - 1).get(j) == 'O') {
                dfs(a, n - 1, j, n, m);
            }
        }

        // Step 2: Start DFS from left and right boundaries

        for (int i = 0; i < n; i++) {

            // Left column
            if (a.get(i).get(0) == 'O') {
                dfs(a, i, 0, n, m);
            }

            // Right column
            if (a.get(i).get(m - 1) == 'O') {
                dfs(a, i, m - 1, n, m);
            }
        }

        // Step 3:
        // Remaining O's are surrounded -> X
        // S's are safe -> O

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (a.get(i).get(j) == 'O') {
                    a.get(i).set(j, 'X');
                }
                else if (a.get(i).get(j) == 'S') {
                    a.get(i).set(j, 'O');
                }
            }
        }
    }

}
