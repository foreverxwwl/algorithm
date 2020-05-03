package leetcode.offer.num17;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @outhor li
 * @create 2020-05-01 1:02
 * 打印从1到最大的n位数
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.printNumbers(1);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
class Solution {
    public int[] printNumbers(int n) {
        int num = (int) Math.pow(10,n) - 1;
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i+1;
        }
        return result;
    }
}