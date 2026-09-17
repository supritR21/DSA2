import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class TarjanAP {
    int timer = 0;
    public List<Integer> articulationPoints(int n, List<List<Integer>> connections) {
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
        boolean[] ap = new boolean[n];
        Arrays.fill(tin,-1);
        for(int i=0; i<n; i++) {
            if(tin[i]==-1) {
                dfs(i,-1,low,tin,adj,ap);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(ap[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
    void dfs(int u, int par, int[] low, int[] tin, List<List<Integer>> adj, boolean[] ap) {
        tin[u] = low[u] = timer++;
        int children = 0;
        for(int v : adj.get(u)) {
            if(v==par) continue;
            if(tin[v]!=-1) {
                low[u] = Math.min(low[u],tin[v]);
            } else {
                dfs(v,u,low,tin,adj,ap);
                low[u] = Math.min(low[u],low[v]);

                if(par!=-1 && low[v]>=tin[u]) {
                    ap[u] = true;
                }
                children++;
            }
        }
        if(par==-1 && children>1) {
            ap[u] = true;
        }
    }
}