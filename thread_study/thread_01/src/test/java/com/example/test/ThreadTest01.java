package com.example.test;

public class ThreadTest01 extends Thread {

    // run方法线程体
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("做第" + i + "次俯卧撑");
        }
    }

    // main线程，主线程
    public static void main(String[] args) {
        // 创建一个线程对象
        ThreadTest01 threadTest01 = new ThreadTest01();
        // 调用start方法开启新线程
        threadTest01.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("做第" + i + "次扩胸运动");
        }
    }

}
