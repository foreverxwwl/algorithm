package DataStructure.LinkList.heap;

import java.util.Arrays;

/**
 * @outhor li
 * @create 2019-11-12 16:33
 * 二叉堆，最小二叉堆
 */
public class BinaryHeap {
    /**
     * 上浮调整
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array){
        //用于表示当前子节点
        int childIndex = array.length - 1;
        //用于表示当前子节点的父节点
        int parentIndex = (childIndex - 1)/2;
        //将最初始要上浮的叶子节点保存，用于最后赋值
        int temp = array[childIndex];
        //上浮
        while (childIndex > 0 && temp < array[parentIndex]){
            //无需交换 直接赋值
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1)/2;
        }
        //此节点最终位置
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
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
     * 构建堆
     * @param array 待构建的堆
     */
    public static void buildHeap(int[] array){
        //从最后一个非叶子节点开始依次下沉
        for (int i = (array.length-2)/2; i >= 0; i--){
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        //将最后一个新节点0上浮到适当位置
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        //构建一个小根堆
        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
