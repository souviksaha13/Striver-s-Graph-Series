/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1
 */

import java.util.*;

 class Solution {
    class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        } 
    }
    
    public void bfs(char[][] grid, int row, int col, boolean[][] vis) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;
        
        while(!q.isEmpty()) {
            row = q.peek().row;
            col = q.peek().col;
            q.poll();
            
            for(int i=-1; i<2; i++) {
                for(int j=-1; j<2; j++) {
                    int nrow = row + i;
                    int ncol = col + j;
                    
                    if(nrow>=0 && ncol>=0 && nrow<n && ncol<m && grid[nrow][ncol] == '1' && !vis[nrow][ncol]) {
                        vis[nrow][ncol] = true;
                        q.offer(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }
    
    // Function to find the number of islands.
    public int numIslands(char[][] grid) {
        // Code here
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        boolean[][] vis = new boolean[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == '1' && !vis[i][j]) {
                    ans++;
                    bfs(grid, i, j, vis);
                }
            }
        }
        
        return ans;
    }
}