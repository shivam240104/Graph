package DIJKSTRA;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Pair{
    int node ;
    int distance;

    Pair(int n, int d){
        this.node = n;
        this.distance = d;
    }
}
public class dijkstra {

    static  List<List<Pair>> buildGraph(int n, int [][] edges){
        List<List<Pair>> graph = new ArrayList<>();

        for(int i =0; i< n;i++){
            graph.add(new ArrayList<>());
        }

        for(int [] edge : edges){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
        }

        return graph;
    }

    static int [] findShortestPath(int n, int src, int [][] edges){
        List<List<Pair>> graph = buildGraph(n , edges);

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b) -> a.distance - b.distance);
        minHeap.add(new Pair(src,0));
        dist[src] = 0;

        while(!minHeap.isEmpty()){
            Pair pair = minHeap.poll();
            int currNode = pair.node;
            int currDist = pair.distance;

            for(Pair p : graph.get(currNode)){
                int nbr = p.node;
                int nbrDist = p.distance;

                if(currDist + nbrDist < dist[nbr]){
                    dist[nbr] = currDist + nbrDist;
                    minHeap.add(new Pair(nbr, dist[nbr]));
                }
            }
        }

        return dist;

    }

    public static void main(String[] args) {
        int [][]edges = new int[][] {{0,1,10},{0,3,20},
                                    {1,2,20}, {1, 5, 35},
                                    {2,4,7},{2,5,10},
                                    {3,1,17},{3,2,21},
                                    {3,4,18},{4,5,2}};

        int n = 6;
        int src =0;

        int [] distance = findShortestPath(n,src,edges);

        for(int ele : distance){
            System.out.print(ele +" ");
        }
    }
}
