package leetcode.offer.num36;

import javax.swing.tree.TreeNode;

/**
 * @outhor li
 * @create 2020-06-01 9:38
 * 36. 二叉搜索树与双向链表
 */
public class Solution {
    Node pre;//指向上一个节点的指针
    Node head, tail; // 指向头的指针 和 指向尾的指针
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        node1.left=node2;
        node1.right=node3;
        node3.left = node4;
        node3.right = node5;
//        treeToDoublyList(node1);
    }

    public Node treeToDoublyList(Node root) {
        if (root == null){
            return null;
        }
        process(root);
        //将头和尾相连
        head.left = tail;
        tail.right = head;
        return root;
    }

    public void process(Node node){
        if (node == null)
            return;
        process(node.left);
        //指向前一个节点
        node.left = pre;
        //如果是第一个元素
        if (pre == null){
            //将head赋值
            head = node;
        }else {
            //指向后一个节点
            pre.right = node;
        }
        //修改当前节点
        pre = node;
        tail = node;
        process(node.right);
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}