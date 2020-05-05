package leetcode.offer.num21;

/**
 * @outhor li
 * @create 2020-05-03 23:05
 * 调整数组顺序使奇数位于偶数前面
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {1,2,3,4};
        int[] exchange = solution.exchange(num);
        for (int i = 0; i < num.length; i++) {
            System.out.println(exchange[i]);
        }

    }
}
class Solution {
    /**
     * 使用快排的思想，头尾指针  交换元素
     * @param nums 待交换的数组
     * @return 交换好的数组
     */
    public int[] exchange(int[] nums) {
        //左指针
        int l = 0;
        //右指针
        int r = nums.length - 1;
        while (l<r){
            //从左找到第一个偶数
            while (!isUse(nums[l]) && l <r){
                l++;
            }
            //从右找到第一个奇数
            while (isUse(nums[r])&& l <r){
                r--;
            }
            //交换l和r
            int temp;
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
        return nums;
    }

    /**
     * 判断是奇数还是偶数
     * @param num 待判断的数
     * @return 偶数返回true 奇数返回 false
     */
    public boolean isUse(int num){
        if (num%2==0)
            return true;
        return false;
    }
}