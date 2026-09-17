public class DisjointSetUnion {

    private int[] parent;
    private int[] rank;
    private int[] size;

    // Constructor
    public DisjointSetUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            makeSet(i);
        }
    }

    // Make a set for one element
    public void makeSet(int x) {
        parent[x] = x;
        rank[x] = 0;
        size[x] = 1;
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank
    public void unionByRank(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Union by size
    public void unionBySize(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }

    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    // Get size of the set containing x
    public int getSize(int x) {
        return size[find(x)];
    }

    // Get rank of the root of x
    public int getRank(int x) {
        return rank[find(x)];
    }

    // Print parent array
    public void printParent() {
        for (int i = 0; i < parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }

    // Print leader/root of each element
    public void printRoots() {
        for (int i = 0; i < parent.length; i++) {
            System.out.print(find(i) + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion(7);

        dsu.unionByRank(0, 1);
        dsu.unionByRank(1, 2);
        dsu.unionByRank(3, 4);
        dsu.unionByRank(5, 6);
        dsu.unionByRank(4, 5);

        System.out.println("0 and 2 connected? " + dsu.connected(0, 2));
        System.out.println("0 and 3 connected? " + dsu.connected(0, 3));

        System.out.println("Leader of 2: " + dsu.find(2));
        System.out.println("Size of set containing 4: " + dsu.getSize(4));

        System.out.print("Roots: ");
        dsu.printRoots();

        System.out.print("Parent array: ");
        dsu.printParent();
    }
}