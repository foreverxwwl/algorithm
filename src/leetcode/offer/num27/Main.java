package leetcode.offer.num27;


/**
 * @outhor li
 * @create 2020-05-07 23:24
 * 二叉树镜像
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}