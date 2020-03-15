package DataStructure.stack;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2020-03-14 12:15
 * 用一个数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
//测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");

    }

}

class ArrayStack{
    int maxSize;
    int[] stack;
    int top = -1;

    public ArrayStack(int maxSize){
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
