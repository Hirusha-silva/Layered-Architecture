package lk.ijse.gdse71.mrphone.BO;

import lk.ijse.gdse71.mrphone.BO.custom.impl.*;
import lk.ijse.gdse71.mrphone.dao.SuperDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.CustomerDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }
    public static BOFactory getInstance() {
        return boFactory ==null? boFactory =new BOFactory(): boFactory;
    }
    public enum BOType{
        CUSTOMER,ITEM,ITEMDETAIL,EMPLOYEE,REPAIR,SALARY;
    }
    public SuperBO getBO(BOType type){
        switch (type) {
            case CUSTOMER:
                return new CustomerBOimpl();
                case ITEM:
                    return new ItemBOImpl();
                    case ITEMDETAIL:
                        return new ItemDetailBOImpl();
                        case EMPLOYEE:
                            return new EmployeeBOimpl();
                            case REPAIR:
                                return new RepairBOImpl();
                                case SALARY:
                                    return new SalaryBOImpl();
            default:
                return null;
        }
    }


}