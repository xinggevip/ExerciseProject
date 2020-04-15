package com.example.testpro;

public class ThreadProTest01 implements Runnable{

    // 设置一个标志位
    private Boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
    }

    // 设置一个公共得方法停止
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadProTest01 t1 = new ThreadProTest01();
        new Thread(t1).start();

        for (int i = 0; i < 1000; i++) {
            Thread.sleep(10);
            if (i == 900) {
                t1.stop();
                System.out.println("停止了");
            }
        }
    }

}
