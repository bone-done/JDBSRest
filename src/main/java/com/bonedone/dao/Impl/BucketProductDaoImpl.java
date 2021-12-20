package com.bonedone.dao.Impl;

import com.bonedone.dao.BucketProductDAO;
import com.bonedone.model.BucketProduct;
import com.bonedone.util.Queries;
import com.bonedone.util.SQLConnection;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class BucketProductDaoImpl implements BucketProductDAO {
    @Override
    public void create(BucketProduct bucketProduct) {
        final String SQL = Queries.CREATE_BUCKET_PRODUCT;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, bucketProduct.getBucketId());
            statement.setInt(2, bucketProduct.getProductId());
            statement.setInt(3, bucketProduct.getNumber());
            statement.setInt(4, bucketProduct.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public BucketProduct getById(int id) {
        BucketProduct bucketProduct = new BucketProduct();
        final String SQL = Queries.GET_BUCKET_PRODUCT_BY_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                bucketProduct.setId(resultSet.getInt("id"));
                bucketProduct.setBucketId(resultSet.getInt("bucket_id"));
                bucketProduct.setProductId(resultSet.getInt("product_id"));
                bucketProduct.setNumber(resultSet.getInt("number"));

            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (bucketProduct.getId() == 0 &&
                bucketProduct.getBucketId() == 0 &&
                bucketProduct.getProductId() == 0 &&
                bucketProduct.getNumber() == 0) {
            NullPointerException exception = new NullPointerException("BucketProduct is empty");
            log.error(exception);
            throw exception;
        } else return bucketProduct;
    }

    @Override
    public List<BucketProduct> getAll() {
        List<BucketProduct> bucketProductList = new ArrayList<>();
        final String SQL = Queries.GET_ALL_BUCKETPRODUCTS;
        try (Connection connection = SQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                bucketProductList.add(
                        new BucketProduct(
                                resultSet.getInt("id"),
                                resultSet.getInt("bucket_id"),
                                resultSet.getInt("product_id"),
                                resultSet.getInt("number")
                        )
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return bucketProductList;
    }

    @Override
    public void deleteById(int id) {
        final String SQL = Queries.DELETE_BUCKET_PRODUCT;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            log.info("BucketProduct with ID " + id + "was deleted");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(BucketProduct bucketProduct) {
        final String SQL = Queries.UPDATE_BUCKET_PRODUCT;
        if (isExist(bucketProduct)) {
            try (Connection connection = SQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setInt(1, bucketProduct.getBucketId());
                statement.setInt(2, bucketProduct.getProductId());
                statement.setInt(3, bucketProduct.getNumber());
                statement.setInt(4, bucketProduct.getId());
                statement.execute();
                log.info("BucketProduct with ID " + bucketProduct.getId() + "was updated");
            } catch (SQLException e) {
                log.error(e);
            }
        } else {
            log.warn("BucketProduct with ID " + bucketProduct.getId() + "was not updated");
        }
    }

    private boolean isExist(BucketProduct bucketProduct) {
        final String SQL = Queries.BUCKET_PRODUCT_IS_EXIST;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, bucketProduct.getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    log.warn("BucketProduct with ID " + bucketProduct.getId() + " not found");
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return true;
    }
}
