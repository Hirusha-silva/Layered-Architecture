package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.CustomerBO;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.dto.OrdersDto;
import lk.ijse.gdse71.mrphone.dto.tm.CartTm;

import lk.ijse.gdse71.mrphone.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse71.mrphone.model.OrdersModel;

import java.io.IOException;
import java.sql.Date;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private TextField addCartQty;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnaddCart;

    @FXML
    private Button btnplaceOrder;

    @FXML
    private TableColumn<?, ?> clmAction;

    @FXML
    private TableColumn<CartTm, Integer> clmCartQty;

    @FXML
    private TableColumn<CartTm, String> clmItemId;

    @FXML
    private TableColumn<CartTm, String> clmName;

    @FXML
    private TableColumn<CartTm, Double> clmTotal;

    @FXML
    private TableColumn<CartTm, Double> clmUnitPrice;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblODate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblUPrice;

    @FXML
    private AnchorPane orderA;


    @FXML
    private TableView<CartTm> tblCart;

    private final OrdersModel orderModel = new OrdersModel();
    private final ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    private double totalAmount;
    CustomerBO customerBO= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    private final ObservableList<CartTm> cartTms = FXCollections.observableArrayList();

    @FXML
    void addCart(ActionEvent event) {
        String selectedItemId = cmbItemId.getValue();
        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR,"Please select an item !").show();
            return;
        }

        String cartQty = addCartQty.getText();
        String qtyPattern = "^\\d+(\\.\\d{1,2})?$";
        if (!cartQty.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR,"Please enter a valid quantity !").show();
            return;
        }

        String itemName = lblItemName.getText();
        int qty = Integer.parseInt(cartQty);
        int qtyOnHand = Integer.parseInt(lblQty.getText());

        if (qtyOnHand < qty){
            new Alert(Alert.AlertType.ERROR,"Not enough Items !").show();
            return;
        }

        addCartQty.setText("");

        double unitPrice = Double.parseDouble(lblUPrice.getText());
        double total = unitPrice * qty ;
        totalAmount = total;

        for (CartTm cartTm : cartTms) {
            if (cartTm.getItemId().equals(selectedItemId)) {
                int newQty = cartTm.getCartQty() + qty;
                cartTm.setCartQty(newQty);
                cartTm.setTotal(unitPrice * newQty);

                tblCart.refresh();
                return;
            }
        }
        Button button = new Button("Remove");

        CartTm cartTm = new CartTm(
                selectedItemId,
                itemName,
                qty,
                unitPrice,
                total,
                button
        );

        button.setOnAction(actionEvent -> {
            cartTms.remove(cartTm);
            tblCart.refresh();
        });
            cartTms.add(cartTm);
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDto customerDto = (CustomerDto) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);


        if(customerDto != null){
            lblCustomerName.setText(customerDto.getName());
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        ItemDto itemDto = itemDAOImpl.findById(selectedItemId);

        if (itemDto != null) {
            lblItemName.setText(itemDto.getBrand());
            lblQty.setText(String.valueOf(itemDto.getQty()));
            lblUPrice.setText(String.valueOf(itemDto.getPrice()));
        }
    }

    @FXML
    void placeOrder(ActionEvent event) throws Exception {
        if (tblCart.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please add items to cart..!").show();
            return;
        }
        if (cmbCustomerId.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please select a customer !").show();
            return;
        }

        String orderId = lblOrderId.getText();
        Date dateOfOrder = Date.valueOf(lblODate.getText());
        String customerId = cmbCustomerId.getValue();

        ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();

        for (CartTm cartTm : cartTms){
            OrderDetailDto orderDetailDto = new OrderDetailDto(
                    orderId,
                    cartTm.getItemId(),
                    cartTm.getCartQty(),
                    cartTm.getUnitPrice()
            );
            orderDetailDtos.add(orderDetailDto);
        }

        OrdersDto ordersDto = new OrdersDto(
                orderId,
                customerId,
                dateOfOrder,
                orderDetailDtos
        );

        boolean isSaved = orderModel.saveOrder(ordersDto);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Order saved..!").show();
            reFreshPage();
            openPaymentPage(orderId, totalAmount);
        }else{
            new Alert(Alert.AlertType.ERROR, "Order fail..!").show();
        }
    }

    @FXML
    void resetItem(ActionEvent event) throws Exception {
        reFreshPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();

        try {
            reFreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load data !").show();
        }
    }
    private void setCellValues() {
        clmItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clmCartQty.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));

        tblCart.setItems(cartTms);
    }
    private void reFreshPage() throws Exception {
        lblOrderId.setText(orderModel.getNextOderId());
        lblODate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadItemIds();

        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblCustomerName.setText("");
        lblItemName.setText("");
        lblUPrice.setText("");
        lblQty.setText("");
        addCartQty.clear();

        cartTms.clear();
        tblCart.refresh();
    }
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerBO.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIds = itemDAOImpl.getAllItemId();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemId.setItems(observableList);
    }
    private void openPaymentPage(String orderId, double totalAmount) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment.fxml"));
        Parent parent = loader.load();
        PaymentController paymentController = loader.getController();
        paymentController.setOrderId(orderId);
        paymentController.setTotalAmount(String.valueOf(totalAmount));
        paymentController.setOrdersController(this);

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
