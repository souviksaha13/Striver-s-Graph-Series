/*
 * Problem Link ::
 *  https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 */

import java.util.*;

class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> ls = new ArrayList<>();
        boolean[] vis = new boolean[V];
        dfs(adj, ls, vis, 0);
        
        return ls;
    }
    
    void dfs(ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ls, boolean[] vis, int src) {
        ls.add(src);
        vis[src] = true;
        for(Integer el : adj.get(src)) {
            if(!vis[el]) dfs(adj, ls, vis, el);
        }
    }
}