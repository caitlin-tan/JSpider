package com.gog.spider.worker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gog.spider.Entity;
import com.gog.spider.Worker;
import com.gog.spider.util.Config;

public class QchuiProduct extends Worker {
    public QchuiProduct() {
        super();
    }

    @Override
    public Entity parseDocument(Document doc) {
        com.gog.spider.entity.QchuiProduct product = new com.gog.spider.entity.QchuiProduct();
        for (Element element : doc.select("div .deal_list_box >ul >li >div")) {
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
        }

        return product;
    }

    @Override
    public void configBaseUrl() {
        baseUrl = Config.getStringByKey("system", "qchui_product_url");
    }
}
