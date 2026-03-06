package BFS;

import BFS.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import static BFS.bfs.buildGraph;

public class disconnectedBFS {

   public static int bfsConnect(List<List<Integer>> graph, int[] ans, boolean[] visited, int src, int idx){
       Queue<Integer> q = new LinkedList<>();
       q.add(src);

       visited[src] = true;
//       int idx =0;
       while( !q.isEmpty()){
           int curr = q.poll();

           ans[idx++] = curr;

           for(int nbr : graph.get(curr)){
               if(!visited[nbr]){
                   visited[nbr] = true;
                   q.add(nbr);
               }
           }

       }
       return idx;
   }

    public static int [] bfsAns(int [][] edges, int n){
        List<List<Integer>>graph = buildGraph(edges, n);

        int [] ans = new int [n];
        boolean [] visited = new boolean[n];
       int idx =0;
        for(int i=0; i<n;i++){
            if(!visited[i]){
               idx = bfsConnect(graph,ans,visited,i, idx);
            }
        }


        return ans;
    }



    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2}, {2, 0}, {0, 3}, {4, 5}, {5, 6}};
        int n = 7;
        int src = 0;



        int[] bfsAnswer = bfsAns(edges, n);
        for(int node : bfsAnswer) {
            System.out.print(node + " ");
        }
    }
}
