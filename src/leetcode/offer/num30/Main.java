package leetcode.offer.num30;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @outhor li
 * @create 2020-05-30 10:05
 * 包含main函数的栈
 */
public class Main {

}

class MinStack {
    Stack stack;
    Stack minNum;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack();
        this.minNum = new Stack();
        minNum.push(Integer.MAX_VALUE); //初始化一个最大值，方便第一次判断
    }

    public void push(int x) {
        stack.push(x);
        //当插入元素小于当前最小元素时，入最小元素栈。
        //应为原数据结构是栈，所以当大于时，栈低总会有小的元素在所以不用判断放入
        if (minNum == null || x <= (int)minNum.peek()){
            minNum.push(x);
        }
    }

    public void pop() {
        Integer pop = (Integer) stack.pop();
        if (pop.equals(minNum.peek())){
            minNum.pop();
        }
    }

    public int top() {
        return (int)stack.peek();
    }

    public int min() {
        return (int)minNum.peek();
    }
}