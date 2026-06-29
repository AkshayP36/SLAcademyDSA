package Arrays1.Day32_CarryForwardAndSubarrays;

/*
Problem Description
Given an array A of N non-negative numbers and a non-negative number B,
you need to find the number of subarrays in A with a sum less than B.
We may assume that there is no overflow.


Problem Constraints
1 <= N <= 5 x 103
1 <= A[i] <= 1000
1 <= B <= 107


Input Format
First argument is an integer array A.
Second argument is an integer B.


Output Format
Return an integer denoting the number of subarrays in A having sum less than B.


Example Input
Input 1:
 A = [2, 5, 6]
 B = 10
Input 2:
 A = [1, 11, 2, 3, 15]
 B = 10


Example Output
Output 1:
 4
Output 2:
 4


Example Explanation
Explanation 1:
 The subarrays with sum less than B are {2}, {5}, {6} and {2, 5},
Explanation 2:
 The subarrays with sum less than B are {1}, {2}, {3} and {2, 3}
 */
public class Q9_CountingSubarraysEasy {

    public boolean subarraySum(int[]psum, int l, int r, int B){
        int sum;

        if(l == 0){
            sum = psum[r];
        }
        else{
            sum = psum[r] - psum[l-1];
        }

        return sum < B;
    }
    public int solve(int[] A, int B) {

        int counter = 0;

        int[] psum = new int[A.length];
        psum[0] = A[0];

        for(int i=1; i<A.length; i++){
            psum[i] = psum[i-1] + A[i];
        }

        for(int i=0;i<A.length; i++){
            for(int j=i; j<A.length; j++){
                boolean flag = subarraySum(psum, i,j, B);
                if(flag) counter++;
            }

        }


        return counter;
    }

}
