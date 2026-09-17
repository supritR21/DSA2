import java.util.*;

class ZOBFS {

    public int[] zeroOneBFS(int n, int[][] edges, int src) {

        // Adjacency list: {neighbor, weight}
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new int[]{v, wt});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> dq = new ArrayDeque<>();

        dist[src] = 0;
        dq.offerFirst(src);

        while (!dq.isEmpty()) {

            int u = dq.pollFirst();

            for (int[] edge : adj.get(u)) {

                int v = edge[0];
                int wt = edge[1];

                if (dist[u] + wt < dist[v]) {

                    dist[v] = dist[u] + wt;

                    // Weight 0 -> process immediately
                    if (wt == 0) {
                        dq.offerFirst(v);
                    }
                    // Weight 1 -> process later
                    else {
                        dq.offerLast(v);
                    }
                }
            }
        }

        return dist;
    }
}