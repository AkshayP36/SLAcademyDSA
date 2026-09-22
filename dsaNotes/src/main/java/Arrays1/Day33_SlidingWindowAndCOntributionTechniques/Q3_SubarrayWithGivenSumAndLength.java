package Arrays1.Day33_SlidingWindowAndCOntributionTechniques;

/*
Problem Description
Given an array A of length N. Also given are integers B and C.
Return 1 if there exists a subarray with length B having sum C and 0 otherwise


Problem Constraints
1 <= N <= 105
1 <= A[i] <= 104
1 <= B <= N
1 <= C <= 109


Input Format
First argument A is an array of integers.
The remaining arguments B and C are integers


Output Format
Return 1 if such a subarray exist and 0 otherwise


Example Input
Input 1:
A = [4, 3, 2, 6, 1]
B = 3
C = 11
Input 2:
A = [4, 2, 2, 5, 1]
B = 4
C = 6


Example Output
Output 1:
1
Output 2:
0


Example Explanation
Explanation 1:
The subarray [3, 2, 6] is of length 3 and sum 11.
Explanation 2:
There are no such subarray.
 */
public class Q3_SubarrayWithGivenSumAndLength {
    public int solve(int[] A, int B, int C) {

        if(A.length==0) return 0;
        if(A.length==1 && B==1 && C==A[0]) return 1;

        int[] psum = new int[A.length];
        psum[0] = A[0];

        for(int i=1; i<A.length; i++){
            psum[i] = A[i] + psum[i-1];
        }

        int start = 0, end = B-1;

        while(end<A.length){
            int temp;
            if(start==0){
                temp = psum[end];
            }else{
                temp = psum[end] - psum[start-1];
            }

            if(temp==C) return 1;
            start++;
            end++;
        }

        return 0;
    }
}
