package lk.ijse.gdse71.mrphone.model;

import lk.ijse.gdse71.mrphone.dto.EmployeeDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute("insert into employee values(?,?,?,?)",
                employeeDto.getEmployee_id(),
                employeeDto.getName(),
                employeeDto.getPhone_no(),
                employeeDto.getAddress()
                );
        return isSaved;
    }
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");
        if (resultSet.next()) {
            String lastEmployeeId = resultSet.getString(1);
            String substring = lastEmployeeId.substring(2);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("E%03d",newIndex);
        }
        return "E001";
    }
    public static List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from employee");
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeDto employeeDto = new EmployeeDto(
                    resultSet.getNString(1),
                    resultSet.getNString(2),
                    resultSet.getNString(3),
                    resultSet.getNString(4)
            );
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from employee where employee_id=?", employeeId);
    }

    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update employee set name=? , phone_no=? , address=? where employee_id=?",
                employeeDto.getName(),
                employeeDto.getPhone_no(),
                employeeDto.getAddress(),
                employeeDto.getEmployee_id()
                );
    }
}
