package Algorithm;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-04 1:07
 * 大数阶乘
 */
public class LargeFactorial {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //存放结果数据
        int[] a = new int[10000];
        a[0] = 1;
        //l 为结果所占数组位数 k 进位位
        int l = 1, k = 0;
        int i, j;
        //输入要阶乘的数
        int num = input.nextInt();
        //从1开始阶乘
        for (i = 1; i <= num; i++){
            //将数组中的数 从低到高每个与阶乘数相乘
            for (j = 0; j < l; j++){
                a[j] = a[j] * i + k;
                //数组一位代表结果四位，计算进位k
                k = a[j] / 10000;
                a[j] = a[j] % 10000;
            }
            //计算到最高位，如果有进位
            if (k != 0){
                //进位直接为最高位
                a[j] = k;
                //增加位数
                l++;
                k = 0;
            }
        }
        System.out.println(a[l - 1]);
        for (i = l - 2; i >= 0; i--){
            System.out.print(a[i]);
        }
    }
}
