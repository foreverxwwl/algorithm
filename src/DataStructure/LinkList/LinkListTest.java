package DataStructure.LinkList;

import java.util.Stack;

/**
 * @outhor li
 * @create 2019-11-02 18:39
 * 一个Node类表示一个节点，在实现时定义一个头节点为0号
 */
public class LinkListTest {
    public static void main(String[] args) {
        Node headNode = new Node();
        LinkList linkList = new LinkList(headNode);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        linkList.add(node5);
        linkList.add(node1);
        linkList.add(node3);
        linkList.add(node4);
        linkList.add(node2);
        linkList.list();
        reversePrint(linkList.headNode);
        reversetList(linkList.headNode);
        linkList.list();
    }

    //链表反转
    public static void reversetList(Node head){
        //判断如果当前链表为空，或只有一个节点则直接返回
        if (head.next == null||head.next.next==null){
            return;
        }

        //指向原链表待插入节点
        Node temp = head.next;
        //指向temp节点的下一个节点，用于记忆下一个待插入节点
        Node next = null;
        //倒序以后的链表
        Node reversHead = new Node(0);
        while (temp != null){
            next = temp.next;
            temp.next = reversHead.next;
            reversHead.next = temp;
            temp = next;
        }
        head.next = reversHead.next;
    }

    //逆序输出
    public static void reversePrint(Node head){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Node temp = head.next;
        //将节点压栈
        while (temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中节点打印
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

}

//链表类
class LinkList{
    //定义一个头节点
    public Node headNode = new Node(0);
    //定义一个可移动的指针
    Node temp = new Node();

    public LinkList() {
    }
    public LinkList(Node headNode) {
        this.headNode = headNode;
    }

    //添加节点方法
    public void add(Node node){
        temp = headNode;
        //遍历让temp指向节点尾部
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }
    //有序添加节点方法（升序）
    public void addByOrder(Node node){
        temp = headNode;
        Boolean flag = false;
        //找到待插入点
        while (true){
            if (temp.next==null){
                //找到最后
                break;
            }
            if (temp.next.data > node.data){
                //找到中间合适位置
                break;
            }
            if (temp.next.data == node.data){
                //有与插入节点数值相同饿节点 报错
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("有与插入节点数值相同饿节点 报错");
            return;
        }else {
            //将节点加入
            node.next = temp.next;
            temp.next = node;
        }
    }

    //遍历输出
    public void list(){
        temp = headNode;
        //查看是否有节点
        if (headNode.next != null){
            temp=temp.next;
            while (temp != null){
                System.out.println(temp);
                temp = temp.next;
            }
        }else {
            System.out.println("链表为空");
        }
    }
}

//节点类
class Node{
    //节点属性
    public int data;
    public Node next;
    //无参构造
    public Node(){
    }
    //节点构造方法
    public Node(int data) {
        this.data = data;
    }
    //节点toString方法
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
