import java.util.*;

// Pair class to store (node, weight)
class Pair {
    int node;
    int weight;

    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class PrimsAlgorithm {

    // Function to find total weight of MST
    static int primMST(int V, ArrayList<ArrayList<Pair>> adj) {

        // Min-heap based on edge weight
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> a.weight - b.weight
        );

        boolean[] visited = new boolean[V];
        int mstWeight = 0;

        // Start from node 0
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int node = curr.node;
            int wt = curr.weight;

            // If already included in MST, skip
            if (visited[node]) continue;

            // Include this node in MST
            visited[node] = true;
            mstWeight += wt;

            // Add all adjacent edges
            for (Pair neighbor : adj.get(node)) {
                if (!visited[neighbor.node]) {
                    pq.add(new Pair(neighbor.node, neighbor.weight));
                }
            }
        }

        return mstWeight;
    }

    // Driver code
    public static void main(String[] args) {

        int V = 5; // number of vertices
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Undirected graph
        adj.get(0).add(new Pair(1, 2));
        adj.get(1).add(new Pair(0, 2));

        adj.get(0).add(new Pair(3, 6));
        adj.get(3).add(new Pair(0, 6));

        adj.get(1).add(new Pair(2, 3));
        adj.get(2).add(new Pair(1, 3));

        adj.get(1).add(new Pair(3, 8));
        adj.get(3).add(new Pair(1, 8));

        adj.get(1).add(new Pair(4, 5));
        adj.get(4).add(new Pair(1, 5));

        adj.get(2).add(new Pair(4, 7));
        adj.get(4).add(new Pair(2, 7));

        int result = primMST(V, adj);
        System.out.println("Total weight of MST = " + result);
    }
}

// Time Complexity: O(E log V) where E is the number of edges and V is the number of vertices.
// Space Complexity: O(V) for the visited array and priority queue.