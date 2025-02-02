package lk.ijse.gdse71.mrphone.BO;

import lk.ijse.gdse71.mrphone.BO.custom.impl.CustomerBOimpl;
import lk.ijse.gdse71.mrphone.BO.custom.impl.ItemBOImpl;
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
        CUSTOMER,ITEM
    }
    public SuperBO getBO(BOType type){
        switch (type) {
            case CUSTOMER:
                return new CustomerBOimpl();
                case ITEM:
                    return new ItemBOImpl();
            default:
                return null;
        }
    }


}