package com.example.test;

public class ThreadTest05 implements Runnable {

    private String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            // 模拟兔子偷懒,每走10米歇1毫秒
            if (Thread.currentThread().getName().equals("兔子") && (i % 10 == 0)) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 检查是否结束
            if (checkOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "米");
        }
    }

    Boolean checkOver(int i) {
        if (winner != null) {
            return true;
        } else if (i >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println(winner + "赢得了比赛");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ThreadTest05 t1 = new ThreadTest05();
        new Thread(t1,"乌龟").start();
        new Thread(t1,"兔子").start();
    }
}
