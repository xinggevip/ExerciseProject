package com.mp;

import com.mp.entity.Student;
import com.mp.service.StudentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@MapperScan("com.mp.dao")
public class Studyfrommook02Application {

    @Autowired
    private static StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Studyfrommook02Application.class, args);

        // 作业
        showMenu();

    }

    public static void showMenu() {

        Integer id = 1;

        String name = "";

        String sex = "";

        Integer age = 10;

        System.out.println("----------------------------------");
        System.out.println("｜\t欢迎来到学生信息管理系统！\t｜");
        System.out.println("｜-------------------------------｜");
        System.out.println("｜\t按以下数字+enter键进行操作!\t｜");
        System.out.println("｜\t\t\t\t｜");
        System.out.println("｜\t1.添加学生\t\t\t｜");
        System.out.println("｜\t2.所有学生\t\t\t｜");
        System.out.println("｜\t3.查询学生\t\t\t｜");
        System.out.println("｜\t4.删除学生\t\t\t｜");
        System.out.println("｜\t5.清空学生\t\t\t｜");
        System.out.println("｜\t6.自动演示\t\t\t｜");
        System.out.println("｜\t7.编辑学生\t\t\t｜");
        System.out.println("｜\t8.退出程序\t\t\t｜");
        System.out.println("｜\t\t\t\t｜");
        System.out.println("｜-------------------------------｜");
        Scanner Sc = new Scanner(System.in);
        String str = Sc.nextLine();

        if (str.equals("1")) {
            System.out.println("\n----1.添加学生----\n");

            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    // 存储姓名
                    System.out.print("请输入学生姓名：");
                    name = Sc.nextLine();
                } else if (i == 1) {
                    // 存储性别
                    System.out.print("请输入学生性别：");
                    sex = Sc.nextLine();
                } else if (i == 2) {
                    // 存储年龄
                    System.out.print("请输入学生年龄：");
                    age = Sc.nextInt();
                } else if (i == 3) {
                    // 存储学号
                    System.out.print("请输入学生学号：");
                    id = Sc.nextInt();
                }
            }

            // 添加
            Student student = new Student(id,name,sex,age);
            boolean success = student.insert();
            if (success) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败");
            }
            // 展示所有学生
            List<Student> students = new Student().selectAll();
            students.forEach(System.out::println);
            // 展示菜单
            showMenu();
        } else if (str.equals("2")) {
            // 展示所有学生
            List<Student> students = new Student().selectAll();
            students.forEach(System.out::println);
            // 展示菜单
            showMenu();
        } else if (str.equals("3")) {
            System.out.print("请输入学生学号：");
            id = Sc.nextInt();
            // 根据学号查询
            Student student = new Student().selectById(id);
            if (student == null) {
                System.out.println("查无此学生");
            } else {
                System.out.println(student);
            }
            // 展示菜单
            showMenu();
        } else if (str.equals("4")) {
            System.out.print("请输入学生学号：");
            id = Sc.nextInt();
            // 根据学号删除学生
            boolean success = new Student().deleteById(id);
            if (success) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败");
            }
            List<Student> students = new Student().selectAll();
            students.forEach(System.out::println);
            // 展示菜单
            showMenu();
        } else if (str.equals("5")) {
            System.out.println("TODO 清空");
            // 展示所有菜单
            showMenu();
        } else if (str.equals("6")) {
            System.out.println("自动演示");
            // 展示菜单
            showMenu();
        }else if(str.equals("7")){
            System.out.print("请输入待编辑学生学号：");
            id = Sc.nextInt();
            // 查询并展示
            Student student = new Student().selectById(id);

            // 查到显示信息，查不到提示查无此人
            if (student != null) {

                System.out.println(student);

                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        System.out.print("请输入该学生的新姓名：");
                        name = Sc.nextLine();//修复缓冲区自动输入空格问题
                        name = Sc.nextLine();
                    } else if (i == 1) {
                        System.out.print("请输入该学生的新性别：");
                        sex = Sc.nextLine();
                    } else if (i == 2) {
                        System.out.print("请输入该学生的新年龄：");
                        age = Sc.nextInt();
                    }
                }
                // 更新学生
                Student student1 = new Student(id, name, sex, age);
                boolean success = student1.updateById();
                if (success) {
                    System.out.println("更新成功！");
                } else {
                    System.out.println("更新失败");
                }
                // 展示菜单
                showMenu();
            } else {
                System.out.println("查无此学生");
                // 展示菜单
                showMenu();
            }


        } else if (str.equals("8")) {
            System.exit(0);
        } else {
            System.out.println("无效的指令！！");
            // 展示菜单
        }

    }

}
