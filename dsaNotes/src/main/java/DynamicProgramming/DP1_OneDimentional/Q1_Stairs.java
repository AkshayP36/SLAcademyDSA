package DynamicProgramming.DP1_OneDimentional;

/*
Problem Description
You are climbing a staircase and it takes A steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Return the number of distinct ways modulo 1000000007


Problem Constraints
1 <= A <= 105


Input Format
The first and the only argument contains an integer A, the number of steps.


Output Format
Return an integer, representing the number of ways to reach the top.


Example Input
Input 1:
 A = 2
Input 2:
 A = 3


Example Output
Output 1:
 2
Output 2:
 3


Example Explanation
Explanation 1:
 Distinct ways to reach top: [1, 1], [2].
Explanation 2:
 Distinct ways to reach top: [1 1 1], [1 2], [2 1].
 */


public class Q1_Stairs {

    private static int modulo = 1000000007;

    private int waystoClimb(int[] storage, int steps){
        if(steps ==0 || steps ==1 || steps ==2){
            return steps;
        }

        if(storage[steps] !=-1){
            return storage[steps];
        }

        int totalWaysTillNow = (waystoClimb(storage, steps -1) + waystoClimb(storage, steps -2))%modulo;

        storage[steps] = totalWaysTillNow;
        return totalWaysTillNow;
    }
    public int climbStairs(int A) {
        //creating storage
        int[] storage = new int[A+1];
        for(int i=0;i<storage.length; i++){
            storage[i] = -1;
        }

        return waystoClimb(storage, A)%modulo;

    }

}
