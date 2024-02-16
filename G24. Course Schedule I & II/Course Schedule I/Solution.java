/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/prerequisite-tasks/1
 */

import java.util.*;

class Solution {
    public boolean isPossible(int N,int P, int[][] prerequisites)
    {
        // Your Code goes here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] indeg = new int[N];
        
        for(int i=0; i<N; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0; i<P; i++) {
            indeg[prerequisites[i][0]]++;
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        
        for(int i=0; i<N; i++) {
            if(indeg[i] == 0) q.offer(i);
        }
        
        while(!q.isEmpty()) {
            int val = q.poll();
            cnt++;
            
            for(Integer el : adj.get(val)) {
                indeg[el]--;
                if(indeg[el] == 0) q.offer(el);
            }
        }
        
        return cnt == N;
    }
    
}
