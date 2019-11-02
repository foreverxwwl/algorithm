package DataStructure.LinkList;

/**
 * @outhor li
 * @create 2019-11-02 18:39
 * java编写单链表
 */
public class LinkListTest {
    public static void main(String[] args) {
        Node headNode = new Node();
        LinkList linkList = new LinkList(headNode);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        linkList.add(node1);
        linkList.add(node2);
        linkList.add(node3);
        linkList.list();
    }
}

//节点使用类
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
    //遍历输出
    public void list(){
        temp = headNode;
        //查看是否有节点
        if (headNode.next != null){
            while (temp.next != null){
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
