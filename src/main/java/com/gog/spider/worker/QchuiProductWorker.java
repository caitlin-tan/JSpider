package com.gog.spider.worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gog.spider.model.BaseModel;
import com.gog.spider.util.Config;

public class QchuiProductWorker extends BaseWorker {

    public QchuiProductWorker() {
        super();
    }

    @Override
    public void config() {
        baseUrl = Config.getStringByKey("system", "qchui_product_url");
        batchInsertStatement = "com.gog.spider.mapper.qchuiProduct.batchInsert";
    }

    @Override
    public ArrayList<String> getPageLinks() {
        Document doc = null;
        try {
            doc = Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            logger.error(e);
            return null;
        }

        ArrayList<String> pageLinks = new ArrayList<String>();
        int pageCount = 1;

        Element lastPageElement = null;
        for (Element element : doc.select("div .deal_list_box .pages >a")) {
            if (!element.text().equals("最后一页")) {
                continue;
            }

            lastPageElement = element;
        }

        String lastPageLink = lastPageElement.attr("abs:href");
        Pattern pattern = Pattern.compile("\\?p=(\\d+)?$");
        Matcher matcher = pattern.matcher(lastPageLink);
        if (matcher.find()) {
            pageCount = Integer.parseInt(matcher.group(1));
        }

        for (int index = 1; index <= pageCount; index++) {
            pageLinks.add(baseUrl + "?p=" + index);
        }
        return pageLinks;
    }

    @Override
    public ArrayList<BaseModel> parseDocument(Document doc) {
        ArrayList<BaseModel> products = new ArrayList<BaseModel>();
        for (Element element : doc.select("div .deal_list_box >ul >li >div")) {
            com.gog.spider.model.QchuiProduct product = new com.gog.spider.model.QchuiProduct();
            // Get product href
            String href = element.select(".deal_img >a").attr("abs:href");
            product.setHref(href);

            // Get product id
            Pattern pattern = Pattern.compile("/(\\d+)?$");
            Matcher matcher = pattern.matcher(href);
            if (matcher.find()) {
                int entityId = Integer.parseInt(matcher.group(1));
                product.setEntityId(entityId);
            }

            // Get product name
            String name = element.select(".deal_name >a").text();
            product.setName(name);

            // Get product description
            String description = element.select(".deal_brief >a").text();
            product.setDescription(description.trim());

            // Get product current price
            String currentPrice = element.select(".deal_price .current_price").text();
            pattern = Pattern.compile("¥(\\d+(.\\d+){0,1})?$");
            matcher = pattern.matcher(currentPrice);
            if (matcher.find()) {
                product.setCurrentPrice(Double.parseDouble(matcher.group(1)));
            }

            // Get product original price
            String originalPrice = element.select(".deal_price .origin_price").text();
            pattern = Pattern.compile("\\w*¥(\\d+(.\\d+){0,1})?$");
            matcher = pattern.matcher(originalPrice);
            if (matcher.find()) {
                product.setOriginalPrice(Double.parseDouble(matcher.group(1)));
            }

            // Get product sale count
            String saleCount = element.select(".deal_extra .sale_count >span").text();
            product.setSaleCount(Integer.parseInt(saleCount));

            // Get product sale count
            String saleReviewCount = element.select(".deal_extra .review_count >i").text();
            product.setSaleReviewCount(Integer.parseInt(saleReviewCount));

            products.add(product);
        }

        return products;
    }
}
