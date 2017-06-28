package com.gog.spider.model;

public class QchuiProduct extends BaseModel {
    private int entityId;
    private String name;
    private String href;
    private double currentPrice;
    private double originalPrice = 0.0;
    private String description;
    private int saleCount;
    private int saleReviewCount;

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return this.href;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice  = originalPrice;
    }

    public double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getSaleCount() {
        return this.saleCount;
    }

    public void setSaleReviewCount(int saleReviewCount) {
        this.saleReviewCount = saleReviewCount;
    }

    public int getSaleReviewCount() {
        return this.saleReviewCount;
    }

    public String toString() {
        return String.valueOf(this.entityId);
    }
}
