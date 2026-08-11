package DynamicProgramming.DP1_OneDimentional;

/*
Problem Description
Given an integer array A of size N. Find the contiguous subarray within the given array (containing at least one number) which has the largest product.
Return an integer corresponding to the maximum product possible.
NOTE: Answer will fit in 32-bit integer value.


Problem Constraints
1 <= N <= 5 * 105
-100 <= A[i] <= 100


Input Format
First and only argument is an integer array A.


Output Format
Return an integer corresponding to the maximum product possible.


Example Input
Input 1:
 A = [4, 2, -5, 1]
Input 2:
 A = [-3, 0, -5, 0]


Example Output
Output 1:
 8
Output 2:
 0


Example Explanation
Explanation 1:
 We can choose the subarray [4, 2] such that the maximum product is 8.
Explanation 2:
 0 will be the maximum product possible.
 */


public class Q5_MaxProductSubarray {

    public int maxProduct(final int[] A) {
        int n = A.length;
        int maxEnd = A[0];
        int minEnd = A[0];
        int globalMax = A[0];

        for (int i = 1; i < n; i++) {
            int x = A[i];
            if (x < 0) {
                // swap: what was the min path can become the max path
                int temp = maxEnd;
                maxEnd = minEnd;
                minEnd = temp;
            }
            maxEnd = Math.max(x, maxEnd * x);
            minEnd = Math.min(x, minEnd * x);
            globalMax = Math.max(globalMax, maxEnd);
        }

        return globalMax;
    }
}





/*
The twist that makes this harder than "max sum subarray"

With sums, one running "best sum ending here" is enough — adding a negative number just makes things worse, never better. But with products, a negative number can flip a very bad (very negative) product into a great one. Two negatives make a positive.

So one tracker isn't enough. You need two runners at every position:

maxEnd — the best (largest) product of a subarray ending exactly here
minEnd — the worst (most negative) product of a subarray ending exactly here

Why keep the worst one around? Because if the next number is negative, that worst (most negative) product gets multiplied by a negative and becomes a great positive product — the villain becomes the hero. That's exactly what happens at index 2 in the diagram: minEnd was -40 at index... actually watch closely — minEnd becomes -40 right when we hit -5, and that -40 is sitting there ready in case a future negative number arrives to flip it back up.

The rule at each step

At every position x, three things could be the new best-ending-here product:

A[x] alone (start fresh here)
A[x] × maxEnd (extend the previous best)
A[x] × minEnd (extend the previous worst — this is the one that saves you when A[x] is negative)

Take the max of those three for maxEnd, and the min of those three for minEnd. Then update your global answer with maxEnd.

Handling zero

Zero acts as a hard reset — any product touching 0 becomes 0, which naturally breaks the chain. Since the problem guarantees at least a single number is chosen, and A[i] can itself be 0, your global max will correctly land on 0 if every subarray product is negative (like example 2, where all valid runs multiply out to something ≤ 0).
 */