package com.gog.spider;

import com.gog.spider.worker.QchuiProduct;

public class Spider {
    public static void main(String[] args) {
        Worker worker = new QchuiProduct();
        int pageCount = worker.pageLinks.size();

        for (int index = 1; index <= pageCount; index++) {
            worker.setCurrentPage(index);
            Thread thread = new Thread(worker);
            thread.start();
        }
    }
}
