package DataStructure.heap;

import java.util.Arrays;

/**
 * @outhor li
 * @create 2019-11-14 23:59
 * 堆排序
 */
public class HeapSort {
    /**
     * 下沉调整
     * @param array 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        int temp = array[parentIndex];
        int childrenIndex = parentIndex * 2 + 1;
        while (childrenIndex < length){
            if (childrenIndex + 1 < length && array[childrenIndex] < array[childrenIndex + 1]){
                childrenIndex++;
            }
            if (temp > array[childrenIndex])
                break;
            array[parentIndex] = array[childrenIndex];
            parentIndex = childrenIndex;
            childrenIndex = parentIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 堆排序
     * @param array 待排序数组
     */
    public static void heapSort(int[] array){
        //构建二叉堆
        for (int i = (array.length - 2) / 2; i >= 0; i--){
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        //不断将堆顶元素放到堆低
        for (int i = array.length - 1; i > 0; i--) {
            //最后一个未交换元素和堆顶交换
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            //往后就不用在调整换下来的堆低元素
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,3,6,5,7,8,9,10,0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
