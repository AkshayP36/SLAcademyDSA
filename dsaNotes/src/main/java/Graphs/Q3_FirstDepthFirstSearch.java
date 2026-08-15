package Graphs;

/*
Problem Description
You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
Given 2 towns find whether you can reach the first town from the second without repeating any edge.
B C : query to find whether B is reachable from C.
Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i for every 1 <= i < N.
NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.


Problem Constraints
1 <= N <= 100000


Input Format
First argument is vector A
Second argument is integer B
Third argument is integer C


Output Format
Return 1 if reachable, 0 otherwise.


Example Input
Input 1:
 A = [1, 1, 2]
 B = 1
 C = 2
Input 2:
 A = [1, 1, 2]
 B = 2
 C = 1


Example Output
Output 1:
 0
Output 2:
 1


Example Explanation
Explanation 1:
 Tree is 1--> 2--> 3 and hence 1 is not reachable from 2.
Explanation 2:
 Tree is 1--> 2--> 3 and hence 2 is reachable from 1.
 */


import java.util.ArrayList;

public class Q3_FirstDepthFirstSearch {

    private ArrayList<Integer>[] graph;
    private boolean[] visited;

    private int dfs(ArrayList<Integer>[] graph, boolean[] visited, int source, int destination){
        if(source == destination) {
            return 1;
        }
        visited[source] = true;
        for(int nbr : graph[source]){
            if(visited[nbr]==false){
                if(dfs(graph, visited, nbr, destination) == 1) return 1;
            }
        }

        return 0;
    }

    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int solve(int[] A, final int B, final int C) {
        int n = A.length;
        // to find path from C to B
        graph = new ArrayList[n+1];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            int u = A[i];
            int v = i+1;
            graph[u].add(v);
        }
        visited = new boolean[n+1];
        return dfs(graph, visited, C, B);

    }
}
