import java.util.*;

public class Articulation {
    static int timer;
    static int[] low;
    static int[] tin;
    static boolean[] vis;
    static boolean[] ap;
    static ArrayList<ArrayList<Integer>> graph;
    private static void dfs(int node, int par) {
        vis[node] = true;
        low[node] = tin[node] = timer++;
        int children = 0;

        for(int nei : graph.get(node)) {
            if(nei==par) continue;
            if(!vis[nei]) {
                dfs(nei,node);
                low[node] = Math.min(low[node],low[nei]);
                if(par!=-1 && low[nei]>=tin[node]) {
                    ap[node] = true;
                }
                children++;
            } else {
                low[node] = Math.min(low[node],tin[nei]);
            }
        }
        if(par==-1 && children>1) {
            ap[node] = true;
        }
    }
    public static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        timer = 0;
        low = new int[V];
        tin = new int[V];
        vis = new boolean[V];
        ap = new boolean[V];

        graph = new ArrayList<>();
        for(int i=0; i<V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(i,-1);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<V; i++) {
            if(ap[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
