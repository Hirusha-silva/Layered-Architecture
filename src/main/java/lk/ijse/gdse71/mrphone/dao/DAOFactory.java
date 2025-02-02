package lk.ijse.gdse71.mrphone.dao;

import lk.ijse.gdse71.mrphone.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse71.mrphone.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse71.mrphone.dao.custom.impl.ItemDetailDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        CUSTOMER,ITEM,ITEMDETAIL
    }
    public SuperDAO getDAO(DAOType type){
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
                case ITEM:
                    return new ItemDAOImpl();
                    case ITEMDETAIL:
                        return (SuperDAO) new ItemDetailDAOImpl();
                default:
                    return null;
        }
    }


}
