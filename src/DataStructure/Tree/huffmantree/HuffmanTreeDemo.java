package DataStructure.Tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @outhor li
 * @create 2020-03-18 18:43
 * 哈夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        HuffmanTree root = new HuffmanTree(arr);
        HuffmanNode huffmanTree = root.createHuffmanTree();
        huffmanTree.preOrder(huffmanTree);
    }
}

class HuffmanTree{
    //哈夫曼树的节点权值
    int[] values;
    HuffmanNode root;
    //存放携带好权值的节点，用集合方便排序
    List<HuffmanNode> huffmanNodes = new ArrayList<HuffmanNode>();

    public HuffmanTree(int[] values){
        this.values = values;
    }

    /**
     * 创建哈夫曼树
     * @return
     * 1、创建叶子节点，放入集合中
     * 2、对叶子节点根据权值从小到大排序
     * 3、从集合最开始去两个节点（最小的两个），把这两个当成左右叶子节点创建一个树，
     *    并将该数根节点（两个叶子节点权值相加）放入集合
     * 4、循环上面方法，直到最后一个节点
     */
    public HuffmanNode createHuffmanTree(){
        //更具数组创建节点并放入集合
        for(int value: values){
            huffmanNodes.add(new HuffmanNode(value));
        }

        //训环将节点加入树中
        while (huffmanNodes.size() > 1){
            //排序
            Collections.sort(huffmanNodes);
            //取出两个最小节点合成一个树
            HuffmanNode leftNode = huffmanNodes.get(0);
            HuffmanNode rightNode = huffmanNodes.get(1);
            HuffmanNode huffmanNode = new HuffmanNode(leftNode.data+rightNode.data);
            huffmanNode.left = leftNode;
            huffmanNode.right = rightNode;
            //从集合中删除那两个节点 并放入新节点
            huffmanNodes.remove(leftNode);
            huffmanNodes.remove(rightNode);
            huffmanNodes.add(huffmanNode);
        }

        root = huffmanNodes.get(0);
        return root;
    }



}

//节点类
class HuffmanNode implements Comparable<HuffmanNode>{
    int data;
    HuffmanNode right;
    HuffmanNode left;

    public HuffmanNode(int data){
        this.data = data;
    }

    //前序遍历类
    public void preOrder(HuffmanNode node){
        System.out.println(node.data);
        if (node.left != null){
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.data - o.data;
    }
}