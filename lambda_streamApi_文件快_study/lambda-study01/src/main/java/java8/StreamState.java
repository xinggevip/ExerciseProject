package java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xinggevip
 * @date 2020/4/19 15:21
 * 本章学习地址：https://www.kancloud.cn/hanxt/javacrazy/1589649
 */
public class StreamState {

    // 1.Limit与Skip管道数据截取
    private void limitAndSkipTest() {
        // 1.截取前两个元素
        List<String> limitN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .limit(2)
                .collect(Collectors.toList());
        // 2.跳过前两个元素
        List<String> skipN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .skip(2)
                .collect(Collectors.toList());

        System.out.println(limitN);
        System.out.println(skipN);
    }

    // 2.Distinct元素去重
    private void distinctTest() {
        List<String> uniqueAnimals = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueAnimals);
    }

    // 3.Sorted排序
    private void sortedTest() {
        List<String> alphabeticOrder = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .sorted()
                .collect(Collectors.toList());
        System.out.println(alphabeticOrder);
    }

    // 4.并行操作,每次运行结果都不一样。通常情况下，parallel()能够很好的利用CPU的多核处理器，达到更好的执行效率和性能，建议使用。
    private void parallelTest() {
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .parallel()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        StreamState s1 = new StreamState();
        // 1.Limit与Skip管道数据截取
        s1.limitAndSkipTest();
        // 2.Distinct元素去重
        s1.distinctTest();
        // 3.Sorted排序
        s1.sortedTest();
        // 4.并行操作
        s1.parallelTest();
    }
}
