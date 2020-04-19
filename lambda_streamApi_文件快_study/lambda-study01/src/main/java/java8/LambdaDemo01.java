package java8;

/**
 * @author xinggevip
 * @date 2020/4/19 10:17
 */
public class LambdaDemo01 {


    interface Printer {
        void printer(String value);
    }

    public void printSomething(String value,Printer printer) {
        printer.printer(value);
    }

    public static void main(String[] args) {
        LambdaDemo01 lambdaDemo01 = new LambdaDemo01();
        String slogan = "今天天气不错";

        /**
         * 1.传统写匿名实现类
         */
//        Printer printer = new Printer() {
//            @Override
//            public void printer(String value) {
//                System.out.println(value);
//            }
//        };

        /**
         * 2.lambda写法一
         */
//        Printer printer = (String value) -> {
//            System.out.println(value);
//        };

        /**
         * 3.lambda写法二
         */
//        Printer printer = (value) -> {
//            System.out.println(value);
//        };

        /**
         * 4.lambda写法三，前提只有一个参数才能去掉括号
         */
//        Printer printer = value -> {
//            System.out.println(value);
//        };

        /**
         * 4.lambda写法四，前提只有一行方法体
         */
//        Printer printer = value -> System.out.println(value);

        /**
         * 5.lambda写法五
         */
        lambdaDemo01.printSomething(slogan,value -> System.out.println(value));
//        lambdaDemo01.printSomething(slogan,printer);

    }


}
