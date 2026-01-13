package repository.IMPL;

import database.DBConnection;
import model.dto.UserDTO;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryIMPL implements UserRepository {

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO user (first_name, second_name, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        preparedStatement.setString(1, userDTO.getFirstName());
        preparedStatement.setString(2, userDTO.getSecondName());
        preparedStatement.setString(3, userDTO.getEmail());
        preparedStatement.setString(4, userDTO.getPassword());

        int result = preparedStatement.executeUpdate();

        return result > 0;
    }
}
