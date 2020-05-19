package leetcode.offer.num26;

import java.util.ArrayList;
import java.util.List;

/**
 * @outhor li
 * @create 2020-05-13 22:36
 * 26. 数的子结构
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        TreeNode node10 = new TreeNode(4);
        TreeNode node11 = new TreeNode(8);
        TreeNode node12 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;

        node10.left = node11;
        node10.right = node12;
        System.out.println(solution.isSubStructure(node1, node4));
    }
}

class Solution {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B ==null)
            return false;
        return hasSubtree(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean hasSubtree(TreeNode a, TreeNode b){
        if (b == null){
            return true;
        }
        if (a == null){
            return false;
        }
        if (a.val != b.val){
            return false;
        }

        return hasSubtree(a.left,b.left) && hasSubtree(a.right, b.right);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

}
