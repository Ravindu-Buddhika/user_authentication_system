package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.dto.LogingInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class DashbodeFormController implements Initializable {

    @FXML
    private Label lblName;


    public void setUserDetails(LogingInfoDTO dto) {
        // Now you have access to the user data in the new window!
        lblName.setText("Welcome, " + dto.getEmail());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

