package java8;

import java8.entity.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinggevip
 * @date 2020/4/19 21:14
 */
public class ReduceDemo01 {
    // 1.数字累加
    private void test01() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
//        Integer res = nums.stream()
//                .reduce(0, (subTotal, value) -> subTotal + value);
        Integer res = nums.stream()
                .reduce(0, Integer::sum);
        System.out.println(res);
    }

    // 2.字符串累加
    private void test02() {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        String res = strings.stream()
                .reduce("", String::concat);
        System.out.println(res);
    }

    // 3.求员工平均年龄
    private void test03() {
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

        // 串行
        Integer size = employees.size();
//        Integer sun = employees.stream()
//                .map(Employee::getAge)
//                .sun(0, Integer::sum);
        // 并行
        Integer sun = employees.parallelStream()  // 并行运算，效率更快
                .map(Employee::getAge)
                .reduce(0,Integer::sum,Integer::sum);  // 初始值、累加器、合并器
        Integer res = sun / size;
        System.out.println(res);
        // 不用map  累加年龄
        Integer res2 = employees.parallelStream()
                .reduce(0, (subTotal, employee) -> subTotal + employee.getAge(), Integer::sum);
        System.out.println(res2);
    }

    public static void main(String[] args) {
        ReduceDemo01 r1 = new ReduceDemo01();
        // 1.数字累加
        r1.test01();
        // 2.字符串累加
        r1.test02();
        // 3.求员工平均年龄
        r1.test03();
    }
}
