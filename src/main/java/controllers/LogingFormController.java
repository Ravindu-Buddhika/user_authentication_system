package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LogingFormController {
    Stage stage=new Stage();

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void clickedCreateAnAccount(MouseEvent event) {
        stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/singUpForm.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clickedSingIn(ActionEvent event) {

    }

}
