/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 */

import java.util.*;

class Solution {
    class Pair {
        Integer node;
        Integer parent;
        
        public Pair(Integer node, Integer parent) {
            this.node = node;
            this.parent = parent;
        }
    }
    
    
    /*
        In BFS, we need to traverse through from the starting point 
        In each traversal, we need to remember the node from where it came
        If at any point of time, we come across any node which is already visited, the
            it is a cycle
            
        In pair, the first element will be  the node, & second element is the parent
    */
    public boolean detectCycleBfs(ArrayList<ArrayList<Integer>> adj, int src, boolean[] vis) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(src, -1));
        vis[src] = true;
        
        while(!q.isEmpty()) {
            Integer node = q.peek().node;
            Integer parent = q.peek().parent;
            q.poll();
            
            for(Integer el : adj.get(node)) {
                if(el.equals(parent)) continue;
                if(vis[el]) 
                    return true;
                vis[el] = true;
                q.offer(new Pair(el, node));
            }
        }
        return false;
    }
    
    /*
    In DFS, we need to travel through the depth of the graph
    While traversing , we should remember the parent of the node
    If at any point of time, we find that : if a element is not the parent and is also visited
        then there is a cycle
    */
    public boolean detectCycleDfs(ArrayList<ArrayList<Integer>> adj, int node, int parent, boolean[] vis) {
        vis[node] = true;
        
        for(Integer el : adj.get(node)) {
            if(el.equals(parent)) continue;
            if(vis[el]) return true;
            if(detectCycleDfs(adj, el, node, vis)) return true;
        }
        
        return false;
    }
    
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                if(detectCycleBfs(adj, i, vis)) return true;
                // if(detectCycleDfs(adj, i, -1, vis)) return true;
            }
        }
        
        return false;
    }
}