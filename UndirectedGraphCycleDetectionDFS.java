import java.util.*;

public class UndirectedGraphCycleDetectionDFS {
    /**
     * DFS helper function to detect cycle in undirected graph
     * 
     * Time Complexity: O(V + E) - called for each vertex and edge once
     * Space Complexity: O(V) - recursion call stack depth in worst case
     */
    public boolean dfs(int i, int par, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[i] = true;
        for(int neighbor : adj.get(i)) {
            if(!vis[neighbor]) {
                if(dfs(neighbor,i,adj,vis)) {
                    return true;
                }
            } else if(neighbor != par) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Detect cycle in an undirected graph using DFS
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Each vertex is visited exactly once: O(V)
     *   - Each edge is explored exactly once: O(E)
     *   - DFS traversal takes O(V + E)
     * 
     * Space Complexity: O(V)
     *   - Visited array: O(V)
     *   - Recursion call stack: O(V) in worst case (linear chain)
     * 
     * Algorithm: Uses DFS with parent tracking. If we find a visited neighbor
     * that is not the parent node, then a cycle exists.
     * 
     * @param V number of vertices
     * @param adj adjacency list representation of undirected graph
     * @return true if cycle exists, false otherwise
     */
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                if(dfs(i,-1,adj,vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}
