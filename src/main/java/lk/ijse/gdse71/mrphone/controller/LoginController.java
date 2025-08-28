package lk.ijse.gdse71.mrphone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse71.mrphone.BO.custom.LoginBO;
import lk.ijse.gdse71.mrphone.BO.custom.impl.LoginBOImpl;
import lk.ijse.gdse71.mrphone.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public AnchorPane rootNode;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink hyperRegister;

    private final LoginBO loginBO = new LoginBOImpl();

    public void btnLogInAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (isValid()){
            checkCredential(username,password);
        }
    }
    private boolean isValid() {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Username or Password cannot be empty").show();
            return false;
        }
        return true;
    }


    public void checkCredential(String userName, String password) {
        try {
            boolean isValid = loginBO.checkCredential(userName, password);
            if (isValid) {
                navigateToCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }


    private void navigateToCustomer() throws IOException{
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard-form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage primaryStage = (Stage) rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void hyperRegisterOnAction(ActionEvent actionEvent) {
    }
}
