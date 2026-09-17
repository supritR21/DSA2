import java.util.*;

class Pair {
    int curr;
    int par;
    public Pair(int _curr, int _par) {
        this.curr = _curr;
        this.par = _par;
    }
}
public class UndirectedGraphCycleDetectionBFS {
    /**
     * Detect cycle in an undirected graph using BFS
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Each vertex is visited exactly once: O(V)
     *   - Each edge is explored exactly once: O(E)
     *   - BFS traversal takes O(V + E)
     * 
     * Space Complexity: O(V)
     *   - Visited array: O(V)
     *   - Queue: O(V) in worst case
     *   - Pair objects: O(V)
     * 
     * Algorithm: Uses BFS with parent tracking. If we find a visited neighbor
     * that is not the parent node, then a cycle exists.
     * 
     * @param V number of vertices
     * @param adj adjacency list representation of undirected graph
     * @return true if cycle exists, false otherwise
     */
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                q.add(new Pair(i,-1));
                vis[i] = true;
                while(!q.isEmpty()) {
                    Pair p = q.poll();
                    for(int neighbor : adj.get(p.curr)) {
                        if(!vis[neighbor]) {
                            vis[neighbor] = true;
                            q.add(new Pair(neighbor,p.curr));
                        }
                        else {
                            if(neighbor != p.par) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Reference problems where Undirected Graph Cycle Detection (BFS) is used:
    // 1. Detect cycle in an undirected graph (GFG)
    // 2. Redundant Connection (LeetCode 684)
    // 3. Graph Valid Tree (LeetCode 261)
    // 4. Number of Operations to Make Network Connected (LeetCode 1319)
    // 5. Is Graph Bipartite? (LeetCode 785) - similar BFS traversal pattern
}
