package Algorithm;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-03 9:25
 * 筛法求素数
 */
public class PrimeNumber {
    static int[] a = new int[10000];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num;
        num = input.nextInt();
        //求num以内的素数
        primer(num);
        for (int i = 0; i < num; i++){
            System.out.print(a[i]);
        }
    }

    public static void primer(int num){
        //遍历数组a，将非素数置为1
        for (int i = 2; i < num; i++){
            //如果i为素数
            if (a[i] != 1){
                //那么i的倍数不是素数
                for (int j = i * 2; j < num; j += i){
                    a[j] = 1;
                }
            }
        }
    }
}
