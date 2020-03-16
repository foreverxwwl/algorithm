package Algorithm.recursion;

/**
 * @outhor li
 * @create 2019-11-03 8:51
 * 加法分解
 */
/*
6分解
5 1
4 2  4 1 1
3 3  3 2 1  3 1 1 1
2 2 2  2 2 1 1  2 1 1 1 1
1 1 1 1 1 1
*/
public class AdditionDecompostition {
    public static void main(String[] args) {
        int[] a = new int[100];
        decompostition(6, a, 0);
    }

    /**
     * 分解函数
     * @param i 待分解的数
     * @param a 将分解出的数放入数组
     * @param k 递归的深度也是分解开数字个数
     */
    public static void decompostition(int i, int[] a, int k) {
        for (int j = i; j > 0 ; j--){
            //当本次分解出的数j比前面的数a[k-1]大时，跳过本次分解
            if (k > 0 && j > a[k - 1])
                continue;
            //将本次分解出的数存入数组
            a[k] = j;
            //递归，将本次分解剩下的数i-j再次分解，深度k+1
            decompostition(i - j, a, k + 1);
        }
        //当不能再分解时
        if (i <= 0){
            //输出本次分解数组
            for (int j = 0; j < k; j++){
                System.out.print(a[j]);
            }
            System.out.println();
        }
    }
}
