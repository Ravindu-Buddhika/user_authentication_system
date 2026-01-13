package service.IMPL;

import model.dto.UserDTO;
import repository.IMPL.UserRepositoryIMPL;
import repository.UserRepository;
import service.UserService;

import java.sql.SQLException;

public class UserServiceIMPL implements UserService {
    UserRepository repository=new UserRepositoryIMPL();

    @Override
    public boolean addUser(UserDTO userDTO){
        boolean status= false;
        try {
            status =repository.saveUser(userDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
