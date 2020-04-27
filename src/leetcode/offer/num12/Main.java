package leetcode.offer.num12;

/**
 * @outhor li
 * @create 2019-11-16 10:53
 * 矩阵中的最短路径
 */
public class Main {
    public static void main(String[] args) {
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        char[][] board = {{'A','A'}};
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";
        Solution solution = new Solution();
        boolean exist = solution.exist(board, word);
        System.out.println(exist);
    }

}

class Solution {
    public boolean exist(char[][] board, String word) {
        int x = board.length; // 行数
        int y = board[0].length; // 列数
        //如果输入矩阵为空直接返回
        if (x == 0 || y == 0)
            return false;
        //循环找到第一个匹配字符作为入口，当第一个匹配字符不能达成条件时，还可继续寻找下一个入口
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (word.charAt(0) == board[i][j]){
                    //定义一个数组，表示用过的字母防止重复
                    //这个数组必须定义在这，每次入口需要更新，否则当第一个入口不能匹配，下一个入口运行会受影响 ***
                    boolean[][] full = new boolean[x][y];
                    //递归匹配
                    if (find(board, i, j, word, 0, full))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过递归查找路径是能匹配
     * @param board 待匹配矩阵
     * @param i 现在所在行
     * @param j 现在所在列
     * @param word 待匹配字符
     * @param index 递归深度（也表示现在匹配字符串word中第几个字符）
     * @param full 足迹数组，表示是否用过当前元素
     * @return
     */
    public boolean find(char[][] board, int i, int j, String word, int index, boolean[][] full){
        if (j>=board[0].length || i>=board.length || i < 0 || j < 0){
            //如果越界则返回
            return false;
        }
        if (full[i][j]){
            //如果使用过当前元素则返回
            return false;
        }
        //如果当前矩阵元素和word中元素相同，则上下左右递归查找下一个是否匹配
        if (board[i][j] == word.charAt(index)){
            //如果查找长度和字符长度相同 说明匹配完全 ***
            if (index == word.length()-1)
                return true;
            //进入矩阵下一个元素时将当前位置标记为已用 ***
            full[i][j]=true;
            //分别递归匹配
            if (find(board, i + 1, j, word, index + 1,full))
                return true;
            if (find(board, i - 1, j, word, index + 1,full))
                return true;
            if (find(board,i, j-1,word,index+1,full))
                return true;
            if (find(board,i, j+1,word,index+1,full))
                return true;
            //如果上下左右递归没有一个匹配成功，则说明当前位置也是错的所以重新标记为为用过 ***
            full[i][j]=false;
        }
        //如果都不能匹配则返回
        return false;
    }
}


