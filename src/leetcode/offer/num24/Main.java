package leetcode.offer.num24;

/**
 * @outhor li
 * @create 2020-05-06 22:22
 * 反转链表
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode reverseList(ListNode head) {

        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        //指向当前节点
        ListNode node = head;
        //指向下一个节点
        ListNode pre = head.next;
        //中间记录节点
        ListNode temp;
        //将头结点下一个节点置空变为尾节点
        node.next = null;

        while (pre.next != null){
            temp = pre.next;
            pre.next = node;
            node = pre;
            pre = temp;
        }
        pre.next = node;
        return pre;
    }
}