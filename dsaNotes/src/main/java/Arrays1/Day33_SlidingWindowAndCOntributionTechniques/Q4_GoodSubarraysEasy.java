package Arrays1.Day33_SlidingWindowAndCOntributionTechniques;

/*
Problem Description
Given an array of integers A, a subarray of an array is said to be good if it fulfills any one of the criteria:
1. Length of the subarray is be even, and the sum of all the elements of the subarray must be less than B.
2. Length of the subarray is be odd, and the sum of all the elements of the subarray must be greater than B.
Your task is to find the count of good subarrays in A.


Problem Constraints
1 <= len(A) <= 5 x 103
1 <= A[i] <= 103
1 <= B <= 107


Input Format
The first argument given is the integer array A.
The second argument given is an integer B.


Output Format
Return the count of good subarrays in A.


Example Input
Input 1:
A = [1, 2, 3, 4, 5]
B = 4
Input 2:
A = [13, 16, 16, 15, 9, 16, 2, 7, 6, 17, 3, 9]
B = 65


Example Output
Output 1:
6
Output 2:
36


Example Explanation
Explanation 1:
Even length good subarrays = {1, 2}
Odd length good subarrays = {1, 2, 3}, {1, 2, 3, 4, 5}, {2, 3, 4}, {3, 4, 5}, {5}
Explanation 1:
There are 36 good subarrays
 */
public class Q4_GoodSubarraysEasy {

    public int solve(int[] A, int B) {

        int[] psum = new int[A.length];
        psum[0] = A[0];

        for(int i=1;i<A.length; i++){
            psum[i] = psum[i-1] + A[i];
        }
        int count = 0;

        for(int i=0;i<A.length; i++){
            for(int j=i;j<A.length; j++){
                int lengthOfSubarray = j-i+1;
                int sum;
                if(i==0){
                    sum = psum[j];
                }else{
                    sum = psum[j] - psum[i-1];
                }
                if(lengthOfSubarray%2==0 && sum<B) {

                    count++;
                }else if(lengthOfSubarray%2!=0 && sum>B){

                    count++;
                }
            }
        }


        return count;
    }
}
