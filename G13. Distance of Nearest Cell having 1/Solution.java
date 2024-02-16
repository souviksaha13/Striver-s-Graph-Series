/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1
 */

/*
    Brute force -> for each cell, do a BFS traversal until a cell containing 1 is found
    Optimized Approach ->
        We will insert all the 1's in a queue and initialise a dist with 0
        for each iteration-> 
            poll out all the current elements in the queue
            put their value as dist in answer
            put all their unvisited neighbours in the queue. 
            Increment the dist by 1
        Continue the iteration until all the cells are visited and the queue is empty
*/

import java.util.*;

class Solution
{
    class Pair {
        Integer row; 
        Integer column;
        
        public Pair(Integer row, Integer column) {
            this.row = row;
            this.column = column;
        }
    }
    
    
    //Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid)
    {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] ans = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        Queue<Pair> q = new LinkedList<>();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 1) {
                    q.offer(new Pair(i, j));
                    vis[i][j] = true;
                }
            }
        }
        int dist = 0;
        
        while(!q.isEmpty()) {
            int s = q.size();
            
            for(int i=0; i<s; i++) {
                int row = q.peek().row;
                int col = q.peek().column;
                q.poll();
                ans[row][col] = dist;
                
                for(int k=0; k<4; k++) {
                    int nrow = row + dx[k];
                    int ncol = col + dy[k];
                    
                    if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !vis[nrow][ncol]) {
                        vis[nrow][ncol] = true;
                        q.offer(new Pair(nrow, ncol));
                    }
                }
            }
            dist++;
        }
        
        return ans;
    }
}