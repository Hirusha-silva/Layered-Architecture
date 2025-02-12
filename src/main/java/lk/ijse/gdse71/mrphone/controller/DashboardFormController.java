package lk.ijse.gdse71.mrphone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class DashboardFormController {

    @FXML
    private Button btnOrder;
    @FXML
    private Button btnSupplier;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnRepairing;

    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button btnLogOut;

    @FXML
    void btnRepairOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/repairing.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Orders.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item.fxml"));
        node.getChildren().clear();
        node.getChildren().add(anchorPane);
    }

    @FXML
    void btnLogOutAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try{
                Parent loginView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login-form.fxml")));
                Scene loginScene = new Scene(loginView);

                Stage window = (Stage) node.getScene().getWindow();
                window.setScene(loginScene);
                window.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
