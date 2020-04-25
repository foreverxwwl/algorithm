package DataStructure.Tree;

/**
 * @outhor li
 * @create 2020-03-18 15:06
 * 线索化二叉树
 * 线索化二叉树就是将普通二叉树的叶子节点中没有用到的左右节点指针利用起来
 * 左指针指向前驱节点，右指针指向后继节点
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        ThreadNode root =  new ThreadNode(1, 1);
        ThreadNode node2 = new ThreadNode(3, 3);
        ThreadNode node3 = new ThreadNode(6, 6);
        ThreadNode node4 = new ThreadNode(8, 8);
        ThreadNode node5 = new ThreadNode(10,10);
        ThreadNode node6 = new ThreadNode(14,14);
        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.left=node2;
        root.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes(root);

        //测试: 以10号节点测试
        ThreadNode leftNode = node5.left;
        ThreadNode rightNode = node5.right;
        System.out.println("10号结点的前驱结点是 ="  + leftNode.data); //3
        System.out.println("10号结点的后继结点是="  + rightNode.data); //1

        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

/**
 * 线索化二叉树
 */
class ThreadedBinaryTree{
    ThreadNode root;//根节点
    ThreadNode pre = null;//待处理节点前一个节点


    public ThreadedBinaryTree(ThreadNode root){
        this.root = root;
    }

    /**
     * 中序线索化二叉树
     * @param node 待线索化节点
     */
    public void threadedNodes(ThreadNode node){
        if (node == null){
            return;
        }

        //向左递归遍历线索化
        threadedNodes(node.left);

        //处理当前节点的前驱节点(第一个节点前驱节点为空但是其type要是1)
        if (node.left == null){
            //让该节点指向前驱节点
            node.left = pre;
            //让该节点左指针类型改变
            node.leftType = 1;
        }

        //处理后继节点  处理后继节点时，是在一下层递归中，
        //当前节点为刚才要处理节点的后继节点，pre指向刚才要处理的节点则pre的后继节点是新的node
        if (pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        //让pre节点前进
        pre = node;

        //向右递归遍历线索化
        threadedNodes(node.right);

    }

    /**
     * 中序遍历线索化二叉树
     * 因为是中序遍历，则先遍历左子树，直到一个被线索话的节点（左子树类型为1）
     * 然后打印此节点
     * 如果此节点有后继节点（右子树类型为1）进入其后继节点并打印
     * 进入这个节点的右子点
     * 循环直到进入最后一个节点 其后继节点为空，则退出循环
     */
    public void threadedList(){
        //定义一个遍历的节点
        ThreadNode node = root;
        while (node != null){
            //因为是中序遍历，则先遍历左子树，直到一个被线索话的节点（左子树类型为1）
            while (node.leftType != 1){
                node = node.left;
            }
            //打当前节点
            System.out.println(node.data);
            //如果此节点有后继节点（右子树类型为1）进入其后继节点并打印
            if (node.rightType == 1){
                node = node.right;
                System.out.println(node.data);
            }
            //进入这个节点的右子点
            node = node.right;
        }
    }
}

class ThreadNode{
    int id;
    int data;
    ThreadNode left;
    ThreadNode right;
    //左右子树的类型
    int leftType;//如果是1 则表示他的前驱节点
    int rightType;//如果是1 则表示他的后继节点

    public ThreadNode(int id, int data) {
        this.id = id;
        this.data = data;
    }
}