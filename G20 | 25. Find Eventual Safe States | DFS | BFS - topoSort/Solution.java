/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/eventual-safe-states/1
 */

import java.util.*;

class Solution {
    
    // DFS to determine which elements are part of cycle and which elements are not
    // Elements which are not a part of any cycle are inserted inside the list before marking pathvis as false
    
    boolean dfs(List<List<Integer>> adj, int src, boolean[] vis, boolean[] pathvis, List<Integer> ans) {
        vis[src] = true;
        pathvis[src] = true;
        
        for(Integer el : adj.get(src)) {
            // if the element is never visited earlier
            if(!vis[el]) {
                boolean check = dfs(adj, el, vis, pathvis, ans);
                if(check) return true;
            }
            // else if the element is also path visited
            else if(pathvis[el]) return true;
        }
        
        ans.add(src);
        pathvis[src] = false;
        
        return false;
    }
    
    
    // BFS Method
    // Here, we reverse the edges, then use toposort
    // This is bcz, all the terminal nodes are safe nodes on the first place.
    // And then, after removing those edges, the next eventual terminal nodes are safe nodes and so on
    
    void bfs(int V, List<List<Integer>> adj, List<Integer> ans) {
        List<List<Integer>> reverse = new ArrayList<>();
        int[] indeg = new int[V];
        
        for(int i=0; i<V; i++) {
            reverse.add(new ArrayList<>());
        }
        
        for(int i=0; i<V; i++) {
            for(Integer el : adj.get(i)) {
                reverse.get(el).add(i);
                indeg[i]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<V; i++) {
            if(indeg[i] == 0) q.offer(i);
        }
        
        while(!q.isEmpty()) {
            int val = q.poll();
            ans.add(val);
            
            for(Integer el : reverse.get(val)) {
                indeg[el]--;
                if(indeg[el] == 0) q.offer(el);
            }
        }
    }

    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {

        // Your code here
        List<Integer> ans = new ArrayList<>();
        
        // // DFS solution
        
        // boolean[] vis = new boolean[V];
        // boolean[] pathvis = new boolean[V];
        // for(int i=0; i<V; i++) {
        //     if(!vis[i]) {
        //         dfs(adj, i, vis, pathvis, ans);
        //     }
        // }
        
        
        // BFS solution
        
        bfs(V, adj, ans);
        
        Collections.sort(ans);
        
        return ans;
    }
}