package Algorithm;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-05 16:53
 * 大数相加
 */
public class LargeAdd {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str1 = input.nextLine();
        String str2 = input.nextLine();
        System.out.println(largeAdd(str1, str2));
    }

    public static String largeAdd(String str1, String str2){
        //存放结果
        StringBuffer result = new StringBuffer();
        //1.将字符串反转
        str1 = new StringBuffer(str1).reverse().toString();
        str2 = new StringBuffer(str2).reverse().toString();
        //存放结果长度
        int lResult = str1.length() > str2.length() ? str1.length() : str2.length();
        // 将短的字符串补成和长字符串一样的长度
        //这一步要有，否则后面取单个字符会报系统越界异常
        if (str1.length() < str2.length()) {
            for (int x = str1.length(); x < str2.length(); x++) {
                str1 += "0";
            }
        } else if (str1.length() > str2.length()) {
            for (int x = str2.length(); x < str1.length(); x++) {
                str2 += "0";
            }

        }
        //2.从低位逐一相加
        boolean overFlow = false;//判断最高位是否溢出
        int nOver = 0; // 进位数量
        for (int i = 0; i < lResult; i++){
            //将相应位取出
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            //相应位相加
            int sum = Integer.parseInt(String.valueOf(c1)) + Integer.parseInt(String.valueOf(c2)) + nOver;
            //判断是否有进位
            if (sum >= 10){
                //判断进位是否为最高位
                if (i == lResult - 1){
                    overFlow = true;
                }
                //获取进位
                nOver = sum / 10;
                sum = sum % 10;
                result.append(sum);
            }else {
                nOver = 0;
                result.append(sum);
            }
        }

        if (overFlow){
            result.append(1);
        }
        //3.将相加结果反转，返回
        return result.reverse().toString();

    }
}
