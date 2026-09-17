import java.util.*;

public class DFSTopo {
    /**
     * DFS helper function for topological sort
     * 
     * Time Complexity: O(V + E) - called for each vertex and edge once
     * Space Complexity: O(V) - recursion call stack depth in worst case
     */
    public void util(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for(int neighbor : adj.get(node)) {
            if(!vis[neighbor]) {
                util(neighbor, vis, adj, st);
            }
        }
        st.push(node);
    }
    
    /**
     * Topological Sort using DFS approach
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Building adjacency list: O(V + E)
     *   - DFS traversal: O(V + E) - each vertex and edge visited once
     *   - Popping from stack: O(V)
     * 
     * Space Complexity: O(V + E)
     *   - Adjacency list: O(V + E)
     *   - Visited array: O(V)
     *   - Stack: O(V)
     *   - Result list: O(V)
     *   - Recursion call stack: O(V) in worst case
     * 
     * @param V number of vertices
     * @param edges directed edges [from, to]
     * @return topologically sorted list of vertices
     */
    public List<Integer> topoSort(int V, int[][] edges) {
        List<Integer> ls = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            //  a->b
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b); 
        }
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                util(i, vis, adj, st);
            }
        }
        while(!st.isEmpty()) {
            ls.add(st.pop());
        }
        return ls;
    }
}
