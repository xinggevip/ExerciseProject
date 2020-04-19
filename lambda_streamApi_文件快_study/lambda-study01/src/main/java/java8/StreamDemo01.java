package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xinggevip
 * @date 2020/4/19 10:58
 */
public class StreamDemo01 {

    /**
     * 需求：
     *  1.把非A开头的项过滤出来
     *  2.所有项转大写
     *  3.排序
     */

    void before() {
        List<String> list = Arrays.asList("AAA", "ccc", "bbb","ddd");

        /**
         * 1.传统写法
         */
        ArrayList<String> res1List = new ArrayList<>();
        for (String s : list) {
            if (!s.startsWith("A")) {
                s = s.toUpperCase();
                res1List.add(s);
            }
        }

        res1List.sort(new Comparator<String>(){
            //重点是这个函数
            public int compare(String o1, String o2) {
                //忽略掉大小写后,进行字符串的比较
                String s1 = o1.toLowerCase();
                String s2 = o2.toLowerCase();
                return s1.compareTo(s2);//从a-z的排
                //return s2.compareTo(s1);//从z-a的排
            }
        });

        System.out.println("传统写法：" + res1List);  // [BBB, CCC, DDD]
    }

    void after() {
        List<String> list = Arrays.asList("AAA", "ccc", "bbb","ddd");
        List<String> resList = list.stream()
                .filter(s -> !s.startsWith("A"))
                .map(String::toUpperCase)  // 同s -> s.toUpperCase()
                .sorted()  // 可传排序规则
                .collect(Collectors.toList()); // 可以Collectors指定返回数据类型
        System.out.println("Stream + lambda写法：" + resList);
    }

    void test01() {
        String[] strList = {"AAA", "ccc", "bbb","ddd"};

        List<String> resList = Stream.of(strList).filter(s -> !s.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("数组转Stream：" + resList);
    }

    public static void main(String[] args) {
        StreamDemo01 streamDemo01 = new StreamDemo01();

        // 1.经典写法
        streamDemo01.before();
        // 2.Stream写法
        streamDemo01.after();
        // 3.数组类型转Stream
        streamDemo01.test01();


    }
}
