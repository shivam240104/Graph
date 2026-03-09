package DIJKSTRA.Based_Problem;
/*
link - https://unstop.com/code/practice/568209
Problem Statement
You are stranded on a mysterious archipelago consisting of N islands. These islands are interconnected by M one-way bridges that have specific time-based conditions for crossing. Each bridge can only be crossed during a specific time window, and you need to figure out the shortest possible time to escape from the first island (Island 1) to the last island (Island N). The islands are numbered sequentially from 1 to N.

Your task is to determine the minimum time required to reach Island N from Island 1, starting at time = 0. If it is impossible to reach the last island, output -1.

Each bridge is characterized by the following parameters:

Source island u: The island from which the bridge starts.

Destination island v: The island where the bridge leads to.

Activation time t: The time after which the bridge becomes active and can be crossed.

Duration d: The time it takes to cross the bridge once it is active.

You can wait on any island as long as you want. However, you cannot go backward and cannot use the same bridge more than once. The bridges are one-way, and the only way to escape the archipelago is by reaching the last island, N.

The key challenge is that bridges become available only at specific times, and you need to find the optimal time to traverse the bridges while waiting for them to activate. The problem requires you to implement an efficient algorithm to determine the minimum time required to travel from Island 1 to Island N, or output -1 if it is impossible.
 */

import java.util.*;

class Details{
    int isLand;
    int time;
    int dura;
    Details(int i, int t, int d){
        this.isLand = i;
        this.time = t;
        this.dura = d;
    }
}

class Node {
    int isLand;
    int time;
    Node(int i, int t){
        this.isLand = i;
        this.time = t;
    }
}

public class time_shiftedIland_Escape {


    public static List<List<Details>> buildGraph(int n, int[][] bridges) {
        List<List<Details>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] bridge : bridges) {
            graph.get(bridge[0]).add(new Details(bridge[1], bridge[2], bridge[3]));
        }

        return graph;

    }

    public static int findMinTime(int N, int M, int K, int[][] bridges) {
        List<List<Details>> graph = buildGraph(N, bridges);
        int[] ans = new int[N + 1];
        Arrays.fill(ans, Integer.MAX_VALUE);

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.time - b.time);

        minHeap.add(new Node(1, 0));
        ans[1] = 0;

        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            int currisLand = curr.isLand;
            int currTime = curr.time;

            if (currTime > ans[currisLand]) continue;

            for (Details nbr : graph.get(currisLand)) {

                if ((Math.max(currTime, nbr.time) + nbr.dura) < ans[nbr.isLand]) {
                    ans[nbr.isLand] = (Math.max(currTime, nbr.time) + nbr.dura);
                    minHeap.add(new Node(nbr.isLand, ans[nbr.isLand]));
                }

            }
        }

        return ans[N] != Integer.MAX_VALUE ? ans[N] : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        int[][] bridges = new int[M][4];
        for (int i = 0; i < M; ++i) {
            bridges[i][0] = scanner.nextInt();
            bridges[i][1] = scanner.nextInt();
            bridges[i][2] = scanner.nextInt();
            bridges[i][3] = scanner.nextInt();
        }

        int result = findMinTime(N, M, K, bridges);
        System.out.println(result);
    }
}
/* input -
5 6 3
1 2 0 3
2 3 4 2
3 5 10 1
1 4 2 5
4 5 10 2
2 5 8 3
 */
/*
output
11
 */