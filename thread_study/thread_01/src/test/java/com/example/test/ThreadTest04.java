package com.example.test;

// 多个线程操作同一个对象
// 买火车票例子

// TODO 发现问题，多线程操作同一个资源的情况下，线程不安全，数据错乱
public class ThreadTest04 implements Runnable{
    // 定义票数
    private Integer ticketNum = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum + "张票");
            ticketNum--;
        }
    }

    public static void main(String[] args) {
        ThreadTest04 t1 = new ThreadTest04();
        new Thread(t1,"张三").start();
        new Thread(t1,"李四").start();
        new Thread(t1,"黄牛").start();
        /**
         * 输出结果：
         * 张三拿到了第10张票
         * 黄牛拿到了第10张票
         * 李四拿到了第10张票
         * 张三拿到了第7张票
         * 黄牛拿到了第7张票
         * 李四拿到了第7张票
         * 李四拿到了第4张票
         * 黄牛拿到了第4张票
         * 张三拿到了第4张票
         * 李四拿到了第1张票
         * 张三拿到了第1张票
         * 黄牛拿到了第1张票
         */
    }
}
