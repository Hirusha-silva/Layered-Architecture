package lk.ijse.gdse71.mrphone.dto;


import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDto {
    private String orderId;
    private String customerId;
    private Date orderDate;

    private ArrayList<OrderDetailDto> orderDetailDtos;
}
