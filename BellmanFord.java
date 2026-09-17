import java.util.*;

class Edge {
    int u, v, wt;
    Edge(int u, int v, int wt) {
        this.u = u;   // source vertex
        this.v = v;   // destination vertex
        this.wt = wt; // edge weight
    }
}

class BellmanFord {
    /*
     * Bellman-Ford Algorithm
     * ----------------------
     * Finds shortest distances from source to all vertices.
     * Works with negative edge weights.
     * Can detect negative weight cycles.
     */
    public int[] bellmanFord(int V, ArrayList<Edge> edges, int src) {

        // Step 1: Initialize distance array
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9); // represent infinity
        dist[src] = 0;

        // Step 2: Relax all edges (V - 1) times
        // After V-1 relaxations, shortest paths are guaranteed
        for (int i = 1; i <= V - 1; i++) {

            // Try relaxing every edge
            for (Edge e : edges) {
                int u = e.u;
                int v = e.v;
                int wt = e.wt;

                // Relaxation condition:
                // if distance to u is known and
                // going through u gives a shorter path to v
                if (dist[u] != (int)1e9 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Step 3: Check for negative weight cycle
        // If we can still relax an edge, then a negative cycle exists
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;
            int wt = e.wt;

            if (dist[u] != (int)1e9 && dist[u] + wt < dist[v]) {
                // Negative cycle detected
                return new int[]{-1};
            }
        }

        // Step 4: Return shortest distances
        return dist;
    }
}
