package java8;

import java8.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xinggevip
 * @date 2020/4/19 14:38
 */
public class StreamMap01 {

    List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

    // 1.传统过滤数据
    void before() {
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }
        System.out.println("传统过滤数据:" + alphaUpper); //[MONKEY, LION, GIRAFFE, LEMUR]


    }
    // 2.lambda过滤数据
    void after() {
        // 使用Stream管道流
        List<String> collect = alpha.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //上面使用了方法引用，和下面的lambda表达式语法效果是一样的
        //List<String> collect = alpha.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println("lambda过滤数据：" + collect); //[MONKEY, LION, GIRAFFE, LEMUR]
    }
    // 3.lambda之map数据转换
    void test01() {
        alpha.stream()
                .mapToInt(s -> s.length())
                .forEach(System.out::println);

    }
    // 4.lambda之map处理复杂逻辑
    void test02() {
        Employee e1 = new Employee(1,23,"M","Rick","Beethovan");
        Employee e2 = new Employee(2,13,"F","Martina","Hengis");
        Employee e3 = new Employee(3,43,"M","Ricky","Martin");
        Employee e4 = new Employee(4,26,"M","Jon","Lowman");
        Employee e5 = new Employee(5,19,"F","Cristine","Maria");
        Employee e6 = new Employee(6,15,"M","David","Feezor");
        Employee e7 = new Employee(7,68,"F","Melissa","Roy");
        Employee e8 = new Employee(8,79,"M","Alex","Gussin");
        Employee e9 = new Employee(9,15,"F","Neetu","Singh");
        Employee e10 = new Employee(10,45,"M","Naveen","Jain");


        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

//        List<Employee> maped = employees.stream()
//            .map(e -> {
//                e.setAge(e.getAge() + 1);
//                e.setGender(e.getGender().equals("M")?"male":"female");
//                return e;
//            }).collect(Collectors.toList());

        List<Employee> maped = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                    // peek不用return
                }).collect(Collectors.toList());

        System.out.println(maped);
    }
    // 5.flatMap  管道中还有管道，数组中还有数组则使用flatMap
    void test03() {
        List<String> words = Arrays.asList("hello", "word");
        words.stream()
                .flatMap(w -> Arrays.stream(w.split("")))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        StreamMap01 s1 = new StreamMap01();
        // 1.传统过滤数据
        s1.before();
        // 2.lambda过滤数据
        s1.after();
        // 3.lambda之map数据转换
        s1.test01();
        // 4.lambda之map处理复杂逻辑
        s1.test02();
        // 5.flatMap  管道中还有管道，数组中还有数组则使用flatMap
        s1.test03();

    }
}
