package DataStructure.Tree;

/**
 * @outhor li
 * @create 2020-03-17 21:00
 * 二叉树的基本操作
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        Node root = new Node(1, 1);
        Node node2 = new Node(2,2);
        Node node3 = new Node(3,3);
        Node node4 = new Node(4,4);
        Node node5 = new Node(5,5);

        root.lift = node2;
        root.right = node3;
        node3.right = node4;
        node3.lift = node5;
        binaryTree.setRoot(root);

//        System.out.println("前序遍历"); // 1,2,3,5,4
//		binaryTree.preOrder();
//
//		System.out.println("中序遍历");
//		binaryTree.infixOrder(); // 2,1,5,3,4
//
//		System.out.println("后序遍历");
//		binaryTree.postOrder(); // 2,5,4,3,1

        //前序遍历
        //前序遍历的次数 ：4
//		System.out.println("前序遍历方式~~~");
//		Node resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.id, resNode.data);
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //中序遍历查找
        //中序遍历3次
//		System.out.println("中序遍历方式~~~");
//		Node resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.id, resNode.data);
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //后序遍历查找
        //后序遍历查找的次数  2次
//		System.out.println("后序遍历方式~~~");
//		Node resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.id, resNode.data);
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}


        //测试一把删除结点

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); //  1,2,3,5,4
        binaryTree.delNode(5);
        //binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4


    }

}

//二叉树类
class BinaryTree{
    //定义头结点
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 遍历查找
     */
    //前序遍历查找
    public Node preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序遍历查找
    public Node infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历查找
    public Node postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
    //删除结点
    public void delNode(int no) {
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.id == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }
}

class Node{
    int id;
    int data;
    Node lift;//左子节点
    Node right;//又子节点

    public Node(int id, int data){
        this.data = data;
        this.id = id;
    }

    /**
     * 所谓前序中序后序，就是根节点在什么地方遍历，如果在前面则是前序以此类推
     */

    /**
     * 前序遍历输出
     */
    public void preOrder(){
        System.out.println(this.data);
        //向左遍历子树
        if (this.lift != null){
            this.lift.preOrder();
        }
        //向右遍历子树
        if (this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * 前序遍历输出
     */
    public void infixOrder(){

        //向左遍历子树
        if (this.lift != null){
            this.lift.infixOrder();
        }
        System.out.println(this.data);
        //向右遍历子树
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 前序遍历输出
     */
    public void postOrder(){
        //向左遍历子树
        if (this.lift != null){
            this.lift.postOrder();
        }

        //向右遍历子树
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this.data);
    }

    /**
     * 前序遍历查找
     * @param id
     * @return
     */
    public Node preOrderSearch(int id){
        if(this.id == id){
            return this;
        }
        Node resultNode = null;
        //向左遍历子树
        if (this.lift != null){
            resultNode = this.lift.preOrderSearch(id);
        }//如果有结果 说明找到了待查找点直接返回 不用再找
        if (resultNode != null){
            return resultNode;
        }

        //向右遍历子树
        if (this.right != null){
            resultNode = this.right.preOrderSearch(id);
        }

        //此时resultNode就是null
        return resultNode;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return
     */
    public Node infixOrderSearch(int id){
        Node resultNode = null;
        //向左遍历子树
        if (this.lift != null){
            resultNode = this.lift.infixOrderSearch(id);
        }//如果有结果 说明找到了待查找点直接返回 不用再找
        if (resultNode != null){
            return resultNode;
        }

        if(this.id == id){
            return this;
        }

        //向右遍历子树
        if (this.right != null){
            resultNode = this.right.infixOrderSearch(id);
        }

        //此时resultNode就是null
        return resultNode;
    }

    /**
     * 后序遍历查找
     * @param id
     * @return
     */
    public Node postOrderSearch(int id){
        Node resultNode = null;
        //向左遍历子树
        if (this.lift != null){
            resultNode = this.lift.postOrderSearch(id);
        }//如果有结果 说明找到了待查找点直接返回 不用再找
        if (resultNode != null){
            return resultNode;
        }

        //向右遍历子树
        if (this.right != null){
            resultNode = this.right.postOrderSearch(id);
        }
        if (resultNode != null){
            return resultNode;
        }

        if(this.id == id){
            return this;
        }

        //此时resultNode就是null
        return resultNode;
    }

    /**
     * 删除某个节点，如果是叶子节点直接删除，如果不是则删除这个节点和其子节点
     * @param id 待删除节点id
     */
    public void delNode(int id){

        if (this.lift != null && this.lift.id == id){
            this.lift = null;
            return;
        }
        if (this.right != null && this.right.id == id){
            this.right = null;
            return;
        }

        if (this.lift != null){
            this.lift.delNode(id);
        }
        if (this.right != null){
            this.right.delNode(id);
        }

    }
}
