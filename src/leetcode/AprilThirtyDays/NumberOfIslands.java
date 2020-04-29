package leetcode.AprilThirtyDays;

import java.util.*;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '1') {
                    // Deque<Character> queue = new Deque<>();
                    numberOfIslands += doBFS(row, col, grid);
                }
            }
        }
        return numberOfIslands;
    }

    public int doBFS(int row, int col, char[][] grid) {
//        Deque<Character> queue = new ArrayDeque<>();
        Deque<int[]> visited = new ArrayDeque<>();

        grid[row][col] = '0';
        visited.add(new int[]{row, col});

        while (!visited.isEmpty()) {
            int[] current = visited.pop();
            int[] right = new int[]{current[0], current[1] + 1}; // its meant to be off current. Think David, think and be patient.
            int[] down = new int[]{current[0]+1, current[1]};
            int[] left = new int[]{current[0], current[1]-1};
            int[] up = new int[]{current[0]-1, current[1]};

            constrain(grid, visited, right); // IntelliJ cooked this up.
            constrain(grid, visited, down);
            constrain(grid, visited, left);
            constrain(grid, visited, up);
        }
//        while(/*!queue.isEmpty()*/ grid[row][col+1] == '1' || grid[row+1][col] == '1') {
//            if (grid[row+1][col] == '1') {
//                queue.add(grid[row+1][col]);
//                grid[row+1][col] = '0';
//            }
//            if (grid[row][col+1] == '1') {
//                queue.add(grid[row][col+1]);
//                grid[row][col+1] = '0';
//            }
//            row = row + 1;
//            col = col + 1;
//            // queue.pop();
//        }
//        while(/*!queue.isEmpty()*/ grid[row][col] == '1') {
//            if (grid[row+1][col] == '1') {
//                queue.add(grid[row+1][col]);
//                grid[row+1][col] = '0';
//            }
//            if (grid[row][col+1] == '1') {
//                queue.add(grid[row][col+1]);
//                grid[row][col+1] = '0';
//            }
//            row = row + 1;
//            col = col + 1;
//            // queue.pop();
//        }
        return 1;
    }

    private void constrain(char[][] grid, Deque<int[]> visited, int[] right) {
        if (right[0] >= 0 && right[0] < grid.length
                && right[1] >= 0 && right[1] < grid[right[0]].length
                && (grid[right[0]][right[1]]) == '1') {
            visited.add(new int[]{right[0], right[1]});
            grid[right[0]][right[1]] = '0';
        }
    }

    public static void main(String[] args) {
//        char[][] grid1 = new char[][]{{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};
//        char[][] grid2 = new char[][]{{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
        char[][] grid3 = new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        char[][] grid4 = new char[][]{{'1','0','1','1','1'},
                                      {'1','0','1','0','1'},
                                      {'1','1','1','0','1'}};
        NumberOfIslands solution = new NumberOfIslands();
//        System.out.println(solution.numIslands(grid1));
//        System.out.println(solution.numIslands(grid2));
        System.out.println(solution.numIslands(grid3));
        System.out.println(solution.numIslands(grid4));
    }
}

class AnotherSolution1 {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        boolean[][] visited = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans += dfs(grid, m, n, visited, r, c);
            }
        }
        return ans;
    }
    int[] DIR = new int[]{0, 1, 0, -1, 0};
    int dfs(char[][] grid, int m, int n, boolean[][] visited, int r, int c) {
        if (r < 0 || r == m || c < 0 || c == n || visited[r][c] || grid[r][c] == '0') return 0;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            dfs(grid, m, n, visited, r + DIR[i], c + DIR[i+1]);
        }
        return 1;
    }
}

class AnotherSolution2 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        else{
            int R = grid.length;
            int C = grid[0].length;
            int i, j, count = 0;

            for(i = 0; i < R; i++){
                for(j = 0; j < C; j++){
                    if(grid[i][j] == '1'){
                        DFS(grid, i, j, R, C);
                        ++count;
                    }

                }
            }

            return count;
        }
    }

    private void DFS(char[][] grid, int x, int y, int R, int C){
        if(x >= 0 && x < R && y >= 0 && y < C && grid[x][y] == '1'){
            grid[x][y] = '2';
            DFS(grid, x + 1, y, R, C);
            DFS(grid, x - 1, y, R, C);
            DFS(grid, x, y + 1, R, C);
            DFS(grid, x, y - 1, R, C);
        }
    }
}

class Solution4 {
    final int[][] DIR = new int[][] {{0,1}, {1,0}, {0,-1} , {-1,0}};
    public int numIslands(char[][] grid) {
        if (grid==null || grid.length==0) return 0;
        int rows = grid.length;
        int cols=grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int count=0;
        Set<String> visited = new HashSet<>();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (grid[i][j]=='1') {
                    if (visited.contains(i+","+j)) continue;
                    count++;
                    q.offer(new int[]{i,j});
                    bfs(q,  visited, grid);
                }
            }
        }

        return count;
    }

    private void bfs(Queue<int[]> q, Set<String> visited, char[][] grid) {

        while(!q.isEmpty()) {
            int[] cur=q.poll();
            for (int[] dir:DIR) {
                int newRow = cur[0]+dir[0];
                int newCol = cur[1]+dir[1];
                if (newRow<0 || newCol<0 || newRow>=grid.length || newCol>=grid[0].length || grid[newRow][newCol]!='1') continue;

                if (!visited.add(newRow+","+newCol) ) continue;

                q.offer(new int[]{newRow, newCol});
            }
        }

    }
}