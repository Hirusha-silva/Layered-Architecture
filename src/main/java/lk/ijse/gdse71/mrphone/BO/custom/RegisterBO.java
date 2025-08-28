package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.RegisterDto;
import lk.ijse.gdse71.mrphone.entity.User;

public interface RegisterBO extends SuperBO {
    boolean save(RegisterDto registerDto) throws Exception;
}
