/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/course-schedule/1
 */

import java.util.*;

class Solution
{
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) 
    {
        // add your code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] indeg = new int[n];
        
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0; i<m; i++) {
            indeg[prerequisites.get(i).get(0)]++;
            adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
        }
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        int[] ans = new int[n];
        
        for(int i=0; i<n; i++) {
            if(indeg[i] == 0) q.offer(i);
        }
        
        while(!q.isEmpty()) {
            int val = q.poll();
            ans[cnt] = val;
            cnt++;
            
            for(Integer el : adj.get(val)) {
                indeg[el]--;
                if(indeg[el] == 0) q.offer(el);
            }
        }
        
        if(cnt != n) return new int[0];
        
        return ans;
    }
}