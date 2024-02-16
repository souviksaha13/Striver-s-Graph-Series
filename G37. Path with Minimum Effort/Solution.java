/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/path-with-minimum-effort/1
 */

import java.util.*;

class Solution {
    class Pair {
        Integer dist;
        Integer row;
        Integer col;
        
        public Pair(Integer dist, Integer row, Integer col) {
            this.dist = dist;
            this.row = row;
            this.col = col;
        }
    }
    
    int MinimumEffort(int heights[][]) {
        int n = heights.length;
        int m = heights[0].length;
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        
        int[][] dist = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dist[0][0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.dist-b.dist);
        pq.offer(new Pair(0, 0, 0));
        
        while(!pq.isEmpty()) {
            int distance = pq.peek().dist;
            int row = pq.peek().row;
            int col = pq.peek().col;
            pq.poll();
            
            for(int i=0; i<4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                
                if(nrow<n && nrow >= 0 && ncol<m && ncol>=0) {
                    int effort = Math.abs(heights[row][col] - heights[nrow][ncol]);
                    effort = Math.max(distance, effort);
                    
                    if(effort < dist[nrow][ncol]) {
                        dist[nrow][ncol] = effort;
                        pq.offer(new Pair(effort, nrow, ncol));
                    }
                }
            }
        }
        
        return dist[n-1][m-1];
    }
}