package leetcode.offer.num20;

/**
 * @outhor li
 * @create 2020-05-01 23:15
 * 表示数值的字符串
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println('e'<'0');
//        System.out.println('e'>'9');
        System.out.println(solution.isNumber("2e0"));
    }
}

class Solution {
    public boolean isNumber(String s) {
        boolean f = false;
        boolean dian = false;
        boolean e = false;
        boolean num = false;
        int index = 0;
        char temp;
        s = s.replace(" ","");
        if (s.length()==0)
            return false;
        while (index < s.length()) {
            //1.在开始位置
            if (index == 0) {
                temp = s.charAt(index);
                //如果开始位置不是数字
                if ('0' > temp || temp > '9') {
                    //第一个位置如果有+、-那么后面不能再有
                    if (temp == '+' || temp == '-') {
                        f = true;
                    } else if (temp == '.'){
                        dian = true;
                        //.后面要有数字
                        if (s.length() == 1 || s.charAt(1) > '9' || s.charAt(1) < '0'){
                            return false;
                        }
                    }else {
                        //第一个位置只能是+、—、.、数字
                        return false;
                    }
                } else {
                    //如果是数字
                    num = true;
                }
                //后面的从1开始
                if (s.length() == 1)
                    return true;
                index++;
            }
            temp = s.charAt(index);
            //2.后面的位置
            //如果不是数字
            if ('0' > temp || temp > '9') {
                //2.1如果有+，-那么错误
                if (temp == '+' || temp == '-') {
                    return false;
                }
                //2.2 如果有e,看其前面有没有数字.或者e
                if (temp == 'e' || temp == 'E') {
                    if (!num) {
                        return false;
                    }
                    if (e || dian) {
                        return false;
                    }
                    if (s.length() <= index + 1)
                        return false;
                    //如果e下一个不是数字则返回错
                    if (s.charAt(index + 1) < '0' || s.charAt(index + 1) > '9') {
                        return false;
                    }
                    e = true;
                }
                //2.3如果有.看其前面有没有数字.或者e
                if (temp == '.') {
                    if (e || dian) {
                        return false;
                    }
//                    //如果e下一个不是数字则返回错
//                    if (s.length() <= index + 1)
//                        return false;
//                    if (s.charAt(index + 1) < '9' || s.charAt(index + 1) > '9') {
//                        return false;
//                    }
                    dian = true;
                }
            }else {
                num = true;
            }
            index++;
        }
        return true;
    }
}