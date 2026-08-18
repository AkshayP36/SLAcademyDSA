package Graphs.Graph2_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Problem Description
Given a matrix of integers A of size N x M describing a maze. The maze consists of empty locations and walls.
1 represents a wall in a matrix and 0 represents an empty location in a wall.
There is a ball trapped in a maze. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall (maze boundary is also considered as a wall). When the ball stops, it could choose the next direction.
Given two array of integers of size B and C of size 2 denoting the starting and destination position of the ball.
Find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the starting position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.


Problem Constraints
2 <= N, M <= 100
0 <= A[i] <= 1
0 <= B[i][0], C[i][0] < N
0 <= B[i][1], C[i][1] < M


Input Format
The first argument given is the integer matrix A.
The second argument given is an array of integer B.
The third argument if an array of integer C.


Output Format
Return a single integer, the minimum distance required to reach destination


Example Input
Input 1:
A = [ [0, 0],
      [0, 0] ]
B = [0, 0]
C = [0, 1]
Input 2:
A = [ [0, 1],
      [1, 0] ]
B = [0, 0]
C = [1, 1]


Example Output
Output 1:
 1
Output 2:
 -1


Example Explanation
Explanation 1:
 Go directly from start to destination in distance 1.
Explanation 2:
 It is impossible to reach the destination from (0, 0) to (1, 1) as there are walls at (1, 0) and (0, 1)
 */
public class Q4_ShortestDistanceinAMaze {

    public class Pair{
        private int x;
        private int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public int solve(int[][] maze, int[] source, int[] destination) {
        int source_x = source[0];
        int source_y = source[1];
        int destination_x = destination[0];
        int destination_y = destination[1];
        int[][] directions = {{-1,0},{0,-1},{1,0},{0,1}};//TLDR

        int[][] storage = new int[maze.length][maze[0].length];
        for(int i=0;i<storage.length;i++){
            for(int j=0;j<storage[0].length;j++){
                storage[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(source_x, source_y));
        storage[source_x][source_y] = 0;

        while(queue.size()>0){
            Pair temp = queue.remove();

            for(int i=0;i<4;i++){ //to travel entire direction array
                int current_x = temp.x + directions[i][0];
                int current_y = temp.y + directions[i][1];

                int count=0; //to add count how many cells we travel
                while(current_x>=0 && current_x<maze.length && current_y>=0 && current_y<maze[0].length && maze[current_x][current_y]==0){
                    count++;
                    current_x += directions[i][0];
                    current_y += directions[i][1];
                }

                //adjusting after bounce back case
                current_x -= directions[i][0];
                current_y -= directions[i][1];

                if(storage[temp.x][temp.y] + count < storage[current_x][current_y]){
                    storage[current_x][current_y] = storage[temp.x][temp.y]+ count;
                    queue.add(new Pair(current_x, current_y));
                }
            }
        }



        if(storage[destination_x][destination_y] == Integer.MAX_VALUE) return -1;
        else
            return storage[destination_x][destination_y];
    }
}
