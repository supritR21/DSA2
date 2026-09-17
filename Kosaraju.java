import java.util.*;

public class Kosaraju {
    public void dfs1(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for(int it : adj.get(node)) {
            if(!vis[it]) {
                dfs1(it,vis,adj,st);
            }
        }
        st.push(node);
    }
    public void dfs2(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adjT) {
        vis[node] = true;
        for(int it : adjT.get(node)) {
            if(!vis[it]) {
                dfs2(it, vis, adjT);
            }
        }
    }
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs1(i,vis,adj,st);
            }
        }

        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for(int i=0; i<V; i++) {
            adjT.add(new ArrayList<>());
        }
        for(int i=0; i<V; i++) {
            for(int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        Arrays.fill(vis, false);
        int sccCount = 0;

        while(!st.isEmpty()) {
            int node = st.pop();
            if(!vis[node]) {
                sccCount++;
                dfs2(node, vis, adjT);
            }
        }
        return sccCount;
    }
}
