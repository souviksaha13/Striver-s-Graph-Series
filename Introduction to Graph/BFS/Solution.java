/*
 * Problem Link ::
 *  https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 */

import java.util.*;

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> ls = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[V];
        vis[0] = true;
        q.offer(0);
        while(!q.isEmpty()){
            int temp = q.poll();
            ls.add(temp);
            for(int j : adj.get(temp)){
                if(!vis[j]){
                    vis[j] = true;
                    q.offer(j);
                }
            }
        }
        return ls;
    }
}