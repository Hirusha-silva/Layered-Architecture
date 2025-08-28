package lk.ijse.gdse71.mrphone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.RegisterBO;
import lk.ijse.gdse71.mrphone.dto.RegisterDto;

import java.io.IOException;

public class RegisterController {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnBack;

    @FXML
    private PasswordField passowrd1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField username;

    RegisterBO registerBO = (RegisterBO) BOFactory.getInstance().getBO(BOFactory.BOType.REGISTER);

    @FXML
    public void registerOnClick(ActionEvent event) {
        String userName = username.getText().trim();
        String password = passowrd1.getText().trim();
        String confirmPassword = password2.getText().trim();

        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        try {
            boolean isSaved = registerBO.save(new RegisterDto(userName, password));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User Registered Successfully!").show();

                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login-form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage primaryStage = (Stage) btnRegister.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
                primaryStage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Username  All ready exist ").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong: " + e.getMessage()).show();
        }
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login-form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage primaryStage = (Stage) btnRegister.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

}