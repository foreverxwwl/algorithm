package Algorithm;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-07 1:37
 * 最长上升子序列
 */
public class LongestRiseSubstring {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //存放输入串
        int[] a = new int[10000];
        //串长度
        int l = input.nextInt();
        //存放状态记录，dp[i]表示以a[i]元素结尾的上升子串长度
        int[] dp = new int[10000];
        //存放结果
        int result = 0;
        //输入串
        for (int i = 0; i < l; i++) {
            a[i] = input.nextInt();
        }
        //计算最长子序列
        //从第一个元素开始，遍历其前面的元素，记录状态
        for (int i = 0; i < l; i++) {
            dp[i] = 1;//起始有他自己，所以为1
            //开始遍历其前面元素
            for (int j = 0; j < i; j++) {
                //当前面有元素比本元素小，且该元素的子串记录+1比本元素记录大，那么将其纳入子串
                if (a[j] < a[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        //dp[i]中存放的就是响应a[i]的子串长度，求出最大的即可
        for (int i = 1; i < l; i++){
            result = dp[0];
            if (dp[i] > dp[0]){
                result = dp[i];
            }
        }
        System.out.println(result);
    }
}
