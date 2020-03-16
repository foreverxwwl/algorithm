package DataStructure.stack;

import org.junit.Test;

/**
 * @outhor li
 * @create 2020-03-16 14:07
 * 正则表达式
 */
public class RegexTest {
    public static void main(String[] args) {
        // 去除单词与 , 和 . 之间的空格
        String Str = "Hello , World .";
        String pattern = "(\\w)(\\s+)([.,])";
        // $0 匹配 `(\w)(\s+)([.,])` 结果为 `o空格,` 和 `d空格.`
        // $1 匹配 `(\w)` 结果为 `o` 和 `d`
        // $2 匹配 `(\s+)` 结果为 `空格` 和 `空格`
        // $3 匹配 `([.,])` 结果为 `,` 和 `.`
        System.out.println(Str.replaceAll(pattern, "$1$3")); // Hello, World.
        String[] strings = Str.split(pattern);
        for (String str: strings){
            System.out.println(str);
        }
    }

    @Test
    public void testSplit(){
        String Str = "segmentfault.com/search?type=article&q=java+正则表达式";
        String pattern = "[/?&]";
        String[] strings = Str.split(pattern);
        for (String str: strings){
            System.out.println(str);
        }
    }
}
