package BFS;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class bfs{
    public  static List<List<Integer>> buildGraph(int [][] edges , int n){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());

        }
        for( int [] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
     public static int [] breadthFirstSearch( int[][] edges, int n, int src){

         List<List<Integer>> graph = buildGraph( edges , n);

         int [] answer = new int[n];

         Queue<Integer> q = new LinkedList<>();
         q.add(src);
         boolean [] visited = new boolean[n];
         visited[src] = true;

         int idx = 0;
         while(!q.isEmpty()){
             int curr = q.poll();

             answer[idx++] = curr;

             for( int nbr : graph.get(curr)){
                 if(! visited[nbr]){
                     visited[nbr] = true;
                     q.add(nbr);
                 }
             }
         }


         return  answer;
     }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        int n = 6;
        int src = 0;



        int[] bfsAnswer = breadthFirstSearch(edges, n, src);
        for(int node : bfsAnswer) {
            System.out.print(node + " ");
        }
    }
}
