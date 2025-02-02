package lk.ijse.gdse71.mrphone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private String orderId;
    private String itemId;
    private int qty;
    private double price;
}
