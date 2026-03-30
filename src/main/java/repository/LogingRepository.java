package repository;

import model.entity.LogingInfoEntity;

import java.sql.SQLException;

public interface LogingRepository {

    LogingInfoEntity getUser(LogingInfoEntity entity) throws SQLException;
}
