/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
 */

import java.util.*;

class Solution {
    
    class Pair {
        Integer node;
        Integer weight;
        
        public Pair(Integer node, Integer weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    
    void calculateDist(List<List<Pair>> adj, int[] dist, Stack<Integer> st) {
        dist[st.peek()] = 0;
        while(!st.isEmpty()) {
            int val = st.pop();
            
            for(Pair pair : adj.get(val)) {
                dist[pair.node] = Math.min(dist[pair.node], dist[val] + pair.weight);
            }
        }
        
        // mark all unreachable nodes with distance -1
        for(int i=0; i<dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
    }
    
    void topoOrder(List<List<Pair>> adj, int src, Stack<Integer> st, boolean[] vis) {
        vis[src] = true;
        
        for(Pair pair : adj.get(src)) {
            if(!vis[pair.node]) topoOrder(adj, pair.node, st, vis);
        }
        
        st.push(src);
    }

	public int[] shortestPath(int N,int M, int[][] edges) {
		//Code here
		
		List<List<Pair>> adj = new ArrayList<>();
		
		// adding the nodes in the adjacency list
		for(int i=0; i<N; i++) {
		    adj.add(new ArrayList<>());
		}
		
		// create the adjacency list of the edges along with their weights
		for(int[] edge : edges) {
		    adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
		}
		
		int[] dist = new int[N];
		// initlialize the dist between the nodes as infinity
		for(int i=0; i<N; i++) {
		    dist[i] = Integer.MAX_VALUE;
		}
		
		// Creating the queue to store the topological order of the nodes using indegree
		// topological order is reqd so that we can calculate the distance to the node from the source, by considering all the paths
		boolean[] vis = new boolean[N];
		Stack<Integer> st = new Stack<>();
		topoOrder(adj, 0, st, vis);
		
		// Now, we have the topological order of the nodes, so it is time to calculate the distance
		calculateDist(adj, dist, st);
		
		return dist;
	}
}