package leetcode.offer.num16;

/**
 * @outhor li
 * @create 2020-05-01 0:05
 * 数值的整数次方
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Math.pow(2.00,2.00));
        System.out.println(solution.myPow(2.00,10));
    }
}

class Solution {
    public double myPow(double x, int n) {
       if (n == 0){
           return 1;
       }
       if (n == 1){
           return x;
       }
       if (n == -1){
           return 1/x;
       }
       if (n % 2 == 0){
           double r;
           r = myPow(x, n /2);
           return r * r;
       }else {
           double r;
           //如果求余不为0，则要在下层平方的基础上*本层
           r = myPow(x, n / 2);
           //如果本层n<0.则将本层置为1/x
           if (n < 0){
              x = 1 / x;
           }
           return r*r*x;
       }
    }

}