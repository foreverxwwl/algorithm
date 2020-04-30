package leetcode.offer.num14;

/**
 * @outhor li
 * @create 2020-04-28 23:35
 * 剪绳子
 */
public class Main {

}

class Solution {

    /**
     * 算法入口(动态规划解法)
     * @param n  绳子长度
     * @return 最大乘积
     */
    public int cuttingRope(int n) {
        //1.构建状态记录数组
        int[] dp = new int[n+1];
        //2.给数组赋初值
        dp[1] = 1;
        //3.通过状态转移方程，构建状态数组
        //i表示此状态下绳子的长度  j表示此长度下依次从长度1到k剪绳子
        //在每个状态下都可计算出本状态下剪绳子的最大乘积，后面的状态只用依次试出增加的状态如何剪就好
        for (int i = 2; i <= n; i++){
            for (int j = 1; j < i; j++) {
                //状态转移方程
                //dp[i]表示绳子长为i时的最大乘积，则状态转移方程可分类：
                // 1. 不剪绳子保持原状态（或者说是上种情况的状态）。2.从j处剪绳子 2.1剪了以后不再剪 2.2剪了以后再把长度j的绳子剪
                //第一个max(保持现有状态不剪，max(从j处剪绳子剩下的绳子长i-j，从j处剪绳子剩下的绳字长i-j在把j从适当位置剪开))
                //这个适当位置就可以从状态数组中查出应为是以前的状态最优解就在数组中
                dp[i] = Math.max(dp[i], Math.max(j * ( i - j), dp[j] * ( i - j)));
            }
        }
        return dp[n];
    }
}