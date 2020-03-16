package Algorithm.sort;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-03 9:46
 * 快速排序
 */
public class QuickSort {
    //存放待排序数组
    public static int[] a = new int[1000];

    //用于两数交换函数
    public static void swap(int[] a, int i, int j){
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //快速排序中循环比较交换阶段
    //此函数调用结束后，会将此数组比基准数num小的放在左面，大的防在右边
    public static int part(int[] a, int l, int h){
        //定义l,h指向数组两端
        int i = l;
        int j = h;
        //将最前面的数作为基准数
        int num = a[l];
        while (i < j){
            //将最右边的数与基准比较，如果比基准小则退出，交换
            while (a[j] >= num && i < j){
                j--;
            }
            if (i < j)
                swap(a, i, j);
            //将最左边的数与基准比较，如果比基准大则退出，交换
            while (a[i] <= num && i < j){
                i++;
            }
            if (i < j)
                swap(a, i, j);
        }
        return i;
    }
    //在一趟交换结束后，获取基准节点位置q，以此划分两边递归进行排序
    public static void quickSort(int[] a, int l, int h){
        //直到两头指针相遇时结束
        if (l < h){
            int q = part(a, l, h);
            quickSort(a, l, q - 1);
            quickSort(a, q + 1, h);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //排序数组长度
        int num = input.nextInt();
        //输入排序数组
        for (int i = 0; i < num; i++) {
            a[i] = input.nextInt();
        }
        //排序
        quickSort(a, 0, num - 1);
        for (int i = 0; i < num; i++) {
            System.out.print(a[i] + " ");
        }
    }
    
}
