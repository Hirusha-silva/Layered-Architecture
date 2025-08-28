package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.RegisterBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.RegisterDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.gdse71.mrphone.dto.RegisterDto;
import lk.ijse.gdse71.mrphone.entity.User;

public class RegisterBOImpl implements RegisterBO {
    private final RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTER);
    @Override
    public boolean save(RegisterDto registerDto) throws Exception {
        if (registerDAO.existsById(registerDto.getUser_id())) {
            // user already exists
            return false;
        }
        return registerDAO.save(new User(
                registerDto.getUser_id(),
                registerDto.getPassword()
        ));
    }


}
