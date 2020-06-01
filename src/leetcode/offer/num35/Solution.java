package leetcode.offer.num35;

/**
 * @outhor li
 * @create 2020-05-30 17:42
 * 35. 复杂链表的复制
 */
public class Solution {
//    public Node copyRandomList(Node head) {
//        Node copyList = null;
//        Node now = null;
//        Node pre = null;
//        //首先建立新链表的头节点
//        if (head != null){
//            //建立头节点
//            copyList = new Node(head.val);
//            now = copyList.next;
//            pre = copyList;
//        }else {
//            //如果头节点为空则直接返回
//            return copyList;
//        }
//        while (head.next != null) {
//            head = head.next;
//            now =
//
//        }
//        return copyList;
//    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
