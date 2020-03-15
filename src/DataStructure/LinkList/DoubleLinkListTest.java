package DataStructure.LinkList;

/**
 * @outhor li
 * @create 2020-03-13 11:09
 */
public class DoubleLinkListTest {
    public static void main(String[] args) {
        DoubleNode headNode = new DoubleNode(0);
        DoubleLinkList linkList = new DoubleLinkList(headNode);
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(5);
        linkList.addByOrder(node5);
        linkList.addByOrder(node1);
        linkList.addByOrder(node3);
        linkList.addByOrder(node4);
        linkList.addByOrder(node2);
        linkList.list();
    }

}

class DoubleLinkList{
    //创建头节点
    DoubleNode head;

    public DoubleLinkList(DoubleNode head){
        this.head = head;
    }

    //添加节点
    public void add(DoubleNode node){
        //定义一个游标，找到插入位置
        DoubleNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }
    //有序添加节点
    Boolean flag = false;
    public void addByOrder(DoubleNode node){
        //定义一个游标，找到插入位置（在找到节点的后面插入）
        DoubleNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.num > node.num){
                break;
            }

            if (temp.num == node.num){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //判断是不是最后一个节点，如果是则插入方式不同
        if (temp.next==null){
            temp.next = node;
            node.pre = temp;
        }else if (flag) {
            System.out.println("序列重复");
            return;
        }else {
                temp.next.pre = node;
                node.next=temp.next;
                temp.next=node;
                node.pre=temp;
        }
    }
    //链表展示
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
        }
        DoubleNode temp = head.next;
        while (true){
            System.out.println(temp);
            if (temp.next==null){
                break;
            }
            temp = temp.next;
        }
    }

}

class DoubleNode{
    public Integer num;//数据
    public DoubleNode next;//下一个节点
    public DoubleNode pre;//上一个节点

    public DoubleNode(int num){
        this.num = num;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "num=" + num +
                '}';
    }
}