package DepthFirstSearch.dfs;

import java.util.List;

import static BFS.bfs.buildGraph;

public class dfs {
     static int idx =0;
     public static void solve(List<List<Integer>> graph, int src, int n, boolean visited[], int [] ans){
         ans[idx++] = src;
         visited[src] = true;

         for(int nbr : graph.get(src)){
             if(!visited[nbr]){
                 solve(graph,nbr,n,visited,ans);
             }
         }
     }
    public static int [] dfs(int [][] edges,int n, int src){
        List<List<Integer>>graph = buildGraph(edges, n);

        int [] ans = new int[n];

        boolean [] visited = new boolean[n];
        solve(graph, src,n, visited,ans);

        return ans;
    }

    public static void main(String[] args) {
//        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5},{2,6},{3,7},{4,7},{5,8},{6,8},{7,9},{8,9},{9,10},{10,11},{11,7},{6,12,},{12,13},{13,14},{14,6} };
        int n = 15;
        int src = 0;



        int[] dfsAnswer = dfs(edges, n, src);
        for(int node : dfsAnswer) {
            System.out.print(node + " ");
        }
    }
}
