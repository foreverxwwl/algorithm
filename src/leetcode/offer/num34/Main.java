package leetcode.offer.num34;

import java.util.ArrayList;
import java.util.List;


/**
 * @outhor li
 * @create 2020-05-30 16:49
 * 34. 二叉树中和为某一值的路径
 */
public class Main {
}
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null){
            return res;
        }
        List<Integer> list = new ArrayList<>();
        find(root, 0, list, sum);
        return res;
    }

    /**
     * 寻找路径
     * @param node 当前节点
     * @param parent 父节点值
     * @param list 当前节点经过的路径
     * @param sum 目标总和
     */
    public void find(TreeNode node,int parent, List<Integer> list, int sum){
        int nodeValue = node.val;
        List<Integer> list1 = new ArrayList<>(list);
        list1.add(nodeValue);
        if (node.left != null){
            find(node.left, nodeValue + parent, list1, sum);
        }
        if (node.right != null){
            find(node.right, nodeValue + parent, list1, sum);
        }
        if (node.left == null && node.right == null){
            if (nodeValue + parent == sum){
                res.add(list);
            }
        }
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
