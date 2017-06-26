package com.gog.spider;

import com.gog.spider.worker.QchuiProduct;

public class Spider {
    public static void main(String[] args) {
        Worker worker = new QchuiProduct();
        worker.run();
        System.out.println("Successful!");
    }
}
