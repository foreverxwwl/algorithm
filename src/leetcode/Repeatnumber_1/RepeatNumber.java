package leetcode.Repeatnumber_1;

/**
 * @outhor li
 * @create 2020-03-20 0:12
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * 可以将值为 i 的元素调整到第 i 个位置上进行求解
 * 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复
 */
public class RepeatNumber {
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0){
            throw new RuntimeException("传入数组为空");
        }
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != i) {//确保当前位置等于当前位置上的元素（说明是刚才交换过来的）不算要等其他位置与当前位置相等
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        throw new RuntimeException("找不到相应值");
    }
    public static void swap(int[] arr, int i, int j){
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
