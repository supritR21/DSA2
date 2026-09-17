import java.util.*;

class ArticulationPoint {
    int timer = 1;
    public void dfs(int node, int par, int[] low, int[] tin, boolean[] vis, ArrayList<ArrayList<Integer>> adj, boolean[] isArt) {
        vis[node]=true;
        low[node]=timer;
        tin[node]=timer;
        timer++;
        int child = 0;
        
        for(int it : adj.get(node)) {
            if(it==par) continue;
            
            if(!vis[it]) {
                dfs(it,node,low,tin,vis,adj,isArt);
                
                low[node] = Math.min(low[node], low[it]);
                if(tin[node] <= low[it] && par!=-1) {
                    isArt[node]=true;
                } 
                child++;
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }
        if(par==-1 && child>1) {
            isArt[node]=true;
        }
    }
    public ArrayList<Integer> articulationPoints(int V,
                                                 ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] isArt = new boolean[V];
        int[] low = new int[V];
        int[] tin = new int[V];
        ArrayList<Integer> ls = new ArrayList<>();
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfs(i,-1,low,tin,vis,adj,isArt);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<V; i++) {
            if(isArt[i]) res.add(i);
        }
        if(res.size()==0) res.add(-1);
        return res;
    }
}