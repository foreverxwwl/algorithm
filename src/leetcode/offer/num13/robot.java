package leetcode.offer.num13;

/**
 * @outhor li
 * @create 2020-04-27 23:19
 * 机器人运动范围
 */
public class robot{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.movingCount(14,14,5));
    }
}

class Solution{
    int m, n, k;
    boolean full[][];//是否已经遍历过
    int count; //运动范围计数器

    /**
     * 算法入口
     * @param m 总行数
     * @param n 总列数
     * @param k 最大值
     * @return 范围大小
     */
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.full = new boolean[m][n];
        dfs(0, 0);
        return count;
    }

    /**
     * 深度优先搜索
     * @param i 行
     * @param j 列
     */
    public void dfs(int i, int j){
        //如果越界 或者已经遍历过 直接返回count
        if (i<0 || j<0 || i >=m || j >= n || full[i][j]){
            return;
        }
        //如果大小超过 则直接返回
        if (!isIn(i, j ,k)){
            return;
        }
        //如果没有遍历过
        if (!full[i][j]){
            //计数器加一
            count++;
            //标记遍历过
            full[i][j] = true;
        }
        //遍历搜索
        dfs(i-1, j);
        dfs(i+1, j);
        dfs(i, j-1);
        dfs(i, j+1);

    }

    /**
     * 判断点i，j 是否可达
     * @param i 行
     * @param j 列
     * @param k 最大值
     * @return 是否可达
     */
    public boolean isIn(int i, int j, int k){
        if (sumNum(i) + sumNum(j) <= k){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 计算数字各位相加和
     * @param i
     * @return
     */
    public int sumNum(Integer i){
        String num = String.valueOf(i);
        int sum = 0;
        for (int j = 0; j < num.length(); j++) {
            sum = sum + Integer.parseInt(String.valueOf(num.charAt(j)));
        }
        return sum;
    }
}
