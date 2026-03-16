package BFS.problems;

import java.util.LinkedList;
import java.util.Queue;
//https://leetcode.com/problems/rotting-oranges/
public class rotten_Oranges {

        int[][] directions = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

        boolean isValid(int i, int j, int rows, int cols) {
            if (i >= 0 && i < rows && j >= 0 && j < cols)
                return true;

            return false;
        }

        public int orangesRotting(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            int ones = 0;
            int time = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) {
                        q.add(new int[] { i, j,0 });
                    } else if(grid[i][j] == 1) {
                        ones++;
                    }
                }
            }
            System.out.println(ones);

            while (!q.isEmpty()) {
                int[] pos = q.poll();
                int currRow = pos[0];
                int currCol = pos[1];


                for (int[] d : directions) {
                    int newRow = d[0] + currRow;
                    int newCol = d[1] + currCol;

                    if (isValid(newRow, newCol, rows, cols) && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        q.add(new int[] { newRow, newCol, pos[2]+1 });
                        time = pos[2] +1;
                        ones--;
                    }
                }
            }
            System.out.println(time);
            return ones == 0 ? time : -1;
        }
    }

