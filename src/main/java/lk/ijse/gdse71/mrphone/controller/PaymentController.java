package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.PaymentDto;
import lk.ijse.gdse71.mrphone.dto.tm.CustomerTm;
import lk.ijse.gdse71.mrphone.dto.tm.PaymentTm;
import lk.ijse.gdse71.mrphone.model.PaymentModel;
import lombok.Setter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button btnPay;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TableColumn<?, ?> clmAmount;

    @FXML
    private TableColumn<?, ?> clmMethod;

    @FXML
    private TableColumn<?, ?> clmOrderId;

    @FXML
    private TableColumn<?, ?> clmPaymentId;

    @FXML
    private ComboBox<String> cmbMethod;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Button btnInvoice;

    @Setter
    private OrdersController ordersController;


    @FXML
    void PaymentOnAction(MouseEvent event) {

    }

    @FXML
    void invoiceOnAction(ActionEvent event) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();

        if (paymentTm == null) {
            return;
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/InvoiceReport.jrxml"));

            Connection connection = DBConnection.getInstance().getConnection();

//            Map<String, Object> parameters = new HashMap<>();
            Map<String, Object> parameter = new HashMap<>();

            parameter.put("P_Date", LocalDate.now().toString());
            parameter.put("P_Pay_id", paymentTm.getPay_id());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, connection);

            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();

        }catch (SQLException  | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "DB erro").show();
        }


    }

    @FXML
    void PayOnAction(ActionEvent event) throws Exception {
        String paymentId = lblPaymentId.getText();
        String orderId = lblOrderId.getText();
        String amount = lblAmount.getText();
        String paymentMethod = cmbMethod.getValue();


        cmbMethod.setStyle("-fx-border-color: #2e86de;");

        boolean isValid = true;

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            cmbMethod.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Please select a payment method.").show();
            isValid = false;
        }

        if (isValid) {
            PaymentDto paymentDto = new PaymentDto(paymentId, orderId,  amount,paymentMethod);

            boolean isSaved = paymentModel.savePayment(paymentDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Payment...!").show();
            }
        }
    }

//    @FXML
//    void cmbMethodOnAction(ActionEvent event) {
//
//    }
    public void setOrderId(String orderId) {
        lblOrderId.setText(orderId);
    }

    public void setTotalAmount(String totalAmount) {
        lblAmount.setText(totalAmount);
    }
    public void initialize(URL url, ResourceBundle rb) {
        clmPaymentId.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        clmOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmMethod.setCellValueFactory(new PropertyValueFactory<>("method"));

        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Bank Card", "Bank Transfer");
        cmbMethod.setItems(paymentMethods);

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }
    PaymentModel paymentModel = new PaymentModel();

    private void loadNextPaymentId() throws Exception {
        String nextPaymentId = paymentModel.getNextPaymentId();
        lblPaymentId.setText(nextPaymentId);
    }

    private void refreshPage() throws Exception {
        loadTableData();
        loadNextPaymentId();
    }
    private void loadTableData() throws Exception {
        ArrayList<PaymentDto> paymentDtos = paymentModel.getAllPayments();

        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(paymentDto.getPay_id(), paymentDto.getOrder_id(), paymentDto.getAmount(), paymentDto.getMethod());
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    public void cmbMethodOnAction(ActionEvent actionEvent) {
    }
}

