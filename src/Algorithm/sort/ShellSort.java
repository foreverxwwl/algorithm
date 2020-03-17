package Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @outhor li
 * @create 2020-03-17 0:54
 * 希尔排序
 * 思想：将数据分组，对每次分的组进行直接插入排序
 * 分组：分组的方式不是相邻分组，而是按照步长，间隔分组。步长的大小为总数据长度/2，下一轮步长大小为当前步长/2，直到步长为1结束
 * 直接插入排序：在每个组进行直接插入排序时，从改组第二个元素开始与前一个元素比较 如果小，那么找合适位置插入，保证前面的元素有序
 * 例如：8, 9, 1, 7, 2, 3, 5, 4, 6, 0   第一次分组 步长10/2=5则分组为(8,3)(9,5)(1,4)(7,6)(2,0) 对每组插入排序
 *      3, 5, 1, 6, 0, 8, 9, 4, 7, 2   第二次分组 步长5/2=2 则分组为(3,1,0,9,7)(5,6,8,4,2) 对每组插入排序  以此类推
 *      0, 1, 3, 9, 7, 2, 4, 5, 6, 8   第三次分组 步长2/2=1 则分组为(0,1,3,9,7,2,4,5,6,8) 对每组插入排序 得到最终结果
 *      0, 1, 2, 3, 4, 5, 6, 7, 8, 9
 */
public class ShellSort {
    public static void main(String[] args) {
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
//        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };


        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        //shellSort(arr); //交换式
        shellSort(arr);//移位方式

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

        //System.out.println(Arrays.toString(arr));
    }


    public static void shellSort(int[] arr){
        //循环每次分组
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            //从组内第二个，即序号为gap的元素开始插入排序
            for (int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[i];
                //当比最后一个元素小时就需要寻找插入位置
                if (arr[i-gap] > arr[i]){
                    //不断按照步长向前比较寻找合适位置
                    while (j-gap >= 0 && arr[j-gap] > temp){
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    //找到后插入
                    arr[j] = temp;
                }
            }
        }
    }
}
