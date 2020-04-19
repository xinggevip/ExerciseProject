package java8;

import java8.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xinggevip
 * @date 2020/4/19 12:08
 * 本章学习地址：https://www.kancloud.cn/hanxt/javacrazy/1575714
 */
public class StreamFilterPredicate {

    /**
     * 需求：
     *  找出年龄大于70且性别为女性的
     */

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


    // 1.lambda
    void lambdaTest() {
        List<Employee> resList01 = employees.stream()
                .filter(e -> e.getAge() > 70 && e.getGender().equals("M"))
                .collect(Collectors.toList());
        System.out.println("lambda + stream：" + resList01);
    }
    // 2.谓词逻辑
    void predicateTest() {
        List<Employee> resList = employees.stream()
                .filter(Employee.ageGreaterThan70.and(Employee.genderM))
                .collect(Collectors.toList());
        System.out.println("谓词逻辑：" + resList);
    }
    public static void main(String[] args) {
        StreamFilterPredicate s1 = new StreamFilterPredicate();
        // 1.lambda
        s1.lambdaTest();
        // 2.谓词逻辑
        s1.predicateTest();
    }
}
