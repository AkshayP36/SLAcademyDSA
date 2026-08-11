package DynamicProgramming.DP1_OneDimentional;


/*
Problem Description
You are given an array A of N integers and three integers B, C, and D.
You have to find the maximum value of A[i]*B + A[j]*C + A[k]*D, where 1 <= i <= j <= k <= N.


Problem Constraints

1 <= N <= 105
-10000 <= A[i], B, C, D <= 10000


Input Format

First argument is an array A
Second argument is an integer B
Third argument is an integer C
Fourth argument is an integer D


Output Format

Return an Integer S, i.e maximum value of (A[i] * B + A[j] * C + A[k] * D), where 1 <= i <= j <= k <= N.


Example Input

Input 1:
 A = [1, 5, -3, 4, -2]
 B = 2
 C = 1
 D = -1
Input 2:
 A = [3, 2, 1]
 B = 1
 C = -10
 D = 3


Example Output

Output 1:
 18
Output 2:
 -4


Example Explanation

Explanation 1:
 If you choose i = 2, j = 2, and k = 3 then we will get
 A[2]*B + A[2]*C + A[3]*D = 5*2 + 5*1 + (-3)*(-1) = 10 + 5 + 3 = 18
Explanation 2:
 If you choose i = 1, j = 3, and k = 3 then we will get
 A[1]*B + A[3]*C + A[3]*D = (3*1) + (-10*1) + (3*1) = 3 - 10 + 3 = -4
 */
public class Q4_MaximumSumValue {

    public int solve(int[] A, int B, int C, int D) {

        long[] dp1 = new long[A.length];
        long[] dp2 = new long[A.length];
        long[] dp3 = new long[A.length];

        //pass1 - walk left to right, keep max so far
        for(int i=0;i<dp1.length;i++){
            dp1[i] = A[i]*B;
        }
        for(int i=1;i<dp1.length; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i]);
        }
        //pass2 - walk left to right, keep max so far adding max from dp1
        for(int j=0;j<dp2.length;j++){
            dp2[j] = A[j]*C;
        }
        dp2[0] += dp1[0];
        for(int j=1;j<dp2.length;j++){
            dp2[j] = Math.max(dp2[j-1], dp2[j]+dp1[j]);
        }
        //pass3 - walk left to right, keep max so far adding max from dp2
        for(int k=0;k<dp3.length; k++){
            dp3[k] = A[k]*D;
        }
        dp3[0] += dp2[0];
        for(int k=1;k<dp3.length;k++){
            dp3[k] = Math.max(dp3[k-1], dp3[k]+dp2[k]);
        }

        return (int)dp3[A.length-1];
    }
}
