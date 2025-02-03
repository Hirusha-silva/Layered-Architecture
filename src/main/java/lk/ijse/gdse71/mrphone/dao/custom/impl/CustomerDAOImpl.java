package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.CustomerDAO;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;
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
    public  boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into customer values(?,?,?,?)",
                    customerDto.getCustomer_id(),
                    customerDto.getName(),
                    customerDto.getPhone_no(),
                    customerDto.getEmail()
        );
        return isSaved;
    }

    public  List<CustomerDto> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = CrudUtil.execute("select * from customer");
        List<CustomerDto> customerDtos = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDto customerDto = new CustomerDto(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
            );
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from customer where customer_id=?", customerId);
    }
    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(
                "Update customer set name=?,phone_no=?,email=? where customer_id=?",
                customerDto.getName(),
                customerDto.getPhone_no(),
                customerDto.getEmail(),
                customerDto.getCustomer_id()


        );
    }
    public CustomerDto findById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from customer where customer_id=?",customerId);

        if (resultSet.next()) {
            return new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
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
