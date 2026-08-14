package DynamicProgramming.DP4_ApplicationsOfKnapsack;

/*
Problem Description
Given a rod of length N units and an array A of size N denotes prices that contains prices of all pieces of size 1 to N.
Find and return the maximum value that can be obtained by cutting up the rod and selling the pieces.


Problem Constraints
1 <= N <= 1000
0 <= A[i] <= 106


Input Format
First and only argument is an integer array A of size N.


Output Format
Return an integer denoting the maximum value that can be obtained by cutting up the rod and selling the pieces.


Example Input
Input 1:
 A = [3, 4, 1, 6, 2]
Input 2:
 A = [1, 5, 2, 5, 6]


Example Output
Output 1:
 15
Output 2:
 11


Example Explanation
Explanation 1:
 Cut the rod of length 5 into 5 rods of length (1, 1, 1, 1, 1) and sell them for (3 + 3 + 3 + 3 + 3) = 15.
Explanation 2:
 Cut the rod of length 5 into 3 rods of length (2, 2, 1) and sell them for (5 + 5 + 1) = 11.
 */
public class Q1_CuttingARod {

    public int getMax(int[]A, int[][] storage, int index, int capacity){
        if(index<0 || capacity ==0) return 0;
        if(storage[index][capacity]!=-1) return storage[index][capacity];

        int include = 0;
        int pieceLength = index+1;
        if(pieceLength<=capacity){
            include = getMax(A, storage, index, capacity-pieceLength) + A[index];
        }
        int exclude = getMax(A, storage, index-1, capacity);

        int ans = Math.max(include, exclude);

        storage[index][capacity] = ans;

        return ans;
    }
    public int solve(int[] A) {
        int n = A.length;
        int capacity = A.length;

        int[][] storage = new int[n][capacity+1];
        for(int i=0;i<storage.length; i++){
            for(int j=0;j<storage[0].length; j++){
                storage[i][j] = -1;
            }
        }

        int ans = getMax(A, storage, n-1, capacity);

        return ans;
    }
}
