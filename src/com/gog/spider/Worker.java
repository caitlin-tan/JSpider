package com.gog.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gog.spider.util.Config;

public abstract class Worker {
    public String baseUrl = Config.getStringByKey("system", "qchui_base_url");

    public abstract void configBaseUrl();

    public Worker() {
        configBaseUrl();
    }

    public void run() {
        // 入口方法，开始采集数据
        // 1. 循环获取下页链接

        // 2. 获取 documents，提取有用数据
        Document doc = null;
        try {
            doc = Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        Entity entity = parseDocument(doc);

        // 3. 保存数据
        saveEntity(entity);
    }

    public abstract Entity parseDocument(Document doc);

    public void saveEntity(Entity entity) {
        entity.insert();
    }
}
