package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.CustomerDAO;
import lk.ijse.gdse71.mrphone.entity.Customer;
import lk.ijse.gdse71.mrphone.entity.Item;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {

    public String getNextId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "select customer_id from customer order by customer_id desc limit 1";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSet resultSet = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;

            return String.format("C%03d",newIdIndex);
        }
        return "C001";
    }
    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into customer values(?,?,?,?)",
                    entity.getCustomer_id(),
                    entity.getName(),
                    entity.getPhone_no(),
                    entity.getEmail()
        );
        return isSaved;
    }

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = CrudUtil.execute("select * from customer");
        ArrayList<Customer> customerDtos = new ArrayList<>();
        while (resultSet.next()) {
            customerDtos.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
 /*           CustomerDto customerDto = new CustomerDto(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
            );
            customerDtos.add(customerDto);*/
        }
        return customerDtos;
    }
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from customer where customer_id=?", customerId);
    }
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(
                "Update customer set name=?,phone_no=?,email=? where customer_id=?",
                entity.getName(),
                entity.getPhone_no(),
                entity.getEmail(),
                entity.getCustomer_id()


        );
    }
    public String findById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select name from customer where customer_id=?",customerId);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select customer_id from customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (resultSet.next()) {
            customerIds.add(resultSet.getString(1));

        }
        return customerIds;
    }
}
