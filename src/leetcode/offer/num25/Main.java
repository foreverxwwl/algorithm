package leetcode.offer.num25;

/**
 * @outhor li
 * @create 2020-05-06 23:02
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null && cur2 != null){
            if (cur1.val <= cur2.val){
                temp.next = cur1;
                temp = temp.next;
                cur1 = cur1.next;
            }else {
                temp.next = cur2;
                temp = temp.next;
                cur2 = cur2.next;
            }
        }

        if (cur1 == null && cur2 != null){
            temp.next = cur2;
        }else {
            temp.next = cur1;
        }

        return head.next;
    }
}
