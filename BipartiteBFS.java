import java.util.*;

public class BipartiteBFS {

    static boolean isBipartite(ArrayList<ArrayList<Integer>> graph, int V) {

        // -1 means not colored
        int[] color = new int[V];
        Arrays.fill(color, -1);

        // Handle disconnected graph
        for (int start = 0; start < V; start++) {

            if (color[start] == -1) {

                Queue<Integer> q = new LinkedList<>();

                q.offer(start);
                color[start] = 0;

                while (!q.isEmpty()) {

                    int node = q.poll();

                    for (int neighbor : graph.get(node)) {

                        // If neighbor not colored
                        if (color[neighbor] == -1) {

                            color[neighbor] = 1 - color[node];
                            q.offer(neighbor);
                        }

                        // Same color found
                        else if (color[neighbor] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int V = 4;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges
        graph.get(0).add(1);
        graph.get(0).add(3);

        graph.get(1).add(0);
        graph.get(1).add(2);

        graph.get(2).add(1);
        graph.get(2).add(3);

        graph.get(3).add(0);
        graph.get(3).add(2);

        System.out.println(isBipartite(graph, V));
    }
}