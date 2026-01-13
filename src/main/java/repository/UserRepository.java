package repository;

import model.dto.UserDTO;

import java.sql.SQLException;

public interface UserRepository {
    boolean saveUser(UserDTO userDTO) throws SQLException;
}
