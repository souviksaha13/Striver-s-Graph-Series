/*
 * Problem Link ::
 *      Problem not detected in GFG anymore as of now
 */

import java.util.*;

class Solution {
    
    class Pair {
        Integer node;
        Integer distance;
            
        public Pair(Integer distance, Integer node) {
            this.node = node;
            this.distance = distance;
        }
    }
    
    // Function using priority queue
    List<Integer> priorityqueue(ArrayList<ArrayList<ArrayList<Integer>>> adj, int V, int[] dist, int[] parent) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> (x.distance == y.distance)? (x.node - y.node) : (x.distance - y.distance));
        
        dist[0] = 0;
        pq.offer(new Pair(0, 0));
        
        while(!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();
            
            for(ArrayList<Integer> el : adj.get(node)) {
                int nextnode = el.get(0);
                int nextDist = el.get(1);
                
                if((dis + nextDist) < dist[nextnode]) {
                    dist[nextnode] = dis + nextDist;
                    parent[nextnode] = node;
                    pq.offer(new Pair(dis + nextDist, nextnode));
                }
            }
        }

        List<Integer> ls = new ArrayList<>();
        if(parent[V-1] == V-1) {
            ls.add(-1);
            return ls;
        }
        ls.add(V-1);
        while(parent[ls.get(0)] != ls.get(0)) ls.add(0, parent[ls.get(0)]);

        return ls;
    }


    public List<Integer> shortestPath(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj)
    {
        // Write your code here
        
        int[] dist = new int[V];
        int[] parent = new int[V];

        for(int i=0; i<V; i++) {
            parent[i] = i;
        }
        Arrays.fill(dist, Integer.MAX_VALUE);

        return priorityqueue(adj, V, dist, parent);
    }
}
