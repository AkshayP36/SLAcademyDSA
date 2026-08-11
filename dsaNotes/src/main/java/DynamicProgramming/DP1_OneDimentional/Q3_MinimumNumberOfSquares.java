package DynamicProgramming.DP1_OneDimentional;

/*
Problem Description
Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.


Problem Constraints
1 <= A <= 105


Input Format
First and only argument is an integer A.


Output Format
Return an integer denoting the minimum count.


Example Input
Input 1:
 A = 6
Input 2:
 A = 5


Example Output
Output 1:
 3
Output 2:
 2


Example Explanation
Explanation 1:
 Possible combinations are : (12 + 12 + 12 + 12 + 12 + 12) and (12 + 12 + 22).
 Minimum count of numbers, sum of whose squares is 6 is 3.
Explanation 2:
 We can represent 5 using only 2 numbers i.e. 12 + 22 = 5
 */

public class Q3_MinimumNumberOfSquares {

    public int findMinCountSquares(int[] storage, int number){
        if(number==0) return 0;

        if(storage[number] != -1) return storage[number];

        int minCount = Integer.MAX_VALUE;

        for(int i=1; i*i<=number; i++){
            int ans = findMinCountSquares(storage, number-i*i);
            minCount = Math.min(ans, minCount);
        }

        storage[number] =minCount + 1;
        return minCount+1;

    }
    public int countMinSquares(int A) {

        //creating storage
        int[] storage = new int[A+1];
        for(int i=0;i<A+1;i++){
            storage[i] = -1;
        }


        int minCount = findMinCountSquares(storage, A);

        return minCount;
    }
}
