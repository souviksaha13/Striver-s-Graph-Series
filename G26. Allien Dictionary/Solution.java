/*
 * Problem Link ::
 *      https://www.geeksforgeeks.org/problems/alien-dictionary/1
 */

import java.util.*;

class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        // Write your code here
        int[] indeg = new int[K];
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i=0; i<K; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0; i<N-1; i++) {
            String s1 = dict[i];
            String s2 = dict[i+1];
            
            int m=0;
            
            while(m < s1.length() && m < s2.length()) {
                if(s1.charAt(m) == s2.charAt(m)) m++;
                else {
                    adj.get(s1.charAt(m)-'a').add(s2.charAt(m)-'a');
                    indeg[s2.charAt(m)-'a']++;
                    break;
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<K; i++) {
            if(indeg[i] == 0) q.offer(i);
        }
        
        String ans = "";
        
        while(!q.isEmpty()) {
            int val = q.poll();
            
            for(Integer el : adj.get(val)) {
                indeg[el]--;
                if(indeg[el] == 0) q.offer(el);
            }
            
            ans = ans + (char)(val + 97);
        }
        // System.out.println(ans);
        return ans;
    }
}