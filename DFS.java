import java.util.*;

public class DFS {
    /**
     * DFS helper function
     * 
     * Time Complexity: O(V + E) - called for each vertex and edge once
     * Space Complexity: O(V) - recursion call stack depth in worst case
     */
    public void dfs(int i, boolean[] vis, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ls) {
        vis[i] = true;
        ls.add(i);
        for(int neighbor : adj.get(i)) {
            if(!vis[neighbor]) {
                dfs(neighbor,vis,adj,ls);
            }
        }
    }
    
    /**
     * DFS (Depth-First Search) of Graph
     * Works for both undirected and directed graphs.
     * For directed graphs, each adjacency list should contain outgoing edges.
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Each vertex is visited exactly once: O(V)
     *   - Each edge is explored exactly once: O(E)
     * 
     * Space Complexity: O(V)
     *   - Visited array: O(V)
     *   - Result list: O(V)
     *   - Recursion call stack: O(V) in worst case (linear chain)
     * 
     * @param V number of vertices
     * @param adj adjacency list representation of the graph
     * @return list of vertices in DFS order
     */
    public List<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ls = new ArrayList<>();
        boolean[] vis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(i,vis,adj,ls);
            }
        }
        return ls;
    }
}
