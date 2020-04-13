package com.example.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class ThreadTest06 implements Callable<Boolean> {
    private String url;
    private String name;

    public ThreadTest06(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoader2 webDownLoader = new WebDownLoader2();
        webDownLoader.downLoader(url, name);
        System.out.println("下载了文件名为：" + name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadTest06 t1 = new ThreadTest06("http://www.qiangssvip.com/usr/uploads/2018/11/740561278.png","1.jpg");
        ThreadTest06 t2 = new ThreadTest06("http://www.qiangssvip.com/usr/uploads/2018/11/1742304928.png","2.jpg");
        ThreadTest06 t3 = new ThreadTest06("http://www.qiangssvip.com/usr/uploads/2018/11/1661449274.png","3.jpg");

        // 创建执行任务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 获取返回值
        System.out.println(r1.get());
        System.out.println(r2.get());
        System.out.println(r3.get());

        // 关闭服务
        ser.shutdownNow();
    }



}

// 下载器
class WebDownLoader2{
    // 下载方法
    public void downLoader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}

