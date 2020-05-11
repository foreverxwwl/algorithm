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
    //递归交换，每次交换看为一个小二叉树
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return null;
        }
        //如果是叶子节点直接返回
        if (root.left == null && root.right == null){
            return root;
        }
        //root的左子树遍历后的结果
        TreeNode l = mirrorTree(root.left);
        //root右子树遍历后的结果
        TreeNode r = mirrorTree(root.right);
        //交换左右
        root.left = r;
        root.right = l;
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}