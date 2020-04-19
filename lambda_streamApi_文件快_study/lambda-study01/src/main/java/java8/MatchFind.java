package java8;

import java8.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author xinggevip
 * @date 2020/4/19 20:29
 */
public class MatchFind {

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

    // 1.传统 判断是否有员工年龄都大于70
    private void test01() {
        boolean ExistAgeThan70 = false;
        for (Employee employee : employees) {
            if (employee.getAge() > 70) {
                ExistAgeThan70 = true;
                break;
            }
        }
        System.out.println(ExistAgeThan70);
    }

    // 2.anyMatch 任意匹配，任意一个符合条件返回true
    private void test02() {
        boolean res = employees.stream()
                .anyMatch(Employee.ageGreaterThan70);  // 使用谓词逻辑
//        boolean res = employees.stream()
//                .anyMatch(e -> e.getAge() > 70);
        System.out.println(res);
    }

    // 3.anyMatch 全部匹配匹配，全部符合条件返回true
    private void test03() {
        boolean res = employees.stream()
                .allMatch(e -> e.getAge() > 70);
        System.out.println(res);
    }

    // 4.noneMatch 全部不匹配，全部都不符合条件呢返回true
    private void test04() {
        boolean res = employees.stream()
                .noneMatch(e -> e.getAge() < 18);
        System.out.println(res);
    }

    // 5.从列表中按照顺序查找第一个年龄大于40的员工
    private void test05() {
        Optional<Employee> first = employees.stream()
                .filter(e -> e.getAge() > 40)
                .findFirst();
        System.out.println(first.get());  // 存在则返回结果，不存在则抛异常
    }

    // 6.找不到返回自定义的默认值
    private void test06() {
        Employee res = employees.stream()
                .filter(e -> e.getAge() > 90)
                .findFirst()
                .orElse(null);  // 设置找不到时的返回结果
        System.out.println(res);  // 存在则返回结果，不存在则返回null
    }

    // 7.找到执行指定代码块
    private void test07() {
        employees.stream()
                .filter(e -> e.getAge() > 40)
                .findFirst()
                .ifPresent(e -> {
                    System.out.println("找到了年龄大于40岁的员工了");
                    System.out.println(e);
                });
    }


    public static void main(String[] args) {
        /**
         * findFirst用于查找第一个符合“匹配规则”的元素，返回值为Optional
         * findAny用于查找任意一个符合“匹配规则”的元素，返回值为Optional
         * 这里用findFirst为例
         */
        MatchFind m1 = new MatchFind();
        // 1.传统 判断是否有员工年龄都大于70
        m1.test01();
        // 2.anyMatch 任意匹配，任意一个符合条件返回true
        m1.test02();
        // 3.allMatch 全部匹配，全部符合条件返回true
        m1.test03();
        // 4.noneMatch 全部不匹配，全部都不符合条件呢返回true
        m1.test04();
        // 5.从列表中按照顺序查找第一个年龄大于40的员工
        m1.test05();
        // 6.找不到返回自定义的默认值
        m1.test06();
        // 7.找到执行代码块
        m1.test07();
    }
}
