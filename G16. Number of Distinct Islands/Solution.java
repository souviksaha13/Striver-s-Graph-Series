/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1
 */

/*
    Find all the islands by traversing
    Store all the islands in a HashSet, which will check the shape of the islands
    HashSet will automatically return the unique islands
    We need to subtract the starting pt from the island coordinates to get the shape
*/

// User function Template for Java

import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    class Pair {
        Integer row;
        Integer col;
        public Pair(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }
    }
    
    void bfs(int[][] grid, int row, int col, boolean vis[][], Set<ArrayList<Integer>> hs) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        ArrayList<Integer> ls = new ArrayList<>();
        
        q.offer(new Pair(row, col));
        vis[row][col] = true;
        ls.add(0);
        ls.add(0);
        
        int sr = row, sc = col;
        
        while(!q.isEmpty()) {
            row = q.peek().row;
            col = q.peek().col;
            q.poll();
            for(int i=0; i<4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                
                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !vis[nrow][ncol] && grid[nrow][ncol] == 1) {
                    vis[nrow][ncol] = true;
                    q.offer(new Pair(nrow, ncol));
                    ls.add(nrow-sr);
                    ls.add(ncol-sc);
                }
            }
        }
        // System.out.println(ls.get(1).first);
        hs.add(new ArrayList<>(ls));
    }
    
    void dfs(int[][] grid, int row, int col, boolean[][] vis, ArrayList<Integer> ls, int sr, int sc) {
        int n = grid.length;
        int m = grid[0].length;
        vis[row][col] = true;
        ls.add(row-sr);
        ls.add(col-sc);
        
        for(int i=0; i<4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && grid[nrow][ncol] == 1 && !vis[nrow][ncol])
                dfs(grid, nrow, ncol, vis, ls, sr, sc);
        }
        return;
    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        
        Set<ArrayList<Integer>> hs = new HashSet<>();
        // we can't store the pair in arraylist bcz it stores the references
        //      so, unique elements can't be found
        // So, we are using integer -> elements of pair will be stored as integer
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    bfs(grid, i, j, vis, hs);
                    
                    // in dfs, we need to provide the list in the function itself
                    //  otherwise, it will insert the partially filled list in every iteration in the set
                    
                    // ArrayList<Integer> ls = new ArrayList<>();
                    // dfs(grid, i, j, vis, ls, i, j);
                    // hs.add(ls);
                }
            }
        }
        return hs.size();
    }
}