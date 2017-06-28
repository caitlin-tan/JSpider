package com.gog.spider;

import org.apache.log4j.Logger;

import com.gog.spider.worker.BaseWorker;
import com.gog.spider.worker.QchuiProductWorker;

public class Spider
{
    private static Logger logger = Logger.getLogger(Spider.class);

    public static void main( String[] args )
    {
        logger.info("Spider start working...");
        BaseWorker worker = new QchuiProductWorker();
        int pageCount = worker.pageLinks.size();

        for (int index = 1; index <= pageCount; index++) {
            worker.setCurrentPage(index);
            worker.run();
        }

        logger.info("Spider finished");
    }
}
