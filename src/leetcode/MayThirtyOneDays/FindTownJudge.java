package leetcode.MayThirtyOneDays;

// I like the graph style content
public class FindTownJudge {
    public int findJudge(int N, int[][] trust) {
        int[] indegree = new int[N + 1];
        int[] outdegree = new int[N + 1];

        for(int i = 0; i < trust.length; i++){
            outdegree[trust[i][0]]++;
            indegree[trust[i][1]]++;
        }

        for(int i = 1; i <= N; i++){
            if(indegree[i] == N - 1 && outdegree[i] == 0){
                return i;
            }
        }

        return -1;
    }
}
