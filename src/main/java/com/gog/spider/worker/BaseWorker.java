package com.gog.spider.worker;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gog.spider.Spider;
import com.gog.spider.dao.BaseDao;
import com.gog.spider.dao.imp.BaseDaoImp;
import com.gog.spider.model.BaseModel;
import com.gog.spider.util.Config;

public abstract class BaseWorker implements Runnable {
    protected static Logger logger = Logger.getLogger(BaseWorker.class);

    protected String batchInsertStatement;
    protected String baseUrl = Config.getStringByKey("system", "qchui_base_url");

    public ArrayList<String> pageLinks;
    protected int currentPage = 1;

    public abstract void config();

    public BaseWorker() {
        config();
        pageLinks = getPageLinks();
    }

    public void run() {
        Document doc = null;

        try {
            doc = Jsoup.connect(pageLinks.get(currentPage - 1)).get();
        } catch (IOException e) {
            logger.error(e);
            return;
        }

        ArrayList<BaseModel> entities = parseDocument(doc);

        saveEntities(entities);
    }

    public abstract ArrayList<String> getPageLinks();

    public abstract ArrayList<BaseModel> parseDocument(Document doc);

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void saveEntities(ArrayList<BaseModel> entities) {
        BaseDao baseDao = new BaseDaoImp();
        baseDao.batchInsert(batchInsertStatement, entities);
    }
}
