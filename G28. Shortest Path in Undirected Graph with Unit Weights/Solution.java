/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
 */

import java.util.*;

class Solution {
    
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        // Code here
        // Forming the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        int[] shortestDist = new int[n];
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(src);
        int dist = 0;
        shortestDist[src] = dist;
        vis[src] = true;
        
        while(!q.isEmpty()) {
            dist++;
            int siz = q.size();
            
            for(int i=0; i<siz; i++) {
                
                int val = q.poll();
                for(Integer el : adj.get(val)) {
                    if(!vis[el]) {
                        vis[el] = true;
                        shortestDist[el] = dist;
                        q.offer(el);
                    }
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            if(i == src) continue;
            if(shortestDist[i] == 0) shortestDist[i] = -1;
        }
        
        return shortestDist;
    }
}