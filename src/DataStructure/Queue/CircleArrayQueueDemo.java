package DataStructure.Queue;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2020-03-12 16:30
 * 用数组表示一个环形队列
 * 一个环形队列，有一个指向对列头的front，一个指向队列最后一个元素的后一个元素front
 * 在队列中预留一个空位，所以实际大小比初始化大小小1
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArray queue = new CircleArray(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

class CircleArray{
    //表示数组最大容量 初始值为0
    private int maxSize;
    //表示队列第一个元素的位置  初始值为0
    private int front;
    //表示队列最后一个元素后一个位置  初始值为0
    private int rear;
    //保存数据
    private int[] arr;

    //构造队列  maxSize是实际大小+1  因为要留出一个空位置
    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断是否为满
    public Boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断是否为空
    public Boolean isEmpty(){
        return rear == front;
    }

    //出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        //1.front指向的就是第一个元素 所以直接取出
        //2.front++后移
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    //入队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        //1.应为rear指向最后一个元素后面的位置 则可直接加入到rear
        //2.rear后移
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    //显示队列所有数
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        //遍历
        for (int i = front; i<front+size();i++){
            System.out.println("arr["+i%maxSize+"] = "+arr[i%maxSize]);
        }
    }

    //当前队列大小
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        return arr[front];
    }
}
