import java.util.*;

public class BFS {
    /**
     * BFS (Breadth-First Search) of Graph
        * Works for both directed and undirected graphs.
        * For directed graphs, provide edges as [source, destination].
     * 
     * Time Complexity: O(V + E)
     *   - V: number of vertices
     *   - E: number of edges
     *   - Each vertex is visited exactly once: O(V)
     *   - Each edge is explored exactly once: O(E)
     * 
     * Space Complexity: O(V)
     *   - Adjacency list: O(V + E)
     *   - Visited array: O(V)
     *   - Queue: O(V) in worst case
    *   - Result list: O(V)
     * 
     * @param edges edges of the graph [source, destination]
     * @param V number of vertices
     * @return list of vertices in BFS order starting from 0
     */
    public List<Integer> bfsOfGraph(int[][] edges, int V) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            // a->b
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b);
        }
        ArrayList<Integer> ls = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while(!q.isEmpty()) {
            int node = q.poll();
            ls.add(node);
            for(int neighbor : adj.get(node)) {
                if(!vis[neighbor]) {
                    vis[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        return ls;
    }
}
