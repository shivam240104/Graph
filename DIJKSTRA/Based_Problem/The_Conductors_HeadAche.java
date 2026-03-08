package DIJKSTRA.Based_Problem;


// https://unstop.com/code/practice/624837

/*
Problem Statement
Imagine you are a train conductor trying to get from your starting city to your final destination city as fast as possible. The cities are connected by a network of two-way train tracks, and each track takes a specific number of minutes to cross.

You possess a rare item: a handful of magical speed boost tokens. You have exactly K tokens. When you use a single token on a track, the train travels super-fast, instantly cutting the travel time of that specific track in half. If the halved time is a fraction, you round it down to the nearest whole minute.

You can choose to use your tokens on any track along your journey. You are even allowed to use multiple tokens on the exact same track to halve its time over and over again.

Your goal is to figure out the absolute minimum amount of time it will take to travel from City 0 to the final city (City N-1), assuming you use your speed boost tokens in the smartest way possible.

Note: The starting city is always 0, and the destination is always n - 1. It is guaranteed that a valid path exists from city 0 to city n - 1. There can be multiple different tracks between the same pair of cities.
 */


import java.util.*;

class Track {
    int city;
    int time;

    Track(int city, int time) {
        this.city = city;
        this.time = time;
    }
}

class State {
    int city;
    int tokens;
    int time;

    State(int city, int tokens, int time) {
        this.city = city;
        this.tokens = tokens;
        this.time = time;
    }
}

public class  The_Conductors_HeadAche  {

    static int minTime(int n, List<List<Track>> graph, int k) {

        int[][] dist = new int[n][k + 1];

        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        dist[0][0] = 0;
        pq.add(new State(0, 0, 0));

        while (!pq.isEmpty()) {

            State cur = pq.poll();

            int city = cur.city;
            int tokens = cur.tokens;
            int time = cur.time;

            if (time > dist[city][tokens])
                continue;

            for (Track edge : graph.get(city)) {

                int nextCity = edge.city;
                int travelTime = edge.time;

                // try using 0..remaining tokens on this edge
                for (int use = 0; tokens + use <= k; use++) {

                    int newTime = travelTime >> use; // divide by 2^use

                    int totalTime = time + newTime;

                    if (dist[nextCity][tokens + use] > totalTime) {

                        dist[nextCity][tokens + use] = totalTime;

                        pq.add(new State(nextCity,
                                tokens + use,
                                totalTime));
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= k; i++)
            ans = Math.min(ans, dist[n - 1][i]);

        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        List<List<Track>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int t = sc.nextInt();

            graph.get(u).add(new Track(v, t));
            graph.get(v).add(new Track(u, t));
        }

        System.out.println(minTime(n, graph, k));
    }
}
/* input (35 output)
3 2 2
0 1 100
1 2 10
 */