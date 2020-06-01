package leetcode.offer.num32_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @outhor li
 * @create 2020-05-30 14:50
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
        List<List<Integer>> lists = solution.levelOrder(node1);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }

}

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return res;
        }
        find(root,0);
        int index = 0;
//        for (List<Integer> re : res) {
//            if (index%2!=0){
//                Collections.reverse(re);
//            }
//            index++;
//        }
        return res;
    }
    public void find(TreeNode node, int h){
        if (res.size()-1 < h){
            res.add(h,new ArrayList<Integer>());
        }
        res.get(h).add(node.val);
        if (node.left != null){
            find(node.left, h + 1);
        }
        if (node.right != null){
            find(node.right, h + 1 );
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}