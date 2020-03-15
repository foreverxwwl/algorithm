package Algorithm;

import java.util.Arrays;

/**
 * @outhor li
 * @create 2019-11-15 10:57
 * 寻找数组中前k大元素
 */
public class FindNumberLargeK {
    /**
     * 小根堆  下沉调整
     * @param array 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        //temp保存父节点值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length){
            //如果有右孩子节点 且右孩子节点比左孩子小，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，直接跳出
            if (temp <= array[childIndex]) {
                break;
            }
            //下沉
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 寻找第k大元素
     * @param array 带寻找数组
     * @param key 第key大
     * @return 该元素
     */
    public static int findNumberLargeK(int[] array, int key){
        //构建一个前key的二叉堆
        downAdjust(array,0, key);
        //遍历其后面的数组
        for (int i = key; i < array.length; i++){
            //如果小于堆顶元素，则交换
            if (array[i] > array[0] ){
                array[0] = array[i];
                //下沉到合适位置
                downAdjust(array, 0, key);
            }
        }
        return array[0];
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,5,2,4,8,9,12,6,323};
        int k = findNumberLargeK(array, 3);
        System.out.println(k);
    }
}
