package com.gog.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gog.spider.util.Config;

public abstract class Worker implements Runnable {
    protected String baseUrl = Config.getStringByKey("system", "qchui_base_url");
    public ArrayList<String> pageLinks;
    protected int currentPage = 1;

    public abstract void configBaseUrl();

    public Worker() {
        configBaseUrl();
        pageLinks = getPageLinks();
    }

    public void run() {

        Document doc = null;
        try {
            doc = Jsoup.connect(pageLinks.get(currentPage - 1)).get();
        } catch (IOException e) {
            // TODO: use log4js
            e.printStackTrace();
            return;
        }

        ArrayList<Entity> entities = parseDocument(doc);

        batchSaveEntities(entities);
    }

    public abstract ArrayList<String> getPageLinks();

    public abstract ArrayList<Entity> parseDocument(Document doc);

    public void batchSaveEntities(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            entity.add();
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
