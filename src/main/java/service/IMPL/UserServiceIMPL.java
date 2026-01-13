package service.IMPL;

import javafx.scene.control.Alert;
import model.dto.LogingInfoDTO;
import service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
    String hashPassword(String password){
        try {
            MessageDigest digest=MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
