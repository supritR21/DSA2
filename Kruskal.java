import java.util.*;

// Edge class to store (u, v, weight)
class Edge {
    int u, v, wt;

    Edge(int u, int v, int wt) {
        this.u = u;
        this.v = v;
        this.wt = wt;
    }
}

class Kruskal {

    // Find with path compression
    int find(int parent[], int x) {
        if (parent[x] == x)
            return x;

        parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // Union by rank
    void union(int parent[], int rank[], int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);

        if (px == py) return;

        // Attach smaller rank tree under larger rank tree
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
    }

    // Kruskal's Algorithm
    int spanningTree(int V, int[][] edges) {

        // Convert edges array to Edge list
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int[] e : edges) {
            edgeList.add(new Edge(e[0], e[1], e[2]));
        }

        // Sort edges by weight
        Collections.sort(edgeList, (a, b) -> a.wt - b.wt);

        // DSU initialization
        int parent[] = new int[V];
        int rank[] = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int mstWeight = 0;
        int edgesUsed = 0;

        // Process edges in increasing weight order
        for (Edge e : edgeList) {

            int u = e.u;
            int v = e.v;
            int wt = e.wt;

            // If u and v are in different sets, include edge
            if (find(parent, u) != find(parent, v)) {
                union(parent, rank, u, v);
                mstWeight += wt;
                edgesUsed++;

                // MST complete
                if (edgesUsed == V - 1)
                    break;
            }
        }

        return mstWeight;
    }
}
 
