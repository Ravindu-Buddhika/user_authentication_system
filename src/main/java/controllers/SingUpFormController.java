package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.IMPL.UserServiceIMPL;
import service.UserService;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;

public class SingUpFormController {
    UserService userService=new UserServiceIMPL();
    Stage stage=new Stage();

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtFirstName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtSecondName;

    @FXML
    private TextField txtEmail;

    @FXML
    void clickedSingUp(ActionEvent event) {
        if (txtFirstName.getText().isEmpty() ||
                txtSecondName.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtPassword.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Form Incomplete");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields before proceeding.");
            alert.showAndWait();
        }else {

            String rawPassword = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            boolean status=false;

            if(txtEmail.getText().endsWith("@gmail.com")){
                if (rawPassword.equals(confirmPassword)) {
                    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

                    if (rawPassword.matches(passwordRegex)) {

                        String hashedPassword = hashPassword(rawPassword);

                        UserDTO userDTO = new UserDTO(
                                txtFirstName.getText(),
                                txtSecondName.getText(),
                                txtEmail.getText(),
                                hashedPassword
                        );

                        userService.addUser(userDTO);

                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password must be 8+ chars, include Upper, Lower, Number, and Symbol!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Email address must end with @gmail.com!").show();
            }
            if (status==true){
                stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/logingForm.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @FXML
    void clickedSingIn(ActionEvent event) {
        stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/logingForm.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
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
}
