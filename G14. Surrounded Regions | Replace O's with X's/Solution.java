/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/replace-os-with-xs0052/1
 */

import java.util.*;

class Solution{
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    
    // DFS
    public void dfs(char[][] a, boolean[][] vis, int row, int col, int n, int m) {
        vis[row][col] = true;
        
        for(int i=0; i<4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && a[nrow][ncol] == 'O' && !vis[nrow][ncol])
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
    
    public void bfs(char[][] a, boolean[][] vis, int row, int col, int n, int m) {
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
                
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && a[nrow][ncol] == 'O' && !vis[nrow][ncol]) {
                    q.offer(new Pair(nrow, ncol));
                    vis[nrow][ncol] = true;
                }
            }
        }
    }
    
    
    char[][] fill(int n, int m, char a[][])
    {
        // code here
        char[][] ans = new char[n][m];
        boolean vis[][] = new boolean[n][m];
        
        // first & last row
        for(int i=0; i<m; i++) {
            if(a[0][i] == 'O' && !vis[0][i]) {
                // bfs(a, vis, 0, i, n, m);
                dfs(a, vis, 0, i, n, m);
            }
            if(a[n-1][i] == 'O' && !vis[n-1][i]) {
                // bfs(a, vis, n-1, i, n, m);
                dfs(a, vis, n-1, i, n, m);
            }
        }
        
        // first & last column
        for(int i=0; i<n; i++) {
            if(a[i][0] == 'O' && !vis[i][0]) {
                // bfs(a, vis, i, 0, n, m);
                dfs(a, vis, i, 0, n, m);
            }
            if(a[i][m-1] == 'O' && !vis[i][m-1]) {
                // bfs(a, vis, i, m-1, n, m);
                dfs(a, vis, i, m-1, n, m);
            }
        }
        
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(vis[i][j]) ans[i][j] = 'O';
                else ans[i][j] = 'X';
            }
        }
        
        return ans;
    }
}