package leetcode.offer.num18;



/**
 * @outhor li
 * @create 2020-05-01 1:13
 * 删除链表节点
 */
public class Main {
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.next == null){
            return null;
        }
        //如果删除的是头结点，则直接将头结点后移删除
        if (head.val == val){
            head = head.next;
            return head;
        }
        ListNode p = head;
        ListNode t = head.next;
        while (true){
            if (t.val == val){
                break;
            }
            t = t.next;
            p = p.next;
        }
        //如果是尾节
        if (t.next == null){
            p.next = null;
        }else {
            p.next = t.next;
        }
        return head;
    }
}