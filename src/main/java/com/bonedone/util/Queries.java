package com.bonedone.util;

public class Queries {

    //User

    public static final String CREATE_USER_WITHOUT_ID = "INSERT INTO user (email, password, first_name, last_name, role) VALUES (?,?,?,?,?)";
    public static final String CREATE_USER = "INSERT INTO user (email, password, first_name, last_name, role, id) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET email = ?, password = ?, first_name = ?, last_name = ?, role = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    public static final String USER_IS_EXIST = "SELECT * FROM user WHERE ID = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE ID = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user";


    //Product

    public static final String CREATE_PRODUCT_WITHOUT_ID = "INSERT INTO product (name, description, price) VALUES (?,?,?)";
    public static final String CREATE_PRODUCT = "INSERT INTO product (name, description, price, id) VALUES (?,?,?,?)";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE ID = ?";
    public static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
    public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    public static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
    public static final String PRODUCT_IS_EXIST = "SELECT * FROM product WHERE ID = ?";

    //Bucket

    public static final String CREATE_BUCKET = "INSERT INTO bucket (created_date, id) VALUES (?,?)";
    public static final String GET_BUCKET_BY_ID = "SELECT * FROM bucket WHERE ID = ?";
    public static final String GET_ALL_BUCKETS = "SELECT * FROM bucket";
    public static final String DELETE_BUCKET = "DELETE FROM bucket WHERE id = ?";
    public static final String UPDATE_BUCKET = "UPDATE product SET create_date = ? WHERE id = ?";
    public static final String BUCKET_IS_EXIST = "SELECT * FROM product WHERE ID = ?";

}
