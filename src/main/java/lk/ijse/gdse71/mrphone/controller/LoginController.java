package lk.ijse.gdse71.mrphone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

    public void btnLogInAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (isValid()){
            try {
                cheakCredintial(username,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean isValid() {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Username or Password cannot be empty").show();
            return false;
        }
        return true;
    }


    public void cheakCredintial (String user_name, String password) throws SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT user_id,password FROM User WHERE user_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,user_name);

        ResultSet rst = pstm.executeQuery();
        if (rst.next()){
            String dbpw = rst.getString("password");
            if (password.equals(dbpw)){
                navigateToCustomer();
            }else {
                new Alert(Alert.AlertType.ERROR,"Wrong Password").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"User not found").show();
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
