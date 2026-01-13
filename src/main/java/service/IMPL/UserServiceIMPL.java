package service.IMPL;

import javafx.scene.control.Alert;
import model.dto.LogingInfoDTO;
import service.UserService;

public class UserServiceIMPL implements UserService {
    @Override
    public void getUser(LogingInfoDTO logingInfoDTO) {
        validateEmails(logingInfoDTO);
    }

    public void validateEmails(LogingInfoDTO dto) {
        if (dto.getEmail().endsWith("@gmail.com")){

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email! try again");
            alert.setHeaderText(null);
            alert.setContentText("Email address must be end with @gmail.com");
            alert.showAndWait();
        }
    }
}
