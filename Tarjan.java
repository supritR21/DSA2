import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Tarjan {
    int timer = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] tin = new int[n];
        int[] low = new int[n];

        Arrays.fill(tin,-1);
        List<List<Integer>> bridges = new ArrayList<>();

        for(int i=0; i<n; i++) {
            if(tin[i]==-1) {
                dfs(i,-1,adj,tin,low,bridges);
            }
        }
        return bridges;
    }
    void dfs(int u, int parent, List<List<Integer>> adj, int[] tin, int[] low, List<List<Integer>> bridges) {
        tin[u] = low[u] = timer++;
        for(int v : adj.get(u)) {
            if(v==parent) continue;
            if(tin[v]!=-1) {
                low[u] = Math.min(low[u],tin[v]);
            } else {
                dfs(v,u,adj,tin,low,bridges);
                low[u] = Math.min(low[u],low[v]);

                if(low[v]>tin[u]) {
                    bridges.add(Arrays.asList(u,v));
                }
            }
        }
    }
}
