import java.util.*;

public class ShortestPathBFS {

    static void shortestPath(ArrayList<ArrayList<Integer>> graph, int source, int V) {

        Queue<Integer> q = new LinkedList<>();

        // Distance array
        int[] distance = new int[V];

        // Initialize all distances with -1
        Arrays.fill(distance, -1);

        // Source node distance = 0
        distance[source] = 0;

        q.offer(source);

        while (!q.isEmpty()) {

            int node = q.poll();

            for (int neighbor : graph.get(node)) {

                // If not visited
                if (distance[neighbor] == -1) {

                    distance[neighbor] = distance[node] + 1;

                    q.offer(neighbor);
                }
            }
        }

        System.out.println("Shortest distances:");

        for (int i = 0; i < V; i++) {
            System.out.println(
                source + " -> " + i + " = " + distance[i]
            );
        }
    }

    public static void main(String[] args) {

        int V = 5;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges
        graph.get(0).add(1);
        graph.get(0).add(2);

        graph.get(1).add(0);
        graph.get(1).add(3);

        graph.get(2).add(0);
        graph.get(2).add(3);
        graph.get(2).add(4);

        graph.get(3).add(1);
        graph.get(3).add(2);

        graph.get(4).add(2);

        shortestPath(graph, 0, V);
    }
}