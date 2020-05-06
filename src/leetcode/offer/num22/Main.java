package leetcode.offer.num22;

import java.util.List;

/**
 * @outhor li
 * @create 2020-05-05 23:11
 * 链表中倒数第k个节点
 */
public class Main {


}

class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        //慢指针，到时会指向倒数第k个节点
        ListNode node = head;
        //快指针，到时会指向最后一个节点
        ListNode pre = head;

        //首先让快慢指针间隔k个节点
        for (int i = 1; i < k; i++) {
            if (node.next == null)
                return head;
            node = node.next;
        }

        //让快慢指针一起移动直到快指针指向结尾
        while (node.next != null){
            node = node.next;
            pre = pre.next;
        }

        return pre;

    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}