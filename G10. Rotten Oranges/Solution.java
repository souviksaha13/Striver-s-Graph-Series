/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/rotten-oranges2536/1
 */

import java.util.*;

class Solution
{
    class Pair {
        Integer first;
        Integer second;
        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }
    }
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        Queue<Pair> q = new LinkedList<>();
        
        // get all rotten oranges at unit time = 0
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 2) {
                    q.offer(new Pair(i, j));
                    vis[i][j] = true;
                }
            }
        }
        
        int ans = -1;
        
        while(!q.isEmpty()) {
            int el = q.size();
            ans++;
            for(int i=0; i<el; i++) {
                int row = q.peek().first;
                int col = q.peek().second;
                // grid[row][col] = 2;
                q.poll();
            
                for(int j=0; j<4; j++) {
                    int nrow = row + dx[j];
                    int ncol = col + dy[j];
                    
                    if(nrow<n && nrow>=0 && ncol<m && ncol>=0 && !vis[nrow][ncol] && grid[nrow][ncol] == 1) {
                        q.offer(new Pair(nrow, ncol));
                        vis[nrow][ncol] = true;
                    }
                }
            }
        }
        
        // check if any fresh oranges are present
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) return -1;
            }
        }
        // handles the case where there is no rotten or fresh oranges
        if(ans == -1) return 0;
        return ans;
    }
}