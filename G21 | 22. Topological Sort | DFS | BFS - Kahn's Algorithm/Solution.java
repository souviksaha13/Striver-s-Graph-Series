/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/topological-sort/1
 */

import java.util.*;

class Solution
{
    static void dfs(ArrayList<ArrayList<Integer>> adj, int src, boolean[] vis, Stack<Integer> st) {
        vis[src] = true;
        
        for(Integer el : adj.get(src)) {
            if(!vis[el]) dfs(adj, el, vis, st);
        }
        
        st.push(src);
    }
    
    
    static void calculateIndegree(ArrayList<ArrayList<Integer>> adj, int[] indegree, int V) {
        for(int i=0; i<V; i++) {
            for(Integer el :  adj.get(i)) {
                indegree[el]++;
            }
        }
    }
    
    static void kahnsBFS(ArrayList<ArrayList<Integer>> adj, int[] indegree, int[] ans) {
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<indegree.length; i++) {
            if(indegree[i]==0) q.offer(i);
        }
        
        int i=0;
        while(!q.isEmpty()) {
            int val = q.poll();
            
            for(Integer el : adj.get(val)) {
                indegree[el]--;
                if(indegree[el] == 0) q.offer(el);
            }
            
            ans[i] = val;
            i++;
        }
    }
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        int[] ans = new int[V];
        
        // // DFS Solution
        // Stack<Integer> st = new Stack<>();
        // boolean[] vis = new boolean[V];
        
        // for(int i=0; i<V; i++) {
        //     if(!vis[i]) dfs(adj, i, vis, st);
        // }
        
        // for(int i=0; i<V; i++) {
        //     ans[i] = st.pop();
        // }
        
        // // BFS Solution -> Kahn's Algorithm
        // int[] indegree = new int[V];
        // calculateIndegree(adj, indegree, V);
        // kahnsBFS(adj, indegree, ans);
        
        return ans;
    }
}
