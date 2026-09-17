import java.util.*;

class MultiBFS {

    public int[] multiSourceBFS(
            int n,
            ArrayList<ArrayList<Integer>> adj,
            int[] sources
    ) {

        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();

        // Add all sources
        for (int src : sources) {
            dist[src] = 0;
            q.offer(src);
        }

        while (!q.isEmpty()) {

            int u = q.poll();

            for (int v : adj.get(u)) {

                if (dist[v] == -1) {

                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }

        return dist;
    }
}