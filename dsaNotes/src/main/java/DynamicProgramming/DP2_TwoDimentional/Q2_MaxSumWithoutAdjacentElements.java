package DynamicProgramming.DP2_TwoDimentional;

import java.util.Arrays;

/*
Problem Description
Given a 2 x N grid of integers, A, your task is to choose numbers from the grid such that sum of these numbers is maximized.
However, you cannot choose two numbers that are adjacent horizontally, vertically, or diagonally.

Return the maximum possible sum.

Note: You are allowed to choose more than 2 numbers from the grid.


Problem Constraints
1 <= N <= 20000
1 <= A[i] <= 2000


Input Format
The first and the only argument of input contains a 2d matrix, A.


Output Format
Return an integer, representing the maximum possible sum.


Example Input
Input 1:
 A = [
        [1]
        [2]
     ]
Input 2:
 A = [
        [1, 2, 3, 4]
        [2, 3, 4, 5]
     ]


Example Output
Output 1:
 2
Output 2:
 8


Example Explanation
Explanation 1:
 We will choose 2 (From 2nd row 1st column).
Explanation 2:
 We will choose 3 (From 2nd row 2nd column) and 5 (From 2nd row 4th column).
 */
public class Q2_MaxSumWithoutAdjacentElements {

    private int[] B;
    private int[] memo;
    private int N;

    private int getMaxValues(int col){
        if(col>=N) return 0;
        if(memo[col] != -1) return memo[col];

        int exclude = getMaxValues(col+1);
        int include = getMaxValues(col+2) + B[col];

        memo[col] = Math.max(include, exclude);

        return memo[col];
    }

    public int adjacent(int[][] A) {

        N = A[0].length;
        B = new int[N];

        for(int i=0;i<N;i++){
            B[i] = Math.max(A[0][i], A[1][i]);
        }

        memo = new int[N];
        Arrays.fill(memo, -1);
        return getMaxValues(0);
    }

}






/*
The trick: shrink the problem first

Look at any column of the grid. Its two numbers sit right on top of each other — that's "vertically adjacent." So you can never pick both numbers from the same column. That means each column really only offers you one usable number: whichever of the two is bigger (why would you ever pick the smaller one if you're only allowed one?).

So step one: squash the grid into a single row, B[i] = max(A[0][i], A[1][i]).

Now here's the second piece of the rule: picking a number also blocks its diagonal neighbors, not just the one directly next to it. If you pick something in column i, that automatically rules out both cells of column i+1 (straight side-neighbor for one row, diagonal-neighbor for the other) and both cells of column i-1. So once you collapse to one number per column, the rule simplifies beautifully to:

Pick numbers from a line, but never pick two numbers that are next to each other.

That's the classic "House Robber" problem — you're a kid raiding a row of cookie jars, and you can't take from two jars sitting side by side, or the guard next door hears you.

Thinking recursively (like a child deciding jar by jar)

Stand at column col and ask yourself just one question: "Do I take this jar, or skip it?"

Skip it → move on to col+1, nothing gained here.
Take it → grab B[col], but now col+1 is forbidden too, so jump straight to col+2.

Whichever choice gives more cookies wins:

solve(col) = max( solve(col+1), B[col] + solve(col+2) )

You keep asking this same question at every column, all the way until you run off the end of the row — at that point there's nothing left to grab, so solve(col) = 0 when col >= N.

Why memoize?

Look at the tree in the diagram: solve(2) gets asked for by two different paths — once from solve(0)'s "skip" branch (via solve(1)), and once from solve(0)'s "take" branch directly. Without memoization, you'd solve solve(2) (and everything below it) twice. With N up to 20,000, that doubling cascades into an exponential mess. So the first time you compute solve(col), you write the answer down in a memo table; every future time you're asked the same question, you just read the answer instead of recomputing.
 */