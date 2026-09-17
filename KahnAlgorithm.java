import java.util.*;

public class KahnAlgorithm {
    /**
     * Topological Sort using Kahn's Algorithm (BFS approach)
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Building adjacency list: O(V + E)
     *   - Computing in-degrees: O(E)
     *   - BFS processing: O(V + E) - each vertex visited once, each edge explored once
     * 
     * Space Complexity: O(V + E)
     *   - Adjacency list: O(V + E)
     *   - In-degree array: O(V)
     *   - Queue: O(V) in worst case
     *   - Result list: O(V)
     * 
     * @param V number of vertices
     * @param edges directed edges [from, to]
     * @return topologically sorted list of vertices
     */
    public List<Integer> topoSort(int V, int[][] edges) {
        ArrayList<Integer> ls = new ArrayList<>();
        // boolean[] vis = new boolean[V];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[V];
        //  a->b
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b);
            inDegree[b]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<V; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int node = q.poll();
            ls.add(node);
            for(int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if(inDegree[neighbor]==0) {
                    q.add(neighbor);
                }
            }
        }
        return ls;
    }

    // Reference problems where Topological Sort (Kahn's Algorithm) is used:
    // 1. Course Schedule (LeetCode 207)
    // 2. Course Schedule II (LeetCode 210)
    // 3. Alien Dictionary (GFG / LeetCode 269)
    // 4. Build a Matrix With Conditions (LeetCode 2392)
    // 5. Parallel Courses (LeetCode 1136)
}
