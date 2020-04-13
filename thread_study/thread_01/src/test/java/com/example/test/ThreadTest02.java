package com.example.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ThreadTest02 extends Thread {
    private String url;
    private String name;

    public ThreadTest02(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args) {
        ThreadTest02 t1 = new ThreadTest02("http://www.qiangssvip.com/usr/uploads/2018/11/740561278.png","1.jpg");
        ThreadTest02 t2 = new ThreadTest02("http://www.qiangssvip.com/usr/uploads/2018/11/1742304928.png","2.jpg");
        ThreadTest02 t3 = new ThreadTest02("http://www.qiangssvip.com/usr/uploads/2018/11/1661449274.png","3.jpg");

        t1.start();
        t2.start();
        t3.start();

    }


}

// 下载器
class WebDownLoader{
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


