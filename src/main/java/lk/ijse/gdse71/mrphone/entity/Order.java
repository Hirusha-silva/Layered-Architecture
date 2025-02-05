package lk.ijse.gdse71.mrphone.entity;


import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order{
    private String orderId;
    private String customerId;
    private Date orderDate;
}
