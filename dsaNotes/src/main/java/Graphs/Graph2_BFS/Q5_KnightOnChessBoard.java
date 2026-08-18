package Graphs.Graph2_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Problem Description
Given any source point, (C, D) and destination point, (E, F) on a chess board of size A x B, we need to find whether Knight can move to the destination or not.

The above figure details the movements for a knight ( 8 possibilities ).
If yes, then what would be the minimum number of steps for the knight to move to the said point. If knight can not move from the source point to the destination point, then return -1.
NOTE: A knight cannot go out of the board.


Problem Constraints
1 <= A, B <= 500


Input Format
The first argument of input contains an integer A.
The second argument of input contains an integer B.
The third argument of input contains an integer C.
The fourth argument of input contains an integer D.
The fifth argument of input contains an integer E.
The sixth argument of input contains an integer F.


Output Format
If it is possible to reach the destination point, return the minimum number of moves.
Else return -1.


Example Input
Input 1:
 A = 8
 B = 8
 C = 1
 D = 1
 E = 8
 F = 8
Input 2:
 A = 2
 B = 4
 C = 2
 D = 1
 E = 4
 F = 4


Example Output
Output 1:
 6
Output 2:
 -1


Example Explanation
Explanation 1:
 The size of the chessboard is 8x8, the knight is initially at (1, 1) and the knight wants to reach position (8, 8).
 The minimum number of moves required for this is 6.
Explanation 2:
 It is not possible to move knight to position (4, 4) from (2, 1)
 */
public class Q5_KnightOnChessBoard {

    public class Pair{
        int x;
        int y;
        int move;
        Pair(int x, int y, int move){
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    public int knight(int A, int B, int source_x, int source_y, int dest_x, int dest_y) {

        // Convert from 1-indexed (as given in problem) to 0-indexed
        source_x = source_x - 1;
        source_y = source_y - 1;
        dest_x = dest_x - 1;
        dest_y = dest_y - 1;

        if (source_x < 0 || source_x >= A || source_y < 0 || source_y >= B ||
                dest_x < 0 || dest_x >= A || dest_y < 0 || dest_y >= B) {
            return -1;
        }

        // Same source and destination
        if (source_x == dest_x && source_y == dest_y) return 0;

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(source_x, source_y, 0));

        int[][] directions = {{-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}};
        boolean[][] visited = new boolean[A][B];
        visited[source_x][source_y] = true;


        while(queue.size()>0){
            Pair temp = queue.remove();

            for(int i=0;i<directions.length;i++){
                int current_x = temp.x + directions[i][0];
                int current_y = temp.y + directions[i][1];

                if(current_x==dest_x && current_y==dest_y) return temp.move+1;

                if(current_x>=0 && current_x<A && current_y>=0 && current_y<B && visited[current_x][current_y]==false){
                    visited[current_x][current_y] = true;
                    queue.add(new Pair(current_x, current_y, temp.move+1));
                }


            }
        }

        return -1;
    }
}
