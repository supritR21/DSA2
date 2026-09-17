import java.util.*;

public class DirectedGraphCycleDetectionDFS {
    /**
     * DFS helper function to detect cycle in directed graph
     * 
     * Time Complexity: O(V + E) - called for each vertex and edge once
     * Space Complexity: O(V) - recursion call stack depth in worst case
     */
    public boolean dfs(int i, boolean[] vis, boolean[] dfsVis, ArrayList<ArrayList<Integer>> adj) {
        vis[i] = true;
        dfsVis[i] = true;
        for(int neighbor : adj.get(i)) {
            if(!vis[neighbor]) {
                if(dfs(neighbor,vis,dfsVis,adj)) return true;
            } else if(dfsVis[neighbor]) {
                return true;
            }
        }
        dfsVis[i] = false;
        return false;
    }
    
    /**
     * Detect cycle in a directed graph using DFS with recursion stack
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Each vertex is visited exactly once: O(V)
     *   - Each edge is explored exactly once: O(E)
     *   - DFS traversal takes O(V + E)
     * 
     * Space Complexity: O(V)
     *   - Visited array (vis): O(V)
     *   - DFS recursion stack array (dfsVis): O(V)
     *   - Recursion call stack: O(V) in worst case (linear chain)
     * 
     * Algorithm: Uses DFS with two boolean arrays:
     *   - vis: tracks vertices visited overall
     *   - dfsVis: tracks vertices in current recursion path (recursion stack)
     * If we encounter a vertex in current recursion path, cycle exists.
     * 
     * @param V number of vertices
     * @param adj adjacency list representation of directed graph
     * @return true if cycle exists, false otherwise
     */
    public boolean detectCycleInDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] dfsVis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                if(dfs(i,vis,dfsVis,adj)) {
                    return true;
                }
            }
        }
        return false;
    }
}
