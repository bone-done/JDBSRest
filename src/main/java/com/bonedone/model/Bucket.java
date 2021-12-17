package com.bonedone.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bucket {
    private int id;
    private LocalDateTime createdDate;

    public Bucket() {
    }

    public Bucket(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return id == bucket.id &&
                Objects.equals(createdDate, bucket.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                '}';
    }
}
