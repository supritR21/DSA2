public class FloydWarshall {
    public void floydWarshall(int[][] dist) {
        int n = dist.length;

        // k = intermediate node
        for (int k = 0; k < n; k++) {

            // i = source node
            for (int i = 0; i < n; i++) {

                // j = destination node
                for (int j = 0; j < n; j++) {

                    // If path i -> k and k -> j exists
                    if (dist[i][k] != 1000000000 && dist[k][j] != 1000000000) {

                        // Relax the edge (i -> j) via k
                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }
    }
}

