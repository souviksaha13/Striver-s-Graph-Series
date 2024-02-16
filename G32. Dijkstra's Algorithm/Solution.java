/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
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
    void priorityqueue(ArrayList<ArrayList<ArrayList<Integer>>> adj, int S, int[] dist) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> (x.distance == y.distance)? (x.node - y.node) : (x.distance - y.distance));
        
        dist[S] = 0;
        pq.offer(new Pair(0, S));
        
        while(!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();
            
            for(ArrayList<Integer> el : adj.get(node)) {
                int nextnode = el.get(0);
                int nextDist = el.get(1);
                
                if((dis + nextDist) < dist[nextnode]) {
                    dist[nextnode] = dis + nextDist;
                    pq.offer(new Pair(dis + nextDist, nextnode));
                }
            }
        }
    }
    
    // Using TreeSet or hashSet in java would not be a good decision as it will take much more time complexity and the code also becomes much more tidious
    // It is not recommended in java, but in C++, we can use Set
    // Using set will reduce some iterations in the future multiple traversals of a node.
    //      But erasing a possible iteration from the set will take logn time
    //      So, it is very subjective to the nature of the graph whether using priorityQueue or Set is a better option
    //  But in case of java, it is recommended to use only PriorityQueue
    
    
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    public int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        // Write your code here
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        priorityqueue(adj, S, dist);
        
        return dist;
    }
}
