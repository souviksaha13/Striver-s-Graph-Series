/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/number-of-provinces/1
 */

import java.util.*;

class Solution {
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        // code here
        int ans = 0;
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<V; i++) {
            if(vis[i]) continue;
            ans++;
            q.add(i);
            vis[i] = true;
            while(!q.isEmpty()) {
                Integer n = q.poll();
                // vis[n] = true;
                
                for(int j=0; j<V; j++) {
                    if(adj.get(n).get(j)==1 && !vis[j]) {
                        q.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        
        return ans;
    }
};