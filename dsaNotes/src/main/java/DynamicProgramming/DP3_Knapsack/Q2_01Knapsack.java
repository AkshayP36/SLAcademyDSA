package DynamicProgramming.DP3_Knapsack;


/*
Problem Description
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
NOTE:
You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).


Problem Constraints
1 <= N <= 103
1 <= C <= 103
1 <= A[i], B[i] <= 103


Input Format
First argument is an integer array A of size N denoting the values on N items.
Second argument is an integer array B of size N denoting the weights on N items.
Third argument is an integer C denoting the knapsack capacity.


Output Format
Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.


Example Input
Input 1:
 A = [60, 100, 120]
 B = [10, 20, 30]
 C = 50
Input 2:
 A = [10, 20, 30, 40]
 B = [12, 13, 15, 19]
 C = 10


Example Output
Output 1:
 220
Output 2:
 0


Example Explanation
Explanation 1:
 Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220
Explanation 2:
 Knapsack capacity is 10 but each item has weight greater than 10 so no items can be considered in the knapsack therefore answer is 0.
 */
public class Q2_01Knapsack {

    public int getMaxValue(int[]A, int[]B, int[][] storage, int index, int capacity){

        if(index<0 || capacity ==0){
            return 0;
        }

        if(storage[index][capacity]!=-1) return storage[index][capacity];
        int include=0;
        if(B[index]<=capacity)
            include = getMaxValue(A, B, storage, index-1, capacity-B[index]) + A[index];
        int exclude = getMaxValue(A, B, storage, index-1, capacity);

        int ans = Math.max(include, exclude);

        storage[index][capacity] = ans;

        return ans;
    }
    public int solve(int[] A, int[] B, int C) {
        int n = A.length;
        int[][] storage = new int[n][C+1];

        for(int i=0;i<storage.length;i++){
            for(int j=0;j<storage[0].length; j++){
                storage[i][j] = -1;
            }
        }

        int ans = getMaxValue(A, B, storage, n-1, C);


        return ans;
    }
}
