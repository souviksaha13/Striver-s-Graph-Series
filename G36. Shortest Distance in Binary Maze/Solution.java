/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1
 */

import java.util.*;

// User function Template for Java

class Solution {
    class Pair {
        Integer row;
        Integer col;
            
        public Pair(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }
    }

    int shortestPath(int[][] grid, int[] source, int[] destination) {

        // Your code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for(int i=0; i<dist.length; i++) {
            for(int j=0; j<dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        Queue<Pair> q = new LinkedList<>();
        
        dist[source[0]][source[1]] = 0;
        q.offer(new Pair(source[0], source[1]));
        
        while(!q.isEmpty()) {
            int row = q.peek().row;
            int col = q.peek().col;
            q.poll();
            
            for(int i=0; i<4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                
                if(nrow < n && nrow >= 0 && ncol < m && ncol >= 0 && grid[nrow][ncol] == 1) {
                    if(dist[nrow][ncol] > 1 + dist[row][col]) {
                        dist[nrow][ncol] = dist[row][col] + 1;
                        q.offer(new Pair(nrow, ncol));
                    }
                    
                    if(nrow == destination[0] && ncol == destination[1]) return dist[nrow][ncol];
                }
            }
        }
        
        
        int val = dist[destination[0]][destination[1]];
        return val == Integer.MAX_VALUE? -1 : val;
    }
}