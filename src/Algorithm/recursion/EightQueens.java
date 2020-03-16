package Algorithm.recursion;

/**
 * @outhor li
 * @create 2020-03-16 21:45
 * 8皇后问题 递归解决
 * 使用一个数组来存放结果 result{0 , 4, 7, 5, 2, 6, 1, 3}
 * 数组下标表示行数，其中元素表示列数，
 */
public class EightQueens {
    static int max = 8;
    static int[] result = new int[max];
    static int count;
    static int judgeCount;

    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }
    /**
     * 放置皇后递归方法
     * @param n 当前放置的皇后个数
     */
    public static void check(int n){
        //当八个皇后都放好的时候 输出并返回 查找别的结果
        if (n == max){
            print();
            count++;
            return;
        }
        for (int i = 0; i < max; i++){
            result[n] = i;
            if (judge(n)){
                check(n+1);
            }
        }
    }

    /**
     * 查看当前位置是否冲突
     * @param n 当前放置皇后个数
     * @return 是否冲突
     */
    public static boolean judge(int n){
        for (int i = 0; i < n;i++){
            judgeCount++;
            /**
             * result[i]==result[n] 判断是否在一行
             * Math.abs(result[i]-result[n]) == Math.abs(i-n) 判断是否在一个斜线上
             * 应为我们使用 i来表示行数，result[i]来表示列数，则这个元素行上元素的坐标应该是行和列都加相同的数
             * 即 i+n 和result[i]+result[n] 就好像在(2,2)元素斜线上的元素是(2+n,2+n)
             */
            if (result[i]==result[n] || Math.abs(result[i]-result[n]) == Math.abs(i-n)){
                return false;
            }
        }
        return true;
    }

    //输出结果
    public static void print(){
        for (int i = 0; i < max; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }
}
