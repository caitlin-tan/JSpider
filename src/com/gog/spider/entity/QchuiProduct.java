package com.gog.spider.entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gog.spider.Entity;

public class QchuiProduct extends Entity {
    private String tableName = "product";
    private String href;
    private double currentPrice;
    private double originalPrice = 0.0;
    private String description;
    private int saleCount;
    private int saleReviewCount;

    public void setHref(String href) {
        this.href = href;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice  = originalPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public void setSaleReviewCount(int saleReviewCount) {
        this.saleReviewCount = saleReviewCount;
    }

    public int insert() {
        int id = 0;
        String sql =
                "insert into " + tableName
                + " (entityId, name, href, currentPrice, originalPrice, description, saleCount, saleReviewCount) "
                + "values (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setInt(1, this.entityId);
            pstmt.setString(2, this.name);
            pstmt.setString(3, this.href);
            pstmt.setDouble(4, this.currentPrice);
            pstmt.setDouble(5, this.originalPrice);
            pstmt.setString(6, this.description);
            pstmt.setInt(7, this.saleCount);
            pstmt.setInt(8, this.saleReviewCount);
            id = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
