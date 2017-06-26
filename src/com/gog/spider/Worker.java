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
        // ��ڷ�������ʼ�ɼ�����
        // 1. ѭ����ȡ��ҳ����

        // 2. ��ȡ documents����ȡ��������
        Document doc = null;
        try {
            doc = Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        Entity entity = parseDocument(doc);

        // 3. ��������
        saveEntity(entity);
    }

    public abstract Entity parseDocument(Document doc);

    public void saveEntity(Entity entity) {
        entity.insert();
    }
}
