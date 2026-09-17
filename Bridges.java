import java.util.*;

public class Bridges {
    static int timer;
    static int[] tin;
    static int[] low;
    static boolean[] vis;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Integer>> bridges;
    private static void dfs(int node, int par) {
        vis[node] = true;
        tin[node] = low[node] = timer++;

        for(int nei : graph.get(node)) {
            if(nei==par) continue;

            if(!vis[nei]) {
                dfs(nei,node);
                low[node] = Math.min(low[node], low[nei]);

                if(low[nei]>tin[node]) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(node);
                    temp.add(nei);
                    bridges.add(temp);
                }
            } else {
                low[node] = Math.min(low[node],tin[nei]);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> findBridges(int V, int[][] edges) {
        timer = 0;
        graph = new ArrayList<>();
        bridges = new ArrayList<>();
        for(int i=0; i<V; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        tin = new int[V];
        low = new int[V];
        vis = new boolean[V];

        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(i,-1);
            }
        }
        return bridges;
    }
}
