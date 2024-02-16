/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/bipartite-graph/1
 */

import java.util.*;

class Solution
{
    boolean bfs(ArrayList<ArrayList<Integer>> adj, int src, int col, int[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        vis[src] = col;
        
        while(!q.isEmpty()) {
            int val = q.poll();
            
            for(Integer el : adj.get(val)) {
                if(vis[el] == -1) {
                    q.offer(el);
                    if(vis[val] == 0) vis[el] = 1;
                    else vis[el] = 0;
                }
                else if(vis[val] == vis[el]) return false;
            }
        }
        
        return true;
    }
    
    boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, int col, int[] vis) {
        vis[src] = col;
        
        for(Integer el : adj.get(src)) {
            if(vis[el] == -1) {
                boolean ans = dfs(adj, el, (col+1)%2, vis);
                if(ans == false) return false;
            }
            else if(vis[src] == vis[el]) return false;
        }
        
        return true;
    }
    
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // Code here
        int[] vis = new int[V];
        boolean ans = true;
        
        for(int i=0; i<V; i++) {
            vis[i] = -1;
        }
        
        for(int i=0; i<V; i++) {
            if(vis[i] == -1) {
                ans = bfs(adj, i, 0, vis);
                // ans = dfs(adj, i, 0, vis);
            }
            if(!ans) return false;
        }
        
        return true;
    }
}