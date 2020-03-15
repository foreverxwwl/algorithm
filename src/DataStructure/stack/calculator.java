package DataStructure.stack;

/**
 * @outhor li
 * @create 2020-03-14 16:37
 * 计算表达式
 */
public class calculator {
    public static void main(String[] args) {
        //定义表达式
        Expression expression = new Expression("3*10-8*3");
        //定义两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的变量
        int num1;
        int num2;
        int oper;
        int res;
        int index = 0;
        char ch = ' ';
        String keepNum = "";
        while (true) {
            //依次获取每一个字符
            ch = expression.value.substring(index, index + 1).charAt(0);
            if (expression.isOper(ch)) {//是一个运算字符
                if (operStack.isEmpty()) {//有无其他运算符
                    operStack.push(ch);
                } else {//如果有其他运算符
                    //小于等于前面的运算符，则对前面运算符进行运算
                    if (expression.priority(ch) <= expression.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        //进行运算
                        res = expression.cal(num1, num2, oper);
                        //运算结果放入数字栈
                        numStack.push(res);
                        //当前符号入栈
                        operStack.push(ch);
                    } else {//如果运算级高，直接入栈
                        operStack.push(ch);
                    }
                }
            } else {//不是一个运算字符
                keepNum += ch;
                //如果是最后一位
                if (index == expression.value.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //如果下一个字符是运算字符，说明该数字只有一位则直接入栈
                    if (expression.isOper(expression.value.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index==expression.value.length()){
                break;
            }
        }
        //表达式扫描完毕,将剩余的运算进行
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            //进行运算
            res = expression.cal(num1, num2, oper);
            //运算结果放入数字栈
            numStack.push(res);
        }
        //最后输出结果
        System.out.println(expression.value+"="+numStack.peek());
    }
}


class Expression{
    public String value;

    public Expression(String expression) {
        value = expression;
    }

    //定义算数字符的优先级
    public int priority(int oper){
        if (oper == '+' || oper == '-'){
            return 0;
        }else if (oper=='*'||oper=='/'){
            return 1;
        }else {
            return -1;
        }
    }
    //判断是不是一个算符
    public Boolean isOper(int oper){
        return oper == '+'||oper=='-'||oper=='*'||oper=='/';
    }

    //基本运算
    public int cal(int num1, int num2, int oper){
        int res = 0;
        switch (oper){
            case '-':
                res = num2 - num1;
                break;

            case '+':
                res = num2 + num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
                default:
                    break;
        }
        return res;
    }
}

class ArrayStack2{
    int maxSize;
    int[] stack;
    int top = -1;

    public ArrayStack2(int maxSize){
        top = -1;
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public Boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public Boolean isEmpty(){
        return top == -1;
    }
    //入栈push
    public void push(int valus){
        //判断是否栈满
        if (isFull()){
            throw new RuntimeException("栈已满");
        }
        //入栈
        top++;
        stack[top] = valus;
    }
    //出栈pop
    public int pop(){
        //判断是否栈空
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        //出栈
        int value = stack[top];
        top--;
        return value;
    }
    //看栈顶
    public int peek(){
        return stack[top];
    }
    //遍历栈
    public void list(){
        //判断是否栈空
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >= 0; i--){
            System.out.println(stack[i]);
        }
    }

}