package leetcode.offer.num28;


/**
 * @outhor li
 * @create 2020-05-19 22:54
 * 对称的二叉树
 */
public class Main {
}
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return symmetricCompare(root.left, root.right);
    }

    public boolean symmetricCompare(TreeNode lift, TreeNode right){
        //如果都为空，则结束递归返回真，如果只有一方为空结束递归返回假
        if (lift == null || right == null){
            return lift == null && right == null;
        }

        //递归判断 1.当前节点相同。2.左子树的左节点与右子树的右节点相同 3.左子树的右节点和右子树的左节点相同
        return lift.val == right.val && symmetricCompare(lift.left,right.right) && symmetricCompare(lift.right, right.left);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}