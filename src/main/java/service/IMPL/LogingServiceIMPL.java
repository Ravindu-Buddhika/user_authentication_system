package service.IMPL;

import controllers.DashbodeFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.dto.LogingInfoDTO;
import model.dto.UserDTO;
import model.entity.LogingInfoEntity;
import repository.IMPL.LogingRepositoryIMPL;
import repository.LogingRepository;
import service.LogingService;

import java.io.IOException;
import java.net.URL;
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
    public void getUser(LogingInfoDTO logingInfoDTO, ActionEvent event) {
        validateEmails(logingInfoDTO,event);
    }

    public void validateEmails(LogingInfoDTO dto,ActionEvent event) {
        LogingInfoEntity searchEntity = new LogingInfoEntity(
                dto.getEmail(),
                dto.getPassword()
        );

        if (dto.getEmail().endsWith("@gmail.com")) {
            try {
                // 2. Get the user from the repository
                LogingInfoEntity dbUser = logingRepository.getUser(searchEntity);

                // 3. FIX: Check if the object is NOT null (this replaces 'status == 1')
                if (dbUser != null) {

                    // 4. FIX: Hash the INPUT from the DTO, not the database result
                    String hashedInput = hashPassword(dto.getPassword());

                    // 5. Compare the hashed input with the hashed password in the DB
                    if (hashedInput.equals(dbUser.getPassword())) {
                        navigateToDashboard(event);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incorrect Password");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect password! try again");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("User Not Found, No user found with that Email!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email !");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email ! Email address must end with @gmail.com");
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

    private void navigateToDashboard(ActionEvent event) {
        try {
            // 1. Create the Loader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashbodeForm.fxml"));
            Parent root = loader.load();

            // 2. Get the Controller of the Dashboard
            DashbodeFormController dashboardController = loader.getController();

            // 3. Pass the data to the Dashboard Controller
            UserDTO dto=
            dashboardController.setUserDetails(dto);

            // 4. Switch the Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
