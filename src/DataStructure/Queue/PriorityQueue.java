package DataStructure.Queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-15 0:32
 * 优先队列
 * 最大优先队列：当前最大元素先出队
 * 使用最大堆来实现最大优先队列
 */
public class PriorityQueue {
    private int[] array;
    private int size;

    public PriorityQueue() {
        //初始大小为32位
        array = new int[32];
        size = 0;
    }

    /**
     * 上浮调整
     */
    private void upAdjust(){
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
     */
    private void downAdjust(){
        int parentIndex = 0;
        int length = size;
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
     * 数组扩容
     */
    private void reSize(){
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }


    /**
     * 入队操作
     * @param key 入队元素
     */
    private void enQueue(int key){
        if (size > array.length){
            reSize();
        }
        //在队尾插入元素
        array[size++] = key;
        //上浮到相应位置
        upAdjust();
    }

    /**
     * 出队
     * @return 出队元素
     */
    private int deQueue(){
        if (size < 0)
            System.out.println("没有元素");
        //获取堆顶元素出队
        int key = array[0];
        //将堆低元素放入堆顶
        array[0] = array[--size];
        downAdjust();
        return key;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue priorityQueue = new PriorityQueue();
        int l = scanner.nextInt();
        for (int i = 0; i < l; i++){
            priorityQueue.enQueue(scanner.nextInt());
        }
        //出队
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
    }
}
