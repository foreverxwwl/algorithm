package Algorithm.search;

/**
 * @outhor li
 * @create 2020-03-17 17:14
 * 插入查找算法（适合于有序的序列）
 * 插入查找时，思路和二分查找相同，就是分割点不是二分查找的(right-left)/2而是一种自适应的
 * left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])这样分割可以有效的减小比较次数
 */
public class insertValueSearch {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);
    }

    /**
     * 插入查找算法
     * @param arr 待查找的数组
     * @param left 查找开始点
     * @param right 查找结束点
     * @param value 待查找值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int value){
        //当越界时返回上一层递归
        if (left > right || arr[left] > value || arr[right]< value) {
            return -1;
        }

        // 确定分割点的位置
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        //分割点的值
        int midVal = arr[mid];
        if (value > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
