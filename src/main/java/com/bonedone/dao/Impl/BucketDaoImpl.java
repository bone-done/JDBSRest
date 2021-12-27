package com.bonedone.dao.Impl;

import com.bonedone.dao.BucketDao;
import com.bonedone.exception.BucketIsNullException;
import com.bonedone.model.Bucket;
import com.bonedone.util.Queries;
import com.bonedone.util.SQLConnection;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class BucketDaoImpl implements BucketDao {
    @Override
    public void create(Bucket bucket) {
        final String SQL = Queries.CREATE_BUCKET;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setTimestamp(1, Timestamp.valueOf(bucket.getCreatedDate()));
            statement.setInt(2, bucket.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @SneakyThrows
    @Override
    public Bucket getById(int id) {
        Bucket bucket = null;
        final String SQL = Queries.GET_BUCKET_BY_ID;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                bucket = new Bucket(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created_date").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (Objects.isNull(bucket)) throw new BucketIsNullException("bucket with ID " + id + " NOT found");
        else return bucket;
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> bucketList = new ArrayList<>();
        final String SQL = Queries.GET_ALL_BUCKETS;
        try (Connection connection = SQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                bucketList.add(
                        new Bucket(
                                resultSet.getInt("id"),
                                resultSet.getTimestamp("created_date").toLocalDateTime()
                        )
                );
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return bucketList;
    }

    @Override
    public void deleteById(int id) {
        final String SQL = Queries.DELETE_BUCKET;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            if (statement.execute()) {
                log.info("Bucket with ID " + id + "was deleted");
            } else log.info("Bucket with ID " + id + "was NOT deleted");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Bucket bucket) {
        final String SQL = Queries.UPDATE_BUCKET;
        if (isExist(bucket)) {
            try (Connection connection = SQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setTimestamp(1, Timestamp.valueOf(bucket.getCreatedDate()));
                statement.setInt(2, bucket.getId());
                statement.execute();
                log.info("Bucket with ID " + bucket.getId() + "was updated");
            } catch (SQLException e) {
                log.error(e);
            }
        } else {
            log.warn("Bucket with ID " + bucket.getId() + "was not updated");
        }
    }

    private boolean isExist(Bucket bucket) {
        final String SQL = Queries.BUCKET_IS_EXIST;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, bucket.getId());
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    log.warn("Bucket with ID " + bucket.getId() + " not found");
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return true;
    }
}
