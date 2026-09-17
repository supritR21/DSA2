import java.util.*;

public class SCSS {
    private static void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for(int nei : adj.get(node)) {
            if(!vis[nei]) {
                dfs(nei,vis,adj,st);
            }
        }
        st.push(node);
    }
    private static void revDfs(int node, ArrayList<ArrayList<Integer>> revAdj, boolean[] vis, ArrayList<Integer> component) {
        vis[node] = true;
        component.add(node);
        for(int nei : revAdj.get(node)) {
            if(!vis[node]) {
                revDfs(nei, revAdj, vis, component);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(i,vis,adj,st);
            }
        }
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for(int i=0; i<V; i++) {
            revAdj.add(new ArrayList<>());
        }
        for(int u=0; u<V; u++) {
            for(int v : adj.get(u)) {
                revAdj.get(v).add(u);
            }
        }
        Arrays.fill(vis,false);

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        while(!st.isEmpty()) {
            int node = st.pop();
            if(!vis[node]) {
                ArrayList<Integer> component = new ArrayList<>();
                revDfs(node, revAdj, vis, component);
                res.add(component);
            }
        }
        return res;
    }
}
