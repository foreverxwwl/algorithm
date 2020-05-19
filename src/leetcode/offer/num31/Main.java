package leetcode.offer.num31;

import java.util.Stack;

/**
 * @outhor li
 * @create 2020-05-17 22:38
 * 栈的压入弹出序列
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int len = popped.length;
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.empty() && stack.peek() == popped[index] && index <= len){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}