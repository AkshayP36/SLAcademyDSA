package DynamicProgramming.DP3_Knapsack;


/*
Problem Description
Given a knapsack weight A and a set of items with certain value B[i] and weight C[i], we need to calculate maximum amount that could fit in this quantity.
This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.


Problem Constraints
1 <= A <= 1000
1 <= |B| <= 1000
1 <= B[i] <= 1000
1 <= C[i] <= 1000


Input Format
First argument is the Weight of knapsack A
Second argument is the vector of values B
Third argument is the vector of weights C


Output Format
Return the maximum value that fills the knapsack completely


Example Input
Input 1:
A = 10
B = [5]
C = [10]
Input 2:
A = 10
B = [6, 7]
C = [5, 5]


Example Output
Output 1:
 5
Output 2:
14


Example Explanation
Explanation 1:
Only valid possibility is to take the given item.
Explanation 2:
Take the second item twice.
 */


public class Q3_UnboundedKnapsack {

    public int getMax(int[] values, int[] weight, int[][] storage, int index, int capacity){
        if(index<0 || capacity ==0) return 0;

        if(storage[index][capacity]!=-1) return storage[index][capacity];

        int include =0;
        if(weight[index]<=capacity){
            include = getMax(values, weight, storage, index, capacity-weight[index]) + values[index];
        }
        int exclude = getMax(values, weight, storage, index-1, capacity);

        int ans = Math.max(include, exclude);
        storage[index][capacity] = ans;

        return ans;
    }
    public int solve(int capacity, int[] values, int[] weight) {
        int n = values.length;

        int[][] storage = new int[n][capacity+1];
        for(int i=0;i<storage.length;i++){
            for(int j=0;j<storage[0].length; j++){
                storage[i][j] = -1;
            }
        }

        int ans = getMax(values, weight, storage, n-1, capacity);

        return ans;
    }
}
