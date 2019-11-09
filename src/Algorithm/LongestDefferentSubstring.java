package Algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @outhor li
 * @create 2019-11-08 22:40
 * 最长无重复子串
 */
public class LongestDefferentSubstring {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        int length1 = lengthOfLongestSubstring1(s);
    }

    /**
     * 暴力解法最初版，从头到尾循环每个子串 n3
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            //查找该元素后面的元素
            for (int j = i + 1; j <= n; j++)
                //从上次结束i处开始，到此序列结尾
                if (allUnique(s, i, j))
                    //如没有重复
                    ans = Math.max(ans, j - i);
        return ans;
    }
    /*
    从区间Start到end查找有无重复元素
     */
    public boolean allUnique(String s, int start, int end) {
        //使用HashSet可变长
        Set<Character> set = new HashSet<>();
        //遍历区间内的串
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch))
                return false;
            set.add(ch);
        }
        return true;
    }

    /**
     * 自己写的最基础算法，改进了上面的暴力算法，
     * 一旦前面的元素中没有重复，那就只用看新元素有无重复
     * 一旦有重复下次也可以直接从上次断点处遍历就好
     */
    public static int lengthOfLongestSubstring1(String s){
        int[] dp = new int[1000000];//存放状态数组
        int f = 0; //标记上次有重复元素结尾
        //将字符串存入数组
        char[] arryStr = s.toCharArray();
        //从头开始遍历数组，每个元素都往前找看有无与之重复元素，并记录个数
        int a = 0;//a表示以本元素结尾的舞重复串长
        for (int i = 0; i < arryStr.length; i++) {
            dp[i] = 1;//默认自身为1
            //循环遍历其前面的数组
            for (int j = f; j < i; j++) {
                //如果有一样的
                if (arryStr[i] == arryStr[j]){
                    //下次就从此处开始循环，不用循环前面的
                    f = j + 1;
                    //a等于此处到下一个待验证元素的距离
                    a = i - j - 1;
                    break;
                }
            }
            dp[i] = a + 1;
            a++;
        }
        int max = dp[0];//存放结果
        //找到最大值
        for (int i = 0; i < arryStr.length; i++) {
            if (dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }



    /**
     * 初始滑动窗口解法
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


}
