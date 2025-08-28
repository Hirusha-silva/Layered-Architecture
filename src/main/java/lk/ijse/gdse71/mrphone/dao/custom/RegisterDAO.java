package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.entity.User;

public interface RegisterDAO extends CrudDAO<User> {
    public boolean existsById(String userName) throws Exception;
}
