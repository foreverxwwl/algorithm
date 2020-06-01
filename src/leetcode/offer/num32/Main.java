package leetcode.offer.num32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @outhor li
 * @create 2020-05-30 13:52
 * 从上到下打印二叉树
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        node1.left=node2;
        node1.right=node3;
        node3.left = node4;
        node3.right = node5;
        int[] ints = solution.levelOrder(node1);
    }
}

class Solution {
    public int[] levelOrder(TreeNode root) {
        //存放当前层节点
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        //存放最终节点序列
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return new int[0];
        nodes.add(root);
        while (!nodes.isEmpty()){
            TreeNode poll = nodes.poll();
            res.add(poll.val);
            if (poll.left != null){
                nodes.add(poll.left);
            }
            if (poll.right != null){
                nodes.add(poll.right);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}