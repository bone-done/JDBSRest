package com.bonedone.dao.Impl;

import com.bonedone.dao.ProductDao;
import com.bonedone.exception.ProductIsNullException;
import com.bonedone.model.Product;
import com.bonedone.util.Queries;
import com.bonedone.util.SQLConnection;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class ProductDaoImpl implements ProductDao {
    @Override
    public void create(Product product) {
        boolean withId = product.getId() != 0;
        final String SQL = withId ? Queries.CREATE_PRODUCT : Queries.CREATE_PRODUCT_WITHOUT_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            if (withId) statement.setInt(4, product.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @SneakyThrows
    @Override
    public Product getById(int id) {
        Product product = null;
        final String SQL = Queries.GET_PRODUCT_BY_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (Objects.isNull(product)) throw new ProductIsNullException("product with ID " + id + " NOT found");
        else return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        final String SQL = Queries.GET_ALL_PRODUCTS;
        try (Connection connection = SQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                productList.add(
                        new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price")
                        )
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return productList;
    }

    @Override
    public void deleteById(int id) {
        final String SQL = Queries.DELETE_PRODUCT;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            log.info("Product with ID " + id + "was deleted");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Product product) {
        final String SQL = Queries.UPDATE_PRODUCT;
        if (isExist(product)) {
            try (Connection connection = SQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, product.getName());
                statement.setString(2, product.getDescription());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getId());
                statement.execute();
                log.info("Product with ID " + product.getId() + "was updated");
            } catch (SQLException e) {
                log.error(e);
            }
        } else {
            log.warn("Product with ID " + product.getId() + "was not updated");
        }
    }

    private boolean isExist(Product product) {
        final String SQL = Queries.PRODUCT_IS_EXIST;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, product.getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    log.warn("Product with ID " + product.getId() + " not found");
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return true;
    }
}
