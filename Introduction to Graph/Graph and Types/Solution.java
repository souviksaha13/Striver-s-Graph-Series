/*  Approach
 * To determine the total number of edges possible in a graph with 'N' vertices, we can use the combination formula
 * Each edge requires two vertices so we can choose any two vertices from the 'N' vertices
 *      E = (N * (N-1)) / 2.
 * For each edge, there is two options, either it can be present in the graph or it can't be present
 * So, total number of graphs that can be formed = 2^E
 */

/*
 * TC -> O(1)
 * SC -> O(1)
 */


public class Solution {
    public static int countingGraphs(int N) {
        // Write your code here.
        if(N==1 || N==2) return N;

        return (int)Math.pow(2, (N*(N-1))/2);
    }
}