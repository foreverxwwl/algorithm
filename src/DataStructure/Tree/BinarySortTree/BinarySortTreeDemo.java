package DataStructure.Tree.BinarySortTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @outhor li
 * @create 2020-04-25 16:39
 * 二叉排序树  比根节点小的在左子树，比根节点大的在右子树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 5, 1, 9, 2, 12,11,18,19,14,13,15};
        int delVal = 12;
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++){
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        System.out.println("___________________________");
        List<Node> nodes = binarySortTree.searchTargetNodeAndParent(delVal);
        System.out.println(nodes.get(0).value);
//        System.out.println(nodes.get(1).value);
        System.out.println("--------------------------");
        binarySortTree.delNode(delVal);
        binarySortTree.infixOrder();
    }
}

/**
 * 二叉排序树类
 */
class BinarySortTree{
    Node root;

    public Node getRoot(){
        return root;
    }

    /**
     * 插入节点方法
     * @param node 待插入节点
     */
    public void add(Node node){

        if (root == null){
            //如果根节点为空则直接插入
            root = node;
        }else {
            root.add(node);
        }
    }

    /**
     * 查找待删除节点右子树最小的节点并 删除
     * @param node 待删除节点
     * @return 最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node.right;
        while (target.left != null){
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    /**
     * 删除节点方法
     * @param value 待删除节点的值
     * 删除时统一找右子树最小的
     */
    public void delNode(int value){
        if (root == null){
            System.out.println("二叉树为空");
            return;
        }
        //1. 查找到待删除节点和其父节点
        List<Node> nodes = root.searchTargetNodeAndParent(value);
        Node targetNode = nodes.get(0);
        Node parentNode = nodes.get(1);
        //2. 没有待删除节点
        if (nodes.get(0) == null && nodes.get(1)== null){

            System.out.println("没有待删除节点");
            return;
        }
        //3. 如果我们发现当前这颗二叉排序树只有一个结点
        if(root.left == null && root.right == null) {
            root = null;
            return;
        }

        //5. 判断待删除节点类型并删除节点
        if (targetNode.left == null && targetNode.right == null){
            //类型1 删除叶子节点
            if (targetNode.value == parentNode.left.value){
                //删除的节点是父节点的左节点
                parentNode.left = null;
            }else {
                //删除的节点是父节点的右节点
                parentNode.right = null;
            }
        } else {
            if (targetNode.left != null && targetNode.right == null) {
                //类型2.1 删除的节点只有一个左子节点
                if (parentNode == null) {
                    //删除的节点是根节点
                    root = targetNode.left;
                } else if (targetNode.value == parentNode.left.value) {
                    //删除的节点是父节点的左节点
                    parentNode.left = targetNode.left;
                } else {
                    //删除的节点是父节点的右节点
                    parentNode.right = targetNode.left;
                }
            } else if (targetNode.right != null && targetNode.left == null) {
                //类型2.2 删除的节点只有一个右子节点
                if (parentNode == null) {
                    //删除的节点是根节点
                    root = targetNode.right;
                } else if (targetNode.value == parentNode.left.value) {
                    //删除的节点是父节点的左节点
                    parentNode.left = targetNode.right;
                } else {
                    //删除的节点是父节点的右节点
                    parentNode.right = targetNode.right;
                }
            } else {
                //类型3 删除的节点右两个子节点
                //从右子树中找到最小的值
                int valueMin = this.delRightTreeMin(targetNode);
                //用这个值替换待删除节点
                targetNode.value = valueMin;
            }
        }
    }


    /**
     * 中序遍历方法
     */
    public void infixOrder(){
        if (root == null){
            System.out.println("二叉树为空");
        }else {
            root.infixOrder();
        }
    }

    /**
     * 查找待删除节点和其父节点
     * @param value 待查找删除节点的值
     * @return list中第一个是带删除节点，第二个是该节点的父节点
     */
    public List<Node> searchTargetNodeAndParent(int value){
        return root.searchTargetNodeAndParent(value);
    }
}

/**
 * 二叉排序树节点类
 */
class Node{
    int value;
    Node right;
    Node left;

    //构造方法
    public Node(int value) {
        this.value = value;
    }

    /**
     * 插入节点方法
     * @param node 待插入节点
     */
    public void add(Node node){
        if(node == null) {
            return;
        }
        //1. 判断待插入节点和当前节点大小
        if (node.value < this.value){
            //小于当前节点，则在左子树递归插入
            if (this.left == null){
                //如果左子树为空则直接插入
                this.left = node;
            }else {
                //不为空则向下递归插入
                this.left.add(node);
            }
        }else {
            //2. 如果不小于当前节点则在右子树递归插入
            if (this.right == null){
                //如果右子树为空则直接插入
                this.right = node;
            }else {
                //不为空则向下递归插入
                this.right.add(node);
            }
        }
    }

    /**
     * 查找待删除节点和其父节点
     * @param value 待查找删除节点的值
     * @return list中第一个是带删除节点，第二个是该节点的父节点
     */
    List<Node> nodes = new ArrayList<Node>(2);
    public List<Node> searchTargetNodeAndParent(int value){
        Node parentNode = this;
        Node targetNode = null;

        //1.判断是否根节点
        if (this.value == value){
            //如果当前你节点的值是待删除节点，则说明待删除节点是根节点，不存在父节点
            targetNode = this;
            parentNode = null;
            nodes.add(targetNode);
            nodes.add(parentNode);
            return nodes;
        }else {//2.如果不是根节点，则查找其左右子节点是否待删除节点
            if (this.left != null) {
                if (this.left.value == value) {
                    //左子节点是待删除节点
                    targetNode = this.left;
                    nodes.add(targetNode);
                    nodes.add(parentNode);
                    return nodes;
                }
            }
            if(this.right != null) {
                if (this.right.value == value) {
                    //右子节点是待删除节点
                    targetNode = this.right;
                    nodes.add(targetNode);
                    nodes.add(parentNode);
                    return nodes;
                }
            }
            //3. 如果左右子节点都不是待查找节点则递归查找左右子树
            if (value < this.value){
                //如果小于当前节点则查找左子树
                nodes = this.left.searchTargetNodeAndParent(value);
            }else {
                //大于当前节点查找右子树
                nodes = this.right.searchTargetNodeAndParent(value);
            }
        }
        return nodes;
    }

    /**
     * 中序遍历方法
     */
    public void infixOrder(){
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this + " ");
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
}
