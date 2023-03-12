package Mar;

public class LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix){
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0;i < N;i++){
            for (int j = 0; j < M; j++){
                ans = Math.max(ans,process(matrix,i,j));
            }
        }
        return ans;
    }

    public static int process(int[][] m,int i,int j){
        int up = i > 0 && m[i][j] < m[i-1][j]? process(m,i-1,j):0;
        int down = i < (m.length-1) && m[i][j] < m[i+1][j]? process(m,i+1,j):0;
        int left = j > 0 && m[i][j] < m[i][j-1]? process(m,i,j-1):0;
        int right = j < (m[0].length-1) && m[i][j] < m[i][j+1]? process(m,i,j+1):0;
        return Math.max(Math.max(up,down),Math.max(left,right)) + 1;
    }

    public int longestIncreasingPath2(int[][] matrix){
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        // dp[i][j] == 0
        int[][] dp = new int[N][M];
        for (int i = 0;i < N;i++){
            for (int j = 0; j < M; j++){
                ans = Math.max(ans,process2(matrix,i,j,dp));
            }
        }
        return ans;
    }

    // 自顶向下的动态规划/记忆化搜索
    // 为什么不优化？因为整理严格依赖比较难，依赖的位置太多
    public static int process2(int[][] m,int i,int j,int[][] dp){
        if (dp[i][j] != 0){
            return dp[i][j];
        }
        int up = i > 0 && m[i][j] < m[i-1][j]? process2(m,i-1,j,dp):0;
        int down = i < (m.length-1) && m[i][j] < m[i+1][j]? process2(m,i+1,j,dp):0;
        int left = j > 0 && m[i][j] < m[i][j-1]? process2(m,i,j-1,dp):0;
        int right = j < (m[0].length-1) && m[i][j] < m[i][j+1]? process2(m,i,j+1,dp):0;
        dp[i][j] = Math.max(Math.max(up,down),Math.max(left,right)) + 1;
        return dp[i][j];
    }
}
