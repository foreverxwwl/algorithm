package DataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @outhor li
 * @create 2020-03-15 17:21
 * 中缀表达式转为后缀表达式并求值
 */
public class PolandNotation {
    public static void main(String[] args) {
        String suffixExperssion = "4*5-8+60+8/2";

//        String suffix = "4 5 * 8 - 60 + 8 2 / +";
//        List<String> listString = getListString(suffix);
//        System.out.println(listString);
//        System.out.println(calculate(listString));

        String expression = "1+((2*3+4)*4)-5";//注意表达式
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]

        System.out.printf("expression=%d", calculate(suffixExpreesionList)); // ?
    }

    /**
     * 得到中缀表达式对应的后缀表达式
     * @param ls 中缀表达式 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
     * @return 后缀表达式 ArrayList [1,2,3,+,4,*,+,5,–]
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls){
        //1.初始化两个栈
        Stack<String> stack = new Stack<String>();//1.1存放运算符
        List<String> list = new ArrayList<String>();//1.2存放结果
        //2.从左往右遍历
        for (String item : ls){
            //3.如果是数字，则直接加入结果
            if (item.matches("\\d+")){
                list.add(item);
            }else if (!item.equals(")")&&!item.equals("(")){ //4.如果是运算符
                if (stack.empty()) {//4.1如果符号栈为空，直接压栈
                    stack.push(item);
                }else if (stack.peek().equals("(")){//4.1如果栈顶为“(”压栈
                    stack.push(item);
                }else if (getVlaue(item)>getVlaue(stack.peek())){//4.2如果优先级比栈顶高压栈
                    stack.push(item);
                }else {
                    //4.3如果优先级小于等于栈顶，则将栈顶出栈并加入结果集，并和新的栈顶比较
                    while (!stack.empty() && getVlaue(item)<=getVlaue(stack.peek())){
                        String value = stack.pop();
                        list.add(value);
                        if (stack.empty()){
                            break;
                        }
                        if (stack.peek().equals("(")){
                            break;
                        }
                    }
                    stack.push(item);
                }
            }else if (item.equals(")") || item.equals("(")){//5.如果是()
                if (item.equals("(")){//5.1 ( 直接入栈
                    stack.push(item);
                }else {
                    //5.2 如果是 ) 依次弹出栈顶符号加入到结果集 知道找到(
                    while (!stack.empty() && !stack.peek().equals("(")){
                        if (stack.empty()) {
                            System.out.println("表达式语法有问题");
                        }else {
                            list.add(stack.pop());
                        }
                    }
                    stack.pop();//弹出(
                }
            }
        }
        while (!stack.empty()){
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 将字符串转化为List集合
     * @param s 字符串
     * @return list集合
     */
    public static List<String> toInfixExpressionList(String s){
        int index = 0;//用于遍历字符串
        String num;//用于拼接多位数
        List<String> result = new ArrayList<String>();
        while (true){
            //如果是运算符，则直接加入集合
            if (s.charAt(index) < 48 || s.charAt(index) > 57){
                result.add("" + s.charAt(index));
                index++;
            }else {//如果是数字则要拼接
                num = "";//首先置空
                while (index<s.length()&&s.charAt(index)>=48&&s.charAt(index)<=57){
                    num+=s.charAt(index);
                    index++;
                }
                result.add(num);
            }
            if (index==s.length()){
                break;
            }
        }
        return result;
    }

    /**
     * 对逆序计算表达式求值
     * @param ls 逆序表达式
     * @return 求值结果
     */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<String>();
        int result = 0;
        for(String item: ls) {
            //遍历集合，如果是数字则直接入栈
            if (item.matches("\\d+")){
                stack.push(item);
            }else {//如果是运算符则弹出两个数进行运算
                int number1 = Integer.parseInt(stack.pop());
                int number2 = Integer.parseInt(stack.pop());
                switch (item){
                    case "+":
                        result = number2 + number1;
                        break;
                    case "-":
                        result = number2 - number1;
                        break;
                    case "*":
                        result = number2 * number1;
                        break;
                    case "/":
                        result = number2 / number1;
                        break;
                    default:
                        System.out.println("不存在该运算符" + item);
                        break;
                }
                stack.push("" + result);
                result = 0;
            }
        }
        return Integer.parseInt(stack.pop());

    }

    /**
     * 返回运算符对应的优先级
     * @param operation
     * @return
     */
    public static int getVlaue(String operation){
        int result = 0;
        switch (operation) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }

        return result;
    }

    /**
     * 将按空格分隔的字符串转化为list集合
     * @param suffixExpression 按照空格分隔的字符串
     * @return 对应list集合
     */
    public static List<String> getListString(String suffixExpression){
        //按空格分隔
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele: split){
            list.add(ele);
        }
        return list;
    }
}
