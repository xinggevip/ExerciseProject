package java8;

import java8.entity.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author xinggevip
 * @date 2020/4/19 16:05
 */
public class SortList {
    // 1.用List排序
    private void sortTest01() {
        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        System.out.println("排序前：" + cities);
        //[Milan, london, San Francisco, Tokyo, New Delhi]

        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("忽略大小写排序：" + cities);
        //[london, Milan, New Delhi, San Francisco, Tokyo]

        cities.sort(Comparator.naturalOrder());
        System.out.println("自然排序：" + cities);
        //[Milan, New Delhi, San Francisco, Tokyo, london]
    }
    // 2.多字段排序
    private void sortTest02() {
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

        employees.sort(
                Comparator.comparing(Employee::getGender)
                        .thenComparing(Employee::getAge)
                        .reversed()
        );
        employees.forEach(System.out::println);

        //都是正序 ，不加reversed
        //都是倒序，最后面加一个reserved
        //先是倒序（加reserved），然后正序
        //先是正序（加reserved），然后倒序（加reserved）
    }
    // 3.自定义Comparator排序
    private void sortTest03() {
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

//        employees.sort(new Comparator<Employee>() {
//            @Override
//            public int compare(Employee em1, Employee em2) {
//                if(em1.getAge() == em2.getAge()){
//                    return 0;
//                }
//                return em1.getAge() - em2.getAge() > 0 ? -1:1;
//            }
//        });
//        employees.forEach(System.out::println);

        employees.sort((em1,em2) -> {
            if(em1.getAge() == em2.getAge()){
                return 0;
            }
            return em1.getAge() - em2.getAge() > 0 ? -1:1;
        });
        employees.forEach(System.out::println);
    }
    public static void main(String[] args) {
        SortList s1 = new SortList();
//        s1.sortTest01();
//        s1.sortTest02();
        s1.sortTest03();
    }
}
