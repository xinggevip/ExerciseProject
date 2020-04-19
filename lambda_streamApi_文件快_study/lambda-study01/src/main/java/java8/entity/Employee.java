package java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

/**
 * @author xinggevip
 * @date 2020/4/19 12:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;

    // 定义谓词逻辑
    public static Predicate<Employee> ageGreaterThan70 = x -> x.getAge() >70;
    public static Predicate<Employee> genderM = x -> x.getGender().equals("M");
}
