/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 */

import java.util.*;

class Solution {
    
    boolean topoBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDeg = new int[V];
        
        for(ArrayList<Integer> arr : adj) {
            for(Integer el : arr) {
                inDeg[el]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        
        for(int i=0; i<V; i++) {
            if(inDeg[i] == 0) q.offer(i);
        }
        
        while(!q.isEmpty()) {
            int val = q.poll();
            cnt++;
            
            for(Integer el : adj.get(val)) {
                inDeg[el]--;
                if(inDeg[el] == 0) q.offer(el);
            }
        }
        // if cnt == V, there is no cycle, otherwise there exists a cycle
        return cnt != V;
    }
    
    boolean dfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] pathvis, boolean[] vis) {
        pathvis[src] = true;
        vis[src] = true;
        
        for(Integer el : adj.get(src)) {
            // if the element is never visited earlier
            if(!vis[el]) {
                boolean ans = dfs(el, adj, pathvis, vis);
                if(ans) return true;
            }
            // else, we need to check if the element is also path- visited
            else if(pathvis[el]) return true;
        }
        pathvis[src] = false;
        return false;
    }
    
    // Function to detect cycle in an undirected graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        // // DFS solution
        // boolean[] pathvis = new boolean[V];
        // boolean[] vis = new boolean[V];
        
        // for(int i=0; i<V; i++) {
        //     if(!vis[i]) {
        //         boolean ans = dfs(i, adj, pathvis, vis);
        //         if(ans) return true;
        //     }
        // }
        // return false;
        
        // BFS Solution
        return topoBFS(V, adj);
    }
}