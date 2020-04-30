package leetcode.offer.num15;

/**
 * @outhor li
 * @create 2020-04-30 23:54
 * 二进制中1的个数
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hammingWeight(8));
    }
}
class Solution {

    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }
}