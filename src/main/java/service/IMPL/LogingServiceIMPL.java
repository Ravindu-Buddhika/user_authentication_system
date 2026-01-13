package service.IMPL;

import javafx.scene.control.Alert;
import model.dto.LogingInfoDTO;
import model.entity.LogingInfoEntity;
import repository.IMPL.LogingRepositoryIMPL;
import repository.LogingRepository;
import service.LogingService;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Base64;

public class LogingServiceIMPL implements LogingService {
    LogingRepository logingRepository;
    LogingInfoEntity entity =null;

    {
        try {
            logingRepository = new LogingRepositoryIMPL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getUser(LogingInfoDTO logingInfoDTO) {
        validateEmails(logingInfoDTO);
    }

    public void validateEmails(LogingInfoDTO dto) {
        LogingInfoEntity logingInfoEntity=new LogingInfoEntity(
                dto.getEmail(),
                dto.getPassword()
        );
        if (dto.getEmail().endsWith("@gmail.com")){
            int status= checkUser(logingInfoEntity);
            System.out.println(status);
            if (status == 1){
                if (entity.getPassword().equals(logingInfoEntity.getPassword())) {
                    System.out.println("Login Successful!");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Password");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect password! try again");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Email! try again");
                alert.setHeaderText(null);
                alert.setContentText("No user foud in that Email! try again");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email! try again");
            alert.setHeaderText(null);
            alert.setContentText("Email address must be end with @gmail.com");
            alert.showAndWait();
        }
    }

    int checkUser(LogingInfoEntity logingInfoEntity){
        try {
            logingInfoEntity=logingRepository.getUser(logingInfoEntity);

            if(logingInfoEntity==null){
                return 0;
            }else{
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    void CheckPassword(String hashPassword){

    }
}
