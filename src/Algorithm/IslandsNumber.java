package Algorithm;

import java.util.Scanner;

/**
 * @outhor li
 * @create 2019-11-03 14:58
 * 用一个矩阵代表地图，1代表岛屿，岛屿见上下左右四面可以相连为一个
 * 求岛屿数量
 */
public class IslandsNumber {
    //地图数组
    public static int[][] map = new int[100][100];
    //遍历岛屿函数
    public static void number(int i, int j){
        if (map[i][j] == 1){
            //当此节点是岛屿时，先将其置为2，再看其上下左右是否有岛屿
            map[i][j] = 2;
            number(i + 1, j);
            number(i, j + 1);
            number(i - 1, j);
            number(i, j - 1);
        }else {
            return;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int islands = 0;
        //地图的长和宽
        int a = input.nextInt();
        int b = input.nextInt();
        //输入地图
        for (int i = 1; i <= a; i++){
            for (int j = 1; j <= b; j++){
                map[i][j] = input.nextInt();
            }
        }
        //从第一个节点开始遍历地图，如果是1，那么说明有岛屿，计数器+1
        //并且进入递归函数，将次岛屿遍历一遍置为2，说明次岛屿已经计数
        for (int i = 1; i <= a; i++){
            for (int j = 1; j <= b; j++){
                if (map[i][j] == 1){
                    islands++;
                    number(i, j);
                }
            }
        }

        System.out.println(islands);

    }
}
