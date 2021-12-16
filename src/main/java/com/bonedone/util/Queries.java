package com.bonedone.util;

public class Queries {
    //User
    public static final String CREATE_USER_WITHOUT_ID = "INSERT INTO user (email, password, fullname, lastname, role) VALUES (?,?,?,?,?)";
    public static final String CREATE_USER = "INSERT INTO user (email, password, fullname, lastname, role, id) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE user SET email = ?, password = ?, fullname = ?, lastname = ?, role = ? WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    public static final String USER_IS_EXIST = "SELECT * FROM STUDENT WHERE ID = ?"

}
