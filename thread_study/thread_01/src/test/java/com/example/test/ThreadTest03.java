package com.example.test;

public class ThreadTest03 implements Runnable {

    // run方法线程体
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("做第" + i + "次俯卧撑");
        }
    }

    // main线程，主线程
    public static void main(String[] args) {

        // 开启线程对象，通过线程对象开启线程
//        ThreadTest03 threadTest03 = new ThreadTest03();
//        Thread thread = new Thread(threadTest03);
//        thread.start();
        new Thread(new ThreadTest03()).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("做第" + i + "次扩胸运动");
        }
    }
}
