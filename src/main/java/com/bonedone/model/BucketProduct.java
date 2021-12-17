package com.bonedone.model;

import java.util.Objects;

public class BucketProduct {
    private int id;
    private int bucketId;
    private int productId;
    private Integer number;

    public BucketProduct() {
    }

    public BucketProduct(int id, int bucketId, int productId, Integer number) {
        this.id = id;
        this.bucketId = bucketId;
        this.productId = productId;
        this.number = number;
    }

    public BucketProduct(int bucketId, int productId, Integer number) {
        this.bucketId = bucketId;
        this.productId = productId;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BucketProduct that = (BucketProduct) o;
        return id == that.id &&
                bucketId == that.bucketId &&
                productId == that.productId &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bucketId, productId, number);
    }

    @Override
    public String toString() {
        return "BucketProduct{" +
                "id=" + id +
                ", bucketId=" + bucketId +
                ", productId=" + productId +
                ", number=" + number +
                '}';
    }
}
