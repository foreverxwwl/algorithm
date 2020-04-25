package DataStructure.haffmanCode;

import java.util.*;

/**
 * @outhor li
 * @create 2020-03-19 0:24
 * 哈夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); //40

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//
//        Node root = createHuffmanTree(nodes);
//        preOrder(root);
//
//        Map<Byte, String> codes = getCodes(root);
//        System.out.println(codes);

        //进行哈夫曼编码
        List<byte[]> list = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(list.get(0)));
        System.out.println(list.get(0).length);
        System.out.println(new String(list.get(1)));

    }

    //将赫夫曼编码表存放在 Map<Byte,String> 形式
    //生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 编写一个方法整合下面方法，传入字符串直接转为编码返回
     * @param bytes 要编码的字符串
     * @return 编码好的字符数组
     */
    private static List<byte[]> huffmanZip(byte[] bytes){
        //调用下面的1234步
        List<Node> nodes = getNodes(bytes);
        Node root = createHuffmanTree(nodes);
        root.preOrder();
        Map<Byte, String> codes = getCodes(root);
        System.out.println(codes);
        byte[] zip = zip(bytes, codes);
        byte[] decode = decode(huffmanCodes, zip);
        List<byte[]> list = new ArrayList<byte[]>();
        System.out.println(Arrays.toString(zip));
        System.out.println(codes);
        list.add(zip);
        list.add(decode);
        return list;
    }

    /**
     * 一、字符数组-->哈夫曼树节点集合
     * 接收字节数组，将其中的字母极其出现的次数统计出来，并创建成相应的节点存入List集合中
     * @param bytes 传入的字符数组
     * @return 哈夫曼树节点的集合  节点中 data=数组中出现的字母 weight 该字母出现的次数
     */
    private static List<Node> getNodes(byte[] bytes){
        //创建一个List集合
        List<Node> nodes = new ArrayList<>();
        //遍历数组 ，统计每个字母出现的次数,放入map集合汇总
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for(byte b: bytes){
            Integer count = counts.get(b);
            if (count == null){//如果没有该字母 则现在为1
                counts.put(b, 1);
            }else {//如果有 则加一
                counts.put(b, count+1);
            }
        }
        //把每一个键值对抓成一个Node，并加入到集合
        for (Map.Entry<Byte, Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 二、哈夫曼树节点集合-->哈夫曼树
     * 通过list集合创建哈夫曼树
     * @param nodes 节点集合
     * @return 哈夫曼树的头结点
     */
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);
            //取出两个最小的分别为左右子节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一个新的二叉树
            Node parent = new Node(null, leftNode.weight+rightNode.weight);
            parent.right = rightNode;
            parent.left = leftNode;

            //将两个节点删去加入新的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }


    /**
     * 三、哈夫曼树-->哈夫曼编码的Map集合（字母和其相应的编码）
     * 利用创建好的哈夫曼树来进行编码存放在map集合huffmanCodes中
     * @param node 待编码的树
     * @param code 路径 左 0 ，右 1
     * @param stringBuilder 用于拼接路径编码
     */

    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        //创建一个临时变量
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //把上次遍历的路径加入
        stringBuilder1.append(code);
        if (node != null){
            if (node.data == null){//说明是中间节点 继续遍历
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            }else { //如果是根节点 则加入,此时一个字面编码完毕
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }
    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if(root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 四、哈夫曼编码集合-->压缩后的数组（哈夫曼编码后的数组8位一组存储）
     * String content = "i like like like java do you like a java"
     * "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * 将上面的01字串8位一组按照反码转为byte 如前8位 10101000->huffmanCodeBytes[0] = -88
     * @param bytes 要压缩的字符串
     * @param huffmanCodes 哈夫曼编码表
     * @return 压缩后的数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //1.利用huffmanCodes将bytes编程对应字符串
        StringBuilder stringBuilder = new StringBuilder();

        for (Byte b: bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //2.将 "1010100010111111110..." 转成 byte[] 8位
        //确定编码后长度
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8 + 1;
        }
        //创建编码后的数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8){
            String strByte;
            if (i+8>stringBuilder.length()){//如果剩余位数不足8位则剩余几位用几位
                strByte = stringBuilder.substring(i);
            }else {
                //8位一组放入一个字符串
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将字符串转为byte放入
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte);
        }
        return huffmanCodeBytes;
    }

    /**
     * 将哈夫曼编码解压为原来的数组
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 哈夫曼编码出的字节数组
     * @return 解码后的字符串
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        //1.将字节数组转化为二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length;i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i==huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        System.out.println("ddd"+stringBuilder);
        //2.通过huffmanCodes将二进制字符串匹配出原字符
        //应为huffmanCodes的key是字符，value是二进制码，我们现在要通过二进制码找字符 则将其反转
        Map<String, Byte> map = new HashMap<String, Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //应为哈夫曼编码是可变长编码 所以我们要从第一个二进制位匹配 每次加一 知道成功
        List<Byte> list = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); i++){
            Boolean flag=true;
            int count = 0;
            Byte b = null;
            while (flag){
                //i 不动，让count移动，指定匹配到一个字符
                String key = stringBuilder.substring(i, i+count);
                b = map.get(key);
                if (b!= null){
                    flag = false;
                }else {
                    count++;
                }
            }
            //i直接移动到count位置
            i+=count;
            list.add(b);
        }
        //3.返回byte数组返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将字节数转化为二进制字符串
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @param b 传入的 byte
     * @return 对应的二进制字符串
     */

    private static String byteToBitString(boolean flag, byte b){
        int temp = b;
        //如果是正数则要补高位
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的事temp对应的二进制补码
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }




    //前序遍历的方法
    private static void preOrder(Node root) {
        if(root != null) {
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }



}

class Node implements Comparable<Node>{
    Byte data;//存放节点本身数据
    int weight; //存放节点权重
    Node left;  //存放节点左子节点
    Node right; //存放节点右子节点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历方法
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
