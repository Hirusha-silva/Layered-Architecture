package lk.ijse.gdse71.mrphone.BO;

import lk.ijse.gdse71.mrphone.BO.custom.impl.CustomerBOimpl;
import lk.ijse.gdse71.mrphone.BO.custom.impl.EmployeeBOimpl;
import lk.ijse.gdse71.mrphone.BO.custom.impl.ItemBOImpl;
import lk.ijse.gdse71.mrphone.BO.custom.impl.ItemDetailBOImpl;
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
        CUSTOMER,ITEM,ITEMDETAIL,EMPLOYEE;
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
            default:
                return null;
        }
    }


}