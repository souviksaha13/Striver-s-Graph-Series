/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 */

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    
    public void dfs(int[][] image, int sr, int sc, int iColor, int newColor, int n, int m) {
        image[sr][sc] = newColor;
        
        for(int i=0; i<4; i++) {
            int row = sr + dx[i];
            int col = sc + dy[i];
            
            if(row<n && row>=0 && col<m && col>=0 && image[row][col] == iColor) {
                dfs(image, row, col, iColor, newColor, n, m);
            }
        }
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        // Code here 
        int n = image.length;
        int m = image[0].length;
        
        int iColor = image[sr][sc];
        if(iColor == newColor) return image;
        int[][] ans = image.clone();
        
        dfs(ans, sr, sc, iColor, newColor, n, m);
        return ans;
    }
}