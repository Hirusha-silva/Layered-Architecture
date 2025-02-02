package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.CustomerBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.CustomerDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;
import lk.ijse.gdse71.mrphone.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOimpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomerIds();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

    @Override
    public List<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<CustomerDto> customers = customerDAO.getAll();
//        while (customers.add(new CustomerDto())) {
//            CustomerDto customerDto = new CustomerDto(
//                    customers.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//            );
//            customerDtos.add(customerDto);
//        }
        for (CustomerDto customer : customers) {
           customerDtos.add(new CustomerDto(
                   customer.getCustomer_id(),
                   customer.getName(),
                   customer.getPhone_no(),
                   customer.getEmail()));
        }
        return customerDtos;
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new CustomerDto(
                customerDto.getCustomer_id(),
                customerDto.getName(),
                customerDto.getPhone_no(),
                customerDto.getEmail()
        ));
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new CustomerDto(
                customerDto.getCustomer_id(),
                customerDto.getName(),
                customerDto.getPhone_no(),
                customerDto.getEmail()
                ));
    }

}
