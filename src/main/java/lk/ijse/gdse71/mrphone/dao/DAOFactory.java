package lk.ijse.gdse71.mrphone.dao;

import lk.ijse.gdse71.mrphone.dao.custom.SupplierDetailDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        CUSTOMER,ITEM,ITEMDETAIL,EMPLOYEE,REPAIR,SALARY,PAYMENT,SUPPLIER,SUPPLIERDETAIL,ORDER,ORDER_DETAIL;
    }
    public SuperDAO getDAO(DAOType type){
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
                case ITEM:
                    return new ItemDAOImpl();
                    case ITEMDETAIL:
                        return new ItemDetailDAOImpl();
                        case EMPLOYEE:
                            return new EmployeeDAOImpl();
                            case REPAIR:
                                return new RepairDAOImpl();
                                case SALARY:
                                    return new SalaryDAOImpl();
                                    case PAYMENT:
                                        return new PaymentDAOImpl();
                                        case SUPPLIER:
                                            return new SupplierDAOImpl();
                                            case SUPPLIERDETAIL:
                                                return new SupplierDetailDAOImpl();
                                                case ORDER:
                                                    return new OrderDAOImpl();
                                                    case ORDER_DETAIL:
                                                        return new OrderDetailDAOImpl();

                default:
                    return null;
        }
    }


}
