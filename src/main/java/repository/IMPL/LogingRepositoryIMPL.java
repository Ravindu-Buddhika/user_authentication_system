package repository.IMPL;

import database.DBConnection;
import model.entity.LogingInfoEntity;
import repository.LogingRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogingRepositoryIMPL implements LogingRepository {
    Connection connection= DBConnection.getInstance().getConnection();

    public LogingRepositoryIMPL() throws SQLException {
    }

    @Override
    public LogingInfoEntity getUser(LogingInfoEntity entity) throws SQLException {
        LogingInfoEntity logingInfoEntity = null;

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM login_info WHERE email = ?"
        );
        preparedStatement.setString(1, entity.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            logingInfoEntity.setEmail(resultSet.getString("email"));
            logingInfoEntity.setPassword(resultSet.getString("password"));
        }
        return logingInfoEntity;
    }
}
