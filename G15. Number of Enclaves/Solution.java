/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/number-of-enclaves/1
 */

import java.util.*;

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    
    // DFS
    public void dfs(int[][] a, boolean[][] vis, int row, int col, int n, int m) {
        vis[row][col] = true;
        
        for(int i=0; i<4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && a[nrow][ncol] == 1 && !vis[nrow][ncol])
                dfs(a, vis, nrow, ncol, n, m);
        }
    }
    
    
    
    // BFS
    class Pair {
        Integer row;
        Integer col;
        public Pair(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public void bfs(int[][] a, boolean[][] vis, int row, int col, int n, int m) {
        Queue<Pair> q = new LinkedList<>();
        vis[row][col] = true;
        q.offer(new Pair(row, col));
        
        while(!q.isEmpty()) {
            row = q.peek().row;
            col = q.peek().col;
            q.poll();
            
            for(int i=0; i<4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && a[nrow][ncol] == 1 && !vis[nrow][ncol]) {
                    q.offer(new Pair(nrow, ncol));
                    vis[nrow][ncol] = true;
                }
            }
        }
    }

    int numberOfEnclaves(int[][] grid) {

        // Your code here
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        boolean vis[][] = new boolean[n][m];
        
        // first & last row
        for(int i=0; i<m; i++) {
            if(grid[0][i] == 1 && !vis[0][i]) {
                bfs(grid, vis, 0, i, n, m);
                // dfs(grid, vis, 0, i, n, m);
            }
            if(grid[n-1][i] == 1 && !vis[n-1][i]) {
                bfs(grid, vis, n-1, i, n, m);
                // dfs(grid, vis, n-1, i, n, m);
            }
        }
        
        // first & last column
        for(int i=0; i<n; i++) {
            if(grid[i][0] == 1 && !vis[i][0]) {
                bfs(grid, vis, i, 0, n, m);
                // dfs(grid, vis, i, 0, n, m);
            }
            if(grid[i][m-1] == 1 && !vis[i][m-1]) {
                bfs(grid, vis, i, m-1, n, m);
                // dfs(grid, vis, i, m-1, n, m);
            }
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) ans++;
            }
        }
        
        return ans;
    }
}