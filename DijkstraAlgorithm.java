import java.util.*;

class Pair {
    int node;
    int dist;
    public Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

public class DijkstraAlgorithm {
    public static void dijkstra(int V, ArrayList<ArrayList<Pair>> adj, int src) {
        // Step 1: Distance array initialized to infinity
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 2: Min-heap (PriorityQueue) based on distance
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Pair(src, 0));

        // Step 3: Process the queue
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int d = curr.dist;

            // Skip if we already found a better path
            if (d > dist[node]) continue;

            // Step 4: Explore neighbors
            for (Pair it : adj.get(node)) {
                int adjNode = it.node;
                int edgeWeight = it.dist;

                // Relaxation step
                if (d + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = d + edgeWeight;
                    pq.add(new Pair(adjNode, dist[adjNode]));
                }
            }
        }

        // Step 5: Print results
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " -> " + dist[i]);
        }
    }

    // Example usage
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Add edges (undirected graph example)
        adj.get(0).add(new Pair(1, 2));
        adj.get(0).add(new Pair(2, 4));
        adj.get(1).add(new Pair(2, 1));
        adj.get(1).add(new Pair(3, 7));
        adj.get(2).add(new Pair(4, 3));
        adj.get(3).add(new Pair(4, 1));

        dijkstra(V, adj, 0);
    }
}
